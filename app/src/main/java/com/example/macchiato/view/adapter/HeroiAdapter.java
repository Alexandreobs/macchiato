package com.example.macchiato.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.R;
import com.example.macchiato.model.pojos.heroi.Result;
import com.example.macchiato.view.interfaces.HeroisOnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroiAdapter extends RecyclerView.Adapter <HeroiAdapter.ViewHolder> {
    private List<Result> resultList;
    private HeroisOnClick listener;

    public HeroiAdapter(List<Result> resultList, HeroisOnClick listener) {
        this.resultList = resultList;
        this.listener = listener;
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
                listener.heroisOnClick(result);
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

        private TextView nomeHeroi;
        private ImageView posterHeroi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeHeroi = itemView.findViewById(R.id.txtTitulo);
            posterHeroi = itemView.findViewById(R.id.imgPoster);
        }

        public void onBind(Result result) {
            Picasso.get().load("https://superheroapi.com/api/3158554990885448" + result.getId() + result.getImage()).into(posterHeroi);

            nomeHeroi.setText(result.getName());
        }
    }
}