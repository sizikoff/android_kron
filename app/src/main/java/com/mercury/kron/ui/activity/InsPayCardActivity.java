package com.mercury.kron.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mercury.kron.R;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class InsPayCardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "INSPAYCARD";
    private EditText mDateM;
    private EditText mDateY;
    TextWatcher mDateMMWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 2) {
                mDateY.requestFocus();
            }
        }
    };
    private EditText mCardNum;
    private Button mButton;
    private EditText mCVV;
    private ImageView mCardTypeIV;
    TextWatcher mCardNumWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (start == 0) {
                Log.d(TAG, "OK CHANGE");
                if (charSequence.length() != 0) {
                    int t = Integer.parseInt(charSequence.subSequence(0, 1).toString());
                    switch (t) {
                        case 4: // виза
                            mCardTypeIV.setImageResource(R.drawable.visa);
                            break;
                        case 2: // мир ?
                            mCardTypeIV.setImageResource(R.drawable.mir);
                            break;
                        case 5: // мастеркарт
                            mCardTypeIV.setImageResource(R.drawable.master_card);
                            break;
                        case 3: // american Express
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private int mCardType = 0;// VISA (4)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_pay_card);


        mDateM = findViewById(R.id.ins_pay_mm_et);
        mDateY = findViewById(R.id.ins_pay_gg_et);
        mCardNum = findViewById(R.id.ins_pay_card_num);
        mCVV = findViewById(R.id.ins_pay_cvv);

        mCardTypeIV = findViewById(R.id.ins_pay_logo_card_img);

        mButton = findViewById(R.id.ins_pay_button);

        mButton.setOnClickListener(this);

        mCardNum.addTextChangedListener(mCardNumWatcher);
        mDateM.addTextChangedListener(mDateMMWatcher);

        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("____-____-____-____");
        FormatWatcher formatWatcher = new MaskFormatWatcher( // форматировать текст будет вот он
                MaskImpl.createTerminated(slots)
        );
        formatWatcher.installOn(mCardNum); // устанавливаем форматтер на любой TextView

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Добавить");


        finish();
    }
}
