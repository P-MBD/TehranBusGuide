package com.example.bustehran.Webervice;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GetJson {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    public String jsonRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.e("GetJson", "Unexpected code " + response);
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String jsonResponse = responseBody.string();
                Log.d("GetJson", "Response from URL: " + jsonResponse);
                return jsonResponse;
            } else {
                return "";
            }
        }
    }
}
