import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsMaterielTypeRepositoryController {

 private QmsMaterielTypeRepository qmsmaterieltyperepository;


@GetMapping
("/findByMaterielTypeCdAndFlagStatus")
public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(@RequestParam(name = "materielTypeCd") String materielTypeCd,@RequestParam(name = "string") String string){
  return qmsmaterieltyperepository.findByMaterielTypeCdAndFlagStatus(materielTypeCd,string);
}


@GetMapping
("/findByMaterielTypeCdAndFlagStatus")
public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(@RequestParam(name = "materielTypeCd") String materielTypeCd,@RequestParam(name = "string") String string){
  return qmsmaterieltyperepository.findByMaterielTypeCdAndFlagStatus(materielTypeCd,string);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmsmaterieltyperepository.save(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmsmaterieltyperepository.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return qmsmaterieltyperepository.findById(Object);
}


@GetMapping
("/findByMaterielTypeCdAndFlagStatus")
public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(@RequestParam(name = "materielTypeCd") String materielTypeCd,@RequestParam(name = "string") String string){
  return qmsmaterieltyperepository.findByMaterielTypeCdAndFlagStatus(materielTypeCd,string);
}


}