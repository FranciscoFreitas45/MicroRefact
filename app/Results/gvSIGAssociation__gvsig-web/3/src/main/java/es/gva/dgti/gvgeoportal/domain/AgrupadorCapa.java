package es.gva.dgti.gvgeoportal.domain;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.plural.RooPlural;
import org.springframework.roo.addon.tostring.RooToString;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(sequenceName = "agrupador_capa_id_seq", identifierField = "id")
@RooPlural("AgrupadorCapas")
@GvNIXJpaAudit
public class AgrupadorCapa {

@NotNull
@NotEmpty
 private  String nombre;

@NotNull
@NotEmpty
 private  String descripcion;

@Id
@SequenceGenerator(name = "agrupadorCapaGen", sequenceName = "agrupador_capa_id_seq")
@GeneratedValue(strategy = GenerationType.AUTO, generator = "agrupadorCapaGen")
@Column(name = "id")
 private  Long id;

@ManyToMany(cascade = CascadeType.ALL, mappedBy = "agrupadorCapa")
 private  Set<GeoPortal> geoPortal;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "agrupador")
@OrderBy("id ASC")
 private  Set<AgrupadorCapaServicioWeb> servicios;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
 private  Set<ConfCapasTematicas> confCapasTematicas;


public String toString(){
    return this.nombre;
}


}