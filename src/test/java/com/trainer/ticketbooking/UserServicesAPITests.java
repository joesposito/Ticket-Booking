package com.trainer.ticketbooking;

import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.mapper.LocalUserMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.LocalUserRepo;
import com.trainer.ticketbooking.service.AddressService;
import com.trainer.ticketbooking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class UserServicesAPITests {
    @Mock
    private AddressService addressService;
    @Spy
    private LocalUserMapper localUserMapper = Mappers.getMapper(LocalUserMapper.class);
    @Spy
    private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @Spy
    private LocalUserRepo localUserRepo;
    @Spy
    private AddressRepo addressRepo;
    @InjectMocks
    private UserService userService;

    @Test
    public void createUser200Test() {
        LocalUserDto localUserDto = new LocalUserDto();
        localUserDto.setUsername("Username");
        localUserDto.setPassword("Password");
        localUserDto.setFirstName("FName");
        localUserDto.setLastName("LName");
        localUserDto.setAddressLine1("Address1");
        localUserDto.setAddressLine2("Address2");
        localUserDto.setCity("City");
        localUserDto.setCountry("Country");
        localUserDto.setPostalCode("12345");

        System.out.println(localUserDto);

        LocalUser localUser = userService.createUser(localUserDto);

        System.out.println(localUser);

        assertEquals(localUserDto.getUsername(), localUser.getUsername());
        assertEquals(localUserDto.getPassword(), localUser.getPassword());
        assertEquals(localUserDto.getFirstName(), localUser.getFirstName());
        assertEquals(localUserDto.getLastName(), localUser.getLastName());
        //TODO - Get addressService unit testing mock working so I can test rest of service.
        /*assertEquals(localUserDto.getAddressLine1(), localUser.getAddress().getAddressLine1());
        assertEquals(localUserDto.getAddressLine2(), localUser.getAddress().getAddressLine2());
        assertEquals(localUserDto.getCity(), localUser.getAddress().getCity());
        assertEquals(localUserDto.getCountry(), localUser.getAddress().getCountry());
        assertEquals(localUserDto.getAddressLine1(), localUser.getAddress().getAddressLine1());
        assertEquals(localUserDto.getPostalCode(), localUser.getAddress().getPostalCode());
         */
    }
}
