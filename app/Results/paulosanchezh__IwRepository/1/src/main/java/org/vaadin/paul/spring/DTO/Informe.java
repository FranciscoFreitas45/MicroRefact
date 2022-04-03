package org.vaadin.paul.spring.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
public class Informe {

 private  int id;

 private  Cita cita;

 private  String porQue;

 private  String enfermedadActual;

 private  String intervencion;

 private  String diagnostico;

 private  String medicamentos;

 private  String planClinico;

 private  Date fechaProximaCita;

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


public int getid(){
    return id;
}


public String getEnfermedadActual(){
    return this.enfermedadActual;
}


public String getNombreyApellidospaciente(){
    return cita.getNombreyApellidospaciente();
}


public String getMedicamentos(){
    return this.medicamentos;
}


public Cita getIdCita_(){
    return cita;
}


public String getPlanClinico(){
    return this.planClinico;
}


public Date getFechaProximaCita(){
    return this.fechaProximaCita;
}


}