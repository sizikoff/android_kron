package com.mercury.kron.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.mercury.kron.R;

public class LockButton extends android.support.v7.widget.AppCompatButton {
    private boolean mLock = false;


    public LockButton(Context context) {
        this(context, null);
    }

    public LockButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LockButtonView);
    }

    public LockButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean isLock() {
        return mLock;
    }

        public void setLock(boolean lock) {
        mLock = lock;
        if (lock) {
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.app_white));
            //this.setBackground(R.drawable.lock_button_lock);
            this.setBackgroundResource(R.drawable.lock_button_unlock);
            //this.setBackgroundDrawable(getResources().getDrawable(R.drawable.lock_button_lock));
        } else {
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.app_white));
            this.setBackgroundResource(R.drawable.lock_button_lock);
//            this.setBackgroundDrawable(getResources().getDrawable(R.drawable.lock_button_unlock));
        }
    }


}