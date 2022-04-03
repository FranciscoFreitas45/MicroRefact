package org.vaadin.paul.spring.entities;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.vaadin.paul.spring.Request.TrabajadorRequest;
import org.vaadin.paul.spring.Request.Impl.TrabajadorRequestImpl;
import org.vaadin.paul.spring.DTO.Trabajador;
@Entity
public class Sanitario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Transient
 private  Trabajador trabajador;

@JoinColumn(name = "idEspecialidad")
@ManyToOne
 private  Especialidad especialidad;

@JoinColumn(name = "idTipo")
@OneToOne
 private  TipoSanitario tipo;

@Column(name = "id")
 private int id;

@Transient
 private TrabajadorRequest trabajadorrequest = new TrabajadorRequestImpl();;

public Sanitario() {
}public Sanitario(Trabajador trabajador, Especialidad especialidad, TipoSanitario tipo) {
    this.trabajador = trabajador;
    this.especialidad = especialidad;
    this.tipo = tipo;
}
public void setTrabajador(Trabajador trabajador){
 trabajadorrequest.setTrabajador(trabajador,this.id);
}



public int getId(){
    return id;
}


public Especialidad getEspecialidad(){
    return especialidad;
}


public int getid(){
    return id;
}


public Trabajador getTrabajador(){
  this.trabajador = trabajadorrequest.getTrabajador(this.id);
return this.trabajador;
}


public String getNombreyApellidos(){
    return trabajador.getNombre();
}


public void setEspecialidad(Especialidad especialidad){
    this.especialidad = especialidad;
}


}