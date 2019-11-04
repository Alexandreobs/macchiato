package com.example.macchiato.view.fragment.detalhe;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.macchiato.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheSeriesFragment extends Fragment {


    public DetalheSeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhe_series, container, false);
    }

}
