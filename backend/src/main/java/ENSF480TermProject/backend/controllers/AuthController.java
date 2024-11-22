package ENSF480TermProject.backend.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.services.auth.Authenticator;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final Authenticator authenticator;

    public AuthController(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<RegisteredUser> user = authenticator.authenticateUser(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
 
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password){
        Optional<RegisteredUser> user = authenticator.authenticateUser(password, password);
        if(!user.isPresent()){
            authenticator.registerUser(email, password);
            return ResponseEntity.ok("Registration Successful");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration Failed: Email already in use");
    }
}

