package com.exemple.td3_recyclerview.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.td3_recyclerview.R;
import com.exemple.td3_recyclerview.Singletons;
import com.exemple.td3_recyclerview.presentation.model.Pokemon;

import static com.exemple.td3_recyclerview.Constants.KEY_POKEMON;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra(KEY_POKEMON);
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetail(pokemon);
    }

    public void showDetail(Pokemon pokemon){
        txtDetail.setText(pokemon.getName());
    } // faire controller

}
