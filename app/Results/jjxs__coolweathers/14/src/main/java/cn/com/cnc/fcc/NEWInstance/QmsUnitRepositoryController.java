package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsUnitRepositoryController {

 private QmsUnitRepository qmsunitrepository;


@GetMapping
("/findByIdAndFlagStatus")
public List<QmsUnit> findByIdAndFlagStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "flag") String flag){
  return qmsunitrepository.findByIdAndFlagStatus(id,flag);
}


@GetMapping
("/findByUnitCd")
public List<QmsUnit> findByUnitCd(@RequestParam(name = "s") String s){
  return qmsunitrepository.findByUnitCd(s);
}


}