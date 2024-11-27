package ENSF480TermProject.backend.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ENSF480TermProject.backend.dtos.auth.AuthenticatedUserDTO;
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
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
        Optional<AuthenticatedUserDTO> result = authenticator.authenticateUser(email, password);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestParam String email, @RequestParam String password) {
        Optional<AuthenticatedUserDTO> user = authenticator.authenticateUser(email, password);
        if (!user.isPresent()) {
            return ResponseEntity.ok(authenticator.registerUser(email, password));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new RegistrationResponse(false, "Registration failed: Email already in use")
        );
    }

    // These are classes to format response as JSON for frontend
    static class LoginResponse {
        private boolean authenticated, isAdmin;
        private String authMessage;

        public LoginResponse(boolean authenticated, String authMessage, boolean isAdmin) {
            this.authenticated = authenticated;
            this.authMessage = authMessage;
            this.isAdmin = isAdmin;
        }

        public boolean isAuthenticated() {
            return authenticated;
        }

        public String getAuthMessage() {
            return authMessage;
        }

        public boolean isAdmin(){
            return isAdmin;
        }
    }

    static class RegistrationResponse {
        private boolean success;
        private String message;

        public RegistrationResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
