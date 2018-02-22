package simple.java.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.java.spring.example.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(final String role);

}