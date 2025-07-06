package com.blakewashington.wealthcalc.repository;

import com.blakewashington.wealthcalc.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    private final Firestore firestore;

    public UserRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public void save(User user) {
        firestore.collection("users").document(user.getId()).set(user);
    }

    public User findByEmail(String email) {
        CollectionReference users = firestore.collection("users");
        Query query = users.whereEqualTo("email", email).limit(1);
        try {
            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot snapshot = future.get();
            if (snapshot.isEmpty()) return null;
            return snapshot.getDocuments().getFirst().toObject(User.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error querying user by email", e);
        }
    }
}
