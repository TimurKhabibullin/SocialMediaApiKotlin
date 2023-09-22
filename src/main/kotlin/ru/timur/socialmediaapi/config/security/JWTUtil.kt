package ru.timur.socialmediaapi.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*


@Component
class JWTUtil {
    @Value("\${jwt_secret}")
    val secret: String = ""

    fun generateToken(username: String): String{
        val expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
            .withSubject("User details")
            .withClaim("username", username)
            .withIssuedAt(Date())
            .withIssuer("spring-app")
            .withExpiresAt(expirationDate)
            .sign(Algorithm.HMAC256(secret));
    }

    @Throws(JWTVerificationException::class)
    fun validateTokenAndRetrieveClaim(token: String?): String {
        val verifier = JWT.require(Algorithm.HMAC256(secret))
            .withSubject("User details")
            .withIssuer("spring-app")
            .build()
        val jwt = verifier.verify(token)
        return jwt.getClaim("userName").asString()
    }
}