package upce.semprace.eshop.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.entity.Nakup;
import java.util.*;

@RestController
@CrossOrigin
public class NakupPlatbaController {

@Autowired
 private NakupPlatbaService nakupplatbaservice;


@GetMapping
("/Platba/{id}/Nakup/getNakup")
public Set<Nakup> getNakup(@PathVariable(name="id") Long id){
return nakupplatbaservice.getNakup(id);
}


@PutMapping
("/Platba/{id}/Nakup/setNakup")
public void setNakup(@PathVariable(name="id") Long id,@RequestBody Set<Nakup> nakup){
nakupplatbaservice.setNakup(id,nakup);
}


}