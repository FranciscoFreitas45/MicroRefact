package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsQualityControlDetailsRepositoryController {

 private QmsQualityControlDetailsRepository qmsqualitycontroldetailsrepository;


@GetMapping
("/findByBomTechnologyIdAndFlagStatus")
public List<QmsQualityControlDetails> findByBomTechnologyIdAndFlagStatus(@RequestParam(name = "id") Integer id,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsqualitycontroldetailsrepository.findByBomTechnologyIdAndFlagStatus(id,flagStatus);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return qmsqualitycontroldetailsrepository.deleteById(Object);
}


@GetMapping
("/deleteByBomTechnologyId")
public Integer deleteByBomTechnologyId(@RequestParam(name = "valueOf") Integer valueOf){
  return qmsqualitycontroldetailsrepository.deleteByBomTechnologyId(valueOf);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return qmsqualitycontroldetailsrepository.saveAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmsqualitycontroldetailsrepository.save(Object);
}


@GetMapping
("/findByIdAndFlagStatus")
public QmsQualityControlDetails findByIdAndFlagStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "string") String string){
  return qmsqualitycontroldetailsrepository.findByIdAndFlagStatus(id,string);
}


}