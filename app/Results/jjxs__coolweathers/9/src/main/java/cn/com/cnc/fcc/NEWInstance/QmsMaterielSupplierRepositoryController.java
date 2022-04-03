package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsMaterielSupplierRepositoryController {

 private QmsMaterielSupplierRepository qmsmaterielsupplierrepository;


@GetMapping
("/findByMaterielId")
public List<QmsMaterielSupplier> findByMaterielId(@RequestParam(name = "s") Integer s){
  return qmsmaterielsupplierrepository.findByMaterielId(s);
}


@GetMapping
("/findBySupplierId")
public List<QmsMaterielSupplier> findBySupplierId(@RequestParam(name = "s") Integer s){
  return qmsmaterielsupplierrepository.findBySupplierId(s);
}


}