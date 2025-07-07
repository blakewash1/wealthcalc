package com.blakewashington.wealthcalc.repository;

import ch.qos.logback.core.BasicStatusManager;
import com.blakewashington.wealthcalc.model.PlanDocument;
import com.blakewashington.wealthcalc.model.PlanRequest;
import com.blakewashington.wealthcalc.model.PlanResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class PlanRepository {

    private final Firestore firestore;

    public PlanRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public void save(PlanRequest planRequest, PlanResponse planResponse) {
        PlanDocument planDocument = new PlanDocument(planRequest, planResponse, Instant.now());

        firestore.collection("plans").add(planDocument);
    }

    public List<PlanDocument> findAll() {
        List<PlanDocument> planDocuments = new ArrayList<>();
        CollectionReference plansRef = firestore.collection("plans");

        try {
            ApiFuture<QuerySnapshot> future = plansRef.get();
            List<QueryDocumentSnapshot> docs = future.get().getDocuments();

            for (QueryDocumentSnapshot doc : docs) {
                PlanDocument plan = doc.toObject(PlanDocument.class);
                planDocuments.add(plan);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve plans from Firestore", e);
        }

        return planDocuments;
    }
}
