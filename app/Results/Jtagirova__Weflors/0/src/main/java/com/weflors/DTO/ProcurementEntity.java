package com.weflors.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
public class ProcurementEntity {

 private  int productId;

 private  String articul;

 private  BigDecimal procurementPrice;

 private  Timestamp procurementDate;

 private  int quantity;

 private  String details;

 private  Date validityDate;

 private  ProductEntity productByProductId;

 private  ContragentsEntity contragentsByContragentId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Id
@Column(name = "procurement_price", nullable = false, precision = 2)
public BigDecimal getProcurementPrice(){
    return procurementPrice;
}


@Basic
@Column(name = "quantity", nullable = false)
public int getQuantity(){
    return quantity;
}


@Basic
@Column(name = "procurement_date", nullable = false)
public Timestamp getProcurementDate(){
    return procurementDate;
}


@ManyToOne
@JsonBackReference(value = "product-procurement")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


@Basic
@Column(name = "details", nullable = true, length = 300)
public String getDetails(){
    return details;
}


@Id
@Column(name = "product_id", nullable = false)
public int getProductId(){
    return productId;
}


@Id
@Column(name = "articul", nullable = false, length = 50)
public String getArticul(){
    return articul;
}


@ManyToOne
@JsonBackReference(value = "contragents-procurement")
@JoinColumn(name = "contragent_id", referencedColumnName = "contragent_id")
public ContragentsEntity getContragentsByContragentId(){
    return contragentsByContragentId;
}


@Basic
@Column(name = "validity_date", nullable = true)
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