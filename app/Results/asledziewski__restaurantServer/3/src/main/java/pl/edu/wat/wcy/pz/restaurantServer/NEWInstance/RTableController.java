package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RTableController {

 private RTableRepository rtablerepository;


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") String status){
 rtablerepository.setStatus(id,status);
}


}