package br.senai.sc.listable.recycleView.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.viewHolder.ItemsViewHolder;

public class AdapterItems extends RecyclerView.Adapter<ItemsViewHolder> {
    private final Context context;
    private final List<Item> itemList;
    private final ShoppingList shoppingList;

    public AdapterItems(Context context, List<Item> itemList, ShoppingList shoppingList) {
        this.context = context;
        this.itemList = itemList;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.fragment_add_item, parent, false);
        return new ItemsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        TextView textView = holder.getName();
        textView.setText(itemList.get(position).getName());

        Drawable drawable = holder.itemView.getResources().getDrawable(R.drawable.add_item);
        List<ShoppingList> shoppingLists = itemList.get(position).getShoppingLists();

        if (shoppingLists != null && shoppingLists.size() != 0) {
            for (ShoppingList shoppingList : shoppingLists) {
                if (shoppingList == this.shoppingList) {
                    drawable = holder.itemView.getResources().getDrawable(R.drawable.remove_item);
                    Log.i("josivaldo", "entrei 1");
                }
                holder.getIsOnShoppingListButton().setBackground(drawable);
                Log.i("josivaldo", "entrei 2");
            }
            Log.i("josivaldo", "entrei 3");
            return;
        }

        Log.i("josivaldo", "entrei 4");
        holder.getIsOnShoppingListButton().setBackground(drawable);
        holder.getName().setText(itemList.get(position).getName());

//        if (itemList.get(position).getShoppingLists() != null &&
//                itemList.get(position).getShoppingLists().contains(shoppingList)) {
//            drawable = holder.itemView.getResources().getDrawable(R.drawable.remove_item);
//            Log.i("josivaldo", "entrei 1");
//        } else {
//            drawable = holder.itemView.getResources().getDrawable(R.drawable.add_item);
//            Log.i("josivaldo", "entrei 2");
//        }
//        Log.i("josivaldo", "saí");
//        holder.getIsOnShoppingListButton().setBackground(drawable);

//        holder.getName().setText(itemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
