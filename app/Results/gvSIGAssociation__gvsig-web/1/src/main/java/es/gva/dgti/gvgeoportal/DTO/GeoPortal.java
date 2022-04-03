package es.gva.dgti.gvgeoportal.DTO;
 import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.plural.RooPlural;
import org.springframework.roo.addon.tostring.RooToString;
public class GeoPortal {

 private  String titulo;

 private  String descripcion;

 private  String subtitulo;

 private  byte[] logo;

 private  String logoString;

 private  String alias;

 private  boolean publicado;

 private  String centro;

 private  Integer zoom;

 private  Integer minZoom;

 private  Integer maxZoom;

 private  Calendar fechaAlta;

 private  Calendar fechaBaja;

 private  Set<AgrupadorCapa> agrupadorCapa;

 private  Set<GeoportalServicioWeb> serviciosToc;

 private  SistemaCoordenadas coordenadas;

 private  String url;

 private  String urlCompleta;


public String getLogoString(){
    if (logo == null) {
        return this.logoString;
    } else {
        return new String(this.logo);
    }
}


}