package es.gva.dgti.gvgeoportal.domain.components;
 import java.util.HashSet;
import java.util.Set;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.hibernate.validator.constraints.NotEmpty;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findConfVistasPredefinidasesByGeoPortal" })
@GvNIXJpaAudit
public class ConfVistasPredefinidas extends Componentes{

@NotNull
@NotEmpty
 private  String nombre;

 private  byte[] logo;

@Transient
 private  String logoString;

@ManyToMany(cascade = { javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE })
@JoinTable(name = "vistas_predefinidas_servicio_web")
 private  Set<ServicioWeb> serviciosWeb;

@Transient
 private  String serviciosWebString;


public String getServiciosWebString(){
    String swString = "";
    if (!serviciosWeb.isEmpty()) {
        for (ServicioWeb servicioWeb : serviciosWeb) {
            if (swString == "") {
                swString = servicioWeb.getDescripcion();
            } else {
                swString = swString + ", " + servicioWeb.getDescripcion();
            }
        }
    }
    return swString;
}


public String getLogoString(){
    if (logo == null) {
        return this.logoString;
    } else {
        return new String(this.logo);
    }
}


}