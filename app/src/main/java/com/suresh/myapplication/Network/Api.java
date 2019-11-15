package com.suresh.myapplication.Network;

import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Models.Row;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://dl.dropboxusercontent.com";

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<DataModel> getData();
}
