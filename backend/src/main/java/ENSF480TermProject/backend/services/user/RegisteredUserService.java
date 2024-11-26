package ENSF480TermProject.backend.services.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.user.UserDetailsDTO;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public Optional<RegisteredUser> getUserDetails(Long userId){
        Optional<RegisteredUser> user = this.registeredUserRepository.findById(userId);
        return user;
    }

    public Optional<String> updateUserDetails(Long userId, UserDetailsDTO userDetailsDTO) {
        Optional<RegisteredUser> optionalUser = registeredUserRepository.findById(userId);
    
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
    
        RegisteredUser user = optionalUser.get();
        user.setFirstName(userDetailsDTO.getFirst_name());
        user.setLastName(userDetailsDTO.getLast_name());
        user.setEmail(userDetailsDTO.getEmail());
        user.setAddress(userDetailsDTO.getAddress());
    
        registeredUserRepository.save(user); 
    
        return Optional.of("User details updated successfully.");
    }
    
}
