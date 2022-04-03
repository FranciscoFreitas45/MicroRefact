package com.weflors.service;
 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import com.weflors.entity.SaleEntity;
import com.weflors.repository.SaleRepository;
import org.springframework.stereotype.Service;
import com.weflors.entity.ProductEntity;
import com.weflors.repository.ProductRepository;
@Service
public class SaleServiceImpl {

 private  ProductRepository productRepository;

 private  SaleRepository saleRepository;

public SaleServiceImpl(ProductRepository productRepository, SaleRepository saleRepository) {
    this.productRepository = productRepository;
    this.saleRepository = saleRepository;
}
public boolean addSale(SaleEntity saleEntity){
    saleRepository.save(saleEntity);
    return true;
}


public List<SaleEntity> findAllSalesByProductDatePeriod(Integer productId,Timestamp startDatePeriod,Timestamp endDatePeriod){
    return saleRepository.findAllSalesByProductIDAndDatePeriod(productId, startDatePeriod, endDatePeriod);
}


public List<SaleEntity> findAllSalesByDate(Timestamp date){
    return saleRepository.findAllSalesByDate(date);
}


public List<SaleEntity> findSalesForThisDay(LocalDate localDate){
    return saleRepository.findAllSalesForThisDay(localDate);
}


public ProductEntity getProductByProductId(Integer productId){
    return productRepository.findByProductID(productId);
}


public boolean addAllToSales(List<SaleEntity> saleEntity){
    saleRepository.saveAll(saleEntity);
    return true;
}


public void updateSale(String details,Integer productId){
    saleRepository.updateSaleDetailsById(details, productId);
}


public List<ProductEntity> getAllProduct(){
    return productRepository.findAll();
}


public List<SaleEntity> findAllSalesBySalePrice(BigDecimal salePrice){
    return saleRepository.findAllSalesBySalePrice(salePrice);
}


public List<SaleEntity> findAllSalesByProductID(Integer productID){
    return saleRepository.findAllSalesByProductID(productID);
}


}