package com.cocay.sicecd.DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import com.cocay.sicecd.Request.InscripcionRequest;
import com.cocay.sicecd.Request.Impl.InscripcionRequestImpl;
import com.cocay.sicecd.DTO.Inscripcion;
import com.cocay.sicecd.Request.CertificadoRequest;
import com.cocay.sicecd.Request.Impl.CertificadoRequestImpl;
import com.cocay.sicecd.DTO.Certificado;
public class Profesor {

 private int pk_id_profesor;

 private String nombre;

 private String apellido_paterno;

 private String apellido_materno;

 private String rfc;

 private String curp;

 private String correo;

 private String telefono;

 private Date fechaNac;

 private String ciudad_localidad;

 private String comprobante_doc;

 private String curp_doc;

 private String rfc_doc;

 private String certificado_doc;

 private String ocupacion;

 private String plantel;

 private String clave_plantel;

 private String tempEstado;

 private String tempGenero;

 private String tempTurno;

 private String tempGradoP;

 private Integer stTabla;

 private Turno fk_id_turno;

 private Genero id_genero;

 private Grado_profesor fk_id_grado_profesor;

 private  List<Inscripcion> inscripciones;

 private  List<Certificado> certificados;

 private Estado fk_id_estado;

public Profesor() {
}public Profesor(String nombre, String apellido_paterno, String apellido_materno, String correo, String rfc) {
    this.nombre = nombre;
    this.apellido_paterno = apellido_paterno;
    this.apellido_materno = apellido_materno;
    this.correo = correo;
    this.rfc = rfc;
}
public String getCertificado_doc(){
    return certificado_doc;
}


public String getApellido_paterno(){
    return apellido_paterno;
}


public String getApellido_materno(){
    return apellido_materno;
}


public String getCorreo(){
    return correo;
}


public Genero getGenero(){
    return id_genero;
}


public String getCurp_doc(){
    return curp_doc;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequest.getCertificados(this.pk_id_profesor);
return this.certificados;
}


public String getNombre(){
    return nombre;
}


public String getTelefono(){
    return telefono;
}


public String getTempTurno(){
    return tempTurno;
}


public int getPk_id_profesor(){
    return pk_id_profesor;
}


public String getClave_plantel(){
    return clave_plantel;
}


public String getTempEstado(){
    return tempEstado;
}


public String getPlantel(){
    return plantel;
}


public Grado_profesor getFk_id_grado_profesor(){
    return fk_id_grado_profesor;
}


public String getTempGradoP(){
    return tempGradoP;
}


public Integer getStTabla(){
    return stTabla;
}


public List<Inscripcion> getInscripciones(){
  this.inscripciones = inscripcionrequest.getInscripciones(this.pk_id_profesor);
return this.inscripciones;
}


public String getComprobante_doc(){
    return comprobante_doc;
}


public String getCurp(){
    return curp;
}


public String getCiudad_localidad(){
    return ciudad_localidad;
}


public Genero getId_genero(){
    return id_genero;
}


public String getOcupacion(){
    return ocupacion;
}


public Estado getFk_id_estado(){
    return fk_id_estado;
}


public String getTempGenero(){
    return tempGenero;
}


public String getRfc(){
    return rfc;
}


public String getRfc_doc(){
    return rfc_doc;
}


public Date getFechaNac(){
    return fechaNac;
}


public Turno getFk_id_turno(){
    return fk_id_turno;
}


}