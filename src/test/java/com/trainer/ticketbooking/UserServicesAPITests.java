package com.trainer.ticketbooking;

import com.trainer.ticketbooking.dto.LocalUserRequestDto;
import com.trainer.ticketbooking.dto.LocalUserResponseDto;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.mapper.LocalUserMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.LocalUserRepo;
import com.trainer.ticketbooking.service.AddressService;
import com.trainer.ticketbooking.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


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
        LocalUserRequestDto localUserRequestDto = new LocalUserRequestDto();
        localUserRequestDto.setUsername("Username");
        localUserRequestDto.setPassword("Password");
        localUserRequestDto.setFirstName("FName");
        localUserRequestDto.setLastName("LName");
        localUserRequestDto.setAddressLine1("Address1");
        localUserRequestDto.setAddressLine2("Address2");
        localUserRequestDto.setCity("City");
        localUserRequestDto.setCountry("Country");
        localUserRequestDto.setPostalCode("12345");

        System.out.println(localUserRequestDto);

        LocalUserResponseDto localUserResponseDto = userService.createUser(localUserRequestDto);

        System.out.println(localUserResponseDto);

        assertEquals(localUserRequestDto.getUsername(), localUserResponseDto.getUsername());
        assertEquals(localUserRequestDto.getPassword(), localUserResponseDto.getPassword());
        assertEquals(localUserRequestDto.getFirstName(), localUserResponseDto.getFirstName());
        assertEquals(localUserRequestDto.getLastName(), localUserResponseDto.getLastName());
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
