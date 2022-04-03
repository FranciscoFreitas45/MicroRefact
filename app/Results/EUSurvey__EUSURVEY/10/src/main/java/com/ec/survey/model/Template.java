package com.ec.survey.model;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Element;
@Entity
@Table(name = "TEMPL")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Template {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  Element element;

 private  User owner;


public void setName(String name){
    this.name = name;
}


@Column(name = "TEMPL_NAME")
public String getName(){
    return name;
}


@OneToOne(cascade = CascadeType.ALL)
public Element getElement(){
    return element;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@Column(name = "TEMPL_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setElement(Element element){
    this.element = element;
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


public void setOwner(User owner){
    this.owner = owner;
}


}