package com.ec.survey.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MESSAGE_TYPES")
public class MessageType {

 private  int id;

 private  int criticality;

 private  int defaultTime;

 private  String label;

 private  String icon;

 private  String css;


public void setIcon(String icon){
    this.icon = icon;
}


@Column(name = "MT_TIME")
public int getDefaultTime(){
    return defaultTime;
}


@Column(name = "MT_LABEL")
public String getLabel(){
    return label;
}


public void setCss(String css){
    this.css = css;
}


@Column(name = "MT_CRITICALITY")
public int getCriticality(){
    return criticality;
}


@Column(name = "MT_CSS")
public String getCss(){
    return css;
}


public void setId(Integer id){
    this.id = id;
}


public void setLabel(String label){
    this.label = label;
}


@Id
@Column(name = "MT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setDefaultTime(int defaultTime){
    this.defaultTime = defaultTime;
}


public void setCriticality(int criticality){
    this.criticality = criticality;
}


@Column(name = "MT_ICON")
public String getIcon(){
    return icon;
}


}