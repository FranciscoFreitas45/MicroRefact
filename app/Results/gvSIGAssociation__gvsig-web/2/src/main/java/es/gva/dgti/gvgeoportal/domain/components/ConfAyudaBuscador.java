package es.gva.dgti.gvgeoportal.domain.components;
 import es.gva.dgti.gvgeoportal.domain.Componentes;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
@RooJavaBean
@RooToString
@GvNIXJpaAudit
@RooJpaActiveRecord(finders = { "findConfAyudaBuscadorsByGeoPortal" })
public class ConfAyudaBuscador extends Componentes{

 private  String textoAyuda;


}