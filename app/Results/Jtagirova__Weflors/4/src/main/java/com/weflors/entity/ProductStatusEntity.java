package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
import java.sql.Date;
@Entity
// @EqualsAndHashCode(exclude = "invoice")
@Table(name = "product_status", schema = "flowershop", catalog = "postgres")
@IdClass(ProductStatusEntityPK.class)
public class ProductStatusEntity {

 private  int productId;

 private  String articul;

 private  int quantityWarehouse;

 private  int quantityShopSale;

 private  int totalQuantityWriteoff;

 private  Date validityDate;

 private  ProductEntity productByProductId;


@Basic
@Column(name = "total_quantity_writeoff", nullable = false)
public int getTotalQuantityWriteoff(){
    return totalQuantityWriteoff;
}


public void setQuantityWarehouse(int quantityWarehouse){
    this.quantityWarehouse = quantityWarehouse;
}


public void setArticul(String articul){
    this.articul = articul;
}


public void setProductId(int productId){
    this.productId = productId;
}


public void setTotalQuantityWriteoff(int totalQuantityWriteoff){
    this.totalQuantityWriteoff = totalQuantityWriteoff;
}


@ManyToOne(fetch = FetchType.EAGER)
@JsonBackReference(value = "product-product_status")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


public void setQuantityShopSale(int quantityShopSale){
    this.quantityShopSale = quantityShopSale;
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


@Basic
@Column(name = "quantity_shop_sale", nullable = false)
public int getQuantityShopSale(){
    return quantityShopSale;
}


@Override
public int hashCode(){
    int result = productId;
    result = 31 * result + (articul != null ? articul.hashCode() : 0);
    result = 31 * result + quantityWarehouse;
    result = 31 * result + quantityShopSale;
    result = 31 * result + totalQuantityWriteoff;
    result = 31 * result + (validityDate != null ? validityDate.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ProductStatusEntity that = (ProductStatusEntity) o;
    if (productId != that.productId)
        return false;
    if (quantityWarehouse != that.quantityWarehouse)
        return false;
    if (quantityShopSale != that.quantityShopSale)
        return false;
    if (totalQuantityWriteoff != that.totalQuantityWriteoff)
        return false;
    if (articul != null ? !articul.equals(that.articul) : that.articul != null)
        return false;
    if (validityDate != null ? !validityDate.equals(that.validityDate) : that.validityDate != null)
        return false;
    return true;
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


}