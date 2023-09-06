package br.senai.sc.listable.utils;

import androidx.annotation.NonNull;
import br.senai.sc.listable.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GetUserFromFirebase {
    private static DatabaseReference databaseReference = ConfigurationFirebase.getFirebase();
    private static FirebaseAuth auth = ConfigurationFirebase.getFirebaseAuth();

    public interface UserCallback {
        void onUserLoaded(String idUser);
        void onError(DatabaseError error);
    }
    public static void get(UserCallback callback) {
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String user = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                    callback.onUserLoaded(user);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                callback.onError(error);
            }
        });
    }

}
