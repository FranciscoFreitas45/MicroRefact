package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsBomTechnologyRepositoryController {

 private QmsBomTechnologyRepository qmsbomtechnologyrepository;


@GetMapping
("/findByProcessId")
public List<QmsBomTechnology> findByProcessId(@RequestParam(name = "s") Integer s){
  return qmsbomtechnologyrepository.findByProcessId(s);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return qmsbomtechnologyrepository.findById(Object);
}


@GetMapping
("/findByMaterielIdAndIsDefaultAndFlagStatus")
public List<QmsBomTechnology> findByMaterielIdAndIsDefaultAndFlagStatus(@RequestParam(name = "materielId") Integer materielId,@RequestParam(name = "isDefault") String isDefault,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsbomtechnologyrepository.findByMaterielIdAndIsDefaultAndFlagStatus(materielId,isDefault,flagStatus);
}


@GetMapping
("/findByMaterielId")
public List<QmsBomTechnology> findByMaterielId(@RequestParam(name = "s") Integer s){
  return qmsbomtechnologyrepository.findByMaterielId(s);
}


}