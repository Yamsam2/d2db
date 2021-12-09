package com.example.d2db;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Item_Service {
    @GET("list")
    Call<List<ItemDTO>> findAll();

    @GET("list_category/{category}") // 카테고리로 보기
    Call<List<ItemDTO>> find_Category(@Path("category") String category);

    @GET("search/{names}") //이름 검색
    Call<List<ItemDTO>> search(@Path("names") String name);

    @GET("/detail/{num}")
    Call <ItemDTO> detail(@Path("num") Long num);
}