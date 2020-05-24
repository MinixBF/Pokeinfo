package com.exemple.td3_recyclerview.data;

import com.exemple.td3_recyclerview.presentation.model.RestIdResponse;
import com.exemple.td3_recyclerview.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {
    @GET("/api/v2/pokemon?offset=0&limit=500")
    Call<RestPokemonResponse> getPokemonResponse();

    @GET("/api/v2/pokemon/{id}/")
    Call<RestIdResponse> getPokemonIdResponse(@Path("id") int id);

    //@GET("/api/v2/pokemon")
    //Call<RestPokemonResponse> getPokemonResponse();



}
