package com.suresh.myapplication.ViewModels;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Network.Api;

import org.jetbrains.annotations.NotNull;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// extends AndroidViewModel instead of ViewModel to get application "context" in non activity class
public class DataViewModel extends AndroidViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<DataModel> data;

    // define cache size
    private int cacheSize = 10 * 1024 * 1024; // 10 MB

    // create cache instance
    private Cache cache = new Cache(getApplication().getCacheDir(), cacheSize);

    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    //we will call this method to get the data
    public LiveData<DataModel> getData() {
        //if the list is null
        if (data == null) {
            data = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadData();
        }

        //finally we will return the list
        return data;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadData() {

        // create retrofit instance and build with base_url, gson converter and okHttpClient for offline cache
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);
        Call<DataModel> call = api.getData();

        call.enqueue(new Callback<DataModel>() {

            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {

                //finally we are setting the list to our MutableLiveData
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {

                // when failed getting data from server

                // do something

            }
        });
    }

    // okHttp caching code below

    private Interceptor onlineInterceptor = chain -> {
        okhttp3.Response response = chain.proceed(chain.request());
        int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .removeHeader("Pragma")
                .build();
    };

    private Interceptor offlineInterceptor = chain -> {
        Request request = chain.request();
        if (!isNetworkAvailable()) {
            int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return chain.proceed(request);
    };

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            // .addInterceptor(provideHttpLoggingInterceptor()) // For HTTP request & Response data logging
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build();


    // get network state from NetworkInfo (is connected to internet or not...)
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
