import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
public class Log_sys {

 private int pk_id_log_sys;

 private String ip;

 private Date hora;

 private String comentario;

 private Usuario_sys fk_id_usuario_sys;

 private Log_evento_sys fk_id_log_evento_sys;

 private int pk_id_usuario_sys;

 private Usuario_sysRequestImpl usuario_sysrequestimpl;


public int getPk_id_log_sys(){
    return pk_id_log_sys;
}


public String getIp(){
    return ip;
}


public String getComentario(){
    return comentario;
}


public Date getHora(){
    return hora;
}


public Usuario_sys getFk_id_usuario_sys(){
  this.fk_id_usuario_sys = usuario_sysrequestimpl.getFk_id_usuario_sys(this.int);
return this.fk_id_usuario_sys;
}


public Log_evento_sys getFk_id_log_evento_sys(){
    return fk_id_log_evento_sys;
}


}