package es.gva.dgti.gvgeoportal.domain;
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
import es.gva.dgti.gvgeoportal.Interface.SistemaCoordenadas;
@RooJavaBean
@RooToString
@RooPlural("GeoPortales")
@GvNIXJpaAudit
@RooJpaActiveRecord(sequenceName = "geoportal_id_seq", identifierField = "id", finders = { "findGeoPortalesByTituloLike", "findGeoPortalesByUrlEquals" })
public class GeoPortal {

@NotNull
@NotEmpty
 private  String titulo;

 private  String descripcion;

 private  String subtitulo;

 private  byte[] logo;

@Transient
 private  String logoString;

 private  String alias;

@NotNull
 private  boolean publicado;

 private  String centro;

 private  Integer zoom;

 private  Integer minZoom;

 private  Integer maxZoom;

@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Calendar fechaAlta;

@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Calendar fechaBaja;

@ManyToMany(cascade = { javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE })
@JoinTable(name = "agrupador_capa_geo_portales")
@OrderBy("id ASC")
 private  Set<AgrupadorCapa> agrupadorCapa;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "geoportal")
@OrderBy("posicion ASC")
 private  Set<GeoportalServicioWeb> serviciosToc;

@ManyToOne
 private  SistemaCoordenadas coordenadas;

@NotNull
@NotEmpty
@Column(unique = true)
 private  String url;

@Transient
 private  String urlCompleta;


public String toString(){
    return titulo;
}


public String getLogoString(){
    if (logo == null) {
        return this.logoString;
    } else {
        return new String(this.logo);
    }
}


public TypedQuery<GeoPortal> findGeoportalesByUrlAndPublic(String url,boolean publicado){
    if (url == null || url.length() == 0) {
        throw new IllegalArgumentException("The url argument is required");
    }
    TypedQuery<GeoPortal> q = entityManager().createQuery("SELECT o FROM GeoPortal AS o WHERE o.url = :url AND o.publicado = :publicado", GeoPortal.class);
    q.setParameter("url", url);
    q.setParameter("publicado", publicado);
    return q;
}


}