package com.yalcin.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.repository.ProductRepository;
import com.yalcin.entity.Product;
@Service
public class ProductShowcaseService {

@Autowired
 private ProductRepository productrepository;


public void setProduct(Integer id,Product product){
productrepository.setProduct(id,product);
}


public Product getProduct(Integer id){
return productrepository.getProduct(id);
}


}