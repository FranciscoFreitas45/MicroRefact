package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsMaterielRepositoryController {

 private QmsMaterielRepository qmsmaterielrepository;


@GetMapping
("/findByIdAndFlagStatus")
public List<QmsMateriel> findByIdAndFlagStatus(@RequestParam(name = "valueOf") Long valueOf,@RequestParam(name = "string") String string){
  return qmsmaterielrepository.findByIdAndFlagStatus(valueOf,string);
}


@GetMapping
("/findByMaterielCdAndFlagStatus")
public List<QmsMateriel> findByMaterielCdAndFlagStatus(@RequestParam(name = "materielCd") String materielCd,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsmaterielrepository.findByMaterielCdAndFlagStatus(materielCd,flagStatus);
}


@GetMapping
("/findQmsMaterielByMaterielCdAndFlagStatus")
public Optional<QmsMateriel> findQmsMaterielByMaterielCdAndFlagStatus(@RequestParam(name = "materielCd") String materielCd,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsmaterielrepository.findQmsMaterielByMaterielCdAndFlagStatus(materielCd,flagStatus);
}


@GetMapping
("/findByUseUnitId")
public List<QmsMateriel> findByUseUnitId(@RequestParam(name = "s") Integer s){
  return qmsmaterielrepository.findByUseUnitId(s);
}


@GetMapping
("/findByPackgeUnitId")
public List<QmsMateriel> findByPackgeUnitId(@RequestParam(name = "s") Integer s){
  return qmsmaterielrepository.findByPackgeUnitId(s);
}


@GetMapping
("/findByMaterielCd")
public List<QmsMateriel> findByMaterielCd(@RequestParam(name = "s") String s){
  return qmsmaterielrepository.findByMaterielCd(s);
}


}