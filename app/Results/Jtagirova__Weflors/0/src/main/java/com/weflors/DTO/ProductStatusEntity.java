package com.weflors.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.sql.Date;
public class ProductStatusEntity {

 private  int productId;

 private  String articul;

 private  int quantityWarehouse;

 private  int quantityShopSale;

 private  int totalQuantityWriteoff;

 private  Date validityDate;

 private  ProductEntity productByProductId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


@Basic
@Column(name = "total_quantity_writeoff", nullable = false)
public int getTotalQuantityWriteoff(){
    return totalQuantityWriteoff;
}


@ManyToOne(fetch = FetchType.EAGER)
@JsonBackReference(value = "product-product_status")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


@Id
@Column(name = "product_id", nullable = false)
public int getProductId(){
    return productId;
}


@Basic
@Column(name = "quantity_shop_sale", nullable = false)
public int getQuantityShopSale(){
    return quantityShopSale;
}


@Basic
@Column(name = "articul", nullable = false, length = 50)
public String getArticul(){
    return articul;
}


@Basic
@Column(name = "quantity_warehouse", nullable = false)
public int getQuantityWarehouse(){
    return quantityWarehouse;
}


@Id
@Column(name = "validity_date", nullable = false)
public Date getValidityDate(){
    return validityDate;
}


public void setProductId(int productId){
    this.productId = productId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProductId"))

.queryParam("productId",productId)
;
restTemplate.put(builder.toUriString(),null);
}


}