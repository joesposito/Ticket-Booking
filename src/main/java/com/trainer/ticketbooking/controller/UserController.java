package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.LocalUserRequestDto;
import com.trainer.ticketbooking.dto.LocalUserResponseDto;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LocalUserResponseDto> createUser(@RequestBody LocalUserRequestDto localUserRequestDto){
        try {
            LocalUserResponseDto localUserResponseDto = userService.createUser(localUserRequestDto);
            log.info("New user registered with username: {}", localUserRequestDto.getUsername());
            return new ResponseEntity<>(localUserResponseDto, HttpStatus.CREATED);
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Gets user object using a user ID in the request path
    @GetMapping("/user/{userID}")
    public ResponseEntity<LocalUserResponseDto> getUser(@PathVariable long userID){
        try {
            LocalUserResponseDto localUserResponseDto = userService.getUser(userID);
            log.info("User successfully retrieved with ID: {}", userID);
            return new ResponseEntity<>(localUserResponseDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user/{userID}")
    public ResponseEntity<LocalUserResponseDto> updateUser(@PathVariable long userID, @RequestBody LocalUserRequestDto localUserRequestDto){
        try {
            LocalUserResponseDto localUserResponseDto = userService.updateUser(userID, localUserRequestDto);
            log.info("User successfully updated with ID: {}", userID);
            return new ResponseEntity<>(localUserResponseDto, HttpStatus.OK);
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
