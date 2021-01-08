package com.codingbydumbbell.firebasebingo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BingoActivity extends AppCompatActivity {

    private static final int NUMBER_COUNT = 25;
    private static final String TAG = BingoActivity.class.getSimpleName();
    private String roomId;
    private RecyclerView recyclerView;
    private TextView info;
    private boolean creator;
    private List<Integer> randomNumbers;
    private List<NumberButton> buttons;
    private Map<Integer, NumberButton> nuumberMap = new HashMap<>();
    private FirebaseRecyclerAdapter<Boolean, NumberHolder> adapter;

    boolean myTurn = false;

    ValueEventListener statusListerer = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            long status = (long) dataSnapshot.getValue();
            switch ((int) status) {
                case Room.STATUS_CREATED:
                    info.setText("等待對手加入");
                    break;
                case Room.STATUS_JOINED:
                    setMyTurn(isCreator() ? true : false);
                    info.setText("YA! 對手加入");
                    FirebaseDatabase.getInstance().getReference("rooms")
                            .child(roomId)
                            .child("status")
                            .setValue(Room.STATUS_CREATORS_TURN);
                    break;
                case Room.STATUS_CREATORS_TURN:
                    setMyTurn(isCreator() ? true : false);
                    break;
                case Room.STATUS_JOINERS_TURN:
                    setMyTurn(!isCreator() ? true : false);
                    break;
                case Room.STATUS_CREATORS_BINGO:
                    if (!isCreator()) {
                        new AlertDialog.Builder(BingoActivity.this)
                                .setTitle("賓果")
                                .setMessage("對方賓果了")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        endGame();
                                    }
                                }).show();
                    }
                    break;
                case Room.STATUS_JOINERS_BINGO:
                    if (isCreator()) {
                        new AlertDialog.Builder(BingoActivity.this)
                                .setTitle("賓果")
                                .setMessage("對方賓果了")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        endGame();
                                    }
                                }).show();
                    }
                    break;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private void endGame() {
        if (isCreator()) {
            FirebaseDatabase.getInstance().getReference("rooms")
                    .child(roomId)
                    .child("status")
                    .removeEventListener(statusListerer); // 移除傾聽
            FirebaseDatabase.getInstance().getReference("rooms")
                    .child(roomId)
                    .removeValue(); // 移除資料
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo);

        findViews();
        roomId = getIntent().getStringExtra("ROOM_ID");
        creator = getIntent().getBooleanExtra("IS_CREATOR", false);
        generateRandomNumbers();

        // 如果是開局者
        if (isCreator()) {
            // fill firebase room numbers
            for (int i = 0; i < NUMBER_COUNT; i++) {
                FirebaseDatabase.getInstance().getReference("rooms")
                        .child(roomId)
                        .child("numbers")
                        .child((i + 1) + "")
                        .setValue(false);
            }
            FirebaseDatabase.getInstance().getReference("rooms")
                    .child(roomId)
                    .child("status")
                    .setValue(Room.STATUS_CREATED);
        } else {
            // for joiner
            FirebaseDatabase.getInstance().getReference("rooms")
                    .child(roomId)
                    .child("status")
                    .setValue(Room.STATUS_JOINED);
        }

        // RecyclerView
        Query query = FirebaseDatabase.getInstance().getReference("rooms")
                .child(roomId)
                .child("numbers")
                .orderByKey();
        FirebaseRecyclerOptions<Boolean> options = new FirebaseRecyclerOptions.Builder<Boolean>()
                .setQuery(query, Boolean.class).build();
        adapter = new FirebaseRecyclerAdapter<Boolean, NumberHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NumberHolder holder, final int position, @NonNull Boolean model) {
                holder.button.setText(buttons.get(position).getNumber() + "");
                holder.button.setEnabled(!buttons.get(position).isPick());
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isMyTurn()) {
                            int number = buttons.get(position).getNumber();
                            FirebaseDatabase.getInstance().getReference("rooms")
                                    .child(roomId)
                                    .child("numbers")
                                    .child(number + "")
                                    .setValue(true);
                        }
                    }
                });
            }

            // 當 child 改變時
            @Override
            public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
                super.onChildChanged(type, snapshot, newIndex, oldIndex);

                // 可以透過 key 來取得 child key
                Log.d(TAG, "onChildChanged: " + type.name() + "/" + snapshot.getKey());

                // 當狀態改變時
                if (type == ChangeEventType.CHANGED) {

                    NumberButton numberButton = nuumberMap.get(Integer.parseInt(snapshot.getKey()));

                    // 透過 key 取得 position
                    int pos = numberButton.getPosition();

                    // 透過 position 取得 viewHolder
                    NumberHolder holder = (NumberHolder) recyclerView.findViewHolderForAdapterPosition(pos);
                    holder.button.setEnabled(false);
                    numberButton.setPick(true);

                    if (isMyTurn()) {
                        FirebaseDatabase.getInstance().getReference("rooms")
                                .child(roomId)
                                .child("status")
                                .setValue(isCreator() ? Room.STATUS_JOINERS_TURN : Room.STATUS_CREATORS_TURN);

                        // 進行賓果確認，可以用 index 進行判斷
                        int[] nums = new int[NUMBER_COUNT];
                        for (int i = 0; i < NUMBER_COUNT; i++) {
                            nums[i] = buttons.get(i).isPick() ? 1 : 0;
                        }
                        int bingo = 0;
                        int sum = 0;
                        for (int i = 0; i < 5; i++) {
                            sum = 0;
                            for (int j = 0; j < 5; j++) {
                                sum += nums[i * 5 + i];
                            }
                            bingo += (sum == 5) ? 1 : 0;
                            sum = 0;
                            for (int j = 0; j < 5; j++) {
                                sum += nums[j * 5 + j];
                            }
                            bingo += (sum == 5) ? 1 : 0;
                        }
                        sum = 0;
                        for (int i = 0; i < 5; i++) {
                            sum += nums[i * (5 + 1)];
                        }
                        bingo += (sum == 5) ? 1 : 0;
                        sum = 0;
                        for (int i = 0; i < 5; i++) {
                            sum += nums[(i + 1) * (5 - 1)];
                        }
                        bingo += (sum == 5) ? 1 : 0;
                        Log.d(TAG, "onChildChanged: bingo: " + bingo);
                        if (bingo > 0) {
                            FirebaseDatabase.getInstance().getReference("rooms")
                                    .child(roomId)
                                    .child("status")
                                    .setValue(isCreator() ? Room.STATUS_CREATORS_BINGO : Room.STATUS_JOINERS_BINGO);
                            new AlertDialog.Builder(BingoActivity.this)
                                    .setTitle("賓果")
                                    .setMessage("恭喜! 你賓果了")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            endGame();
                                        }
                                    }).show();
                        }
                    }
                }
            }

            @NonNull
            @Override
            public NumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(BingoActivity.this).inflate(R.layout.single_number, parent, false);
                return new NumberHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        FirebaseDatabase.getInstance().getReference("rooms")
                .child(roomId)
                .child("status")
                .addValueEventListener(statusListerer); // 加入傾聽
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    class NumberHolder extends RecyclerView.ViewHolder {
        NumberButton button;

        public NumberHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.number);
        }
    }

    private void generateRandomNumbers() {
        // 產生 25 個隨機排序的集合
        randomNumbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT; i++)
            randomNumbers.add(i + 1);
        Collections.shuffle(randomNumbers);

        buttons = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            NumberButton button = new NumberButton(this);
            button.setText(randomNumbers.get(i) + "");
            button.setNumber(randomNumbers.get(i));
            button.setPosition(i);
            buttons.add(button);
            nuumberMap.put(button.getNumber(), button);
        }
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        info = findViewById(R.id.info);
    }

    public boolean isCreator() {
        return creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
        info.setText(myTurn ? "請選號" : "等待對手選號");
    }
}
