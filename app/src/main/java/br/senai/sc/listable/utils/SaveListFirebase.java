package br.senai.sc.listable.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;

public class SaveListFirebase {
    public static void save(ShoppingList list) {
        DatabaseReference lists = ConfigurationFirebase.getFirebase().child("lists");
        String id = GeneratorUUID.generate();
        list.setId(id);

        lists.child(id).setValue(list);
    }

    public static void save(Item item, ShoppingList shoppingList) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference list = reference.child("lists").child(shoppingList.getId());
        shoppingList.addItem(item);

        list.setValue(shoppingList);
    }

    public static void editCheckbox(ShoppingList shoppingList, int position) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference list = reference.child("lists").child(shoppingList.getId());
        shoppingList.getItems().get(position).setFinished(!shoppingList.getItems().get(position).isFinished());

        list.setValue(shoppingList);
    }
}
