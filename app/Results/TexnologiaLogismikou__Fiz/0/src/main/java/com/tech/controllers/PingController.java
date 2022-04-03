package com.tech.controllers;
 import com.tech.controllers.superclass.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class PingController extends BaseController{


@RequestMapping(value = "/ping")
@ResponseBody
public String ping(){
    return "All good. You don't need to be authenticated to call this";
}


}