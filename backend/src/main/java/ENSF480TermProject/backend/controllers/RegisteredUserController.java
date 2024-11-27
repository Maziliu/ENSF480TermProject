package ENSF480TermProject.backend.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.dtos.user.UserDetailsDTO;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.services.user.RegisteredUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("/{userId:[0-9]+}/details")
    ResponseEntity<RegisteredUser> getUserDetails(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(registeredUserService.getUserDetails(userId).orElse(null));
    }
    
    @PutMapping(value = "/{userId:[0-9]+}/update-details", consumes = "application/json")
    public ResponseEntity<String> updateUserDetails(@PathVariable("userId") Long userId, @RequestBody UserDetailsDTO userDetailsDTO) {
        Optional<String> result = registeredUserService.updateUserDetails(userId, userDetailsDTO);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " not found."));
    }

}