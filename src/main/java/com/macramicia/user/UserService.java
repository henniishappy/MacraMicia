package com.macramicia.user;

import com.macramicia.BCryptEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    public final UserRepository userRepository;
    private final BCryptEncoderConfig encoderConfig;

    @Autowired
    public UserService(UserRepository userRepository, BCryptEncoderConfig encoderConfig) {
        this.userRepository = userRepository;
        this.encoderConfig = encoderConfig;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void saveUser(User user) {

        String encryptedPw = encoderConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPw);

        userRepository.save(user);
    }

}