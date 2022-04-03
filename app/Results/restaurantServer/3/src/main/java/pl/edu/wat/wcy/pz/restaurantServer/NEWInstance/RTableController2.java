package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import pl.edu.wat.wcy.pz.restaurantServer.repository.RTableRepository;
@RestController
@CrossOrigin
public class RTableController2 {

 private RTableRepository rtablerepository;


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") String status){
 rtablerepository.setStatus(id,status);
}


}