package org.vaadin.paul.spring.entities;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.vaadin.paul.spring.Request.UserRequest;
import org.vaadin.paul.spring.Request.Impl.UserRequestImpl;
import org.vaadin.paul.spring.DTO.User;
import org.vaadin.paul.spring.Request.CitaRequest;
import org.vaadin.paul.spring.Request.Impl.CitaRequestImpl;
import org.vaadin.paul.spring.DTO.Cita;
import org.vaadin.paul.spring.Request.InformeRequest;
import org.vaadin.paul.spring.Request.Impl.InformeRequestImpl;
import org.vaadin.paul.spring.DTO.Informe;
@Entity
public class HistorialClinico {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Transient
 private  User paciente;

@Transient
 private  List<Cita> citas;

@Transient
 private  List<Informe> informes;

@Column(name = "informacionImportante")
 private  String InformacionImportante;

@Column(name = "id")
 private int id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Transient
 private CitaRequest citarequest = new CitaRequestImpl();;

@Transient
 private InformeRequest informerequest = new InformeRequestImpl();;

public HistorialClinico() {
}
public List<Cita> getCitas(){
  this.citas = citarequest.getCitas(this.id);
return this.citas;
}


}