package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.BatmanType;
import com.gp.cricket.service.BatmanTypeService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class BatmanTypeController {

@Autowired
 private BatmanTypeService batmanTypeService;


@GetMapping("batmantype")
public List<BatmanType> getBatmanTypeList(){
    return batmanTypeService.getBatmanTypeList();
}


}