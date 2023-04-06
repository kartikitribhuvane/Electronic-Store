package com.ElectronicStore.ElectronicStore.service.impl;

import com.ElectronicStore.ElectronicStore.dtos.PageableResponse;
import com.ElectronicStore.ElectronicStore.dtos.UserDto;
import com.ElectronicStore.ElectronicStore.entities.User;
import com.ElectronicStore.ElectronicStore.exceptions.ResourceNotFoundException;
import com.ElectronicStore.ElectronicStore.helper.Helper;
import com.ElectronicStore.ElectronicStore.repositories.UserRepo;
import com.ElectronicStore.ElectronicStore.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.image.path}")
    private String ImagePath;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDto createUser(UserDto userDto) {

        // generate unique id in string format
       String userId = UUID.randomUUID().toString();
       userDto.setUserId(userId);
        //encoding password
        userDto.setPassword("passwordEncoder.encode(userDto.getPassword())");
        // dto->entity
        User user= dtoToEntity(userDto);

        //fetch role of normal and set it to user
        Role role = roleRepo.findById(normalRoleId).get();
        User.getRoles().add(role);
         User savedUser = userRepo.save (user);
         // entity-> dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
       User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with given Id"));
       user.setName(userDto.getName());
       // email update
       user.setAbout(userDto.getAbout());
       user.setGender(userDto.getGender());
       user.setPassword(userDto.getPassword());
       user.setImageName(userDto.getImageName());
      // save data
      User updatedUser = userRepo.save(user);
      UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with given Id"));

      // delete user profile image
        String fullpath = ImagePath + user.getImageName();

        try {
            Path path = Paths.get(fullpath);
            Files.delete(path);
        }catch(NoSuchFileException ex){
            logger.info("User image not found in folder");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // delete User




        userRepo.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

       // pageNumber default starts from 0
       Pageable pageable=  PageRequest.of(pageNumber ,pageSize, sort);
       Page<User> page = userRepo.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page,UserDto.class);

        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with given email id"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found with given email id !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User>users = userRepo.findByNameContaining(keyword);
        List<UserDto> dtoList= users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private UserDto entityToDto(User savedUser) {

           //UserDto userDto = UserDto.builder()
               // .userId(savedUser.getUserId())
             //   .name(savedUser.getName())
               // .email(savedUser.getEmail())
               // .password(savedUser.getPassword())
                //.about(savedUser.getAbout())
                //.gender(savedUser.getAbout())
                //.imageName(savedUser.getImageName())
                //.build();

           return mapper.map(savedUser, UserDto.class);
    }

    private User  dtoToEntity(UserDto userDto) {

         //User user = User.builder()
               // .userId(userDto.getUserId())
               // .name(userDto.getName())
               // .email(userDto.getEmail())
             //   .password(userDto.getEmail())
              //  .about(userDto.getAbout())
              //  .gender(userDto.getGender())
              //  .imageName(userDto.getImageName())
             //    .build();
        return mapper.map(userDto,User.class);
    }



}
