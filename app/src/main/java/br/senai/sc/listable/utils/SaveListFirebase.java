package br.senai.sc.listable.utils;

import br.senai.sc.listable.entity.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.senai.sc.listable.entity.ShoppingList;

public class SaveListFirebase {
    public static void save(String name, User user) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference lists = reference.child("lists");
        String id = GeneratorUUID.generate();

        ShoppingList list = new ShoppingList(id, name, user);

        lists.child(id).setValue(list);
    }
}
