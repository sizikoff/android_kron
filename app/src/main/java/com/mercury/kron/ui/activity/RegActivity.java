package com.mercury.kron.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.mercury.kron.R;



public class RegActivity extends BaseActivity implements OnClickListener {
    private FirebaseAuth mAuth;
    AuthStateListener mAuthStateListener;

    Button createAccount;
    EditText Email;
    EditText Password;
    EditText ConfirmPassword;
    ImageButton agreeButton;
    boolean flag = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mAuth = FirebaseAuth.getInstance();
        Email = (EditText) findViewById(R.id.login_email_reg);
        Password = (EditText) findViewById(R.id.login_password_et1);
        createAccount = (Button) findViewById(R.id.login_bt1);
        ConfirmPassword = (EditText) findViewById(R.id._password_replay);
        agreeButton = (ImageButton) findViewById(R.id.AgreeNotAgree);
        findViewById(R.id.AgreeNotAgree).setOnClickListener(this);
        findViewById(R.id.login_bt1).setOnClickListener(this);
        findViewById(R.id.textView_vhod).setOnClickListener(this);
        mAuthStateListener = new AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                } else {
                }
            }
        };
    }


    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Required.");
            valid = false;
        } else {
            Email.setError(null);
        }
        if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("Required.");
            return false;
        }
        Password.setError(null);
        return valid;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt1: {
                if (Password.getText().toString().equals(ConfirmPassword.getText().toString())) {
                    reg(Email.getText().toString(), Password.getText().toString());
                    EditText messageView = (EditText) findViewById(R.id.login_email_reg);
                    String messageText = messageView.getText().toString();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra(LoginActivity.EXTRA_MESSAGE, messageText);
                    startActivity(intent);
                } else {
                    ConfirmPassword.setError("Пароли не совпали.");
                    Password.setError("Пароли несовпали.");
                }
            }
            break;
            case R.id.textView_vhod:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.AgreeNotAgree:
                if (flag) {
                    agreeButton.setImageResource(R.drawable.agree);
                } else {
                    // возвращаем первую картинку
                    agreeButton.setImageResource(R.drawable.not_agree);
                    flag = !flag;
                }
        }
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    public void reg(String email, String password) {
        Log.d("LOGIN", "signIn:" + email);
        if (validateForm()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(RegActivity.this, "Регистрация успешна",
                                        Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(RegActivity.this, "Ошибка:проверьте правильность введенных данных,возможно пароль слишком легкий",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}