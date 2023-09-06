package br.senai.sc.listable.recycleView.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.ListFormatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.pages.fragment.EditItemFragment;
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

        holder.itemView.setOnClickListener(v -> {
            showEditItemFragment(itemList.get(position), context);
        });

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

    private void showEditItemFragment(Item item, Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_edit_item);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
