package com.mercury.kron.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mercury.kron.R;

/**
 * Диалог с информаций о заявке
 */
public class InfoRequestDialog extends DialogFragment implements View.OnClickListener {

    private TextView mCount; // количество посажирова
    private TextView mFrom; // откуда
    private TextView mTo; // куда
    private TextView mSumm; // сумма
    private Button mButton;

    public static InfoRequestDialog newInstance(int countHuman) {
        return new InfoRequestDialog();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.inforequest_dialog, null);
        mCount = (TextView) v.findViewById(R.id.info_count_pass);
        mFrom = (TextView) v.findViewById(R.id.info_from);
        mTo = (TextView) v.findViewById(R.id.info_to);
        mSumm = (TextView) v.findViewById(R.id.info_summ);

        mButton = (Button) v.findViewById(R.id.info_bt_ok);
        mButton.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Спасибо за заявку").setView(v);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
    }
}