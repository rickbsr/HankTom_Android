package com.codingbydumbbell.firebasebingo;

import android.content.Context;
import android.util.AttributeSet;

// 建議繼承 android.support.v7.widget.AppCompatButton
public class NumberButton extends android.support.v7.widget.AppCompatButton {

    int number;
    boolean pick;
    int position;

    public NumberButton(Context context) {
        super(context);
    }

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
