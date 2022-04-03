package com.webapp.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.models.User;
@RestController
@CrossOrigin
public class UserWordController {

@Autowired
 private UserWordService userwordservice;


}