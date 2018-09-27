package com.cloudipsp.android;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by vberegovoy on 6/20/17.
 */

class CardInputBase extends AppCompatEditText {
    public CardInputBase(Context context) {
        super(context);
    }

    public CardInputBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardInputBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        if (isParentCall()) {
            super.addTextChangedListener(watcher);
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    protected CharSequence getTextInternal() {
        return super.getText();
    }

    protected void setTextInternal(CharSequence text) {
        super.setText(text, BufferType.NORMAL);
    }

    @Override
    @Deprecated
    public Editable getText() {
        if (isParentCall()) {
            return super.getText();
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    @Override
    @Deprecated
    public void setText(CharSequence text, BufferType type) {
        if (isParentCall()) {
            super.setText(text, type);
        } else {
            throw new RuntimeException("unsupported operation");
        }
    }

    private final boolean isParentCall() {
        final StackTraceElement stack[] = new Throwable().getStackTrace();

        return isParentClass(stack[2].getClassName()) ||
                isParentClass(stack[3].getClassName()) ||
                isParentClass(stack[4].getClassName());
    }

    private final boolean isParentClass(String className) {
        return className.startsWith("android.widget.")
                || className.equals("android.support.design.widget.TextInputLayout")
                || className.equals("com.huawei.android.hwcontrol.HwEditor");
    }
}
