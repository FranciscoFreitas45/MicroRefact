package com.weflors.service;
 import com.weflors.entity.ProductTypesEntity;
import com.weflors.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductTypeServiceImpl {

 private  ProductTypeRepository productTypeRepository;

public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
}
public Optional<ProductTypesEntity> findByProductName(String productTypeName){
    return productTypeRepository.findByProductTypeName(productTypeName);
}


public void deleteProductType(ProductTypesEntity productTypesEntity){
    productTypeRepository.deleteProductTypeById(productTypesEntity.getProductTypeId());
}


public List<ProductTypesEntity> findAllProductTypes(){
    return productTypeRepository.findAll();
}


public void updateProductType(ProductTypesEntity productTypesEntity){
    productTypeRepository.updateProductTypeById(productTypesEntity.getProductTypeName(), productTypesEntity.getProductTypeId());
}


public ProductTypesEntity saveProductType(ProductTypesEntity productTypesEntity){
    return productTypeRepository.save(productTypesEntity);
}


public List<String> getProductType(){
    return productTypeRepository.getAllProductType();
}


}