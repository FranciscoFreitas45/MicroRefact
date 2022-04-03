package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsSupplierRepositoryController {

 private QmsSupplierRepository qmssupplierrepository;


@GetMapping
("/findQmsSupplierBySupplierCdAndFlagStatus")
public Optional<QmsSupplier> findQmsSupplierBySupplierCdAndFlagStatus(@RequestParam(name = "supplierCd") String supplierCd,@RequestParam(name = "flagStatus") String flagStatus){
  return qmssupplierrepository.findQmsSupplierBySupplierCdAndFlagStatus(supplierCd,flagStatus);
}


}