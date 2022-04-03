package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Brand extends OrderEntity{

 private  long serialVersionUID;

 private  String PATH_PREFIX;

 private  String PATH_SUFFIX;

 private  String name;

 private  Type type;

 private  String logo;

 private  String url;

 private  String introduction;

 private  Set<Product> products;

 private  Set<ProductCategory> productCategories;

 private  Set<Promotion> promotions;


public void setName(String name){
    this.name = name;
}


public void setLogo(String logo){
    this.logo = logo;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@Length(max = 200)
public String getLogo(){
    return logo;
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


@ManyToMany(mappedBy = "brands", fetch = FetchType.LAZY)
public Set<Promotion> getPromotions(){
    return promotions;
}


@Lob
public String getIntroduction(){
    return introduction;
}


public void setType(Type type){
    this.type = type;
}


public void setProductCategories(Set<ProductCategory> productCategories){
    this.productCategories = productCategories;
}


public void setUrl(String url){
    this.url = url;
}


@Length(max = 200)
public String getUrl(){
    return url;
}


@NotNull
@Column(nullable = false)
public Type getType(){
    return type;
}


public void setPromotions(Set<Promotion> promotions){
    this.promotions = promotions;
}


public void setProducts(Set<Product> products){
    this.products = products;
}


@Transient
public String getPath(){
    if (getId() != null) {
        return PATH_PREFIX + "/" + getId() + PATH_SUFFIX;
    }
    return null;
}


@ManyToMany(mappedBy = "brands", fetch = FetchType.LAZY)
@OrderBy("order asc")
public Set<ProductCategory> getProductCategories(){
    return productCategories;
}


@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


@PreRemove
public void preRemove(){
    Set<Product> products = getProducts();
    if (products != null) {
        for (Product product : products) {
            product.setBrand(null);
        }
    }
    Set<ProductCategory> productCategories = getProductCategories();
    if (productCategories != null) {
        for (ProductCategory productCategory : productCategories) {
            productCategory.getBrands().remove(this);
        }
    }
    Set<Promotion> promotions = getPromotions();
    if (promotions != null) {
        for (Promotion promotion : promotions) {
            promotion.getBrands().remove(this);
        }
    }
}


}