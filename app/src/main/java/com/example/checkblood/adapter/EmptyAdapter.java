package com.example.checkblood.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkblood.R;

import butterknife.ButterKnife;


public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder> {


    private Context context;

//   private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_slide, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return 0;
    }

    // view holder class extends from RecyclerView.View Holder

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;


        public ViewHolder(View itemView) {

            super(itemView);
            view = itemView;

            ButterKnife.bind(this, view);

        }

    }

}
