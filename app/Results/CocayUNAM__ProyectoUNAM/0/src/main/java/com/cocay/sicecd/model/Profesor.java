package com.cocay.sicecd.model;
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
@Entity
@Table(name = "Profesor")
public class Profesor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_profesor")
 private int pk_id_profesor;

@Column(name = "nombre", nullable = false, length = 250)
 private String nombre;

@Column(name = "apellido_paterno", nullable = false, length = 250)
 private String apellido_paterno;

@Column(name = "apellido_materno", nullable = true, length = 250)
 private String apellido_materno;

@Column(name = "rfc", nullable = true, length = 13, unique = true)
 private String rfc;

@Column(name = "curp", nullable = true, length = 18, unique = true)
 private String curp;

@Column(name = "correo", nullable = false, length = 200)
 private String correo;

@Column(name = "telefono", nullable = true, length = 20)
 private String telefono;

@Column(name = "fecha_nac", nullable = true)
@DateTimeFormat(pattern = "dd-MM-yyyy")
 private Date fechaNac;

@Column(name = "ciudad_localidad", nullable = true, length = 250)
 private String ciudad_localidad;

@Column(name = "comprobante_doc", nullable = true, length = 200)
 private String comprobante_doc;

@Column(name = "curp_doc", nullable = true, length = 200)
 private String curp_doc;

@Column(name = "rfc_doc", nullable = true, length = 200)
 private String rfc_doc;

@Column(name = "certificado_doc", nullable = true, length = 200)
 private String certificado_doc;

@Column(name = "ocupacion", nullable = true, length = 250)
 private String ocupacion;

@Column(name = "plantel", nullable = true, length = 250)
 private String plantel;

@Column(name = "clave_plantel", nullable = true, length = 20)
 private String clave_plantel;

@Column(name = "tempEstado", nullable = true)
 private String tempEstado;

@Column(name = "tempGenero", nullable = true)
 private String tempGenero;

@Column(name = "tempTurno", nullable = true)
 private String tempTurno;

@Column(name = "tempGradoP", nullable = true)
 private String tempGradoP;

@Column(name = "stTabla", nullable = true)
 private Integer stTabla;

@ManyToOne(targetEntity = Turno.class, optional = true)
@LazyCollection(LazyCollectionOption.TRUE)
@JoinColumn(name = "fk_id_turno", referencedColumnName = "pk_id_turno", insertable = true, updatable = true, nullable = true)
 private Turno fk_id_turno;

@ManyToOne(targetEntity = Genero.class, optional = true)
@LazyCollection(LazyCollectionOption.TRUE)
@JoinColumn(name = "id_genero", referencedColumnName = "pk_id_genero", insertable = true, updatable = true, nullable = true)
 private Genero id_genero;

@ManyToOne(targetEntity = Grado_profesor.class, optional = true)
@LazyCollection(LazyCollectionOption.TRUE)
@JoinColumn(name = "fk_id_grado_profesor", referencedColumnName = "pk_id_grado_profesor", insertable = true, updatable = true, nullable = true)
 private Grado_profesor fk_id_grado_profesor;

@Transient
 private  List<Inscripcion> inscripciones;

@Transient
 private  List<Certificado> certificados;

@ManyToOne(targetEntity = Estado.class, optional = true)
@LazyCollection(LazyCollectionOption.TRUE)
@JoinColumn(name = "fk_id_estado", referencedColumnName = "pk_id_estado", insertable = true, updatable = true, nullable = true)
 private Estado fk_id_estado;

@Transient
 private InscripcionRequest inscripcionrequest = new InscripcionRequestImpl();;

@Transient
 private CertificadoRequest certificadorequest = new CertificadoRequestImpl();;

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


public void setApellido_materno(String apellido_materno){
    this.apellido_materno = apellido_materno;
}


public String getApellido_paterno(){
    return apellido_paterno;
}


public void setOcupacion(String ocupacion){
    this.ocupacion = ocupacion;
}


public void setFk_id_grado_profesor(Grado_profesor fk_id_grado_profesor){
    this.fk_id_grado_profesor = fk_id_grado_profesor;
}


public void setTelefono(String telefono){
    this.telefono = telefono;
}


public String getApellido_materno(){
    return apellido_materno;
}


public String getCorreo(){
    return correo;
}


public void setFk_id_turno(Turno fk_id_turno){
    this.fk_id_turno = fk_id_turno;
}


public void setCurp_doc(String curp_doc){
    this.curp_doc = curp_doc;
}


public Genero getGenero(){
    return id_genero;
}


public void setRfc_doc(String rfc_doc){
    this.rfc_doc = rfc_doc;
}


public String getCurp_doc(){
    return curp_doc;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setCertificados(List<Certificado> certificados){
 certificadorequest.setCertificados(certificados,this.pk_id_profesor);
}



public void setFechaNac(Date fechaNac){
    this.fechaNac = fechaNac;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequest.getCertificados(this.pk_id_profesor);
return this.certificados;
}


public void setCertificado_doc(String certificado_doc){
    this.certificado_doc = certificado_doc;
}


public String getNombre(){
    return nombre;
}


public void setFk_id_estado(Estado fk_id_estado){
    this.fk_id_estado = fk_id_estado;
}


public void setCiudad_localidad(String ciudad_localidad){
    this.ciudad_localidad = ciudad_localidad;
}


public void setPk_id_profesor(int pk_id_profesor){
    this.pk_id_profesor = pk_id_profesor;
}


public String getTelefono(){
    return telefono;
}


public String getTempTurno(){
    return tempTurno;
}


public void setApellido_paterno(String apellido_paterno){
    this.apellido_paterno = apellido_paterno;
}


public int getPk_id_profesor(){
    return pk_id_profesor;
}


public void setTempGenero(String tempGenero){
    this.tempGenero = tempGenero;
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


public void setGenero(Genero id_genero){
    this.id_genero = id_genero;
}


public void setId_genero(Genero id_genero){
    this.id_genero = id_genero;
}


public Integer getStTabla(){
    return stTabla;
}


public void setTempTurno(String tempTurno){
    this.tempTurno = tempTurno;
}


public void setStTabla(Integer stTabla){
    this.stTabla = stTabla;
}


public List<Inscripcion> getInscripciones(){
  this.inscripciones = inscripcionrequest.getInscripciones(this.pk_id_profesor);
return this.inscripciones;
}


public String getComprobante_doc(){
    return comprobante_doc;
}


public void setInscripciones(List<Inscripcion> inscripciones){
 inscripcionrequest.setInscripciones(inscripciones,this.pk_id_profesor);
}



public void setTempEstado(String tempEstado){
    this.tempEstado = tempEstado;
}


public void setCorreo(String correo){
    this.correo = correo;
}


public String getCurp(){
    return curp;
}


public String getCiudad_localidad(){
    return ciudad_localidad;
}


public void setPlantel(String plantel){
    this.plantel = plantel;
}


public void setComprobante_doc(String comprobante_doc){
    this.comprobante_doc = comprobante_doc;
}


public void setTempGradoP(String tempGradoP){
    this.tempGradoP = tempGradoP;
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


public void setClave_plantel(String clave_plantel){
    this.clave_plantel = clave_plantel;
}


public void setRfc(String rfc){
    this.rfc = rfc;
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


public void setCurp(String curp){
    this.curp = curp;
}


public String toString(){
    return "Nombre:" + nombre + ", Apellido Paterno:" + apellido_paterno + ", Apellido Materno:" + apellido_materno + ", RFC: " + rfc;
}


public Turno getFk_id_turno(){
    return fk_id_turno;
}


}