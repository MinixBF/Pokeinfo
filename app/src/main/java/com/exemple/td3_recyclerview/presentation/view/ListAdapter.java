package com.exemple.td3_recyclerview.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exemple.td3_recyclerview.Constants;
import com.exemple.td3_recyclerview.R;
import com.exemple.td3_recyclerview.presentation.model.Pokemon;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Pokemon> values;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Pokemon item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView imageView;
        View layout;

       public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
           // txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imageView = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public ListAdapter(List<Pokemon> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon currentPokemon = values.get(position);
        holder.txtHeader.setText(currentPokemon.getName());
        //holder.txtFooter.setText(currentPokemon.getUrl());

        Glide.with(holder.imageView)
                .load(Constants.URL_IMAGE + currentPokemon.getNumber() + ".png")
                .into(holder.imageView);


         holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            listener.onItemClick(currentPokemon);
        }});
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

