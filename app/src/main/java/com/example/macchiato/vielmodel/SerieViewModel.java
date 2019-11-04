package com.example.macchiato.vielmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.macchiato.model.pojos.tvshows.Result;
import com.example.macchiato.model.repository.SerieRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SerieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> listaSerie = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SerieRepository repository = new SerieRepository();

    public SerieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public LiveData<List<Result>> getListaSerie() {
        return this.listaSerie;
    }

    public void getAllSeries(String apiKey){
        disposable.add(
                repository.getSeries(apiKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loading.setValue(true))
                        .doAfterTerminate(()-> loading.setValue(false))
                        .subscribe(SerieResult -> {
                                    listaSerie.setValue(SerieResult.getResults());
                                },
                                throwable -> {
                                    Log.i("LOG", "Erro" + throwable.getMessage());
                                }
                        )
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
