package com.exemple.td3_recyclerview;

import android.content.Context;
import android.content.SharedPreferences;

import com.exemple.td3_recyclerview.data.PokemonApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PokemonApi pokeApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if( gsonInstance == null) {
            gsonInstance =new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static PokemonApi getPokeApi() {
        if(pokeApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            pokeApiInstance = retrofit.create(PokemonApi.class);
        }
        return pokeApiInstance;
    }

    public static SharedPreferences getShardPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }

}
