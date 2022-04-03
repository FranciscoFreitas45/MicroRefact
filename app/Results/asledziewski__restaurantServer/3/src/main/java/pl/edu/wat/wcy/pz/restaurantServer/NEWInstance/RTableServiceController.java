package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RTableServiceController {

 private RTableService rtableservice;


@GetMapping
("/getRTableById")
public Optional<RTable> getRTableById(@RequestParam(name = "id") Long id){
  return rtableservice.getRTableById(id);
}


@GetMapping
("/getRTablesBySize")
public List<RTable> getRTablesBySize(@RequestParam(name = "size") int size){
  return rtableservice.getRTablesBySize(size);
}


}