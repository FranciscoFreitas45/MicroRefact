package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsMaterielDetailsRepositoryController {

 private QmsMaterielDetailsRepository qmsmaterieldetailsrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return qmsmaterieldetailsrepository.findById(Object);
}


}