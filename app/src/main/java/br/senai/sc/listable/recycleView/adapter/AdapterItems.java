package br.senai.sc.listable.recycleView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.recycleView.viewHolder.ItemsViewHolder;

public class AdapterItems extends RecyclerView.Adapter<ItemsViewHolder> {
    private final Context context;
    private final List<Item> itemList;

    public AdapterItems(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.add_list_fragment, parent, false);
        return new ItemsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.getName().setText(itemList.get(position).getName());
        String name = itemList.get(position).getName();
        holder.getName().setText(name);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
