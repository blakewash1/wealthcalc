package com.blakewashington.wealthcalc;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FireStoreAuthTest {
    
    // test that the Google application credentials exist and are valid
    @Test
    public void testGoogleAplicationCredentials() {
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        assertNotNull(credentialsPath, "GOOGLE_APPLICATION_CREDENTIALS is not set");
        assertFalse(credentialsPath.isBlank(), "GOOGLE_APPLICATION_CREDENTIALS is blank");

        try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPath)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);
            assertNotNull(credentials, "Failed to load GoogleCredentials from file");

            // Try initializing FirestoreOptions to confirm validity
            FirestoreOptions options = FirestoreOptions.newBuilder()
                    .setCredentials(credentials)
                    .build();

            assertNotNull(options.getService(), "Firestore service could not be initialized");
        } catch (IOException e) {
            fail("Exception thrown while loading credentials: " + e.getMessage());
        }
    }
}
