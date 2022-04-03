package com.gp.cricket.controller;
 import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Manager;
import com.gp.cricket.entity.Stadium;
import com.gp.cricket.entity.User;
import com.gp.cricket.entity.Stadium;
import com.gp.cricket.service.StadiumService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class StadiumController {

@Autowired
 private StadiumService stadiumService;


@GetMapping("/stadiums")
public List<Stadium> getAllStadiums(){
    return this.stadiumService.getAllStadium();
}


@PostMapping("stadiumRegister")
public Stadium registerStadium(Stadium stadium){
    System.out.println(stadium);
    return stadiumService.registerStadium(stadium);
}


}