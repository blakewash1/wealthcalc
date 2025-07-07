package com.blakewashington.wealthcalc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Component
public class GoogleTokenVerifier {

    private static final String GOOGLE_ISSUER = "https://accounts.google.com";

    public DecodedJWT verify(String idToken) {
        try {
            DecodedJWT jwt = JWT.decode(idToken);

            String kid = jwt.getKeyId();
            URL url = new URL("https://www.googleapis.com/oauth2/v3/certs");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(url);

            JsonNode keys = root.get("keys");
            for (JsonNode key : keys) {
                if (key.get("kid").asText().equals(kid)) {
                    String n = key.get("n").asText();
                    String e = key.get("e").asText();

                    RSAPublicKey publicKey = buildPublicKey(n, e);

                    JWT.require(com.auth0.jwt.algorithms.Algorithm.RSA256(publicKey, null))
                            .withIssuer(GOOGLE_ISSUER)
                            .build()
                            .verify(idToken); // Throws if invalid

                    return jwt;
                }
            }

            throw new RuntimeException("Matching Google public key not found");

        } catch (Exception e) {
            throw new RuntimeException("Failed to verify Google ID token", e);
        }
    }

    private RSAPublicKey buildPublicKey(String n, String e) throws Exception {
        byte[] modulusBytes = Base64.getUrlDecoder().decode(n);
        byte[] exponentBytes = Base64.getUrlDecoder().decode(e);

        java.math.BigInteger modulus = new java.math.BigInteger(1, modulusBytes);
        java.math.BigInteger exponent = new java.math.BigInteger(1, exponentBytes);

        java.security.spec.RSAPublicKeySpec spec = new java.security.spec.RSAPublicKeySpec(modulus, exponent);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
