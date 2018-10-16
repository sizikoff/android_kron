package com.mercury.kron.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mercury.kron.Js2Gis.WebView2Gis;
import com.mercury.kron.Js2Gis.WebViewCallbacks;
import com.mercury.kron.R;
import com.mercury.kron.ui.dialogs.InfoRequestDialog;
import com.mercury.kron.ui.dialogs.SelectCountHumanDialog;
import com.mercury.kron.ui.views.LockButton;

public class AutoActivity extends BaseActivity implements View.OnClickListener, WebViewCallbacks {

    private Toolbar mToolbar;
    private Button mCancelButton;
    private Button mOkButton;
    private int startEndPoint = 0;
    private boolean isPartner = false;
    public static final String EXTRA_BOOLEAN = "boolean" ;


    private int mCountHuman = 0; // количество человк для поездки
    SelectCountHumanDialog.OnSelectCountListener mSelectCountListener = new SelectCountHumanDialog.OnSelectCountListener() {
        @Override
        public void OnSelectCount(int count) {
            if (count != 0) {
                mCountHuman = count;
                InfoRequestDialog dialog = InfoRequestDialog.newInstance(mCountHuman);
                dialog.setPartner(isPartner);
                dialog.show(getFragmentManager(), "INFO_DIALOG");
            }
        }
    };

    public int getStartEndPoint() {
        return startEndPoint;
    }

    public void setStartEndPoint(int startEndPoint) {
        this.startEndPoint = startEndPoint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        setPermissions();
        isPartner = getIntent().getBooleanExtra(MainActivity.EXTRA_BOOLEAN,false);

        mToolbar = findViewById(R.id.toolbar);
        mOkButton = findViewById(R.id.auto_ok_bt);

        mOkButton.setOnClickListener(this);
        setStyleForPartner(isPartner);
        setupToolBar();


        FrameLayout autoWebView = findViewById(R.id.auto_webview);
        try {
            autoWebView.addView(new WebView2Gis(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // устанавливаем толлбар
    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent1 = new Intent(this,MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto_ok_bt:
                this.showToast("Начало диалога о поездке");
                SelectCountHumanDialog dialog = SelectCountHumanDialog.newInstance();
                dialog.setPartner(isPartner);
                dialog.setOnSelectCountListener(mSelectCountListener);
                dialog.show(getFragmentManager(), "SELECT_COUNT");
                break;

        }

    }

    @Override
    @JavascriptInterface
    public void showToastOnActivity(final String toastMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (startEndPoint) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });

        //Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 3);
        }
    }

    @Override
    @JavascriptInterface
    public void catchException(int errorCode, String message) {
        Toast.makeText(this, "Error " + errorCode + ", " + message, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onBackPressed() {
    }

    private void setStyleForPartner( boolean isChecked){
        LinearLayout linearLayout = new LinearLayout(this);
        if(isChecked){


            int contextCompat = ContextCompat.getColor(AutoActivity.this, R.color.app_partner_background);
            mToolbar.setBackgroundColor(contextCompat);
//            View layout = getLayoutInflater().inflate(R.layout.selectcounthuman_dialog,linearLayout,false);
//              layout.setBackgroundColor(contextCompat);



        }else {

            isPartner = false;
            int contextCompat = ContextCompat.getColor(AutoActivity.this, R.color.app_main_background);
            mToolbar.setBackgroundColor(contextCompat);

        }

    }
}
