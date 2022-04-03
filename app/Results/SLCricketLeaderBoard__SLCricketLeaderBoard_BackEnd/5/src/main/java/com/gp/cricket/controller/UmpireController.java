package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Umpire;
import com.gp.cricket.entity.User;
import com.gp.cricket.service.UmpireService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class UmpireController {

@Autowired
 private UmpireService umpireService;


@PostMapping("/umpireRegister")
public Umpire saveStudent(User user){
    System.out.println(user);
    return umpireService.saveUmpire(user);
}


@GetMapping("/umpires")
public List<Umpire> getAllUmpires(){
    return this.umpireService.getAllUmpires();
}


@GetMapping("umpire/available")
public List<Umpire> getAvailableUmpires(){
    return umpireService.getAvailableUmpires();
}


@GetMapping("umpire/{id}")
public Umpire getAvailableUmpire(Integer id){
    return umpireService.getAvailableUmpire(id);
}


}