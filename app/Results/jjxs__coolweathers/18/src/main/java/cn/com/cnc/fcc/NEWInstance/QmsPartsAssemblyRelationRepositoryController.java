package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsPartsAssemblyRelationRepositoryController {

 private QmsPartsAssemblyRelationRepository qmspartsassemblyrelationrepository;


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return qmspartsassemblyrelationrepository.deleteById(Object);
}


@GetMapping
("/deleteByBomTechnologyId")
public Integer deleteByBomTechnologyId(@RequestParam(name = "valueOf") Integer valueOf){
  return qmspartsassemblyrelationrepository.deleteByBomTechnologyId(valueOf);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return qmspartsassemblyrelationrepository.saveAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmspartsassemblyrelationrepository.save(Object);
}


@GetMapping
("/findByIdAndFlagStatus")
public QmsPartsAssemblyRelation findByIdAndFlagStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "string") String string){
  return qmspartsassemblyrelationrepository.findByIdAndFlagStatus(id,string);
}


@GetMapping
("/findAllByFlagStatusAndBomTechnologyId")
public List<QmsPartsAssemblyRelation> findAllByFlagStatusAndBomTechnologyId(@RequestParam(name = "FlagStatus") String FlagStatus,@RequestParam(name = "bomTechnologyId") Integer bomTechnologyId){
  return qmspartsassemblyrelationrepository.findAllByFlagStatusAndBomTechnologyId(FlagStatus,bomTechnologyId);
}


}