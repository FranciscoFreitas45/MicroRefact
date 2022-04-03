package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsEnclosureRepositoryController {

 private QmsEnclosureRepository qmsenclosurerepository;


@GetMapping
("/findByInspectionInfoId")
public List<QmsEnclosure> findByInspectionInfoId(@RequestParam(name = "bomTechnologyId") Integer bomTechnologyId){
  return qmsenclosurerepository.findByInspectionInfoId(bomTechnologyId);
}


@GetMapping
("/deleteByInspectionInfoId")
public Integer deleteByInspectionInfoId(@RequestParam(name = "valueOf") Integer valueOf){
  return qmsenclosurerepository.deleteByInspectionInfoId(valueOf);
}


@GetMapping
("/findByInspectionInfoIdIn")
public List<QmsEnclosure> findByInspectionInfoIdIn(@RequestParam(name = "groutId") List groutId){
  return qmsenclosurerepository.findByInspectionInfoIdIn(groutId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmsenclosurerepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return qmsenclosurerepository.deleteById(Object);
}


@GetMapping
("/findAllByInspectionInfoIdAndInspectionKbn")
public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(@RequestParam(name = "inspectionInfoId") Integer inspectionInfoId,@RequestParam(name = "inpectionKbn") String inpectionKbn){
  return qmsenclosurerepository.findAllByInspectionInfoIdAndInspectionKbn(inspectionInfoId,inpectionKbn);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return qmsenclosurerepository.delete(Object);
}


}