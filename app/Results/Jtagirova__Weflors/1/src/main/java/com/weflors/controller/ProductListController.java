package com.weflors.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.weflors.entity.ProductEntity;
import com.weflors.service.ProductServiceImpl;
@Controller
@RequestMapping("/productslist")
public class ProductListController {

 private  ProductServiceImpl productServiceImpl;

@Autowired
public ProductListController(ProductServiceImpl productServiceImpl) {
    this.productServiceImpl = productServiceImpl;
}
@GetMapping
public String addProductListPage(Model model){
    List<ProductEntity> productList = productServiceImpl.findListOfProducts();
    model.addAttribute("formName", "Список товаров");
    model.addAttribute("productList", productList);
    return "productslist";
}


@DeleteMapping("/delete")
@ResponseBody
public String deleteProduct(ProductEntity productEntity){
    productServiceImpl.deleteProduct(productEntity.getProductId());
    return "Товар был удален";
}


}