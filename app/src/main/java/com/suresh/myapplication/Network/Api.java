package com.suresh.myapplication.Network;

import com.suresh.myapplication.Models.DataModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {

    // base url for data fetching
    String BASE_URL = "https://dl.dropboxusercontent.com";

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<DataModel> getData();
}
