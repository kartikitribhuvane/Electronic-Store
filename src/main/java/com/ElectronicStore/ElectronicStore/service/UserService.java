package com.ElectronicStore.ElectronicStore.service;

import com.ElectronicStore.ElectronicStore.dtos.PageableResponse;
import com.ElectronicStore.ElectronicStore.dtos.UserDto;
import com.ElectronicStore.ElectronicStore.entities.User;

import java.util.List;

public interface UserService {

    // create
    UserDto createUser (UserDto userDto);

    // update
    UserDto updateUser(UserDto userDto,String userId);

    // delete
     void deleteUser(String userId);

    //get all users
     PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single users bt id
    UserDto getUserById(String userId);

    //get single user by email
    UserDto getUserByEmail(String email);

    //search user
    List<UserDto>searchUser(String keyword);

    // other user specific features




}
