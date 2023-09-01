package br.senai.sc.listable.recycleView.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
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
        View shoppingList = LayoutInflater.from(context).inflate(R.layout.fragment_shopping_list, parent, false);
        return new ShoppingListViewHolder(shoppingList);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        holder.getName().setText(shoppingList.get(position).getName());

        int total = shoppingList.get(position).getItems().size();
        int itemsDone = 0;

        for (Item item : shoppingList.get(position).getItems()) {
            if (item.isFinished()) {
                itemsDone++;
            }
        }

        shoppingList.get(position).setTotal(total);
        shoppingList.get(position).setItemsDone(itemsDone);

        String totalAndItemsDone = itemsDone + "/" + total;

        holder.getTotalAndItemsDone().setText(totalAndItemsDone);
        holder.getProgressBar().setProgress(itemsDone);
        holder.getProgressBar().setMax(total);
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }
}
