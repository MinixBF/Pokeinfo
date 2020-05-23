package com.exemple.td3_recyclerview.presentation.controller;

import com.exemple.td3_recyclerview.Singletons;
import com.exemple.td3_recyclerview.presentation.model.RestIdResponse;
import com.exemple.td3_recyclerview.presentation.view.DetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailController {

    private final DetailActivity viewDetail;

    public DetailController(DetailActivity detailActivity){
        this.viewDetail = detailActivity;
    }

    public void makeApiCallIdPokemon(int id){

        Call<RestIdResponse> call = Singletons.getPokeApi().getPokemonIdResponse(id);
        call.enqueue(new Callback<RestIdResponse>() {

            @Override
            public void onResponse(Call<RestIdResponse> call, Response<RestIdResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    RestIdResponse restIdResponse = response.body();
                    viewDetail.showDetail(restIdResponse.getWeight(),restIdResponse.getHeight(),restIdResponse.getBase_experience());
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

}
