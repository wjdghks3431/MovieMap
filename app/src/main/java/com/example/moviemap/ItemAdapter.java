package com.example.moviemap;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements Filterable {

    private List<ItemModel> itemList;
    private List<ItemModel> itemListFull;
    private OnItemListener onItemListener;

    public ItemAdapter(List<ItemModel> itemList, OnItemListener onItemListener) {
        this.itemList = itemList;
        this.itemListFull = new ArrayList<>(itemList);
        this.onItemListener = onItemListener;
    }

    // onCreateViewHolder
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new ItemViewHolder(v, onItemListener);
    }

    // onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel currentItem = itemList.get(position);

        // Set data to the views in the ViewHolder
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
    }

    // getItemCount
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // getFilter
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ItemModel item : itemListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern) || item.getText2().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((List<ItemModel>) results.values);
            notifyDataSetChanged();
        }
    };

    // ItemViewHolder
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        OnItemListener onItemListener;

        public ItemViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onItemListener.onItemClicked(position);
            }
        }
    }

    // OnItemListener
    public interface OnItemListener {
        void onItemClicked(int position);
    }
}