package com.derekprovance.biometricsapp.auth;

import android.os.AsyncTask;

import com.derekprovance.biometricsapp.util.ApiRequest;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import static com.derekprovance.biometricsapp.util.Constants.SERVER_URL;

public class AuthenticationTask extends AsyncTask<String, String, Boolean> {

    private OnEventListener<Boolean> mCallBack;
    public Exception mException;

    public AuthenticationTask(OnEventListener callback) {
        mCallBack = callback;
    }

    protected Boolean doInBackground(String... credentials) {
        try {
            String credential = Credentials.basic(credentials[0], credentials[1]);
            OkHttpClient client = ApiRequest.getInstance().getClient();
            Request request = new Request.Builder().url(SERVER_URL + "/authenticate").header("Authorization", credential).build();

            Response response = client.newCall(request).execute();
            return response.isSuccessful();
        } catch (Exception ex) {
            mException = ex;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (mCallBack != null) {
            if (mException == null && result) {
                mCallBack.onSuccess(true);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
