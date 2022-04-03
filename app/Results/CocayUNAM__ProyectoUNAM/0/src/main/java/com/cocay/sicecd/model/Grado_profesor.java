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
@Table(name = "Grado_profesor")
public class Grado_profesor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_grado_profesor")
 private int pk_id_grado_profesor;

@Column(name = "nombre", nullable = false, length = 50, unique = true)
 private String nombre;

@OneToMany(mappedBy = "fk_id_grado_profesor", targetEntity = Profesor.class)
@LazyCollection(LazyCollectionOption.FALSE)
 private  List<Profesor> profesores;


public int getPk_id_grado_profesor(){
    return pk_id_grado_profesor;
}


public String getNombre(){
    return nombre;
}


public List<Profesor> getProfesores(){
    return profesores;
}


public void setPk_id_grado_profesor(int pk_id_grado_profesor){
    this.pk_id_grado_profesor = pk_id_grado_profesor;
}


public void setProfesores(List<Profesor> profesores){
    this.profesores = profesores;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


}