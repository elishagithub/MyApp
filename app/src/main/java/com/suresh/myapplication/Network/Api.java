package com.suresh.myapplication.Network;

import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Models.Row;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static com.suresh.myapplication.Network.HttpService.HEADER_CACHE;

public interface Api {

    String BASE_URL = "https://dl.dropboxusercontent.com";

    @GET("/s/2iodh4vg0eortkl/facts.json")
    @Headers(HEADER_CACHE + ": 60")
    Call<DataModel> getData();
}
