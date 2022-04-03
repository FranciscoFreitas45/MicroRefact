package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDicRepositoryController {

 private SysDicRepository sysdicrepository;


@GetMapping
("/findByCode")
public SysDic findByCode(@RequestParam(name = "code") String code){
  return sysdicrepository.findByCode(code);
}


@GetMapping
("/findByDicid")
public Page<SysDic> findByDicid(@RequestParam(name = "id") String id,@RequestParam(name = "paramPageable") Pageable paramPageable){
  return sysdicrepository.findByDicid(id,paramPageable);
}


@GetMapping
("/findById")
public SysDic findById(@RequestParam(name = "id") String id){
  return sysdicrepository.findById(id);
}


@GetMapping
("/findByParentid")
public List<SysDic> findByParentid(@RequestParam(name = "type") String type){
  return sysdicrepository.findByParentid(type);
}


}