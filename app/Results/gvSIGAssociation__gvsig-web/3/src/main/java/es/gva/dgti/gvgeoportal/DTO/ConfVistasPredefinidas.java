package es.gva.dgti.gvgeoportal.DTO;
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
public class ConfVistasPredefinidas extends Componentes{

 private  String nombre;

 private  byte[] logo;

 private  String logoString;

 private  Set<ServicioWeb> serviciosWeb;

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