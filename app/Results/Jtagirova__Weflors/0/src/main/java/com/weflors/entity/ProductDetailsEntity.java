package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
@Entity
@Table(name = "product_details", schema = "flowershop", catalog = "postgres")
public class ProductDetailsEntity {

 private  Integer productId;

 private  String productDescription;

 private  Integer height;

 private  Integer length;

 private  Integer width;

 private  String color;

 private  ProductEntity productByProductId;


public void setProductId(int productId){
    this.productId = productId;
}


@OneToOne
@JsonBackReference(value = "product-product_details")
@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
public ProductEntity getProductByProductId(){
    return productByProductId;
}


@Basic
@Column(name = "color", nullable = true, length = 50)
public String getColor(){
    return color;
}


@Basic
@Column(name = "width", nullable = true)
public Integer getWidth(){
    return width;
}


@Basic
@Column(name = "product_description", nullable = true, length = 300)
public String getProductDescription(){
    return productDescription;
}


public void setHeight(Integer height){
    this.height = height;
}


public void setWidth(Integer width){
    this.width = width;
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
@Column(name = "height", nullable = true)
public Integer getHeight(){
    return height;
}


public void setLength(Integer length){
    this.length = length;
}


public void setColor(String color){
    this.color = color;
}


public void setProductDescription(String productDescription){
    this.productDescription = productDescription;
}


@Override
public int hashCode(){
    int result = productId;
    result = 31 * result + (productDescription != null ? productDescription.hashCode() : 0);
    result = 31 * result + (height != null ? height.hashCode() : 0);
    result = 31 * result + (length != null ? length.hashCode() : 0);
    result = 31 * result + (width != null ? width.hashCode() : 0);
    result = 31 * result + (color != null ? color.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ProductDetailsEntity that = (ProductDetailsEntity) o;
    if (productId != that.productId)
        return false;
    if (productDescription != null ? !productDescription.equals(that.productDescription) : that.productDescription != null)
        return false;
    if (height != null ? !height.equals(that.height) : that.height != null)
        return false;
    if (length != null ? !length.equals(that.length) : that.length != null)
        return false;
    if (width != null ? !width.equals(that.width) : that.width != null)
        return false;
    if (color != null ? !color.equals(that.color) : that.color != null)
        return false;
    return true;
}


@Basic
@Column(name = "length", nullable = true)
public Integer getLength(){
    return length;
}


}