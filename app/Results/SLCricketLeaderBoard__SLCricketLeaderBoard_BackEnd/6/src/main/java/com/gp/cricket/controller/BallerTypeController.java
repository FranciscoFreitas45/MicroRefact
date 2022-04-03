package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.BallerType;
import com.gp.cricket.service.BallerTypeService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class BallerTypeController {

@Autowired
 private BallerTypeService ballerTypeService;


@GetMapping("ballertype")
public List<BallerType> getBallerTypeList(){
    return ballerTypeService.getBallerTypeList();
}


}