package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersListCreditServiceController {

 private ICreepersListCreditService icreeperslistcreditservice;


@PutMapping
("/updateImageAndHtmlPath")
public void updateImageAndHtmlPath(@RequestParam(name = "loginName") String loginName,@RequestParam(name = "imagePath") String imagePath,@RequestParam(name = "filePath") String filePath){
icreeperslistcreditservice.updateImageAndHtmlPath(loginName,imagePath,filePath);
}


}