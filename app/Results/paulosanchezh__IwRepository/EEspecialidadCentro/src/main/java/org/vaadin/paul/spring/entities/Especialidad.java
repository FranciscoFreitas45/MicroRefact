package org.vaadin.paul.spring.entities;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Especialidad {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "nombre", length = 128)
 private  String nombre;

public Especialidad() {
}
public String getNombre(){
    return nombre;
}


public int getId(){
    return id;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


}