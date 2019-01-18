package com.mercury.kron.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.mercury.kron.R;

public class DocksActivity extends BaseActivity {

    TextView tw1;
    TextView tw2;
    TextView tw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docks);
        initToolbar(true);
        setToolbarTitle(getString(R.string.pravo));
        enableUpButton();
        tw1 = (TextView)findViewById(R.id.textPolz);
        tw2 = (TextView)findViewById(R.id.textPolit);
        tw3 = (TextView)findViewById(R.id.textUslovia);

        tw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DocksActivity.this, PolzActivity.class);
                //запускаем вторую активность
                startActivity(intent);
            }
        });

        tw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DocksActivity.this, PolitActivity.class);
                //запускаем вторую активность
                startActivity(intent);
            }
        });

        tw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DocksActivity.this,CookActivity.class);
                //запускаем вторую активность
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

