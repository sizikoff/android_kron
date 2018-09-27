package com.mercury.kron.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mercury.kron.R;
import com.mercury.kron.ui.activity.LoginActivity;
import com.mercury.kron.ui.activity.MainActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class VkAuth extends AppCompatActivity {
    private String[]scope = new String[]{VKScope.WALL,VKScope.EMAIL};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk);

        VKSdk.login(this,scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
            // Пользователь успешно авторизовался
                Intent intent1 = new Intent(VkAuth.this,MainActivity.class);
                startActivity(intent1);
            }
            @Override
            public void onError(VKError error) {
                Intent intent1 = new Intent(VkAuth.this,LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
