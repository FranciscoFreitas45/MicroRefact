package es.gva.dgti.gvgeoportal.DTO;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.plural.RooPlural;
import org.springframework.roo.addon.tostring.RooToString;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoServicio;
public class ServicioWeb {

 private  Long id;

 private  String nombre;

 private  String descripcion;

 private  String url;

 private  String versionProtocolo;

 private  TipoServicio tipo;

 private  Set<SistemaCoordenadas> coordenadas;

 private  Set<ConfVistasPredefinidas> confVistasPredefinidas;

 private  Set<CapasServicioWeb> capasServicioWeb;

 private  String formatoImagen;

 private  String nombresCapas;

 private  String estilosCapas;

 private  String tileMatrixSet;


public String toString(){
    return this.nombre;
}


public TypedQuery<ServicioWeb> findServicesByNoGroup(AgrupadorCapa agrupadorCapa){
    if (agrupadorCapa == null) {
        throw new IllegalArgumentException("The agrupadorCapa argument is required");
    }
    EntityManager em = ServicioWeb.entityManager();
    TypedQuery<ServicioWeb> q = em.createQuery("SELECT sw FROM ServicioWeb sw WHERE id NOT IN (SELECT DISTINCT servicioWeb FROM AgrupadorCapaServicioWeb where agrupador= :agrupadorCapa)", ServicioWeb.class);
    q.setParameter("agrupadorCapa", agrupadorCapa);
    return q;
}


public TypedQuery<ServicioWeb> findServicesInIdList(List<Long> idList){
    if (idList == null) {
        throw new IllegalArgumentException("The idList argument is required");
    }
    EntityManager em = ServicioWeb.entityManager();
    TypedQuery<ServicioWeb> q = em.createQuery("SELECT o FROM ServicioWeb AS o WHERE id IN (:idList)", ServicioWeb.class);
    q.setParameter("idList", idList);
    return q;
}


public TypedQuery<ServicioWeb> findServicesNotInIdListAndNoGroup(List<Long> idList,AgrupadorCapa agrupadorCapa){
    if (idList == null) {
        throw new IllegalArgumentException("The idList argument is required");
    }
    if (agrupadorCapa == null) {
        throw new IllegalArgumentException("The agrupadorCapa argument is required");
    }
    EntityManager em = ServicioWeb.entityManager();
    TypedQuery<ServicioWeb> q = em.createQuery("SELECT sw FROM ServicioWeb sw WHERE id NOT IN (SELECT DISTINCT servicioWeb FROM AgrupadorCapaServicioWeb where agrupador= :agrupadorCapa) AND id NOT IN (:idList)", ServicioWeb.class);
    q.setParameter("idList", idList);
    q.setParameter("agrupadorCapa", agrupadorCapa);
    return q;
}


}