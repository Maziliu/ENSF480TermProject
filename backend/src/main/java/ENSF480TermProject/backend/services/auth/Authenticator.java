package ENSF480TermProject.backend.services.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.auth.AuthenticatedUserDTO;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;

@Service
public class Authenticator {
    private final RegisteredUserRepository userRepository;

    public Authenticator(RegisteredUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisteredUser registerUser(String email, String password){
        return userRepository.save(new RegisteredUser(email, password));
    }

    public Optional<AuthenticatedUserDTO> authenticateUser(String email, String password) {
        Optional<RegisteredUser> registeredUser = userRepository.findByEmail(email);

        if (registeredUser.isPresent() && password.equals(registeredUser.get().getPassword())) {
            boolean isAdmin = registeredUser.get().isAdmin();
            return Optional.of(new AuthenticatedUserDTO(registeredUser.get(), isAdmin));
        }

        return Optional.empty();
    }

}
