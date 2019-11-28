package com.suresh.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.suresh.myapplication.Adapters.CustomAdapter;
import com.suresh.myapplication.Fragments.ListViewFragment;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.ViewModels.DataViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Model gets title from webservice and sets actionbar title dynamically

        DataViewModel model = ViewModelProviders.of(this).get(DataViewModel.class);

        model.getData().observe(this, list -> {

            assert list != null;
            if(list.getTitle() != null) {
                Objects.requireNonNull(getSupportActionBar()).setTitle(list.getTitle());
            }

        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new ListViewFragment());
        fragmentTransaction.commit();

    }
}
