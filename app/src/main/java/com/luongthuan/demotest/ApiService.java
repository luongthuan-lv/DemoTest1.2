package com.luongthuan.demotest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("albums")
    Call<List<Example>> getData();
}
