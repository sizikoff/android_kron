package com.mercury.kron.ui.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mercury.kron.R;
import com.mercury.kron.utils.ConstantManager;

/**
 * базовый класс для активностей
 * добавлены сообщения
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private final String TAG = ConstantManager.TAG_PREFIX;
    protected ProgressDialog mProgressDialog;



    public void showProgressDialog(){
        if (mProgressDialog==null) {
            mProgressDialog = new ProgressDialog(this,R.style.custom_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        }else{
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        }

    }


    public void hideProgressDialog(){
        if (mProgressDialog!=null){
            if (mProgressDialog.isShowing()){
                mProgressDialog.hide();
            }
        }
    }

    // показ toast сообщения
    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    // показ сообщения о ошибке и сброс его в лог
    public void showError(String message,Exception error){
        showToast(message);
        Log.e(TAG,String.valueOf(error));
    }

}