package com.mercury.kron.utils;

/**
 * Константы приложения
 */
public interface ConstantManager {

    String SESSION_TOKEN = "SESSION_TOKEN";
    String ANDROID_ID = "ANDROID_ID";


    // для типа клиента
    int TYPE_USER_CLIENT = 0;
    int TYPE_USER_PARTNER = 1;

    String TAG_PREFIX = "KRON";

    String BASE_URL = "";
    String URL_REGISTRY = "";

    String[] CARD_PAY_TYPE = {"VISA", "MasterCard", ""};

    enum errorCodes{
        NOLOCATIONPROVIDE, NOINTERNETPROVIDER
    }

}