package br.senai.sc.listable.recycleView.viewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.senai.sc.listable.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView name = itemView.findViewById(R.id.fragment_add_item_name);
    private boolean isOnShoppingList;
    private Button isOnShoppingListButton = itemView.findViewById(R.id.fragment_add_item_button);

    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ItemsViewHolder(@NonNull View itemView, TextView name, boolean isOnShoppingList) {
        super(itemView);
        this.name = name;
        this.isOnShoppingList = isOnShoppingList;
        if (isOnShoppingList) {
            isOnShoppingListButton.setBackgroundResource(R.drawable.add_item);
        }
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public boolean isOnShoppingList() {
        return isOnShoppingList;
    }

    public void setOnShoppingList(boolean onShoppingList) {
        isOnShoppingList = onShoppingList;
    }
}
