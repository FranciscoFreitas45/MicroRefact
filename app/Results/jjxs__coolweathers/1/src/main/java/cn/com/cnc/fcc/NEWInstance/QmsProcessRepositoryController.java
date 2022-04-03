package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsProcessRepositoryController {

 private QmsProcessRepository qmsprocessrepository;


@GetMapping
("/findByProcessCdAndFlagStatus")
public QmsProcess findByProcessCdAndFlagStatus(@RequestParam(name = "processCd") String processCd,@RequestParam(name = "string") String string){
  return qmsprocessrepository.findByProcessCdAndFlagStatus(processCd,string);
}


@GetMapping
("/findByFlagStatusAndProcessCd")
public List<QmsProcess> findByFlagStatusAndProcessCd(@RequestParam(name = "string") String string,@RequestParam(name = "processCd") String processCd){
  return qmsprocessrepository.findByFlagStatusAndProcessCd(string,processCd);
}


@GetMapping
("/findByFlagStatusAndId")
public QmsProcess findByFlagStatusAndId(@RequestParam(name = "string") String string,@RequestParam(name = "valueOf") Long valueOf){
  return qmsprocessrepository.findByFlagStatusAndId(string,valueOf);
}


@GetMapping
("/findByIdAndFlagStatus")
public List<QmsProcess> findByIdAndFlagStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "string") String string){
  return qmsprocessrepository.findByIdAndFlagStatus(id,string);
}


}