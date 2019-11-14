package com.demo.demo.services.impl;

import com.demo.demo.dao.UserRepository;
import com.demo.demo.model.User;
import com.demo.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
       return userRepository.getUsers();
    };
}
