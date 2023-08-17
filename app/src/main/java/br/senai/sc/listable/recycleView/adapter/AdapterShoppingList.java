package br.senai.sc.listable.recycleView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.viewHolder.ShoppingListViewHolder;

public class AdapterShoppingList extends RecyclerView.Adapter<ShoppingListViewHolder> {

    private final Context context;
    private final List<ShoppingList> shoppingList;

    public AdapterShoppingList(Context context, List<ShoppingList> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shoppingList = LayoutInflater.from(context).inflate(R.layout.shopping_list_fragment, parent, false);
        return new ShoppingListViewHolder(shoppingList);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        holder.getName().setText(shoppingList.get(position).getName());
        if (position == 0 || position == 1) {
            shoppingList.get(position).setTotal(10);
            shoppingList.get(position).setItemsDone(7);
        } else if (position == 2) {
            shoppingList.get(position).setTotal(10);
            shoppingList.get(position).setItemsDone(2);
        }

        Integer itemsDone = shoppingList.get(position).getItemsDone();
        Integer total = shoppingList.get(position).getTotal();
        String totalAndItemsDone = itemsDone + "/" + total;

        holder.getTotalAndItemsDone().setText(totalAndItemsDone);
        holder.getProgressBar().setProgress(total == 0 ? 0 : (int) ((double) itemsDone / total * 10));
        holder.getProgressBar().setMax(total);
        /*holder.getTotalAndItemsDone().setText(shoppingList.get(position).getTotal());
        holder.getCategory().setCategory(itemList.get(position).getCategory());
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
