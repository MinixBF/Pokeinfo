package com.exemple.td3_recyclerview.data;

import com.exemple.td3_recyclerview.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApi {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();

    //@GET("/api/v2/pokemon")
    //Call<RestPokemonResponse> getPokemonResponse();

    //@GET("/api/v2/pokemon/{id}")
    //Call<RestIdResponse> getPokemonResponse();

}
