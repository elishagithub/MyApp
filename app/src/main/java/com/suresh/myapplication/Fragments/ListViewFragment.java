package com.suresh.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suresh.myapplication.Adapters.CustomAdapter;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Models.Row;
import com.suresh.myapplication.R;
import com.suresh.myapplication.ViewModels.DataViewModel;

import java.util.List;

public class ListViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    List<Row> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        DataViewModel model = ViewModelProviders.of(this).get(DataViewModel.class);

        model.getData().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(@Nullable DataModel heroList) {
                customAdapter = new CustomAdapter(getActivity(), heroList);
                recyclerView.setAdapter(customAdapter);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
