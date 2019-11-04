package com.example.macchiato.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.tmdb.tvshows.Result;
import com.example.macchiato.view.interfaces.SerieOnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolder> {
    private List<Result> resultv;
    private SerieOnClick listener;

    public SerieAdapter(List<Result> resultv, SerieOnClick serieOnClick) {
        this.resultv = resultv;
        this.listener = serieOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmes_series, parent,false);
        return new SerieAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result result = resultv.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.serieOnClick(result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultv.size();

    }

    public void autalizaLista(List<Result> resultList){
        this.resultv.clear();
        this.resultv = resultList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitutloSerie;
        private ImageView cartazSerie;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitutloSerie = itemView.findViewById(R.id.txtTitulo);
            cartazSerie = itemView.findViewById(R.id.imgPoster);

        }

        public void onBind(Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(cartazSerie);
            txtTitutloSerie.setText(result.getName());

        }
    }
}
