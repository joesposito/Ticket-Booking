package com.trainer.ticketbooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.ticketbooking.controller.UserController;
import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerAPITests {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private UserController userController;

    @Test
    public void createUser400Test() throws Exception{
        LocalUser localUser = new LocalUser();
        localUser.setFirstName("Donald");

        String requestBody = objectMapper.writeValueAsString(localUser);
        System.out.println(requestBody);
        mockMvc.perform(post("/user").contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

    @Test
    public void createUser200Test() throws Exception{
        LocalUserDto localUserDto = new LocalUserDto();
        localUserDto.setFirstName("FName");
        localUserDto.setLastName("LName");
        localUserDto.setAddressLine1("Address1");
        localUserDto.setAddressLine2("Address2");
        localUserDto.setCity("City");
        localUserDto.setCountry("Country");
        localUserDto.setPostalCode("12345");

        String requestBody = objectMapper.writeValueAsString(localUserDto);
        System.out.println(requestBody);
        mockMvc.perform(post("/user").contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }
}
