package com.mercury.kron.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mercury.kron.R;

/**
 * Диалог
 */
public class InfoOnCancelDialog extends DialogFragment implements View.OnClickListener {

    private static final String INFO_MSG = "INGO_MSG";
    private String mMsg;

    private OnInfoOnCancelDialogListner mListner;


    public InfoOnCancelDialog() {
        mMsg = getArguments().getString(INFO_MSG);
    }

    public InfoOnCancelDialog newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString(INFO_MSG, msg);

        InfoOnCancelDialog dialog = new InfoOnCancelDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.info_on_cancel_dialog, null);
        TextView mTvMsg = (TextView) v.findViewById(R.id.info_on_cancle_msg);

        mTvMsg.setText(mMsg);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("").setView(v);

        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if (mListner != null) {
            switch (view.getId()) {
                case R.id.info_on_cancle_ok:
                    mListner.onPositiveButton();
                    break;
                case R.id.info_on_cancle_cancle:
                    mListner.onNegativeButton();
                    break;
            }
        }
    }

    public void setOnInfoOnCancelDialogListner(OnInfoOnCancelDialogListner listner) {
        mListner = listner;
    }

    public interface OnInfoOnCancelDialogListner {
        void onPositiveButton();

        void onNegativeButton();
    }

}