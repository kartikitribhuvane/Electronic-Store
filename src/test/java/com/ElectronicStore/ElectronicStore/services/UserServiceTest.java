package com.ElectronicStore.ElectronicStore.services;

import com.ElectronicStore.ElectronicStore.dtos.UserDto;
import com.ElectronicStore.ElectronicStore.entities.User;
import com.ElectronicStore.ElectronicStore.repositories.UserRepo;
import com.ElectronicStore.ElectronicStore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.relation.Role;
import java.util.Optional;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private UserService userService;

    User user;
    Role role;
    @BeforeEach
    public void init(){

        role=Role.builder().roleId("abc").roleName("NORMAL").build();

     user = User.builder()
             .name("Karti")
             .email("Karti@gmail,com")
             .about("This is testing create method")
             .gender("female")
             .imageName("abc.png")
             .password("lcwd")
             .roles(Set.of(role))
             .build();

      roleId"abc"

    }

@Test
    public void createUserTest(){

        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

        Mockito.when(roleRepo.findById(roleId)).thenReturn(Optional.of(role));

        UserDto user1 = userService.createUser(mapper.map(user,UserDto.class));
        System.out.println(user1.getName());

    Assertions.assertNotNull(user1);

    }
}
