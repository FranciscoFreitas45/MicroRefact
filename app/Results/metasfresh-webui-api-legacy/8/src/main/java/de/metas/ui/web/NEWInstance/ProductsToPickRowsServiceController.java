package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductsToPickRowsServiceController {

 private ProductsToPickRowsService productstopickrowsservice;


@GetMapping
("/createProductsToPickRowsData")
public ProductsToPickRowsData createProductsToPickRowsData(@RequestParam(name = "packageableRow") PackageableRow packageableRow){
  return productstopickrowsservice.createProductsToPickRowsData(packageableRow);
}


}