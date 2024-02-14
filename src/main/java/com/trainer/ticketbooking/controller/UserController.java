package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    //Creates user using request body information
    @PostMapping("/create-user")
    public void createUser(@RequestBody LocalUserDto localUserDto){
        try {
            userService.createUser(localUserDto);
            log.info("New user registered with username: {}", localUserDto.getUsername());
        }catch(EntityExistsException e){
            log.error("Username already exists: {}", localUserDto.getUsername());
        }
    }

    //Gets user object using a user ID in the request path
    @GetMapping("/get-user/{userID}")
    public LocalUser getUser(@PathVariable long userID){
        try {
            LocalUser user = userService.getUser(userID);
            log.info("User successfully retrieved with ID: {}", userID);
            return user;
        }catch(EntityNotFoundException e){
            log.error("User ID does not exist: {}", userID);
            return null;
        }
    }

    @PatchMapping("/update-user/{userID}")
    public void updateTrain(@PathVariable long userID, @RequestBody LocalUserDto localUserDto){
        try {
            userService.updateUser(userID, localUserDto);
            log.info("User successfully updated with ID: {}", userID);
        }catch(EntityNotFoundException e){
            log.error(String.valueOf(e));
        }
    }

    //Deletes user object using a user ID in the request path
    @DeleteMapping("/delete-user/{userID}")
    public void deleteUser(@PathVariable long userID){
        try {
            userService.deleteUser(userID);
            log.info("user successfully deleted with ID: {}", userID);
        }catch(EntityNotFoundException e){
            log.error("user ID does not exist: {}", userID);
        }
    }
}
