package com.derekprovance.biometricsapp.util;

import com.squareup.okhttp.OkHttpClient;

public class ApiRequest {
    private static final ApiRequest instance = new ApiRequest();
    private OkHttpClient client;

    public static ApiRequest getInstance() {
        return instance;
    }

    private ApiRequest() {
        client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return client;
    }
}
