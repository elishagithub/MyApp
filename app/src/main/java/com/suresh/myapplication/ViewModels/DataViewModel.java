package com.suresh.myapplication.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Network.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<DataModel> data;

    //we will call this method to get the data
    public LiveData<DataModel> getData() {
        //if the list is null
        if (data == null) {
            data = new MutableLiveData<DataModel>();
            //we will load it asynchronously from server in this method
            loadData();
        }

        //finally we will return the list
        return data;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<DataModel> call = api.getData();


        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                //finally we are setting the list to our MutableLiveData
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }

}
