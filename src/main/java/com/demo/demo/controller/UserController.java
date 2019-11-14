package com.demo.demo.controller;

import com.demo.demo.model.User;
import com.demo.demo.services.AuthorService;
import com.demo.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "get/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }


}
