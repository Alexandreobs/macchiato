package com.example.macchiato.view.fragment.detalhe;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.tmdb.tvshows.Result;
import com.squareup.picasso.Picasso;

import static com.example.macchiato.view.fragment.recyclerviews.FilmesFragment.FILME_KEY;
import static com.example.macchiato.view.fragment.recyclerviews.SeriesFragment.SERIE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheSeriesFragment extends Fragment {
    private TextView txtData;
    private TextView txtDescricao;
    private TextView txtLingua;
    private ImageView posterFundo;


    public DetalheSeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhe_series, container, false);

        initViews(view);


        if (getArguments() != null){

            Result result = getArguments().getParcelable(SERIE_KEY);

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(posterFundo);


            txtData.setText( "Local de Origem " + result.getOriginCountry());
            txtLingua.setText("Língua Original " + result.getOriginalLanguage());
            txtDescricao.setText(result.getOverview());
        }

        return view;


    }

    private void initViews(View view) {

        txtData = view.findViewById(R.id.text_dataSeries);
        txtDescricao = view.findViewById(R.id.text_DescricaoSeries);
        txtLingua = view.findViewById(R.id.text_linguaSeries);
        posterFundo = view.findViewById(R.id.posterFundoSeries);

    }

}
