package com.macramicia.user;

import com.macramicia.BCryptEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final BCryptEncoderConfig encoderConfig;

    @Autowired
    public UserService(UserRepository userRepository, BCryptEncoderConfig encoderConfig) {
        this.userRepository = userRepository;
        this.encoderConfig = encoderConfig;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void save(User user) {
         if(!encoderConfig.isEncrypted(user.getPassword())) {
             String encryptedPw = encoderConfig.passwordEncoder().encode(user.getPassword());
             user.setPassword(encryptedPw);
         }
             userRepository.save(user);
    }
}