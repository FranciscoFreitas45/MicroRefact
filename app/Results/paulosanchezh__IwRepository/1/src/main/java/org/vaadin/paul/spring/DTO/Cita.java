package org.vaadin.paul.spring.DTO;
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
public class Cita {

 private  int id;

 private  User paciente;

 private  Sanitario sanitario;

 private  Informe informe;

 private  LocalDate fecha;

 private  LocalTime hora;

 private  float importe;

 private  boolean confirmada;

 private int id;

 private int id;

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


public int getId(){
    return id;
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