package com.mercury.kron.ui.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mercury.kron.R;
import com.mercury.kron.ui.views.LockButton;

/**
 * Выбор количества человек
 * в слушателе возвращает количество отмеченых человек.
 */

public class SelectCountHumanDialog extends DialogFragment implements View.OnClickListener{
    private int mCount = 0;
    private LockButton[] mButtons = {null, null, null, null};
    private Button mOkButton;
    private OnSelectCountListener mSelectCountListener;
    private boolean isPartner =false;

    public static SelectCountHumanDialog newInstance() {
        return new SelectCountHumanDialog();
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.selectcounthuman_dialog, null);
        LinearLayout insider = v.findViewById(R.id.linearLayout_insider);
        mOkButton = v.findViewById(R.id.select_tg_ok);
        if(isPartner){
            int contextCompat = ContextCompat.getColor(getActivity(), R.color.app_partner_background);
            mOkButton.setBackgroundColor(contextCompat);
            v.setBackgroundColor(contextCompat);
            insider.setBackgroundColor(contextCompat);
        }
        mOkButton.setOnClickListener(this);
        mButtons[0] = v.findViewById(R.id.select_tg_button_1);
        mButtons[1] = v.findViewById(R.id.select_tg_button_2);
        mButtons[2] = v.findViewById(R.id.select_tg_button_3);
        mButtons[3] = v.findViewById(R.id.select_tg_button_4);
        for (int i = 0; i < mButtons.length; i++) {
            if(isPartner) mButtons[i].setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.app_partner_background));
            mButtons[i].setOnClickListener(this);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setTitle("Количество человек");
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_tg_ok:
                if (mSelectCountListener != null) {
                    mCount = 0;
                    for (int i = 0; i < mButtons.length; i++) {
                        //if (mButtons[i].isLock()) mCount +=1;
                        if (mButtons[i].isLock()) mCount = i + 1;
                    }
                    mSelectCountListener.OnSelectCount(mCount);
                }
                this.dismiss();
                break;
            case R.id.select_tg_button_1:
                changeSelectButton(0);
                break;
            case R.id.select_tg_button_2:
                changeSelectButton(1);
                break;
            case R.id.select_tg_button_3:
                changeSelectButton(2);
                break;
            case R.id.select_tg_button_4:
                changeSelectButton(3);
                break;
        }

    }

    private void changeSelectButton(int i) {
        // mButtons[i].setTextAppearance(this.getContext(),R.style.lock_button_lock);
        for (int l = 0; l < mButtons.length; l++) {
            mButtons[l].setLock(false);
        }
        mButtons[i].setLock(true);
        //mButtons[i].setLock(!mButtons[i].isLock());
    }

    public void setOnSelectCountListener (OnSelectCountListener listener){
        mSelectCountListener = listener;
    }

    public interface OnSelectCountListener {
        void OnSelectCount(int count);
    }
}