package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductStatusServiceImplController {

 private ProductStatusServiceImpl productstatusserviceimpl;


@GetMapping
("/saveProductStatus")
public ProductStatusEntity saveProductStatus(@RequestParam(name = "productStatusEntity") ProductStatusEntity productStatusEntity){
  return productstatusserviceimpl.saveProductStatus(productStatusEntity);
}


@GetMapping
("/findProductStatusEntity")
public ProductStatusEntity findProductStatusEntity(@RequestParam(name = "productId") Integer productId){
  return productstatusserviceimpl.findProductStatusEntity(productId);
}


@PutMapping
("/updateQuantityShopSaleAndWarehouse")
public void updateQuantityShopSaleAndWarehouse(@RequestParam(name = "productId") Integer productId,@RequestParam(name = "quantityShopSale") Integer quantityShopSale){
productstatusserviceimpl.updateQuantityShopSaleAndWarehouse(productId,quantityShopSale);
}


@PutMapping
("/updateQuantityWriteoffAndWarehouse")
public void updateQuantityWriteoffAndWarehouse(@RequestParam(name = "productId") Integer productId,@RequestParam(name = "quantityWriteoff") Integer quantityWriteoff){
productstatusserviceimpl.updateQuantityWriteoffAndWarehouse(productId,quantityWriteoff);
}


}