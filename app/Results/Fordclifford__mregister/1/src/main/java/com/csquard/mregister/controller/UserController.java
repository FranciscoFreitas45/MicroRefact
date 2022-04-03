package com.csquard.mregister.controller;
 import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.User;
import com.csquard.mregister.payload;
import com.csquard.mregister.repository.AgentRepository;
import com.csquard.mregister.repository.UserRepository;
import com.csquard.mregister.security.UserPrincipal;
import com.csquard.mregister.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import com.csquard.mregister.Interface.UserRepository;
import com.csquard.mregister.DTO.UserPrincipal;
@RestController
@RequestMapping("/api")
public class UserController {

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  AgentRepository agentRepository;


@GetMapping("/user/me")
public UserSummary getCurrentUser(UserPrincipal currentUser){
    UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    return userSummary;
}


@GetMapping("/user/checkUsernameAvailability")
public UserIdentityAvailability checkUsernameAvailability(String username){
    Boolean isAvailable = !userRepository.existsByUsername(username);
    return new UserIdentityAvailability(isAvailable);
}


@GetMapping("/user/checkEmailAvailability")
public UserIdentityAvailability checkEmailAvailability(String email){
    Boolean isAvailable = !userRepository.existsByEmail(email);
    return new UserIdentityAvailability(isAvailable);
}


@GetMapping("/users/{username}")
public UserProfile getUserProfile(String username){
    User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    long agentCount = agentRepository.countByCreatedBy(user.getId());
    // long salesAreaCount = agentRepository.countBySalesAreaId(user.getId());
    // long SalesRegionCount = agentRepository.countBySalesRegionId(user.)
    UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), agentCount);
    return userProfile;
}


}