package com.mercury.kron.utils;

import android.content.Context;
import android.widget.Button;

//https://stackoverflow.com/questions/2016249/how-to-programmatically-setting-style-attribute-in-a-view
public class ButtonStyleHolder implements StyleHolder<Button> {
    /*
        private final Drawable background;
        private final ColorStateList textColor;
        private final int textSize;
    */
    public ButtonStyleHolder(Context context) {
        /*
        TypedArray ta = context.obtainStyledAttributes(R.style.button, R.styleable.LockButtonView);

        Resources resources = context.getResources();

        background = ta.getDrawable(ta.getIndex(R.styleable.LockButtonView_style_lock_android_background));

        textColor = ta.getColorStateList(ta.getIndex(R.styleable.ButtonStyleHolder_android_textColor));

        textSize = ta.getDimensionPixelSize(
                ta.getIndex(R.styleable.ButtonStyleHolder_android_textSize),
                resources.getDimensionPixelSize(R.dimen.standard_text_size)
        );

        // Don't forget to recycle!
        ta.recycle();
        */
    }

    @Override
    public void applyStyle(Button btn) {
        /*
        btn.setBackground(background);
        btn.setTextColor(textColor);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        */
    }
}