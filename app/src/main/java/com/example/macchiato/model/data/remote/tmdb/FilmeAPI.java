package com.example.macchiato.model.data.remote.tmdb;

import com.example.macchiato.model.pojos.tmdb.filmes.FilmeResult;
import com.example.macchiato.model.pojos.tmdb.tvshows.Tv;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmeAPI {

    @GET("movie/now_playing")
    Observable<FilmeResult> getALLFilmes(
            @Query("api_key") String apyKey,
            @Query("page") int pagina
            // ,          @Path("pt-BR") String language
    );

    @GET("tv/popular")
    Observable<Tv> getALLSeires(
            @Query("api_key") String apyKey,
            @Query("page") int pagina
    );
}
