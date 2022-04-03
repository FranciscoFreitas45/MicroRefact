package com.weflors.service;
 import com.weflors.entity.ProductEntity;
import com.weflors.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImpl {

 private  ProductRepository productRepository;

public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
}
public List<ProductEntity> findListOfProducts(){
    List<ProductEntity> productList = productRepository.findAll();
    return productList;
}


public List<Integer> getAllProductId(){
    return productRepository.getAllProductId();
}


public List<ProductEntity> findAllProductsByProductId(List<Integer> productIDs){
    return productRepository.findAllById(productIDs);
}


public ProductEntity findByProductId(Integer productId){
    return productRepository.findByProductID(productId);
}


public ProductEntity saveProduct(ProductEntity productEntity){
    return productRepository.save(productEntity);
}


public void deleteProduct(Integer productId){
    productRepository.deleteByProductId(productId);
}


}