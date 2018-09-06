package com.example.yamuna.shoppingcart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://bitnami-39xfosdmxa.appspot.com/";

    @GET("get-items")
    Call<List<Phone>> getPhones();
}
