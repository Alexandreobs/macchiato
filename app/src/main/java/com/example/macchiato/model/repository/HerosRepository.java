package com.example.macchiato.model.repository;

import com.example.macchiato.model.pojos.heroi.SuperHero;

import io.reactivex.Observable;

import static com.example.macchiato.model.data.remote.superheroapi.RetrofitServiceHero.getApiService;

public class HerosRepository {

   public Observable<SuperHero> getHeroi (String ApiKey){
        return getApiService().getALLHerois(ApiKey);
    }
}
