package com.mkurbanov.appforalefim.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mkurbanov.appforalefim.R;
import com.mkurbanov.appforalefim.utils.Functions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setRefreshing(true);

        MainAdapter adapter = new MainAdapter(this, viewModel.items.getValue());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.recycler_size));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(viewModel::getMainData);

        viewModel.result.observe(this, result -> {
            if (result == null)
                return;

            if (result.getErrorText() != null) {
                Functions.toast(MainActivity.this, result.getErrorText());
                return;
            }

            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.items.observe(this, itemTypes -> {
            adapter.items = itemTypes;
            adapter.notifyDataSetChanged();
        });

        viewModel.getMainData();
    }
}