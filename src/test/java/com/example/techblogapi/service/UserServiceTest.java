package com.example.techblogapi.service;

import com.example.techblogapi.entity.Users;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

  /*  @Test
    @DisplayName("Test Find Single User Sucesss")
    void getSingleUserSuccess(){

        Users mockUser=new Users(1,"haseb@gmail.com","12345","Haseb","01789533586");
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        Users expectedUser=userService.getSingleUser(1);
        Assertions.assertSame(expectedUser,mockUser,"User should be same");

    }

    @Test
    @DisplayName("Test Find Single User Failed")
    void getSingleUserFailed(){

        Users mockUser=new Users(1,"haseb@gmail.com","12345","Haseb","01789533586");
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        Users expectedUser=userService.getSingleUser(1);
        //Assertions.assertEquals(1,expectedUser.getId(),"User is found, when it should not be");

    }*/

    @Test
    @DisplayName("Test Find All User Success")
    void getAllUser(){

        Users userOne=new Users(1,"haseb@gmail.com","12345","Haseb","01789533586");
        Users userTwo=new Users(1,"sakib@gmail.com","abcde","Sakib","01789533586");
        when(userRepository.findAll()).thenReturn(Arrays.asList(userOne,userTwo));
        List<Users>allUser=userService.getAllUser();
        System.out.println("As a "+allUser.size());
        Assertions.assertEquals(2,allUser.size(),"Expected 2 User");
    }


}
