package com.mercury.kron.Js2Gis;

import android.webkit.JavascriptInterface;

/**
 * Created by vbm on 9/27/17.
 */


// Next will be callback methods to fetch info from WebView

public interface WebViewCallbacks {

    @JavascriptInterface
    void showToastOnActivity(String toastMessage);

    // Need our Exception enum
    @JavascriptInterface
    void catchException( int errorCode, String message);

    void setPermissions();


}
