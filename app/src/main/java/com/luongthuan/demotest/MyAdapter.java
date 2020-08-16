package com.luongthuan.demotest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PhotoHolder> implements Filterable {

    private List<Example> exampleList;
    private List<Example> exampleListFull;
    Context context;


    public MyAdapter(List<Example> exampleList, Context context) {
        this.exampleList = exampleList;
        this.context = context;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.tvId.setText(exampleList.get(position).getId().toString());
        int number = exampleList.get(position).getId();
        if (number % 3 == 0) {
            holder.imgStar.setVisibility(View.VISIBLE);
        } else {
            holder.imgStar.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", exampleList.get(position).getId().toString());
                bundle.putString("userid", exampleList.get(position).getUserId().toString());
                bundle.putString("title", exampleList.get(position).getTitle());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Example> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filter = charSequence.toString().toLowerCase().trim();
                for (Example item : exampleListFull) {
                    if (item.getId().toString().contains(filter)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            exampleList.clear();
            exampleList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class PhotoHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        ImageView imgStar;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            imgStar = itemView.findViewById(R.id.imgStar);
        }
    }
}
