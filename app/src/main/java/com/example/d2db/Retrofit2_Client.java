package com.example.d2db;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2_Client {
    private static Retrofit2_Client instance;
    private Item_Service itemService;

    public Retrofit2_Client(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.55.37:8887")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        itemService = retrofit.create(Item_Service.class);
    }

    public static Retrofit2_Client getInstance(){
        if(instance == null){
            instance = new Retrofit2_Client();
        }
        return instance;
    }
    public Item_Service getItemService(){
        return itemService;
    }
}
