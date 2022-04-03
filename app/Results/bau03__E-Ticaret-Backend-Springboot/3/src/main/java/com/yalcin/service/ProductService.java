package com.yalcin.service;
 import com.yalcin.dto.request.AddProductForm;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.entity.Category;
import com.yalcin.entity.Categorys;
import com.yalcin.entity.Product;
import com.yalcin.entity.User;
import com.yalcin.repository.CategoryRepository;
import com.yalcin.repository.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util;
import java.lang.Integer.parseInt;
import com.yalcin.Interface.UserServiceImpl;
@Service
public class ProductService {

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private CategoryRepository categoryRepository;

@Autowired
 private ProductRepository productRepository;


public Product getProduct(String productId){
    return productRepository.findAllById(parseInt(productId));
}


public void productEdit(String productId){
    Product product = productRepository.findAllById(parseInt(productId));
    product.setEnabled(true);
    productRepository.save(product);
}


public void productSave(AddProductForm addProductForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Product product = new Product(addProductForm.getProductName(), addProductForm.getExplanation(), Float.parseFloat(addProductForm.getPrice()), parseInt(addProductForm.getStock()));
    /*        File convertFile = new File("/var/tmp/"+addProductForm.getProductImage());
        product.setFileProductImage(addProductForm.getProductImage());*/
    System.out.println(addProductForm.getProductImage());
    product.setProductImage(addProductForm.getProductImage());
    Set<Category> category = new HashSet<>();
    Category categories = categoryRepository.findByCategorys(Categorys.valueOf(addProductForm.getProductCategory().toString())).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Product category not found."));
    category.add(categories);
    product.setUser(user);
    product.setShowcaseEnabled(false);
    product.setCategorys(category);
    product.setEnabled(false);
    product.setTimestap(new Date());
    productRepository.save(product);
}


public List<Product> getUserProduct(String userId){
    return productRepository.findAllByUser_Id(parseInt(userId));
}


public List<Product> getCheckProduct(){
    List<Product> product = productRepository.findAllByEnabled(true);
    for (int i = product.size() - 1; i >= 0; i--) {
        if (product.get(i).getStock() <= 0) {
            product.get(i).setEnabled(false);
            productRepository.save(product.get(i));
        }
    }
    return productRepository.findAllByEnabled(true);
}


}