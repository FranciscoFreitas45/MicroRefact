package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas.class })
public interface ConfVistasPredefinidasService {


public void deleteConfVistasPredefinidasServicioWebByGeoPortal(GeoportalServicioWeb geoPortalServicioWeb)
;

public List<ConfVistasPredefinidas> findConfVistasPredefinidasesByGeoPortal(GeoPortal geoPortal)
;

}