package com.exemple.td3_recyclerview.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.td3_recyclerview.Constants;
import com.exemple.td3_recyclerview.R;
import com.exemple.td3_recyclerview.Singletons;
import com.exemple.td3_recyclerview.presentation.controller.MainController;
import com.exemple.td3_recyclerview.presentation.model.Pokemon;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private ListAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;
    private MaterialSearchBar searchBar;

    private List<String> lastSearch = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getShardPreferences(getApplicationContext())
        );
        controller.onStart();
    }

    public void showList(final List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Pokemon item) {
                controller.OnItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra(Constants.KEY_POKEMON,Singletons.getGson().toJson(pokemon));
        MainActivity.this.startActivity(myIntent);
    }
}
