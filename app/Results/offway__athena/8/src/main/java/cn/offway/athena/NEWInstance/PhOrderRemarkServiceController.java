package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderRemarkServiceController {

 private PhOrderRemarkService phorderremarkservice;


@GetMapping
("/save")
public List<PhOrderRemark> save(@RequestParam(name = "entities") List<PhOrderRemark> entities){
  return phorderremarkservice.save(entities);
}


@GetMapping
("/findAllByPage")
public Page<PhOrderRemark> findAllByPage(@RequestParam(name = "id") String id,@RequestParam(name = "page") Pageable page){
  return phorderremarkservice.findAllByPage(id,page);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "id") Long id){
phorderremarkservice.delete(id);
}


}