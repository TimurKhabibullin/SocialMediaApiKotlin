package ru.timur.socialmediaapi.api.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.timur.socialmediaapi.api.dto.AuthenticationDTO
import ru.timur.socialmediaapi.api.dto.PersonDTO
import ru.timur.socialmediaapi.config.security.JWTUtil
import ru.timur.socialmediaapi.core.model.PersonModel
import ru.timur.socialmediaapi.core.model.PersonRole
import ru.timur.socialmediaapi.core.service.RegistrationService
import ru.timur.socialmediaapi.core.utils.PersonValidator

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController(val registrationService: RegistrationService, val personValidator: PersonValidator, val jwtUtil: JWTUtil, val modelMapper: ModelMapper, val authenticationManager: AuthenticationManager) {
    @Operation(summary = "Регистрация")
    @PostMapping("/registration")
    fun performRegistration(
        @RequestBody personDTO: @Valid PersonDTO?,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        val personModel = convertToPerson(personDTO)
        personValidator.validate(personModel, bindingResult)
        if (bindingResult.hasErrors()) {
            val errors: MutableMap<String, String?> = HashMap()
            for (error in bindingResult.fieldErrors) {
                errors[error.field] = error.defaultMessage
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<Map<String, String?>>(errors)
        }
        registrationService.register(personModel)
        personModel.role = PersonRole.ROLE_USER.toString()
        val token = jwtUtil.generateToken(personModel.username!!)
        return ResponseEntity.status(HttpStatus.OK).body(java.util.Map.of("jwt-token", token))
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/login")
    fun performLogin(@RequestBody authenticationDTO: AuthenticationDTO): ResponseEntity<*> {
        val authInputToken = UsernamePasswordAuthenticationToken(
            authenticationDTO.username,
            authenticationDTO.password
        )
        try {
            authenticationManager.authenticate(authInputToken)
        } catch (e: BadCredentialsException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(java.util.Map.of("message", "Incorrect credentials!"))
        }
        val token = jwtUtil.generateToken(authenticationDTO.username!!)
        return ResponseEntity.status(HttpStatus.OK).body(java.util.Map.of("jwt-token", token))
    }

    fun convertToPerson(personDTO: PersonDTO?): PersonModel = modelMapper.map(personDTO, PersonModel::class.java)
}