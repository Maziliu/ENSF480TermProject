package ENSF480TermProject.backend.services.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.database.RegisteredUserRepository;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.utils.auth.AuthenticatedUser;

@Service
public class Authenticator {
    private final RegisteredUserRepository userRepository;

    public Authenticator(RegisteredUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String email, String password){
        userRepository.save(new RegisteredUser(email, password));
    }

    public Optional<AuthenticatedUser> authenticateUser(String email, String password) {
        Optional<RegisteredUser> registeredUser = userRepository.findByEmail(email);

        if (registeredUser.isPresent() && password.equals(registeredUser.get().getPassword())) {
            boolean isAdmin = userRepository.checkIfAdmin(registeredUser.get().getUserId());
            return Optional.of(new AuthenticatedUser(registeredUser.get(), isAdmin));
        }

        return Optional.empty();
    }

}
