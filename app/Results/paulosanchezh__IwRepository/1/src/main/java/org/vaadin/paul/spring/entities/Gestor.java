package org.vaadin.paul.spring.entities;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Gestor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int idGestor;

@JoinColumn(name = "idTrabajador")
@OneToOne
 private  Trabajador trabajador;

public Gestor() {
}public Gestor(Trabajador trabajador) {
    this.trabajador = trabajador;
}
}