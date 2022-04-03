package com.example.smartkitchenbackend.controllers;
 import com.example.smartkitchenbackend.DTOs.Kitchen.AddKitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.security.CurrentUser;
import com.example.smartkitchenbackend.security.UserPrincipal;
import com.example.smartkitchenbackend.services.Converters.UserConverter;
import com.example.smartkitchenbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

 private  UserService userService;


@GetMapping("/me")
@PreAuthorize("hasRole('USER')")
public UserDTO getCurrentUser(UserPrincipal currentUser){
    return new UserDTO(currentUser.getId(), currentUser.getName(), currentUser.getEmail(), currentUser.getUsername());
}


@PostMapping("/addKitchen")
public KitchenDTO addKitchen(AddKitchenDTO addKitchenDTO){
    return userService.addKitchenToUser(addKitchenDTO.getUserId(), addKitchenDTO.getKitchenId());
}


@GetMapping("/list")
public List<UserDTO> getUser(){
    return userService.getUsers();
}


@GetMapping("/listKitchens/{userId}")
public List<KitchenDTO> getKitchensByUserId(long userId){
    return userService.getKitchensByUserId(userId);
}


}