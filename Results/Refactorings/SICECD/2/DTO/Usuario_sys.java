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
public class Usuario_sys {

 private int pk_id_usuario_sys;

 private String rfc;

 private String password;

 private String correo;

 private String nombre;

 private String apellido_paterno;

 private String apellido_materno;

 private String confirmacion;

 private String codigo;

 private String confirmacioncorreo;

 private String codigoCorreo;

 private String correocambio;

 private String codigorecupera;

 private String confirmarecupera;

 private Estatus_usuario_sys fk_id_estatus_usuario_sys;

 private Perfil_sys fk_id_perfil_sys;

 private  List<Log_sys> Log_sys_s;


public String getCorreocambio(){
    return correocambio;
}


public String getApellido_paterno(){
    return apellido_paterno;
}


public String getApellido_materno(){
    return apellido_materno;
}


public Perfil_sys getFk_id_perfil_sys(){
    return fk_id_perfil_sys;
}


public String getCorreo(){
    return correo;
}


public String getConfirmacion(){
    return confirmacion;
}


public Estatus_usuario_sys getFk_id_estatus_usuario_sys(){
    return fk_id_estatus_usuario_sys;
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


public String getNombre(){
    return nombre;
}


public String getCodigoCorreo(){
    return codigoCorreo;
}


public List<Log_sys> getLog_sys_s(){
    return Log_sys_s;
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


}