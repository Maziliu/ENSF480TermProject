package ENSF480TermProject.backend.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.RegisteredUser;

import java.util.Optional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByEmail(String email);
}
