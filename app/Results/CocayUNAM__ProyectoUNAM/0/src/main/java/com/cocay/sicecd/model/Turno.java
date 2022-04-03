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
@Table(name = "Turno")
public class Turno {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_turno")
 private int pk_id_turno;

@Column(name = "nombre", nullable = false, length = 30)
 private String nombre;

@OneToMany(mappedBy = "fk_id_turno", targetEntity = Profesor.class)
@LazyCollection(LazyCollectionOption.FALSE)
 private  List<Profesor> profesores;


public int getPk_id_turno(){
    return pk_id_turno;
}


public void setPk_id_turno(int pk_id_turno){
    this.pk_id_turno = pk_id_turno;
}


public String getNombre(){
    return nombre;
}


public List<Profesor> getProfesores(){
    return profesores;
}


public void setProfesores(List<Profesor> profesores){
    this.profesores = profesores;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


}