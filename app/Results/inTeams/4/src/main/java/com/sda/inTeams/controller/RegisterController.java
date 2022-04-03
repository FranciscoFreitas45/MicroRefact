package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.model.dto.RegisterTeamDTO;
import com.sda.inTeams.model.dto.RegisterUserDTO;
import com.sda.inTeams.repository.TeamRepository;
import com.sda.inTeams.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sda.inTeams.Interface.TeamRepository;
@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

 private  RegisterService registerService;

 private  TeamRepository teamRepository;


@PostMapping("/team")
public String registerTeam(RegisterTeamDTO registerDTO){
    try {
        Team registeredTeam = registerService.registerNewTeamWithUser(registerDTO);
        return "redirect:/login/";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@PostMapping("/user")
public String registerUser(RegisterUserDTO registerDTO){
    try {
        User registeredUser = registerService.registerNewUserToExistingTeam(registerDTO);
        return "redirect:/login/";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


@GetMapping("/team")
public String getTeamRegisterForm(Model model){
    model.addAttribute("registerInfo", new RegisterTeamDTO());
    return "register-new-team";
}


@GetMapping("/user")
public String getUserRegisterForm(Model model,long teamId,String registerCode){
    try {
        validateRegistrationCode(teamId, registerCode);
        RegisterUserDTO regDto = new RegisterUserDTO();
        regDto.setTeamId(teamId);
        regDto.setRegisterCode(registerCode);
        model.addAttribute("registerInfo", regDto);
        return "register-new-user";
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


public void validateRegistrationCode(long teamId,String registrationCode){
    Team team = teamRepository.findById(teamId).orElseThrow(() -> new InvalidOperation("Team not found"));
    if (!team.getUniqueRegisterId().equals(registrationCode)) {
        throw new InvalidOperation("Registration code does not match!");
    }
}


}