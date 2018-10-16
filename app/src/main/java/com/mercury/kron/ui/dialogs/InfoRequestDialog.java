package com.mercury.kron.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
    private boolean isPartner =false;

    public static InfoRequestDialog newInstance(int countHuman) {
        return new InfoRequestDialog();
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.inforequest_dialog, null);
        mCount = v.findViewById(R.id.info_count_pass);
        mFrom = v.findViewById(R.id.info_from);
        mTo = v.findViewById(R.id.info_to);
        mSumm = v.findViewById(R.id.info_summ);

        mButton = v.findViewById(R.id.info_bt_ok);
        if(isPartner)mButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.app_partner_background));
        mButton.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Спасибо за заявку").setView(v);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
    }
}