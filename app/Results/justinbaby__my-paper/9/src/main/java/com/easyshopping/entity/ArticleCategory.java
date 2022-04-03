package com.easyshopping.entity;
 import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_article_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_article_category_sequence")
public class ArticleCategory extends OrderEntity{

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

 private  ArticleCategory parent;

 private  Set<ArticleCategory> children;

 private  Set<Article> articles;


public void setName(String name){
    this.name = name;
}


@Length(max = 200)
public String getSeoTitle(){
    return seoTitle;
}


@ManyToOne(fetch = FetchType.LAZY)
public ArticleCategory getParent(){
    return parent;
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


@Length(max = 200)
public String getSeoDescription(){
    return seoDescription;
}


public void setSeoDescription(String seoDescription){
    this.seoDescription = seoDescription;
}


@OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY)
public Set<Article> getArticles(){
    return articles;
}


public void setTreePath(String treePath){
    this.treePath = treePath;
}


public void setArticles(Set<Article> articles){
    this.articles = articles;
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
public Set<ArticleCategory> getChildren(){
    return children;
}


public void setSeoTitle(String seoTitle){
    this.seoTitle = seoTitle;
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


public void setParent(ArticleCategory parent){
    this.parent = parent;
}


public void setChildren(Set<ArticleCategory> children){
    this.children = children;
}


@Column(nullable = false)
public Integer getGrade(){
    return grade;
}


}