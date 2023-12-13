package com.seyrek.exchangeapp.services;

import com.seyrek.exchangeapp.entities.User;
import com.seyrek.exchangeapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUserById(int id, User newUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setName(newUser.getName());
            foundUser.setSurname(newUser.getSurname());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
