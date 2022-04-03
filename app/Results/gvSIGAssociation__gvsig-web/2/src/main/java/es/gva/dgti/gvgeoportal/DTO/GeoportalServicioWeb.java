package es.gva.dgti.gvgeoportal.DTO;
 import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
public class GeoportalServicioWeb {

 private  GeoPortal geoportal;

 private  ServicioWeb servicioWeb;

 private  BigDecimal opacidad;

 private  boolean activo;

 private  boolean habilitado;

 private  Integer posicion;


}