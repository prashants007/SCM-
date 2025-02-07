package com.scm.scm.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.User;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    } 

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());
        existingUser.setProfiePic(user.getProfiePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setProviders(user.getProviders());
        existingUser.setProviderUserId(user.getProviderUserId());
         userRepo.save(existingUser);

        return Optional.ofNullable(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String id) {

        return userRepo.findById(id).isPresent();
    }

    @Override
    public boolean isUserExistByEmail(String email) {
            return userRepo.findByEmail(email).isPresent();
       }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
