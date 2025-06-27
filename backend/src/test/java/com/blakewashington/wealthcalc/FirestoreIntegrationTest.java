package com.blakewashington.wealthcalc;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FirestoreIntegrationTest {

    private final Firestore firestore;

    @Autowired
    public FirestoreIntegrationTest(Firestore firestore) {
        this.firestore = firestore;
    }

    @Test
    public void testWriteAndReadFirestore() throws Exception {
        // Create test data
        Map<String, Object> testData = new HashMap<>();
        testData.put("testField", "Testing 123");
        testData.put("timestamp", System.currentTimeMillis());

        // Write to Firestore
        DocumentReference docRef = firestore.collection("test_collection").document("test_doc");
        ApiFuture<WriteResult> writeFuture = docRef.set(testData);
        WriteResult result = writeFuture.get();  // Block until write completes

        assertNotNull(result);
        System.out.println("Write time: " + result.getUpdateTime());

        // Optional: read it back
        String value = docRef.get().get().getString("testField");
        assertEquals("Testing 123", value);
    }
}
