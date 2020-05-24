package com.exemple.td3_recyclerview.presentation.controller;

import android.content.SharedPreferences;

import com.exemple.td3_recyclerview.Singletons;
import com.exemple.td3_recyclerview.presentation.model.RestIdResponse;
import com.exemple.td3_recyclerview.presentation.view.DetailActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.exemple.td3_recyclerview.Constants.KEY_BASEXP;
import static com.exemple.td3_recyclerview.Constants.KEY_HEIGHT;
import static com.exemple.td3_recyclerview.Constants.KEY_ID;
import static com.exemple.td3_recyclerview.Constants.KEY_WEIGHT;

public class DetailController {

    private final DetailActivity viewDetail;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public DetailController(DetailActivity detailActivity,Gson gson, SharedPreferences sharedPreferences){
        this.viewDetail = detailActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void makeApiCallIdPokemon(final int id){

        Call<RestIdResponse> call = Singletons.getPokeApi().getPokemonIdResponse(id);
        call.enqueue(new Callback<RestIdResponse>() {

            @Override
            public void onResponse(Call<RestIdResponse> call, Response<RestIdResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    RestIdResponse restIdResponse = response.body();
                    viewDetail.showDetail(restIdResponse.getWeight(),restIdResponse.getHeight(),restIdResponse.getBase_experience());
                    saveDetail(restIdResponse, id);

                    //Toast.makeText(viewDetail.getApplicationContext(),"API ID SUCCESS", Toast.LENGTH_SHORT).show(); // debug
                } else {
                    viewDetail.showError();
                }
            }

            @Override
            public void onFailure(Call<RestIdResponse> call, Throwable t) {
                viewDetail.showError();
            }
        });

    }

    private void saveDetail(RestIdResponse restIdResponse,int id) {
        sharedPreferences.edit()
                .putInt(KEY_ID, id)
                .putInt(KEY_HEIGHT, restIdResponse.getHeight())
                .putInt(KEY_WEIGHT, restIdResponse.getWeight())
                .putInt(KEY_BASEXP,restIdResponse.getBase_experience())
                .apply();

       // Toast.makeText(viewDetail.getApplicationContext(), "Detail Saved", Toast.LENGTH_SHORT).show(); //debug
    }

    public int verifDataFromCache(int id_pos) {
       int id = sharedPreferences.getInt(KEY_ID, 0);

        if(sharedPreferences.contains(KEY_HEIGHT) &&
                sharedPreferences.contains(KEY_WEIGHT) &&
                sharedPreferences.contains(KEY_BASEXP) &&
                sharedPreferences.contains(KEY_ID) && id_pos == id)
        {
            viewDetail.showDetail(
                    sharedPreferences.getInt(KEY_WEIGHT,0),
                    sharedPreferences.getInt(KEY_HEIGHT,0),
                    sharedPreferences.getInt(KEY_BASEXP,0));
            return 1;
        }
        return 0;
    }

}
