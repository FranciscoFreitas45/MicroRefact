package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence;
import java.util.Collection;
@Entity
@Table(name = "product_types", schema = "flowershop", catalog = "postgres")
public class ProductTypesEntity {

 private  int productTypeId;

 private  String productTypeName;

 private  Collection<ProductEntity> productsByProductTypeId;


@Basic
@Column(name = "product_type_name", nullable = false, length = 30)
public String getProductTypeName(){
    return productTypeName;
}


@OneToMany(mappedBy = "productTypesByProductTypeId")
@JsonManagedReference(value = "product_types-product")
public Collection<ProductEntity> getProductsByProductTypeId(){
    return productsByProductTypeId;
}


public void setProductTypeId(int productTypeId){
    this.productTypeId = productTypeId;
}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "product_type_id", nullable = false)
public int getProductTypeId(){
    return productTypeId;
}


public void setProductsByProductTypeId(Collection<ProductEntity> productsByProductTypeId){
    this.productsByProductTypeId = productsByProductTypeId;
}


@Override
public int hashCode(){
    int result = productTypeId;
    result = 31 * result + (productTypeName != null ? productTypeName.hashCode() : 0);
    return result;
}


public void setProductTypeName(String productTypeName){
    this.productTypeName = productTypeName;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ProductTypesEntity that = (ProductTypesEntity) o;
    if (productTypeId != that.productTypeId)
        return false;
    if (productTypeName != null ? !productTypeName.equals(that.productTypeName) : that.productTypeName != null)
        return false;
    return true;
}


}