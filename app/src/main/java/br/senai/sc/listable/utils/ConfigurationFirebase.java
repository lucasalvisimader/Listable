package br.senai.sc.listable.utils;

import com.google.firebase.auth.FirebaseAuth;

public class ConfigurationFirebase {

    private static FirebaseAuth auth;
//    private static DatabaseReference firebaseRef;
//    private static StorageReference storage;

    public static FirebaseAuth getFirebaseAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

//    public static DatabaseReference getFirebase() {
//        if(firebaseRef == null) {
//            firebaseRef = FirebaseDatabase.getInstance().getReference();
//        }
//        return firebaseRef;
//    }

//    public static StorageReference getFirebaseStorage() {
//        if (storage == null) {
//            storage = FirebaseStorage.getInstance().getReference();
//        }
//        return storage;
//    }


}
