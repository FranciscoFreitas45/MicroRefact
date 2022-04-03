package com.sprint2.service;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.model.Product;
public interface IProductService {


public Product getProductByproductId(Integer productId)
;

public List<Product> getAllProducts()
;

public Product addProduct(Product Product)
;

public Product updateProduct(Product Product)
;

public boolean deleteProductbyproductId(Integer productId)
;

}