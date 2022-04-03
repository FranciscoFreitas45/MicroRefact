package com.weflors.service;
 import com.weflors.entity.ProductDetailsEntity;
import com.weflors.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductDetailsServiceImpl {

@Autowired
 private ProductDetailsRepository productDetailsRepository;


public void saveProductDetail(ProductDetailsEntity productDetailsEntity){
    productDetailsRepository.saveProductDetail(productDetailsEntity.getProductId(), productDetailsEntity.getProductDescription(), productDetailsEntity.getHeight(), productDetailsEntity.getLength(), productDetailsEntity.getWidth(), productDetailsEntity.getColor());
}


}