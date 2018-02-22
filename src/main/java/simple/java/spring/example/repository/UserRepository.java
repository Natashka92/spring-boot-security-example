package simple.java.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simple.java.spring.example.entity.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmail(final String email);

}