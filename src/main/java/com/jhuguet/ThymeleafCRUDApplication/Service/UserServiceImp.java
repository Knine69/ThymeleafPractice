package com.jhuguet.ThymeleafCRUDApplication.Service;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if(!optional.isPresent()) throw new RuntimeException("User not found");
        User user = optional.get();
        return user;
    }

    @Override
    public void updateUser(User user) {
        User userInDB = getUserById(user.getId());
        //Update fields in DB object
        userInDB.setEmail(user.getEmail());
        userInDB.setLastName(user.getLastName());
        userInDB.setFirstName(user.getFirstName());
        try{
            userRepository.save(userInDB);
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("User " + user.getId() + " successfully updated!");
    }
}
