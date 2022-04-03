package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BotigaRepositoryController {

 private BotigaRepository botigarepository;


@GetMapping
("/findById")
public Botiga findById(@RequestParam(name = "id") Integer id){
  return botigarepository.findById(id);
}


@GetMapping
("/findByOrganitzacioId")
public List<Botiga> findByOrganitzacioId(@RequestParam(name = "organitzacioId") Integer organitzacioId){
  return botigarepository.findByOrganitzacioId(organitzacioId);
}


}