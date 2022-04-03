package org.sdrc.childinfo.controller;
 import org.sdrc.childinfo.model.UserDetailsModel;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AdminController {

@Autowired
 private  StateManager stateManager;


@RequestMapping("/imageGalleyUpload")
public String redirectPage(){
    UserDetailsModel userDetailsModel = (UserDetailsModel) stateManager.getValue(Constants.USER_PRINCIPAL);
    if (userDetailsModel.getUserName().equals("admin"))
        return "cmsManagement";
    else
        return "403";
}


@RequestMapping("/newsLetter")
public String dashboard(){
    UserDetailsModel userDetailsModel = (UserDetailsModel) stateManager.getValue(Constants.USER_PRINCIPAL);
    if (userDetailsModel.getUserName().equals("admin"))
        return "newsLetterManagement";
    else
        return "403";
}


}