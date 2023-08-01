package br.senai.sc.listable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.viewHolder.ItemViewHolder;

public class AdapaterItem extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private List<ShoppingList> shoppingList;

    public AdapaterItem(Context context, List<ShoppingList> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.getName().setText(shoppingList.get(position).getName());
        /*holder.getCategory().setCategory(itemList.get(position).getCategory());
        holder.getQtd().setQtd(itemList.get(position).getQtd());
        holder.getUn().setUn(itemList.get(position).getUn());
        holder.getPrice().setPrice(itemList.get(position).getPrice());
        holder.getDescription().setDescription(itemList.get(position).getDescription());
        holder.getDate().setDate(itemList.get(position).getDate());
        holder.isFinished().setFinished(itemList.get(position).isFinished());*/
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}
