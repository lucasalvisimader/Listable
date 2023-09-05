package br.senai.sc.listable.recycleView.viewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.senai.sc.listable.R;

public class ShoppingListItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView name = itemView.findViewById(R.id.shopping_list_item_name);
    private CheckBox checkBox = itemView.findViewById(R.id.shopping_list_item_checkbox);

    public ShoppingListItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
