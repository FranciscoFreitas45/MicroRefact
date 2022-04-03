package es.gva.dgti.gvgeoportal.domain.components;
 import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.hibernate.validator.constraints.NotEmpty;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapa;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findConfCapasTematicasesByGeoPortal" })
@GvNIXJpaAudit
public class ConfCapasTematicas extends Componentes{

@NotNull
@NotEmpty
 private  String nombre;

@ManyToOne
 private  AgrupadorCapa grupo;

 private  byte[] logo;

@Transient
 private  String logoString;


public String getLogoString(){
    if (logo == null) {
        return this.logoString;
    } else {
        return new String(this.logo);
    }
}


}