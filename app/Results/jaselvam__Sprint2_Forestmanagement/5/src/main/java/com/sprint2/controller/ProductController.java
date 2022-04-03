package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Product;
import com.sprint2.service.ProductService;
@Controller
@RequestMapping("/Product")
public class ProductController {

 private Logger logger;

@Autowired
 private  ProductService productService;


@GetMapping("/{productId}")
@ResponseBody
public Product getProductByproductId(Integer productId){
    logger.info("product service was instalized");
    return productService.getProductByproductId(productId);
}


@PostMapping("/")
@ResponseBody
public Product addProduct(Product Product){
    return productService.addProduct(Product);
}


@PutMapping("/{productId}")
@ResponseBody
public Product updateProduct(Product Product){
    return productService.updateProduct(Product);
}


@DeleteMapping("/{productId}")
@ResponseBody
public boolean deleteProductbyproductId(Integer productId){
    return productService.deleteProductbyproductId(productId);
}


@GetMapping("/")
@ResponseBody
public List<Product> getAllProduct(){
    List<Product> Productlist = productService.getAllProducts();
    return Productlist;
}


}