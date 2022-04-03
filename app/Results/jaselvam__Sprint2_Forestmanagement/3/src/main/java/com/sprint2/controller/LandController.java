package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Land;
@Controller
@RequestMapping("/Land")
public class LandController {

 private Logger logger;

@Autowired
 private  com.sprint2.service.LandService LandService;


@GetMapping("/{landId}")
@ResponseBody
public Land getLandBylandId(Integer landId){
    logger.info("land service was instalized");
    return LandService.getLandBylandId(landId);
}


@DeleteMapping("/{landId}")
@ResponseBody
public boolean deleteLandbylandId(Integer landId){
    return LandService.deleteLandbylandId(landId);
}


@PostMapping("/")
@ResponseBody
public Land addLand(Land Land){
    return LandService.addLand(Land);
}


@PutMapping("/")
@ResponseBody
public Land updateLand(Land Land){
    return LandService.updateLand(Land);
}


@GetMapping("/")
@ResponseBody
public List<Land> getAllLand(){
    List<Land> Landlist = LandService.getAllLands();
    return Landlist;
}


}