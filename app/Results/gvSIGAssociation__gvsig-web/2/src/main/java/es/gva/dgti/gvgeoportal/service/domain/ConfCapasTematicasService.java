package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas.class })
public interface ConfCapasTematicasService {


public List<ConfCapasTematicas> findConfCapasTematicasesByGeoPortal(GeoPortal geoPortal)
;

}