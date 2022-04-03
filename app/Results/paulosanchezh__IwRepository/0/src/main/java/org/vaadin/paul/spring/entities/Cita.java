package org.vaadin.paul.spring.entities;
 import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.paul.spring.repositories.CentroRepository;
import org.vaadin.paul.spring.Request.UserRequest;
import org.vaadin.paul.spring.Request.Impl.UserRequestImpl;
import org.vaadin.paul.spring.DTO.User;
import org.vaadin.paul.spring.Request.SanitarioRequest;
import org.vaadin.paul.spring.Request.Impl.SanitarioRequestImpl;
import org.vaadin.paul.spring.DTO.Sanitario;
@Entity
public class Cita {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Transient
 private  User paciente;

@Transient
 private  Sanitario sanitario;

@JoinColumn(name = "idInforme")
@OneToOne
 private  Informe informe;

@Column(name = "fecha")
 private  LocalDate fecha;

 private  LocalTime hora;

@Column(name = "importe")
 private  float importe;

@Column(name = "Confirmada")
 private  boolean confirmada;

@Column(name = "id")
 private int id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private int id;

@Transient
 private SanitarioRequest sanitariorequest = new SanitarioRequestImpl();;

public Cita() {
}public Cita(int id, User paciente, Sanitario sanitario, Informe informe, LocalDate fecha, LocalTime hora, float importe) {
    this.id = id;
    this.paciente = paciente;
    this.sanitario = sanitario;
    this.informe = informe;
    this.fecha = fecha;
    this.hora = hora;
    this.importe = importe;
    this.confirmada = false;
}
public boolean getConfirmada(){
    return confirmada;
}


public void setFecha(LocalDate fechaCita){
    this.fecha = fechaCita;
}


public Sanitario getSanitario(){
  this.sanitario = sanitariorequest.getSanitario(this.id);
return this.sanitario;
}


public Informe getInforme(){
    return informe;
}


public String getNombreyApellidosSanitario(){
    return sanitario.getTrabajador().getUser().getNombreyApellidos();
}


public User getPaciente(){
  this.paciente = userrequest.getPaciente(this.id);
return this.paciente;
}


public String getCentroString(){
    return sanitario.getTrabajador().getCentro().getNombre();
}


public void setInforme(Informe informe){
    this.informe = informe;
}


public int getId(){
    return id;
}


public void setPaciente(User user){
 userrequest.setPaciente(user,this.id);
}



public String getNombreyApellidospaciente(){
    return paciente.getNombreyApellidos();
}


public LocalTime getHora(){
    return hora;
}


public LocalDate getFecha(){
    return fecha;
}


public float getImporte(){
    return importe;
}


public Centro getCentro(){
    return sanitario.getTrabajador().getCentro();
}


public void setHora(LocalTime hora){
    this.hora = hora;
}


public void setImporte(float importe){
    this.importe = importe;
}


public void setSanitario(Sanitario sanitario){
 sanitariorequest.setSanitario(sanitario,this.id);
}



public void setConfirmada(boolean confirmada){
    this.confirmada = confirmada;
}


public Trabajador getTrabajador(){
    return sanitario.getTrabajador();
}


public String getConfirmadaString(){
    if (confirmada == true)
        return "Sí";
    else
        return "No";
}


}