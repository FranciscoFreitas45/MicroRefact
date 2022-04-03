package org.vaadin.paul.spring.entities;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Informe {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@JoinColumn(name = "idCita")
@OneToOne
 private  Cita cita;

@Column(name = "porque")
 private  String porQue;

@Column(name = "enfermedadActual")
 private  String enfermedadActual;

@Column(name = "intervencion")
 private  String intervencion;

@Column(name = "diagnostico")
 private  String diagnostico;

@Column(name = "medicamentos")
 private  String medicamentos;

@Column(name = "planClinico")
 private  String planClinico;

@Column(name = "proximaCita")
 private  Date fechaProximaCita;

@Column(name = "firmaElectronica")
 private  boolean firma;

public Informe() {
}public Informe(int id, Cita cita, String pq, String enfActual, String intervencion, String diagnostico, String medicamentos, String planClinico, Date proximaCita) {
    this.id = id;
    this.cita = cita;
    this.porQue = pq;
    this.enfermedadActual = enfActual;
    this.intervencion = intervencion;
    this.diagnostico = diagnostico;
    this.medicamentos = medicamentos;
    this.planClinico = planClinico;
    this.fechaProximaCita = proximaCita;
    this.firma = true;
}
public boolean getFirma(){
    return this.firma;
}


public void setPlanClinico(String pc){
    this.planClinico = pc;
}


public String getPorQue(){
    return this.porQue;
}


public String getNombreyApellidosSanitario(){
    return cita.getNombreyApellidosSanitario();
}


public String getDiagnostico(){
    return this.diagnostico;
}


public String getIntervencion(){
    return this.intervencion;
}


public void setEnfermedadActual(String e){
    this.enfermedadActual = e;
}


public int getid(){
    return id;
}


public String getEnfermedadActual(){
    return this.enfermedadActual;
}


public void setPorQue(String pq){
    this.porQue = pq;
}


public String getNombreyApellidospaciente(){
    return cita.getNombreyApellidospaciente();
}


public void setMedicamentos(String m){
    this.medicamentos = m;
}


public void setFechaProximaCita(Date f){
    this.fechaProximaCita = f;
}


public String getMedicamentos(){
    return this.medicamentos;
}


public void setIntervencion(String i){
    this.intervencion = i;
}


public Cita getIdCita_(){
    return cita;
}


public void setFirma(boolean f){
    this.firma = f;
}


public void setIdCita_(Cita cita){
    this.cita = cita;
}


public void setDiagnostico(String d){
    this.diagnostico = d;
}


public String getPlanClinico(){
    return this.planClinico;
}


public Date getFechaProximaCita(){
    return this.fechaProximaCita;
}


}