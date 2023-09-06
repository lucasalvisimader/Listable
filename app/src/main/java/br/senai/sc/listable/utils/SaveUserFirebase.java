package br.senai.sc.listable.utils;

import br.senai.sc.listable.entity.User;
import com.google.firebase.database.DatabaseReference;

public class SaveUserFirebase {
    public static void save(String nickname, String email) {
        DatabaseReference lists = ConfigurationFirebase.getFirebase().child("users");
        String id = GeneratorUUID.generate();

        User user = new User(id, nickname, email);

        lists.child(id).setValue(user);
    }
}
