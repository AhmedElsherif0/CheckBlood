package com.example.checkblood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.checkblood.R;
import com.example.checkblood.data.model.getArticle.Datum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleArticleAdapter extends RecyclerView.Adapter<RecycleArticleAdapter.ViewHolder> {


    private List<Datum> dataList;
    private Context context;

    public RecycleArticleAdapter(List<Datum> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_articles_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProtection;
        ImageView ivFavorite, ivProtectionWay;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProtection = itemView.findViewById(R.id.article_tv_protectionOfDiseases);
            ivFavorite = itemView.findViewById(R.id.article_Iv_favorite);
            ivProtectionWay = itemView.findViewById(R.id.article_Iv_imageOfDiseases);
        }

        void bind(Datum datum) {

            tvProtection.setText(datum.getTitle());
            Glide.with(context).load(datum.getIsFavourite()).into(ivFavorite);
            Glide.with(context).load(datum.getThumbnail()).into(ivProtectionWay);
        }

    }
}