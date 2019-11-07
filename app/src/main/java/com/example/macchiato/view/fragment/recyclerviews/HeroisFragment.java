package com.example.macchiato.view.fragment.recyclerviews;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.heroi.Result;
import com.example.macchiato.vielmodel.HeroisViewModel;
import com.example.macchiato.view.adapter.HeroiAdapter;
import com.example.macchiato.view.fragment.detalhe.DetalheHeroiFragment;
import com.example.macchiato.view.interfaces.HeroisOnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeroisFragment extends Fragment implements HeroisOnClick {
    private ProgressBar progressBar;
    private List<Result> resultList = new ArrayList<>();
    private HeroisViewModel heroisViewModel;
    private RecyclerView recyclerView;
    private HeroiAdapter adapter;

    public static final String HEROI_KEY = "heroi";



    public HeroisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heroi, container, false);

        initView(view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        heroisViewModel.getALLHerois("3158554990885448");

        heroisViewModel.getListaHerois().observe(this, resultLista -> {
            adapter.autalizaLista(resultList);
        });

        heroisViewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_Heroi);
        progressBar = view.findViewById(R.id.progress_bar);
        heroisViewModel = ViewModelProviders.of(this).get(HeroisViewModel.class);
        adapter = new HeroiAdapter(resultList, this);

    }


    @Override
    public void heroisOnClick(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(HEROI_KEY, result);

        Fragment detalheFragment = new DetalheHeroiFragment();
        detalheFragment.setArguments(bundle);
        replaceFragment(detalheFragment);
    }

    public void replaceFragment (Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}