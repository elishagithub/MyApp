package com.suresh.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.suresh.myapplication.Adapters.CustomAdapter;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.ViewModels.DataViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataViewModel model = ViewModelProviders.of(this).get(DataViewModel.class);

        model.getData().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(@Nullable DataModel list) {

                getSupportActionBar().setTitle(list.getTitle());

            }
        });


    }
}
