package com.example.macchiato.model.data.remote.superheroapi;

import com.example.macchiato.model.pojos.heroi.SuperHero;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeroAPI {

    @GET("ip/")
    Observable<SuperHero> getALLHerois(
            @Query("access-token") String apyKey
    );
}
