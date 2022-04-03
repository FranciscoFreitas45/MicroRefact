package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
@Entity
@Table(name = "procurement", schema = "flowershop", catalog = "postgres")
@IdClass(ProcurementEntityPK.class)
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


public void setArticul(String articul){
    this.articul = articul;
}


@Basic
@Column(name = "procurement_date", nullable = false)
public Timestamp getProcurementDate(){
    return procurementDate;
}


public void setProductId(int productId){
    this.productId = productId;
}


@ManyToOne
@JsonBackReference(value = "product-procurement")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


public void setContragentsByContragentId(ContragentsEntity contragentsByContragentId){
    this.contragentsByContragentId = contragentsByContragentId;
}


@Basic
@Column(name = "details", nullable = true, length = 300)
public String getDetails(){
    return details;
}


public void setProductByProductId(ProductEntity productByProductId){
    this.productByProductId = productByProductId;
}


@Id
@Column(name = "product_id", nullable = false)
public int getProductId(){
    return productId;
}


public void setValidityDate(Date validityDate){
    this.validityDate = validityDate;
}


public void setProcurementPrice(BigDecimal procurementPrice){
    this.procurementPrice = procurementPrice;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
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


public void setDetails(String details){
    this.details = details;
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


public void setProcurementDate(Timestamp procurementDate){
    this.procurementDate = procurementDate;
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