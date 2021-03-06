package com.example.yamuna.shoppingcart;

import android.content.Intent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://bitnami-39xfosdmxa.appspot.com";

    @GET("get-items")
    Call<List<Phone>> getPhones();

    @GET("get-items")
    Call<List<Phone>> getPhones(
            @Query("manufacturer") String manufacturer,
            @Query("model") String model,
            @Query("min-price") String minprice,
            @Query("max-price") String maxprice
    );

    @GET("buy")
    Call<Buy> getBuy(
            @Query("model") String model,
            @Query("username") String username,
            @Query("qty") String quantity
    );

    @GET("getSalesRecords")
    Call<List<Buy>> getSales();
}
