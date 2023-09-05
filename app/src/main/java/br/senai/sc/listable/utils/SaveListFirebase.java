package br.senai.sc.listable.utils;

import com.google.firebase.database.DatabaseReference;

import br.senai.sc.listable.entity.ShoppingList;

public class SaveListFirebase {
    public static void save(ShoppingList list) {
        DatabaseReference lists = ConfigurationFirebase.getFirebase().child("lists");
        String id = GeneratorUUID.generate();
        list.setId(id);

        lists.child(id).setValue(list);
    }
}
