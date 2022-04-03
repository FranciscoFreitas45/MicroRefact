package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Tag extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Type type;

 private  String icon;

 private  String memo;

 private  Set<Article> articles;

 private  Set<Product> products;


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
public Set<Article> getArticles(){
    return articles;
}


public void setArticles(Set<Article> articles){
    this.articles = articles;
}


@Length(max = 200)
public String getIcon(){
    return icon;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@NotNull(groups = Save.class)
@Column(nullable = false, updatable = false)
public Type getType(){
    return type;
}


@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
public Set<Product> getProducts(){
    return products;
}


}