package com.easyshopping.entity;
 import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_product_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_product_category_sequence")
public class ProductCategory extends OrderEntity{

 private  long serialVersionUID;

 public  String TREE_PATH_SEPARATOR;

 private  String PATH_PREFIX;

 private  String PATH_SUFFIX;

 private  String name;

 private  String seoTitle;

 private  String seoKeywords;

 private  String seoDescription;

 private  String treePath;

 private  Integer grade;

 private  ProductCategory parent;

 private  Set<ProductCategory> children;

 private  Set<Product> products;

 private  Set<Brand> brands;

 private  Set<ParameterGroup> parameterGroups;

 private  Set<Attribute> attributes;

 private  Set<Promotion> promotions;


public void setName(String name){
    this.name = name;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setSeoKeywords(String seoKeywords){
    this.seoKeywords = seoKeywords;
}


@Transient
public List<Long> getTreePaths(){
    List<Long> treePaths = new ArrayList<Long>();
    String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
    if (ids != null) {
        for (String id : ids) {
            treePaths.add(Long.valueOf(id));
        }
    }
    return treePaths;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_product_category_brand")
@OrderBy("order asc")
public Set<Brand> getBrands(){
    return brands;
}


@Length(max = 200)
public String getSeoDescription(){
    return seoDescription;
}


@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("order asc")
public Set<ParameterGroup> getParameterGroups(){
    return parameterGroups;
}


public void setTreePath(String treePath){
    this.treePath = treePath;
}


public void setAttributes(Set<Attribute> attributes){
    this.attributes = attributes;
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


public void setGrade(Integer grade){
    this.grade = grade;
}


public void setParent(ProductCategory parent){
    this.parent = parent;
}


@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


@Column(nullable = false)
public Integer getGrade(){
    return grade;
}


public void setParameterGroups(Set<ParameterGroup> parameterGroups){
    this.parameterGroups = parameterGroups;
}


@Length(max = 200)
public String getSeoTitle(){
    return seoTitle;
}


@ManyToOne(fetch = FetchType.LAZY)
public ProductCategory getParent(){
    return parent;
}


@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("order asc")
public Set<Attribute> getAttributes(){
    return attributes;
}


@ManyToMany(mappedBy = "productCategories", fetch = FetchType.LAZY)
public Set<Promotion> getPromotions(){
    return promotions;
}


public void setSeoDescription(String seoDescription){
    this.seoDescription = seoDescription;
}


@Column(nullable = false)
public String getTreePath(){
    return treePath;
}


@Length(max = 200)
public String getSeoKeywords(){
    return seoKeywords;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
@OrderBy("order asc")
public Set<ProductCategory> getChildren(){
    return children;
}


public void setBrands(Set<Brand> brands){
    this.brands = brands;
}


public void setSeoTitle(String seoTitle){
    this.seoTitle = seoTitle;
}


@PreRemove
public void preRemove(){
    Set<Promotion> promotions = getPromotions();
    if (promotions != null) {
        for (Promotion promotion : promotions) {
            promotion.getProductCategories().remove(this);
        }
    }
}


public void setChildren(Set<ProductCategory> children){
    this.children = children;
}


}