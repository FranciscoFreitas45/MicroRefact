package es.gva.dgti.gvgeoportal.domain;
 import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
@RooJavaBean
@RooToString
@GvNIXJpaAudit
@RooJpaActiveRecord(sequenceName = "capas_servicio_web_id_seq", identifierField = "id", finders = { "findCapasServicioWebsByServicioWeb" })
public class CapasServicioWeb {

@NotNull
 private  String nombreCapa;

 private  String tituloCapa;

 private  String estiloCapa;

@ManyToOne
 private  ServicioWeb servicioWeb;


}