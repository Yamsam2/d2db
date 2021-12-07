package com.example.d2db;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Item_Service {
    @GET("list")
    Call<List<ItemDTO>> findAll();
}
