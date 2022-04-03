package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.Componentes.class })
public interface ComponentesService {


public List<Componentes> findComponentesByGeoPortal(GeoPortal geoPortal)
;

}