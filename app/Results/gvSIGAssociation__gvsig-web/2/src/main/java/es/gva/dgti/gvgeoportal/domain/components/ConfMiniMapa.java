package es.gva.dgti.gvgeoportal.domain.components;
 import es.gva.dgti.gvgeoportal.domain.Componentes;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import javax.persistence.ManyToOne;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findConfMiniMapasByGeoPortal" })
@GvNIXJpaAudit
public class ConfMiniMapa extends Componentes{

@ManyToOne
 private  ServicioWeb servicioWeb;


}