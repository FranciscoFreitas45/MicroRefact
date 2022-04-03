package org.vaadin.paul.spring.DTO;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
public class Centro {

 private  int id;

 private  String nombre;

 private  String telefono;

 private  List<Especialidad> especialidades;

 private  List<User> usuarios;

 private  List<Trabajador> trabajadores;

 private  Localidad localidad;

public Centro() {
}public Centro(int id, String nombre, String telefono, List<Especialidad> especialidades, List<User> usuarios, List<Trabajador> trabajadores, Localidad localidad) {
    this.especialidades = especialidades;
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.usuarios = usuarios;
    this.trabajadores = trabajadores;
    this.localidad = localidad;
}
public List<Trabajador> getTrabajadores(){
    return trabajadores;
}


public Localidad getUbicacion(){
    return localidad;
}


public String getNombre(){
    return nombre;
}


public String getTelefono(){
    return telefono;
}


public int getId(){
    return id;
}


public List<User> getUsuarios(){
    return usuarios;
}


public List<Especialidad> getEspecialidad(){
    return especialidades;
}


}