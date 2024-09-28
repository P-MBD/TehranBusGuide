package com.example.bustehran;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {
    @GET("stations/")
    Call<List<Station>> getStations();
}
