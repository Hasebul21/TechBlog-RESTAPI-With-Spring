package com.example.techblogapi.controller;

import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.exception.handler.EntityNotFoundExceptionHandler;
import com.example.techblogapi.security.JwtFilter;
import com.example.techblogapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {

    @MockBean
    private UserService mockUserService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private JwtFilter jwtFilter;

    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders
                .webAppContextSetup(context).addFilter(jwtFilter, "/*").build();
    }



    @Test
    @DisplayName("GET/users/1   Found")
    void getSingleUserSuccess() throws Exception{

        Users mockUser=new Users(1,"haseb@gmail.com","12345","Haseb","01789533586");
        when(mockUserService.getSingleUser(1)).thenReturn(mockUser);

        mockMvc.perform(get("/api/v1/users/{id}",1))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("haseb@gmail.com"))
                .andExpect(jsonPath("$.password").value("12345"))
                .andExpect(jsonPath("$.name").value("Haseb"))
                .andExpect(jsonPath("$.phone").value("01789533586"));

    }

    @Test
    @DisplayName("GET/users/1   NOT FOUND")
    void getSingleUserFailed() throws Exception{

        when(mockUserService.getSingleUser(1)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/v1/users/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT/users/1   SUCESSFUL")
    void updateUserSuccess() throws Exception{

        Users user=new Users(1,"haseb@gmail.com","12345","Haseb","01789533586");
        Users mockUser=new Users(1,"haseb@gmail.com","abcdef","Asif","01789533586");
        when(mockUserService.updateUser(1,user)).thenReturn(mockUser);

        mockMvc.perform(put("/api/v1/users/{id}",1)
                        .content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("haseb@gmail.com"))
                .andExpect(jsonPath("$.password").value("abcdef"))
                .andExpect(jsonPath("$.name").value("Asif"))
                .andExpect(jsonPath("$.phone").value("01789533586"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
