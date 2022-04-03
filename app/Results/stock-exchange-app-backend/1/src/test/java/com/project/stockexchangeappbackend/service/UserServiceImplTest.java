package com.project.stockexchangeappbackend.service;


import com.project.stockexchangeappbackend.DTO.Role;
import com.project.stockexchangeappbackend.DTO.Tag;
import com.project.stockexchangeappbackend.DTO.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.project.stockexchangeappbackend.service.TagServiceImplTest.getTagsList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

   

    @BeforeEach
    void setup() {
        setUsersList();
    }



    private static List<User> users;

    public static List<User> getUsersList() {
        if (users == null) {
            setUsersList();
        }
        return users;
    }

    private static void setUsersList() {
        var tags = getTagsList();
        users = Arrays.asList(
                User.builder()
                        .id(1L).email("user@test").firstName("John").lastName("Kowal").password("password")
                        .money(BigDecimal.TEN).role(Role.USER).isActive(true).tag(tags.get(0))
                        .userStocks(new ArrayList<>())
                        .build(),
                User.builder()
                        .id(2L).email("user2@test").firstName("Jane").lastName("Kowal").password("password")
                        .money(BigDecimal.ZERO).role(Role.ADMIN).isActive(true).tag(tags.get(0))
                        .userStocks(new ArrayList<>())
                        .build(),
                User.builder()
                        .id(3L).email("user3@test").firstName("John").lastName("Kowal").password("password")
                        .money(BigDecimal.TEN).role(Role.USER).isActive(true).tag(tags.get(0))
                        .userStocks(new ArrayList<>())
                        .build());
    }

}
