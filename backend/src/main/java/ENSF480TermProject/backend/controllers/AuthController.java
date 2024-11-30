package ENSF480TermProject.backend.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ENSF480TermProject.backend.dtos.auth.CredentialsDTO;
import ENSF480TermProject.backend.dtos.auth.RegisterUserRequestDTO;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.services.auth.Authenticator;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final Authenticator authenticator;

    public AuthController(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CredentialsDTO credentialsDTO) {
        Optional<RegisteredUser> result = authenticator.authenticateUser(credentialsDTO);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        Optional<RegisteredUser> user = authenticator.authenticateUser(registerUserRequestDTO.getCredentials());
        if (!user.isPresent()) {
            return ResponseEntity.ok(authenticator.registerUser(registerUserRequestDTO));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
    }
}
