package br.senai.sc.listable.recycleView.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.viewHolder.ShoppingListItemsViewHolder;
import br.senai.sc.listable.utils.SaveListFirebase;

public class AdapterShoppingListItems extends RecyclerView.Adapter<ShoppingListItemsViewHolder> {

    private final Context context;
    private final List<Item> itemList;
    private final ShoppingList shoppingList;

    public AdapterShoppingListItems(Context context, List<Item> itemList, ShoppingList shoppingList) {
        this.context = context;
        this.itemList = itemList;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ShoppingListItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View shoppingListItem = LayoutInflater.from(context).inflate(R.layout.fragment_shopping_list_item, parent, false);
        return new ShoppingListItemsViewHolder(shoppingListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListItemsViewHolder holder, int position) {
        AtomicBoolean isFinished = new AtomicBoolean(itemList.get(position).isFinished());
        holder.getCheckBox().setOnClickListener(v -> isFinished.set(onClickCheckboxIsFinished(itemList, position, shoppingList)));

        String name = itemList.get(position).getName();

        itemList.get(position).setName(name);
        itemList.get(position).setFinished(isFinished.get());

        holder.getName().setText(name);
        holder.getCheckBox().setChecked(isFinished.get());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private boolean onClickCheckboxIsFinished(List<Item> itemList, int position, ShoppingList shoppingList) {
        SaveListFirebase.editCheckbox(shoppingList, position);
        return !itemList.get(position).isFinished();
    }
}
