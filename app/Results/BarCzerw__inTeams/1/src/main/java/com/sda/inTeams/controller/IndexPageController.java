package com.sda.inTeams.controller;
 import com.sda.inTeams.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexPageController {

 private  AuthorizationService authorizationService;


@GetMapping()
public String getIndexPage(Principal principal){
    if (authorizationService.getUserCredentials(principal).isEmpty()) {
        return "index";
    } else {
        return "authenticated-page";
    }
}


}