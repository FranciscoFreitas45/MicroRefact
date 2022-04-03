package es.gva.dgti.gvgeoportal.domain;
 import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierField = "id")
@GvNIXJpaAudit
public class AgrupadorCapaServicioWeb {

@NotNull
@ManyToOne
 private  AgrupadorCapa agrupador;

@NotNull
@ManyToOne
 private  ServicioWeb servicioWeb;


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebNotInIdListAndGroup(List<Long> idList,AgrupadorCapa agrupadorCapa){
    if (idList == null) {
        throw new IllegalArgumentException("The idList argument is required");
    }
    if (agrupadorCapa == null) {
        throw new IllegalArgumentException("The idList agrupadorCapa is required");
    }
    EntityManager em = AgrupadorCapaServicioWeb.entityManager();
    TypedQuery<AgrupadorCapaServicioWeb> q = em.createQuery("SELECT o FROM AgrupadorCapaServicioWeb AS o WHERE id NOT IN (:idList) AND agrupador = :agrupadorCapa", AgrupadorCapaServicioWeb.class);
    q.setParameter("idList", idList);
    q.setParameter("agrupadorCapa", agrupadorCapa);
    return q;
}


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebInIdList(List<Long> idList){
    if (idList == null) {
        throw new IllegalArgumentException("The idList argument is required");
    }
    EntityManager em = AgrupadorCapaServicioWeb.entityManager();
    TypedQuery<AgrupadorCapaServicioWeb> q = em.createQuery("SELECT o FROM AgrupadorCapaServicioWeb AS o WHERE id IN (:idList)", AgrupadorCapaServicioWeb.class);
    q.setParameter("idList", idList);
    return q;
}


public String toString(){
    return agrupador.getNombre().concat("-").concat(servicioWeb.getNombre());
}


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebByGroup(AgrupadorCapa agrupadorCapa){
    if (agrupadorCapa == null) {
        throw new IllegalArgumentException("The agrupadorCapa argument is required");
    }
    EntityManager em = AgrupadorCapaServicioWeb.entityManager();
    TypedQuery<AgrupadorCapaServicioWeb> q = em.createQuery("SELECT o FROM AgrupadorCapaServicioWeb o WHERE agrupador= :agrupadorCapa", AgrupadorCapaServicioWeb.class);
    q.setParameter("agrupadorCapa", agrupadorCapa);
    return q;
}


public TypedQuery<Long> findServicesByGroup(Long agrupadorCapaId){
    if (agrupadorCapaId == null) {
        throw new IllegalArgumentException("The agrupadorCapaId argument is required");
    }
    EntityManager em = AgrupadorCapaServicioWeb.entityManager();
    TypedQuery<Long> q = em.createQuery("SELECT DISTINCT(servicioWeb.id) FROM AgrupadorCapaServicioWeb WHERE agrupador.id= :agrupadorCapaId", Long.class);
    q.setParameter("agrupadorCapaId", agrupadorCapaId);
    return q;
}


}