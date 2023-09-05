package br.senai.sc.listable.recycleView.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.senai.sc.listable.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView name = itemView.findViewById(R.id.fragment_add_item_name);
    private ImageButton isOnShoppingListButton = itemView.findViewById(R.id.fragment_add_item_button);

    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

//    public ItemsViewHolder(@NonNull View itemView, TextView name, boolean isOnShoppingList) {
//        super(itemView);
//        this.name = name;
//        this.isOnShoppingList = isOnShoppingList;
//
//        Drawable drawable;
//        if (!isOnShoppingList) {
//            drawable = itemView.getResources().getDrawable(R.drawable.add_item);
//        } else {
//            drawable = itemView.getResources().getDrawable(R.drawable.remove_item);
//        }
//        isOnShoppingListButton.setBackground(drawable);
//    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public ImageButton getIsOnShoppingListButton() {
        return isOnShoppingListButton;
    }

    public void setIsOnShoppingListButton(ImageButton isOnShoppingListButton) {
        this.isOnShoppingListButton = isOnShoppingListButton;
    }
}
