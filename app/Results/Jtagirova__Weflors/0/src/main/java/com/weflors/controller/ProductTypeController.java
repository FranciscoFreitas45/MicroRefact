package com.weflors.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.weflors.entity.ProductTypesEntity;
import com.weflors.service.ProductTypeServiceImpl;
@Controller
@RequestMapping("/typeproduct")
public class ProductTypeController {

 private  ProductTypeServiceImpl productTypeServiceImpl;

@Autowired
public ProductTypeController(ProductTypeServiceImpl productTypeServiceImpl) {
    this.productTypeServiceImpl = productTypeServiceImpl;
}
@DeleteMapping("/delete")
@ResponseBody
public String deleteProductType(ProductTypesEntity productTypesEntity){
    productTypeServiceImpl.deleteProductType(productTypesEntity);
    return "Категория товара была удалена из вашей базы данных";
}


@PostMapping("/add")
@ResponseBody
public String addProductType(ProductTypesEntity productTypesEntity){
    if (productTypeServiceImpl.findByProductName(productTypesEntity.getProductTypeName()).isPresent()) {
        return "Категория товара с таким именем уже существует в вашей базе данных";
    } else {
        productTypeServiceImpl.saveProductType(productTypesEntity);
        return "Новая категория товара добавлена в вашу базу данных";
    }
}


@PostMapping("/update")
@ResponseBody
public String updateProductType(ProductTypesEntity productTypesEntity){
    productTypeServiceImpl.updateProductType(productTypesEntity);
    if (productTypeServiceImpl.findByProductName(productTypesEntity.getProductTypeName()).isPresent()) {
        return "Категория товара была обновлена в вашей базе данных";
    } else {
        return "Ошибка обновления данных о категории товара";
    }
}


@GetMapping
public String addProductTypePage(Model model){
    model.addAttribute("formName", "Справочник Категории товара");
    return "typeproduct";
}


@GetMapping("/list")
@ResponseBody
public List<ProductTypesEntity> getListOfProductTypes(){
    return productTypeServiceImpl.findAllProductTypes();
}


}