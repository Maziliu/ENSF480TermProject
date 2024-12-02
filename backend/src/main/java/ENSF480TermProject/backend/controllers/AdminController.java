package ENSF480TermProject.backend.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.services.user.RegisteredUserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final RegisteredUserService registeredUserService;

    public AdminController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @PutMapping("/toggle-admin")
    public ResponseEntity<RegisteredUser> toggleUserAdmin(@RequestParam Long userId){
        Optional<RegisteredUser> userOpt = registeredUserService.toggleUserAdmin(userId);

        if(userOpt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userOpt.get());
    }
}
