package com.Sunil.journalApp.controller;


import com.Sunil.journalApp.entity.User;
import com.Sunil.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping
        public List<User> getAllUsers(){
            return userService.findAll();
        }

        @PostMapping
        public void createUser(@RequestBody User user){
            userService.save(user);
        }

        @PutMapping("/{username}")
        public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
            User existingUser = userService.findByUsername(username);
            if(existingUser != null){
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                userService.save(existingUser);
                return ResponseEntity.ok("User updated successfully");
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


}
