package com.weflors.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class SaleEntity {

 private  int productId;

 private  String articul;

 private  BigDecimal salePrice;

 private  Timestamp saleDate;

 private  int quantity;

 private  String details;

 private  BigDecimal productPrice;

 private  ProductEntity productByProductId;

 private  ClientEntity clientByClientId;

public SaleEntity() {
}
@Basic
@Column(name = "quantity", nullable = false)
public int getQuantity(){
    return quantity;
}


public void setArticul(String articul){
    this.articul = articul;
}


public void setSaleDate(Timestamp saleDate){
    this.saleDate = saleDate;
}


@ManyToOne
@JsonBackReference(value = "client-sale")
@JoinColumn(name = "client_id", referencedColumnName = "client_id")
public ClientEntity getClientByClientId(){
    return clientByClientId;
}


public void setProductId(int productId){
    this.productId = productId;
}


@Basic
@Column(name = "product_price", nullable = false, precision = 2)
public BigDecimal getProductPrice(){
    return productPrice;
}


@ManyToOne
@JsonBackReference(value = "product-sale")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


public void setSalePrice(BigDecimal salePrice){
    this.salePrice = salePrice;
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


@Basic
@Column(name = "sale_date", nullable = false)
public Timestamp getSaleDate(){
    return saleDate;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
}


@Override
public int hashCode(){
    int result = productId;
    result = 31 * result + (articul != null ? articul.hashCode() : 0);
    result = 31 * result + (salePrice != null ? salePrice.hashCode() : 0);
    result = 31 * result + (saleDate != null ? saleDate.hashCode() : 0);
    result = 31 * result + quantity;
    result = 31 * result + (details != null ? details.hashCode() : 0);
    result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
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
    SaleEntity that = (SaleEntity) o;
    if (productId != that.productId)
        return false;
    if (quantity != that.quantity)
        return false;
    if (articul != null ? !articul.equals(that.articul) : that.articul != null)
        return false;
    if (salePrice != null ? !salePrice.equals(that.salePrice) : that.salePrice != null)
        return false;
    if (saleDate != null ? !saleDate.equals(that.saleDate) : that.saleDate != null)
        return false;
    if (details != null ? !details.equals(that.details) : that.details != null)
        return false;
    if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null)
        return false;
    return true;
}


@Id
@Column(name = "articul", nullable = false, length = 50)
public String getArticul(){
    return articul;
}


@Id
@Column(name = "sale_price", nullable = false, precision = 2)
public BigDecimal getSalePrice(){
    return salePrice;
}


public void setProductPrice(BigDecimal productPrice){
    this.productPrice = productPrice;
}


public void setClientByClientId(ClientEntity clientByClientId){
    this.clientByClientId = clientByClientId;
}


}