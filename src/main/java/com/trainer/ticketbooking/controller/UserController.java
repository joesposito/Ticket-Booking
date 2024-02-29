package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    //Creates user using request body information
    @PostMapping("/user")
    public void createUser(@RequestBody LocalUserDto localUserDto){
        try {
            userService.createUser(localUserDto);
            log.info("New user registered with username: {}", localUserDto.getUsername());
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Gets user object using a user ID in the request path
    @GetMapping("/user/{userID}")
    public LocalUser getUser(@PathVariable long userID){
        try {
            LocalUser user = userService.getUser(userID);
            log.info("User successfully retrieved with ID: {}", userID);
            return user;
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user/{userID}")
    public void updateUser(@PathVariable long userID, @RequestBody LocalUserDto localUserDto){
        try {
            userService.updateUser(userID, localUserDto);
            log.info("User successfully updated with ID: {}", userID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes user object using a user ID in the request path
    @DeleteMapping("/user/{userID}")
    public void deleteUser(@PathVariable long userID){
        try {
            userService.deleteUser(userID);
            log.info("user successfully deleted with ID: {}", userID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
