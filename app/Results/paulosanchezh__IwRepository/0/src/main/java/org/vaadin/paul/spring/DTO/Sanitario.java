package org.vaadin.paul.spring.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
public class Sanitario {

 private  int id;

 private  Trabajador trabajador;

 private  Especialidad especialidad;

 private  TipoSanitario tipo;

public Sanitario() {
}public Sanitario(Trabajador trabajador, Especialidad especialidad, TipoSanitario tipo) {
    this.trabajador = trabajador;
    this.especialidad = especialidad;
    this.tipo = tipo;
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
    return trabajador;
}


public String getNombreyApellidos(){
    return trabajador.getNombre();
}


}