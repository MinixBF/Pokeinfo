package com.exemple.td3_recyclerview.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.exemple.td3_recyclerview.Constants;
import com.exemple.td3_recyclerview.R;
import com.exemple.td3_recyclerview.Singletons;
import com.exemple.td3_recyclerview.presentation.controller.DetailController;
import com.exemple.td3_recyclerview.presentation.model.Pokemon;
import com.exemple.td3_recyclerview.presentation.model.RestIdResponse;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail_Name;
    private TextView txtDetail_Weight;
    private TextView txtDetail_Height;
    private TextView txtDetail_Basexp;
    private ImageView imageView;
    private DetailController detailController;
    public RestIdResponse restIdResponse;
    private DetailActivity detailActivity;
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailController = new DetailController(
                this,
                Singletons.getGson(),
                Singletons.getShardPreferences(getApplicationContext()));


        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra(Constants.KEY_POKEMON);
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        printImageAndName(pokemon);
        //int id_poke = detailActivity.getIntent().getIntExtra(Constants.KEY_ID_POKE, 1);

        if( detailController.verifDataFromCache(pokemon.getNumber()) == 1){}
        else{
            detailController.makeApiCallIdPokemon(pokemon.getNumber());
        }
    }

    public void showDetail(int Weight, int Height, int Basexp){
        txtDetail_Weight = findViewById(R.id.detail_weight);
        txtDetail_Height = findViewById(R.id.detail_height);
        txtDetail_Basexp = findViewById(R.id.detail_basexp);

        txtDetail_Weight.setText( "Weight : " + Integer.toString(Weight));
        txtDetail_Height.setText("Height : " + Integer.toString(Height));
        txtDetail_Basexp.setText("Base Experience : " + Integer.toString(Basexp));
    }

    public void printImageAndName(Pokemon pokemon){
        txtDetail_Name = findViewById(R.id.detail_name);
        imageView = findViewById(R.id.icon_detail);
        txtDetail_Name.setText(  pokemon.getName());
        Glide.with(imageView)
                .load(Constants.URL_IMAGE + pokemon.getNumber() + ".png")
                .apply(new RequestOptions().override(500, 300))
                .into(imageView);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ID Error", Toast.LENGTH_SHORT).show();
    }
}
