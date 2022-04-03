package org.vaadin.paul.spring.entities;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Calle {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "nombre")
 private  String nombre;

@JoinColumn(name = "idLocalidad")
@OneToOne
 private  Localidad localidad;

public Calle() {
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