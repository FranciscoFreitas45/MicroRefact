package com.cocay.sicecd.model;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name = "Estatus_usuario_sys")
public class Estatus_usuario_sys {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_estatus_usuario_sys")
 private int pk_estatus_usuario_sys;

@Column(name = "nombre", nullable = false, length = 50, unique = true)
 private String nombre;

@OneToMany(mappedBy = "fk_id_estatus_usuario_sys", targetEntity = Usuario_sys.class)
@LazyCollection(LazyCollectionOption.FALSE)
 private  List<Usuario_sys> usuarios;


public int getPk_estatus_usuario_sys(){
    return pk_estatus_usuario_sys;
}


public String getNombre(){
    return nombre;
}


public List<Usuario_sys> getUsuarios(){
    return usuarios;
}


public void setPk_estatus_usuario_sys(int pk_estatus_usuario_sys){
    this.pk_estatus_usuario_sys = pk_estatus_usuario_sys;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setUsuarios(List<Usuario_sys> usuarios){
    this.usuarios = usuarios;
}


}