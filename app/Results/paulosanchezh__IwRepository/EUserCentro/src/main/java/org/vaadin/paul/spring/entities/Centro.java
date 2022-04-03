package org.vaadin.paul.spring.entities;
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
import org.vaadin.paul.spring.Request.TrabajadorRequest;
import org.vaadin.paul.spring.Request.Impl.TrabajadorRequestImpl;
import org.vaadin.paul.spring.DTO.Trabajador;
@Entity
public class Centro {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "nombre")
 private  String nombre;

@Column(name = "telefono")
 private  String telefono;

@LazyCollection(LazyCollectionOption.FALSE)
@ManyToMany(targetEntity = Especialidad.class)
 private  List<Especialidad> especialidades;

@LazyCollection(LazyCollectionOption.FALSE)
@ManyToMany(targetEntity = User.class, cascade = CascadeType.REMOVE)
 private  List<User> usuarios;

@Transient
 private  List<Trabajador> trabajadores;

@JoinColumn(name = "idLocalidad")
@OneToOne
 private  Localidad localidad;

@Transient
 private TrabajadorRequest trabajadorrequest = new TrabajadorRequestImpl();;

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
  this.trabajadores = trabajadorrequest.getTrabajadores(this.id);
return this.trabajadores;
}


public Localidad getUbicacion(){
    return localidad;
}


public void setUbicacion(Localidad ubicacion){
    this.localidad = ubicacion;
}


public String getNombre(){
    return nombre;
}


public void setTrabajadores(List<Trabajador> trabajadores){
 trabajadorrequest.setTrabajadores(trabajadores,this.id);
}



public String getTelefono(){
    return telefono;
}


public int getId(){
    return id;
}


public void setTelefono(String telefono){
    this.telefono = telefono;
}


public void setEspecialidad(List<Especialidad> especialidades){
    this.especialidades = especialidades;
}


public List<User> getUsuarios(){
    return usuarios;
}


public List<Especialidad> getEspecialidad(){
    return especialidades;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setUsuarios(List<User> usuarios){
    this.usuarios = usuarios;
}


}