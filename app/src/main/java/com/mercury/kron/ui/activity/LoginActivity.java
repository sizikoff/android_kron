package com.mercury.kron.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mercury.kron.R;

import com.mercury.kron.auth.PhoneActivity;
import com.mercury.kron.auth.VkAuth;
import com.mercury.kron.ui.activity.showInstruction.MainShowInstructionActivity;
import com.mercury.kron.ui.activity.showInstruction.PageFragment;
import com.vk.sdk.util.VKUtil;

import org.w3c.dom.Text;

import java.util.Arrays;

/**
 * Вход в систему
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LOGIN";


    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    private Button mButtonLogin;
    private EditText mEmail;
    private EditText mPassword;
    ImageButton mButtonPhone;
    ImageButton mVkBut;
    ImageButton mFBBut;
    private TextView mRememberPass;
    ProgressDialog pd;
    private TextView RegWindow;
    public static final String EXTRA_MESSAGE = "message" ;
     private RelativeLayout mRelativeLayout;
    private Switch aSwitch;
    private boolean isPartner = false;
    private static boolean isFirst = false;
    public static final String EXTRA_BOOLEAN = "boolean" ;
    private TextView mtextForSwitch;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        isPartner = getIntent().getBooleanExtra(MainShowInstructionActivity.EXTRA_BOOLEAN,false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRelativeLayout = findViewById(R.id.login_activity_maket);
        mtextForSwitch = findViewById(R.id.text_switch);
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.login_email_et);
        mPassword = findViewById(R.id.login_password_et);
        mRememberPass = findViewById(R.id.login_remember_pass);
        mButtonLogin = findViewById(R.id.login_bt);
        mButtonPhone = findViewById(R.id.phoneSign);
        RegWindow = findViewById(R.id.textView2);
        mVkBut = findViewById(R.id.vk_but);
        mFBBut = findViewById(R.id.fb_but);

        RegWindow.setOnClickListener(this);
        mButtonPhone.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mVkBut.setOnClickListener(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "Вошел");
                } else {
                    Log.d(TAG, "не Вошел");
                }
            }
        };
        Intent intent = getIntent ();
        String messageText = intent . getStringExtra ( EXTRA_MESSAGE );
        mEmail = findViewById(R.id.login_email_et);
        mEmail . setText ( messageText );

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        System.out.println(Arrays.asList(fingerprints));
        aSwitch = findViewById(R.id.switch_to_partner);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
               if(!isFirst)showInstruction();
              setStyleForPartner(isChecked);

            }

        });
        setStyleForPartner(isPartner);
    }

    private void showInstruction() {
        isFirst=true;
        Intent intent = new Intent(LoginActivity.this,MainShowInstructionActivity.class);
        startActivity(intent);

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("boolean",isPartner);
                startActivity(intent);
//                signIn(mEmail.getText().toString(), mPassword.getText().toString());
                pd = new ProgressDialog(this);
                pd.setMessage("Пожалуйста,подождите");
                pd.show();
                break;
            case R.id.phoneSign:
                Intent intent2 = new Intent(this, PhoneActivity.class);
                startActivity(intent2);
                break;
            case R.id.textView2:
                Intent intent1 = new Intent(this,RegActivity.class);
                intent1.putExtra("boolean",isPartner);
                startActivity(intent1);
                break;
            case R.id.vk_but:
                Intent intent4 = new Intent(this,VkAuth.class);
                startActivity(intent4);
                break;
        }
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Добро пожаловать",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Такого пользователя не существует или он был удален",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        hideProgressDialog();
                    }
                });
    }

    public void reg(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "Регистрация прошла успешна",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginActivity.this, "Ошибка:проверьте правильность введенных данных",
                                    Toast.LENGTH_SHORT).show();

                        }

                        hideProgressDialog();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setStyleForPartner( boolean isChecked){

        if(isChecked){
            mtextForSwitch.setText(R.string.kron_partner);
            isPartner = true;
            aSwitch.setChecked(true);
            aSwitch.setBackgroundResource(R.drawable.switchbackground);
           int contextCompat = ContextCompat.getColor(LoginActivity.this, R.color.app_partner_background);
            mRelativeLayout.setBackgroundColor(contextCompat);
            mButtonPhone.setBackgroundColor(contextCompat);
            mFBBut.setBackgroundColor(contextCompat);
            mVkBut.setBackgroundColor(contextCompat);
         //   getApplication().setTheme(R.style.Partner_AppTheme);
        }else {
            mtextForSwitch.setText(R.string.kron_client);
            isPartner = false;
            int contextCompat = ContextCompat.getColor(LoginActivity.this, R.color.app_main_background);
            mRelativeLayout.setBackgroundColor(contextCompat);
            mButtonPhone.setBackgroundColor(contextCompat);
            mFBBut.setBackgroundColor(contextCompat);
            mVkBut.setBackgroundColor(contextCompat);
         //   getApplication().setTheme(R.style.AppTheme);
            aSwitch.setBackgroundResource(R.drawable.switch_back_white);
            aSwitch.setChecked(false);

        }

    }
}
//    public void registration(View view) {
//        Intent intent = new Intent(this,RegActivity.class);
//        startActivity(intent);
//    }
//}

