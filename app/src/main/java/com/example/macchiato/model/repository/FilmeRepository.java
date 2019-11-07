package com.example.macchiato.model.repository;

        import com.example.macchiato.model.pojos.tmdb.filmes.FilmeResult;

        import io.reactivex.Observable;

        import static com.example.macchiato.model.data.remote.tmdb.RetrofitService.getApiService;

public class FilmeRepository {

    public Observable<FilmeResult> getFilmes (String apiKey, int pagina){
        return getApiService().getALLFilmes(apiKey, pagina);
    }
}
