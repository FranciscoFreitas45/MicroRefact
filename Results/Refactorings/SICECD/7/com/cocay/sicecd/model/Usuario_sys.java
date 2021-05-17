import java.util.ArrayList;
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
@Entity
@Table(name = "Usuario_sys")
public class Usuario_sys {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_usuario_sys")
 private int pk_id_usuario_sys;

@Column(name = "rfc", nullable = false, length = 13, unique = true)
 private String rfc;

@Column(name = "password", nullable = true, length = 60)
 private String password;

@Column(name = "correo", nullable = false, length = 200)
 private String correo;

@Column(name = "nombre", nullable = false, length = 250)
 private String nombre;

@Column(name = "apellido_paterno", nullable = false, length = 250)
 private String apellido_paterno;

@Column(name = "apellido_materno", nullable = true, length = 250)
 private String apellido_materno;

@Column(name = "confirmacion", nullable = true, length = 150)
 private String confirmacion;

@Column(name = "codigo", nullable = true, length = 150)
 private String codigo;

@Column(name = "confirmacioncorreo", nullable = true, length = 150)
 private String confirmacioncorreo;

@Column(name = "codigoCorreo", nullable = true, length = 150)
 private String codigoCorreo;

@Column(name = "correocambio", nullable = true, length = 150)
 private String correocambio;

@Column(name = "codigorecupera", nullable = true, length = 150)
 private String codigorecupera;

@Column(name = "confirmarecupera", nullable = true, length = 150)
 private String confirmarecupera;

@ManyToOne(targetEntity = Estatus_usuario_sys.class)
@LazyCollection(LazyCollectionOption.FALSE)
@JoinColumn(name = "fk_id_estatus_usuario_sys", referencedColumnName = "pk_estatus_usuario_sys")
 private Estatus_usuario_sys fk_id_estatus_usuario_sys;

@ManyToOne(targetEntity = Perfil_sys.class)
@LazyCollection(LazyCollectionOption.FALSE)
@JoinColumn(name = "fk_id_perfil_sys", referencedColumnName = "pk_id_perfil_sys")
 private Perfil_sys fk_id_perfil_sys;

@Transient
 private  List<Log_sys> Log_sys_s;

@Transient
 private Log_sysRequestImpl log_sysrequestimpl;


public void setPassword(String password){
    this.password = password;
}


public void setApellido_materno(String apellido_materno){
    this.apellido_materno = apellido_materno;
}


public String getCorreocambio(){
    return correocambio;
}


public String getApellido_paterno(){
    return apellido_paterno;
}


public void setLog_sys_s(List<Log_sys> log_sys_s){
 log_sysrequestimpl.setLog_sys_s(log_sys_s,this.pk_id_usuario_sys);



public String getApellido_materno(){
    return apellido_materno;
}


public Perfil_sys getFk_id_perfil_sys(){
    return fk_id_perfil_sys;
}


public String getCorreo(){
    return correo;
}


public void setCorreo(String correo){
    this.correo = correo;
}


public String getConfirmacion(){
    return confirmacion;
}


public void setConfirmarecupera(String confirmarecupera){
    this.confirmarecupera = confirmarecupera;
}


public Estatus_usuario_sys getFk_id_estatus_usuario_sys(){
    return fk_id_estatus_usuario_sys;
}


public void setFk_id_estatus_usuario_sys(Estatus_usuario_sys fk_id_estatus_usuario_sys){
    this.fk_id_estatus_usuario_sys = fk_id_estatus_usuario_sys;
}


public void setCorreocambio(String correocambio){
    this.correocambio = correocambio;
}


public void setCodigorecupera(String codigorecupera){
    this.codigorecupera = codigorecupera;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setCodigo(String codigo){
    this.codigo = codigo;
}


public String getConfirmarecupera(){
    return confirmarecupera;
}


public String getCodigo(){
    return codigo;
}


public String getCodigorecupera(){
    return codigorecupera;
}


public String getConfirmacioncorreo(){
    return confirmacioncorreo;
}


public void setFk_id_perfil_sys(Perfil_sys fk_id_perfil_sys){
    this.fk_id_perfil_sys = fk_id_perfil_sys;
}


public String getNombre(){
    return nombre;
}


public String getCodigoCorreo(){
    return codigoCorreo;
}


public void setConfirmacioncorreo(String confirmacioncorreo){
    this.confirmacioncorreo = confirmacioncorreo;
}


public List<Log_sys> getLog_sys_s(){
  this.Log_sys_s = log_sysrequestimpl.getLog_sys_s(this.pk_id_usuario_sys);
return this.Log_sys_s;
}


public void setRfc(String rfc){
    this.rfc = rfc;
}


public void setApellido_paterno(String apellido_paterno){
    this.apellido_paterno = apellido_paterno;
}


public void setPk_id_usuario_sys(int pk_id_usuario_sys){
    this.pk_id_usuario_sys = pk_id_usuario_sys;
}


public String getRfc(){
    return rfc;
}


public String getPassword(){
    return password;
}


public int getPk_id_usuario_sys(){
    return pk_id_usuario_sys;
}


public void setConfirmacion(String confirmacion){
    this.confirmacion = confirmacion;
}


public void setCodigoCorreo(String codigoCorreo){
    this.codigoCorreo = codigoCorreo;
}


}