package com.yalcin.entity;
 import javax.persistence;
@Entity
@Table(name = "email_templates", schema = "public")
public class Template {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Integer id;

@Column(name = "template_name")
 private  String templateName;

@Column(name = "content")
 private  String content;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public String getTemplateName(){
    return templateName;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setTemplateName(String templateName){
    this.templateName = templateName;
}


}