package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        if (user.getWallets() != null) {
            for (Wallet wallet : user.getWallets()) {
                wallet.setUser(user); 
            }
        }
        return userRepository.save(user);
    }

    public List<Wallet> getWalletsByUserId(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        
        return user.getWallets(); 
    }

    @Transactional
    public User updateUser(long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với id: " + id));

        user.setFullName(userDetails.getFullName());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Không thể xóa vì không tìm thấy User id: " + id);
        }
        userRepository.deleteById(id);
    }
}
