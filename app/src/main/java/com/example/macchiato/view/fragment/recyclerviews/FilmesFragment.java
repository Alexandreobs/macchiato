package com.example.macchiato.view.fragment.recyclerviews;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.tmdb.filmes.Result;
import com.example.macchiato.vielmodel.FilmeViewModel;
import com.example.macchiato.view.activity.MainActivity;
import com.example.macchiato.view.adapter.FilmeAdapter;
import com.example.macchiato.view.fragment.detalhe.DetalheFilmeFragment;
import com.example.macchiato.view.interfaces.FilmeOnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmesFragment extends Fragment implements FilmeOnClick {
    private ProgressBar progressBar;
    private List<Result> resultList = new ArrayList<>();
    private FilmeViewModel filmeViewModel;
    private RecyclerView recyclerView;
    private FilmeAdapter adapter;
    private  int pagina = 1;

    public static final String FILME_KEY = "filme";
    public static final String API_key = "bde8033d3274c91b292a5293c6349052";

    public FilmesFragment(ProgressBar progressBar, List<Result> resultList, FilmeViewModel filmeViewModel, RecyclerView recyclerView, FilmeAdapter adapter) {
        this.progressBar = progressBar;
        this.resultList = resultList;
        this.filmeViewModel = filmeViewModel;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    public FilmesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);

        initView(view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        filmeViewModel.getAllFilmes(API_key, pagina);

        filmeViewModel.getListaFilmes().observe(this, resultaLista -> {
            adapter.autalizaLista(resultaLista);
        });

        filmeViewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;

    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_Filmes);
        progressBar = view.findViewById(R.id.progress_bar);
        filmeViewModel = ViewModelProviders.of(this).get(FilmeViewModel.class);
        adapter = new FilmeAdapter(resultList, this);
        setScrollView();
    }


    @Override
    public void onClick(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(FILME_KEY, result);

        Fragment detalheFragment = new DetalheFilmeFragment();
        detalheFragment.setArguments(bundle);
        replaceFragment(detalheFragment);
    }

    public void replaceFragment (Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void setScrollView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem){
                    pagina++;
                    filmeViewModel.getAllFilmes(API_key, pagina);
                }

            }
        });
    }
}
