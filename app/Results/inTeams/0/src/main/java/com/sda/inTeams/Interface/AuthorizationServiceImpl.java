package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.model.Project.*;
import java.security.Principal;
import java.util.*;
import com.sda.inTeams.model.Comment.*;
import com.sda.inTeams.model.Task.*;


import com.sda.inTeams.DTO.*;

public class AuthorizationServiceImpl implements AuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean isUserEligibleToSeeProjectDetails(Principal principal,Project project){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToSeeProjectDetails"))
    .queryParam("principal",principal)
    .queryParam("project",project)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}

 public boolean isUserEligibleToEditComment(Principal principal,Comment commentToEdit ){
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToEditComment"))
    .queryParam("principal",principal)
    .queryParam("commentToEdit",commentToEdit)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;

   }

   public boolean isUserAdmin(Principal principal){
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserAdmin"))
    .queryParam("principal",principal);  
    boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
   }


   public Optional<User> getUserCredentials(Principal principal) {
     UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserCredentials"))
    .queryParam("principal",principal);  
    Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
   }

   public boolean isUserEligibleToSeeTaskDetails(Principal principal, Task task){
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToSeeTaskDetails"))
    .queryParam("principal",principal)
    .queryParam("task",task)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
   }


  public boolean isUserEligibleToDeleteComment(Principal principal, Comment comment){
     UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToDeleteComment"))
    .queryParam("principal",principal)
    .queryParam("comment",comment)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
  }


}