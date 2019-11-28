package com.suresh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.suresh.myapplication.Fragments.ListViewFragment;
import com.suresh.myapplication.ViewModels.DataViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // observe ViewModel for getting title to display on action bar
        DataViewModel model = ViewModelProviders.of(this).get(DataViewModel.class);

        // observes live data
        model.getData().observe(this, list -> {

            assert list != null;
            if(list.getTitle() != null) {

                // setting title to action bar
                // getSupportActionBar() to get the action bar
                Objects.requireNonNull(getSupportActionBar()).setTitle(list.getTitle());
            }

        });

        // Fragment transaction code to replace main_activity placeholder R.id.frameLayout with ListViewFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new ListViewFragment());
        fragmentTransaction.commit();

    }
}
