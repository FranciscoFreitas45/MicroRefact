package com.cocay.sicecd.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Url_ws_profesor")
public class Url_ws_profesor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_url_ws_profesor")
 private  int pk_id_url_ws_profesor;

@Column(name = "nombre")
 private  String nombre;

@Column(name = "url", nullable = false, length = 200, unique = true)
 private  String url;

@Column(name = "activa", nullable = true)
 private  boolean activa;


public boolean isActiva(){
    return activa;
}


public String getUrl(){
    return url;
}


public String getNombre(){
    return nombre;
}


public void setPk_id_url_ws_profesor(int pk_id_url_ws_profesor){
    this.pk_id_url_ws_profesor = pk_id_url_ws_profesor;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public int getPk_id_url_ws_profesor(){
    return pk_id_url_ws_profesor;
}


public void setUrl(String url){
    this.url = url;
}


public void setActiva(boolean activa){
    this.activa = activa;
}


}