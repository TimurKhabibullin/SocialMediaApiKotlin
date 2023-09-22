package ru.timur.socialmediaapi.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.timur.socialmediaapi.config.security.JWTUtil
import ru.timur.socialmediaapi.core.service.PersonDetailsService
import java.io.IOException

@Component
class JWTFilter(val personDetailsService: PersonDetailsService, val jwtUtil: JWTUtil) : OncePerRequestFilter() {
    @Value("\${jwt_secret}")
    val secret: String = ""


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = httpServletRequest.getHeader("Authorization")
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            val jwt = authHeader.substring(7)
            if (jwt.isBlank()) {
                httpServletResponse.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid JWT Token in Bearer Header"
                )
            } else {
                try {
                    val algorithm = Algorithm.HMAC256(secret)
                    val verifier = JWT.require(algorithm).build()
                    val decodedJWT = verifier.verify(jwt)
                    val usernameClaim = decodedJWT.getClaim("username")
                    val username = usernameClaim.asString()
                    val userDetails = personDetailsService.loadUserByUsername(username)
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.password,
                        userDetails.authorities
                    )
                    if (SecurityContextHolder.getContext().authentication == null) {
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                } catch (exc: JWTVerificationException) {
                    httpServletResponse.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT Token"
                    )
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }

}