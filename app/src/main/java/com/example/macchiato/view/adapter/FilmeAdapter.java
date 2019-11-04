package com.example.macchiato.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.tmdb.filmes.Result;
import com.example.macchiato.view.interfaces.FilmeOnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter <FilmeAdapter.ViewHolder> {
    private List<Result> resultList;
    private FilmeOnClick listner;

    public FilmeAdapter(List<Result> resultList, FilmeOnClick listner) {
        this.resultList = resultList;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmes_series, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result result = resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClick(result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void autalizaLista(List<Result> resultList){
        this.resultList.clear();
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitutlo;
        private ImageView cartazFilme;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitutlo = itemView.findViewById(R.id.txtTitulo);
            cartazFilme = itemView.findViewById(R.id.imgPoster);
        }

        public void onBind(Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(cartazFilme);

            txtTitutlo.setText(result.getTitle());
        }
    }
}
