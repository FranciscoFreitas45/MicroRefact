package org.vaadin.paul.spring.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Especialidad {

 private  int id;

 private  String nombre;

public Especialidad() {
}
public String getNombre(){
    return nombre;
}


public int getId(){
    return id;
}


}