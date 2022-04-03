package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsProductRepositoryController {

 private QmsProductRepository qmsproductrepository;


@GetMapping
("/findByProductNumAndMaterielId")
public List<QmsProduct> findByProductNumAndMaterielId(@RequestParam(name = "productNum") String productNum,@RequestParam(name = "materielId") Integer materielId){
  return qmsproductrepository.findByProductNumAndMaterielId(productNum,materielId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return qmsproductrepository.save(Object);
}


}