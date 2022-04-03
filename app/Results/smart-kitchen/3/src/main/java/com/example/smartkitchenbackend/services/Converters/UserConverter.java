package com.example.smartkitchenbackend.services.Converters;
 import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.entities.User;
import lombok.experimental.UtilityClass;
@UtilityClass
public class UserConverter {


public UserDTO toDTO(User user){
    return UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).userName(user.getUsername()).build();
}


}