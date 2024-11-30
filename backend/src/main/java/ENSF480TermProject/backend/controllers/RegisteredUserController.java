package ENSF480TermProject.backend.controllers;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.dtos.user.PaymentCardDTO;
import ENSF480TermProject.backend.dtos.user.UserDetailsDTO;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Subscription;
import ENSF480TermProject.backend.services.user.RegisteredUserService;
import ENSF480TermProject.backend.services.user.SubscriptionService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    private final SubscriptionService subscriptionService;

    public RegisteredUserController(RegisteredUserService registeredUserService, SubscriptionService subscriptionService) {
        this.registeredUserService = registeredUserService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{userId:[0-9]+}/details")
    ResponseEntity<RegisteredUser> getUserDetails(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(registeredUserService.getUserDetails(userId).orElse(null));
    }
    
    @PutMapping(value = "/{userId:[0-9]+}/update-details", consumes = "application/json")
    public ResponseEntity<RegisteredUser> updateUserDetails(@PathVariable("userId") Long userId, @RequestBody UserDetailsDTO userDetailsDTO) {
        Optional<RegisteredUser> result = registeredUserService.updateUserDetails(userId, userDetailsDTO);

        return ResponseEntity.ok(result.get());
    }

    @DeleteMapping("/{userId:[0-9]+}/delete-user")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        Optional<String> result = registeredUserService.deleteUser(userId);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete user with ID " + userId));
    }

    @PostMapping("/{userId:[0-9]+}/add-payment-card")
    public ResponseEntity<RegisteredUser> addPaymentCard(@PathVariable("userId") Long userId, @RequestBody PaymentCardDTO paymentCardDTO){
        return ResponseEntity.ok(registeredUserService.addPaymentCard(userId, paymentCardDTO).orElse(null));
    }

    @DeleteMapping("/{userId:[0-9]+}/delete-payment-card/{cardId:[0-9]+}")
    public ResponseEntity<RegisteredUser> deletePaymentCard(@PathVariable("userId") Long userId, @PathVariable("cardId") Long cardId){
        return ResponseEntity.ok(registeredUserService.deletePaymentCard(userId, cardId).orElse(null));
    }

    @PutMapping("/{userId:[0-9]+}/renew-subscription")
    public ResponseEntity<Subscription> renewSubscription(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(subscriptionService.renewOrExtendSubscriptionManually(userId).orElse(null));
    }
}
