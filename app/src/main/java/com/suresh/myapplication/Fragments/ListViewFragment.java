package com.suresh.myapplication.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suresh.myapplication.Adapters.CustomAdapter;
import com.suresh.myapplication.R;
import com.suresh.myapplication.ViewModels.DataViewModel;

import java.util.Objects;

public class ListViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private DataViewModel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        model = ViewModelProviders.of(this).get(DataViewModel.class);

        model.getData().observe(this, heroList -> {
            customAdapter = new CustomAdapter(getActivity(), heroList);
            recyclerView.setAdapter(customAdapter);
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        // SwipeRefreshLayout
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /*
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);

            // Fetching data from server
            model.getData().observe(Objects.requireNonNull(getActivity()), heroList -> {
                customAdapter = new CustomAdapter(getActivity(), heroList);
                recyclerView.setAdapter(customAdapter);

                mSwipeRefreshLayout.setRefreshing(false);
            });
        });

        return view;
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);

        model.getData().observe(this, heroList -> {
            customAdapter = new CustomAdapter(getActivity(), heroList);
            recyclerView.setAdapter(customAdapter);

            mSwipeRefreshLayout.setRefreshing(false);
        });

    }
}
