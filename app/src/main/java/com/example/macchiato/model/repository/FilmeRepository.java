package com.example.macchiato.model.repository;

import com.example.macchiato.model.pojos.filmes.FilmeResult;

import io.reactivex.Observable;

import static com.example.macchiato.model.data.remote.RetrofitService.getApiService;

public class FilmeRepository {

    public Observable<FilmeResult> getFilmes (String apiKey){
        return getApiService().getALLFilmes(apiKey);
    }
}
