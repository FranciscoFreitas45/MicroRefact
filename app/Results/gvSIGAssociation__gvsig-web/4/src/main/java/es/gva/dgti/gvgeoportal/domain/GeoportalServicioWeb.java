package es.gva.dgti.gvgeoportal.domain;
 import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import es.gva.dgti.gvgeoportal.Interface.GeoPortal;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
@RooJavaBean
@RooToString
@GvNIXJpaAudit
@RooJpaActiveRecord(identifierField = "id", finders = { "findGeoportalServicioWebsByGeoportal" })
public class GeoportalServicioWeb {

@NotNull
@ManyToOne
 private  GeoPortal geoportal;

@NotNull
@ManyToOne
 private  ServicioWeb servicioWeb;

@NotNull
 private  BigDecimal opacidad;

@NotNull
 private  boolean activo;

@NotNull
 private  boolean habilitado;

@NotNull
 private  Integer posicion;


public TypedQuery<GeoportalServicioWeb> findGeoportalServicioWebByGeoportalAndServicioWeb(GeoPortal geoportal,ServicioWeb servicioWeb){
    if (geoportal == null) {
        throw new IllegalArgumentException("The geoportal argument is required");
    }
    if (servicioWeb == null) {
        throw new IllegalArgumentException("The servicioWeb argument is required");
    }
    TypedQuery<GeoportalServicioWeb> q = entityManager().createQuery("SELECT o FROM GeoportalServicioWeb AS o WHERE o.geoportal = :geoportal AND o.servicioWeb = :servicioWeb", GeoportalServicioWeb.class);
    q.setParameter("geoportal", geoportal);
    q.setParameter("servicioWeb", servicioWeb);
    return q;
}


}