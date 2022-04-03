package com.weflors.controller;
 import com.weflors.entity;
import com.weflors.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import java.util.List;
import com.weflors.Interface.ContragentsServiceImpl;
import com.weflors.Interface.ProductServiceImpl;
import com.weflors.Interface.ProcurementServiceImpl;
import com.weflors.Interface.ProductStatusServiceImpl;
import com.weflors.DTO.ProductEntity;
import com.weflors.DTO.ProcurementEntity;
import com.weflors.DTO.ProductStatusEntity;
@Controller
public class AddProductController {

@Autowired
 private  ContragentsServiceImpl contragentsServiceImpl;

@Autowired
 private  ProductServiceImpl productService;

@Autowired
 private  ProcurementServiceImpl procurementRepository;

@Autowired
 private  ProductDetailsServiceImpl productDetailsService;

@Autowired
 private  ProductStatusServiceImpl productStatusService;

@Autowired
 private  ProductTypeServiceImpl productTypeServiceImpl;

 private  CheckForNull check;


public void saveProcurementEntity(ProductEntity saveProduct){
    for (ProcurementEntity procurementEntity : saveProduct.getProcurementsByProductId()) {
        procurementEntity.setProductId(saveProduct.getProductId());
        procurementRepository.saveProcurement(procurementEntity);
    }
}


@RequestMapping(value = "/addproduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ProductEntity addProduct(ProductEntity productEntity){
    ProductDetailsEntity productDetailsEntity = productEntity.getProductDetailsByProductId();
    productEntity.setProductDetailsByProductId(null);
    ProductEntity saveProduct = productService.saveProduct(productEntity);
    saveProcurementEntity(saveProduct);
    saveProductDetailsEntity(saveProduct, productDetailsEntity);
    saveProductStatus(saveProduct);
    return saveProduct;
}


@RequestMapping(value = { "/addproduct" }, method = RequestMethod.GET)
public String addProductPage(Model model,String name){
    model.addAttribute("name", name);
    List<ContragentsEntity> contragents = contragentsServiceImpl.findAllContragents();
    model.addAttribute("contragents", contragents);
    List<ProductTypesEntity> productTypeList = productTypeServiceImpl.findAllProductTypes();
    model.addAttribute("productTypeList", productTypeList);
    model.addAttribute("formName", "Добавить товар");
    return "addproduct";
}


public void saveProductDetailsEntity(ProductEntity saveProduct,ProductDetailsEntity productDetailsEntity){
    productDetailsEntity.setProductId(saveProduct.getProductId());
    productDetailsEntity = validateProductDetailsEntityForNulls(productDetailsEntity);
    productDetailsService.saveProductDetail(productDetailsEntity);
}


@RequestMapping(value = "/getContragentById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ContragentsEntity getContragentById(Integer contragentId){
    return contragentsServiceImpl.loadContragentByContragentID(contragentId);
}


public ProductDetailsEntity validateProductDetailsEntityForNulls(ProductDetailsEntity productDetailsEntity){
    productDetailsEntity.setHeight(check.checkForNull(productDetailsEntity.getHeight()));
    productDetailsEntity.setLength(check.checkForNull(productDetailsEntity.getLength()));
    productDetailsEntity.setWidth(check.checkForNull(productDetailsEntity.getWidth()));
    // if (productDetailsEntity.getHeight() == null) productDetailsEntity.setHeight(0);
    // if (productDetailsEntity.getLength() == null) productDetailsEntity.setLength(0);
    // if (productDetailsEntity.getWidth() == null) productDetailsEntity.setWidth(0);
    return productDetailsEntity;
}


public void saveProductStatus(ProductEntity saveProduct){
    for (ProductStatusEntity productStatusEntity : saveProduct.getProductStatusByProductId()) {
        productStatusEntity.setProductId(saveProduct.getProductId());
        productStatusService.saveProductStatus(productStatusEntity);
    }
}


}