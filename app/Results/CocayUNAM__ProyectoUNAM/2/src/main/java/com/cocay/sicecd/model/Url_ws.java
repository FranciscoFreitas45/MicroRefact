package com.cocay.sicecd.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Url_ws")
public class Url_ws {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_url_ws")
 private  int pk_id_url_ws;

@Column(name = "url", nullable = false, length = 200, unique = true)
 private  String url;

@Column(name = "varios", nullable = true)
 private  boolean varios;

@Column(name = "activa", nullable = true)
 private  boolean activa;


public boolean isActiva(){
    return activa;
}


public String getUrl(){
    return url;
}


public boolean isVarios(){
    return varios;
}


public int getPk_id_url_ws(){
    return pk_id_url_ws;
}


public void setPk_id_url_ws(int pk_id_url_ws){
    this.pk_id_url_ws = pk_id_url_ws;
}


public void setVarios(boolean varios){
    this.varios = varios;
}


public void setUrl(String url){
    this.url = url;
}


public void setActiva(boolean activa){
    this.activa = activa;
}


}