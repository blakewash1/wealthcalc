package com.blakewashington.wealthcalc.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile("firestore") // only activate if profile is enabled
public class FirestoreConfig {

    @Bean
    public Firestore firestore() throws IOException {
        try {
            return FirestoreOptions.getDefaultInstance().getService();

        } catch (Exception ex) {
            System.err.println("Error initializing Firestore: " + ex.getMessage());
            throw new RuntimeException("Failed to initialize Firestore", ex);
        }
    }
}
