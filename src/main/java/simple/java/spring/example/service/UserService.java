package simple.java.spring.example.service;

import simple.java.spring.example.entity.UserProfile;

public interface UserService {
    public UserProfile findByEmail(final String email);

    public void save(final UserProfile userProfile);
}