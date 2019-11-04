package com.example.macchiato.model.repository;

import com.example.macchiato.model.pojos.tmdb.tvshows.Tv;

import io.reactivex.Observable;

import static com.example.macchiato.model.data.remote.RetrofitService.getApiService;

public class SerieRepository {

    public Observable<Tv> getSeries (String apiKey){
        return getApiService().getALLSeires(apiKey);
    }
}
