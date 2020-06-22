package com.gnwoo.authservice.handlers;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTHandler {
    private final String secret = "secret";
    private final Algorithm algorithm = Algorithm.HMAC256(secret);
    private final String issuer = "gnw-auth-service";

    public JWTHandler() {}

    public String consturctJWT (Long uuid) {
        String JWT_token;
        JWT_token = JWT.create()
                       .withIssuer(issuer)
                       .withClaim("uuid", uuid)
                       .withIssuedAt(new Date())
                       .sign(algorithm);
        return JWT_token;
    }

    public boolean verifyJWT(String JWT_token) {
        try
        {
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withIssuer(issuer)
                                      .build();
            DecodedJWT decoded_JWT_token = verifier.verify(JWT_token);
            return true;
        }
        catch (JWTVerificationException exception)
        {
            // Invalid signature/claims
            return false;
        }
    }
}
