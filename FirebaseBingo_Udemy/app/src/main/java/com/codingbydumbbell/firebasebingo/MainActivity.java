package com.codingbydumbbell.firebasebingo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth auth;
    private TextView nickText;
    private ImageView avatar;
    private Group groupAvatars;
    int[] avatars = {
            R.drawable.avatar_0,
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
    };
    private Member member;
    private FirebaseRecyclerAdapter<Room, RoomHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText roomEdit = new EditText(MainActivity.this);
                roomEdit.setText("Welcome");
                roomEdit.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                roomEdit.setSingleLine();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Room name")
                        .setMessage("Please enter your room name")
                        .setView(roomEdit)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String roomName = roomEdit.getText().toString();
                                Room room = new Room(roomName, member);
                                DatabaseReference rooms = FirebaseDatabase.getInstance().getReference("rooms");

                                // 用 push 來推資料，Firebase 會透過演算法直接加時間
                                DatabaseReference roomRef = rooms.push();
                                roomRef.setValue(room);

                                // 取得 room key
                                String key = roomRef.getKey();
                                Log.d(TAG, "onClick: " + key);
                                roomRef.child("id").setValue(key);

                                // 直接進入 room
                                Intent bingo = new Intent(MainActivity.this, BingoActivity.class);
                                bingo.putExtra("ROOM_ID", key);
                                bingo.putExtra("IS_CREATOR", true);
                                startActivity(bingo);
                            }
                        })
                        .show();
            }
        });

        findViews();
        // Firebase auth：要傾聽登入狀態需要實作 FirebaseAuth.AuthStateListener
        auth = FirebaseAuth.getInstance();
    }

    private void findViews() {

        Log.d(TAG, "findViews: ");
        nickText = findViewById(R.id.nickname);
        avatar = findViewById(R.id.avatar);
        groupAvatars = findViewById(R.id.group_avatars);
        groupAvatars.setVisibility(View.GONE);

        // 監聽 avatar 是否被點擊
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 判斷 groupAvatars 是否正在顯示
                boolean visible = groupAvatars.getVisibility() == View.GONE ? false : true;
                groupAvatars.setVisibility(visible ? View.GONE : View.VISIBLE);
            }
        });

        // 為了程式碼簡潔，讓 Activity 實作 View.OnClickListener
        findViewById(R.id.avatar0).setOnClickListener(this);
        findViewById(R.id.avatar1).setOnClickListener(this);
        findViewById(R.id.avatar2).setOnClickListener(this);
        findViewById(R.id.avatar3).setOnClickListener(this);
        findViewById(R.id.avatar4).setOnClickListener(this);
        findViewById(R.id.avatar5).setOnClickListener(this);
        findViewById(R.id.avatar6).setOnClickListener(this);

        // RecycleView
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // FirebaseRecyclerOptions 需要 Query
        Query query = FirebaseDatabase.getInstance().getReference("rooms").limitToLast(30);

        // FirebaseRecyclerAdapter 需要 FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Room> options = new FirebaseRecyclerOptions.Builder<Room>()
                .setQuery(query, Room.class)
                .build();

        // 當空的時候，產生一個 view 給他
        adapter = new FirebaseRecyclerAdapter<Room, RoomHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RoomHolder holder, int position, @NonNull final Room room) {
                Log.d(TAG, "onBindViewHolder: ");

                holder.image.setImageResource(avatars[room.init.avatarId]);
                holder.text.setText(room.getTitle());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick: " + room.getId());
                        Intent bingo = new Intent(MainActivity.this,BingoActivity.class);
                        bingo.putExtra("ROOM_ID",room.getId());
                        bingo.putExtra("IS_CREATOR",false);
                        startActivity(bingo);
                    }
                });
            }

            @NonNull
            @Override
            public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                Log.d(TAG, "onCreateViewHolder: ");
                // 當空的時候，產生一個 view 給他
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.room_row, parent, false);
                return new RoomHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }


    // 若使用 FirebaseUI Database，只需要設計 ViewHolder
    public class RoomHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public RoomHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.room_image);
            text = itemView.findViewById(R.id.room_text);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 加入傾聽，在 onStart() 可以避免無時無刻都在傾聽
        auth.addAuthStateListener(this);

        // FirebaseAdapter 需要設定開始傾聽
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // 移除傾聽
        auth.removeAuthStateListener(this);
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.action_signout: // 登出
                auth.signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    // 實作 FirebaseAuth.AuthStateListener 的必要方法
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        Log.d(TAG, "onAuthStateChanged: ");

        // 得到一個 firebase user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {

            // 可以透過 FirebaseUser 取得登入使用者的資訊
            final String displayName = user.getDisplayName();
            String uid = user.getUid();

            FirebaseDatabase.getInstance()
                    .getReference("users") // 第一層為 Reference
                    .child(uid)
                    .child("diplayName").setValue(displayName);

            FirebaseDatabase.getInstance()
                    .getReference("users") // 第一層為 Reference
                    .child(uid)
                    .child("uid").setValue(uid);

            FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(uid)
                    // 傾聽全部
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            member = dataSnapshot.getValue(Member.class);
                            if (member.getNickname() == null)
                                showNicknameDialog(displayName);
                            else nickText.setText(member.getNickname());

                            // 設定圖片
                            avatar.setImageResource(avatars[member.getAvatarId()]);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

            // get nickname，因為 firebase 的資料是來至 internet，因此不會立刻得到
//            FirebaseDatabase.getInstance()
//                    .getReference("users")
//                    .child(uid)
//                    .child("nickname")
//                    // 取得 Firebase 的資料，只取這次
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.getValue() != null) {
//                                String nickname = (String) dataSnapshot.getValue();
//                            } else showNicknameDialog(displayName);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
        } else {
            // 代表使用者尚未登入
            startActivityForResult(

                    // 透過 AuthUI 建立 intent
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .setIsSmartLockEnabled(false) // 關閉 SmartLock
                            .build()
                    , RC_SIGN_IN);
        }
    }

    private void showNicknameDialog(String displayName) {

        final EditText nickEdit = new EditText(this);
        nickEdit.setText(displayName);
        nickEdit.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
        nickEdit.setSingleLine();

        new AlertDialog.Builder(this)
                .setTitle("Nick name")
                .setMessage("Please enter your nick name")
                .setView(nickEdit)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nickname = nickEdit.getText().toString();
                        FirebaseDatabase.getInstance()
                                .getReference("users")
                                .child(auth.getUid())
                                .child("nickname")
                                .setValue(nickname);
                    }
                })
                .show();
    }

    public void changeNickname(View view) {
        showNicknameDialog(nickText.getText().toString());
    }

    @Override
    public void onClick(View view) {

        // 先判斷 View 是否為 ImageView
        if (view instanceof ImageView) {

            int selectedAvatarId = 0;
            switch (view.getId()) {
                case R.id.avatar1:
                    selectedAvatarId = 1;
                    break;
                case R.id.avatar2:
                    selectedAvatarId = 2;
                    break;

                case R.id.avatar3:
                    selectedAvatarId = 3;
                    break;
                case R.id.avatar4:
                    selectedAvatarId = 4;
                    break;
                case R.id.avatar5:
                    selectedAvatarId = 5;
                    break;
                case R.id.avatar6:
                    selectedAvatarId = 6;
                    break;
            }

            // 去設定 firebase 上的資料
            FirebaseDatabase.getInstance().getReference("users")
                    .child(auth.getCurrentUser().getUid())
                    .child("avatarId")
                    .setValue(selectedAvatarId);

            groupAvatars.setVisibility(View.GONE);
        }
    }
}
