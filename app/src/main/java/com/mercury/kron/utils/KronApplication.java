package com.mercury.kron.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mercury.kron.ui.activity.LoginActivity;
import com.mercury.kron.ui.activity.RegActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * класс приложения который стартует всегда
 * устанавливаем контекстные переменные.
 */

public class KronApplication extends android.app.Application {
    public static SharedPreferences sSharedPreferences;
    private static Context sContext;

        VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
                if (newToken == null) {
                    Intent intent1 = new Intent(KronApplication.this,LoginActivity.class);
                    startActivity(intent1);
                }
            }
        };

        @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
        sContext = this.getBaseContext();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(sContext);
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public static Context getContext() {
        return sContext;
    }
}