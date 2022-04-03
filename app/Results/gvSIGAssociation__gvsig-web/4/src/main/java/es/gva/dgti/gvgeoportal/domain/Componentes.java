package es.gva.dgti.gvgeoportal.domain;
 import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
import es.gva.dgti.gvgeoportal.Interface.GeoPortal;
@RooJavaBean
@RooToString
@GvNIXJpaAudit
@RooJpaActiveRecord(sequenceName = "componentes_id_seq", identifierField = "id", inheritanceType = "JOINED", finders = { "findComponentesesByGeoPortal" })
public class Componentes {

@ManyToOne
 private  GeoPortal geoPortal;

@NotNull
@Enumerated(EnumType.STRING)
 private  TipoComponente tipo;


public TypedQuery<Componentes> findComponentesesByGeoPortal(GeoPortal geoPortal){
    if (geoPortal == null) {
        throw new IllegalArgumentException("The geoPortal argument is required");
    }
    EntityManager em = Componentes.entityManager();
    TypedQuery<Componentes> q = em.createQuery("SELECT o FROM Componentes AS o WHERE o.geoPortal = :geoPortal", Componentes.class);
    q.setParameter("geoPortal", geoPortal);
    return q;
}


}