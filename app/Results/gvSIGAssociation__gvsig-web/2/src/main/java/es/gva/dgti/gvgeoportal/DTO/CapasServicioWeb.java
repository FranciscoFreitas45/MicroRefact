package es.gva.dgti.gvgeoportal.DTO;
 import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
public class CapasServicioWeb {

 private  String nombreCapa;

 private  String tituloCapa;

 private  String estiloCapa;

 private  ServicioWeb servicioWeb;


}