package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Product;
public interface IProductController {


@ResponseBody
public Product getProductByproductId(Integer productId)
;

@ResponseBody
public Product addProduct(Product Product)
;

@ResponseBody
public Product updateProduct(Product Product)
;

@ResponseBody
public boolean deleteProductbyproductId(Integer productId)
;

@ResponseBody
public List<Product> getAllProduct()
;

}