package simple.java.spring.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simple.java.spring.example.entity.Role;
import simple.java.spring.example.entity.UserProfile;
import simple.java.spring.example.repository.RoleRepository;
import simple.java.spring.example.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserProfile findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(final UserProfile userProfile) {
        userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
        userProfile.setActive(0);
        final Role userRole = roleRepository.findByRole("ADMIN");
        userProfile.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(userProfile);
    }
}