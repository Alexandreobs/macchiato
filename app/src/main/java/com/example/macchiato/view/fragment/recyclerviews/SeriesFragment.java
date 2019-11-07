package com.example.macchiato.view.fragment.recyclerviews;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.tmdb.tvshows.Result;
import com.example.macchiato.vielmodel.SerieViewModel;
import com.example.macchiato.view.adapter.SerieAdapter;
import com.example.macchiato.view.fragment.detalhe.DetalheSeriesFragment;
import com.example.macchiato.view.interfaces.SerieOnClick;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment implements SerieOnClick {
    private ProgressBar progressBar;
    private List<Result> resultList = new ArrayList<>();
    private SerieViewModel serieViewModel;
    private RecyclerView recyclerView;
    private SerieAdapter adapter;
    private  int pagina = 1;

    public static final String SERIE_KEY = "serie";
    public static final String API_KEY = "bde8033d3274c91b292a5293c6349052";



    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        initView(view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        serieViewModel.getAllSeries(API_KEY, pagina);

        serieViewModel.getListaSerie().observe(this, resultaLista -> {
            adapter.autalizaLista(resultaLista);
        });

        serieViewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;

    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_Series);
        progressBar = view.findViewById(R.id.progress_bar);
        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel.class);
        adapter = new SerieAdapter(resultList, this);
        setScrollView();
    }


    public void replaceFragment (Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void serieOnClick(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SERIE_KEY, result);

        Fragment detalheFragment = new DetalheSeriesFragment();
        detalheFragment.setArguments(bundle);
        replaceFragment(detalheFragment);
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
                    serieViewModel.getAllSeries(API_KEY, pagina);
                }

            }
        });
    }
}

