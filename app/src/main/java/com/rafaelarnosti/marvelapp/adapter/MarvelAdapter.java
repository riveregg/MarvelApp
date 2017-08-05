package com.rafaelarnosti.marvelapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafaelarnosti.marvelapp.Model.Super;
import com.rafaelarnosti.marvelapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by RAFAELLUIZMAZZINIARN on 24/07/2017.
 */

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>{
    private List<Super> supers;
    private OnItemClickListener listener;

    public MarvelAdapter(List<Super> supers, OnItemClickListener listener){
        this.supers = supers;
        this.listener = listener;
    }


    @Override
    public MarvelAdapter.MarvelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View meuLayout = inflater.inflate(R.layout.super_row,parent,false);
        return new MarvelViewHolder(meuLayout);
    }

    @Override
    public void onBindViewHolder(MarvelAdapter.MarvelViewHolder holder, final int position) {
        holder.tvTitulo.setText((supers.get(position).getName()));
        holder.tvSubTitulo.setText(supers.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(supers.get(position));
            }
        });
        Picasso.with(holder.itemView.getContext())
                .load(supers.get(position).getResourceURI())
                .into(holder.ivSuper);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MarvelViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivSuper;
        public TextView tvTitulo;
        public TextView tvSubTitulo;

        public MarvelViewHolder(View itemView) {
            super(itemView);
            ivSuper = (ImageView) itemView.findViewById(R.id.ivSuper);
            tvTitulo =(TextView) itemView.findViewById(R.id.tvTitulo);
            tvSubTitulo = (TextView) itemView.findViewById(R.id.tvSubTitulo);
        }
    }
    public void update(List<Super>supers){
        this.supers = supers;
        notifyDataSetChanged();
    }
}