package com.example.bustehran;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {
    private static final String BASE_URL = "http://172.20.2.26:8000/api/stations/";
    private OkHttpClient client;

    public ApiClient() {
        // ساخت یک شی از OkHttpClient
        client = new OkHttpClient();
    }

    // متد برای واکشی ایستگاه‌ها از API
    public void fetchStations(Callback callback) {
        // ایجاد درخواست HTTP
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        // اجرای درخواست به صورت ناهمگام
        Call call = client.newCall(request);
        call.enqueue(callback); // اجرای درخواست با callback برای مدیریت پاسخ‌ها
    }
}
