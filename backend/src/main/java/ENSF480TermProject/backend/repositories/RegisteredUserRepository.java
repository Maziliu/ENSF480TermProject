package ENSF480TermProject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.RegisteredUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByEmail(String email);

    @Query("SELECT ru.theatreCredits FROM RegisteredUser ru WHERE ru.userId = :user_id")
    Optional<Integer> findTheatreCreditsById(@Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE RegisteredUser ru SET ru.theatreCredits = ru.theatreCredits - :amount WHERE ru.userId = :user_id AND ru.theatreCredits >= :amount")
    int subtractTheatreCredits(@Param("user_id") Long userId, @Param("amount") int amount);
    
    @Modifying
    @Transactional
    @Query("UPDATE RegisteredUser ru SET ru.theatreCredits = ru.theatreCredits + :amount WHERE ru.userId = :user_id")
    int addTheatreCredits(@Param("user_id") Long userId, @Param("amount") int amount);
}
