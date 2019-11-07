package com.example.macchiato.vielmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.macchiato.model.pojos.heroi.Result;
import com.example.macchiato.model.repository.HerosRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HeroisViewModel extends AndroidViewModel {
    private MutableLiveData<Result> listaHeroi = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private HerosRepository repository = new HerosRepository();


    public HeroisViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Result> getListaHerois(){
        return this.listaHeroi;
    }

    public LiveData<Boolean> getLoading(){
        return this.loading;
    }

    public void getALLHerois (String ApiKey){
        disposable.add(
                repository.getHeroi(ApiKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loading.setValue(true))
                        .doAfterTerminate(()-> loading.setValue(false))
                        .subscribe(heroiResult -> {
                                    listaHeroi.setValue(heroiResult.getResults());
                                },
                                throwable -> {
                                    Toast.makeText(getApplication(), "Deu merda na" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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

