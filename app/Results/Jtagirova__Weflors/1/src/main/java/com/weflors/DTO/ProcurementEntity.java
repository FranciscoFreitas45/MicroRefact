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


public void setProcurementPrice(BigDecimal procurementPrice){
    this.procurementPrice = procurementPrice;
}


@Override
public int hashCode(){
    int result = productId;
    result = 31 * result + (articul != null ? articul.hashCode() : 0);
    result = 31 * result + (procurementPrice != null ? procurementPrice.hashCode() : 0);
    result = 31 * result + (procurementDate != null ? procurementDate.hashCode() : 0);
    result = 31 * result + quantity;
    result = 31 * result + (details != null ? details.hashCode() : 0);
    result = 31 * result + (validityDate != null ? validityDate.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ProcurementEntity that = (ProcurementEntity) o;
    if (productId != that.productId)
        return false;
    if (quantity != that.quantity)
        return false;
    if (articul != null ? !articul.equals(that.articul) : that.articul != null)
        return false;
    if (procurementPrice != null ? !procurementPrice.equals(that.procurementPrice) : that.procurementPrice != null)
        return false;
    if (procurementDate != null ? !procurementDate.equals(that.procurementDate) : that.procurementDate != null)
        return false;
    if (details != null ? !details.equals(that.details) : that.details != null)
        return false;
    if (validityDate != null ? !validityDate.equals(that.validityDate) : that.validityDate != null)
        return false;
    return true;
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


}