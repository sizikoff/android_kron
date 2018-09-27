package com.mercury.kron.Js2Gis;

import android.content.Context;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mercury.kron.ui.activity.AutoActivity;

/**
 * Created by vbm on 24/09/2017.
 */

public class WebView2Gis extends WebView {

    public static final String GEOLOG = "geolog_tag";
    private static String START_GEO = "25.220317,55.232048,13";
    private final WebViewCallbacks webViewCallbacks;
    private AutoActivity myActivity;


    public WebView2Gis(final WebViewCallbacks webViewCallbacks) throws Exception {
        super((Context) webViewCallbacks);

        this.webViewCallbacks = webViewCallbacks;

        webViewCallbacks.setPermissions();


        setVerticalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setGeolocationEnabled(true);
        getSettings().setGeolocationDatabasePath(getContext().getFilesDir().getPath());
        getSettings().setDatabaseEnabled(true);
        setWebChromeClient(new android.webkit.WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, android.webkit.GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        addJavascriptInterface(webViewCallbacks, "android_callback");

        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WebView2Gis.this.addJavascriptInterface(webViewCallbacks, "android_callback");
                String js = "initMap(" + START_GEO + ", 13);";

                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    evaluateJavascript(js, null);
                } else {
                    loadUrl("javascript: " + js);
                }

                //showMarkers(markers);
                runJSCode("locationCenter();");
            }
        });

        loadUrl("file:///android_asset/map_api.html");
    }

    public static WebView2Gis getInstance(int createOption, WebViewCallbacks webViewCallbacks) {
        WebView2Gis webView2Gis = null;
        try {
            webView2Gis = new WebView2Gis(webViewCallbacks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return webView2Gis;
    }





    private void runJSCode(final String jsCode, int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    evaluateJavascript(jsCode, null);
                } else {
                    loadUrl("javascript: " + jsCode);
                }
            }
        }, delay);
    }

    private void runJSCode(final String jsCode) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    evaluateJavascript(jsCode, null);
                } else {
                    loadUrl("javascript: " + jsCode);
                }
            }
        });
    }

}

