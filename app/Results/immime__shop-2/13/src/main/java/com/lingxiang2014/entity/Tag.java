package com.lingxiang2014.entity;
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
@Entity
@Table(name = "lx_tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_tag_sequence")
public class Tag extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Type type;

 private  String icon;

 private  String memo;

 private  Set<Article> articles;


public void setName(String name){
    this.name = name;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@NotNull(groups = Save.class)
@Column(nullable = false, updatable = false)
public Type getType(){
    return type;
}


@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
public Set<Article> getArticles(){
    return articles;
}


public void setType(Type type){
    this.type = type;
}


public void setArticles(Set<Article> articles){
    this.articles = articles;
}


@PreRemove
public void preRemove(){
    Set<Article> articles = getArticles();
    if (articles != null) {
        for (Article article : articles) {
            article.getTags().remove(this);
        }
    }
}


@Length(max = 200)
public String getIcon(){
    return icon;
}


}