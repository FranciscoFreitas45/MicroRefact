package es.gva.dgti.gvgeoportal.service.domain;
 import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfAyudaBuscador;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.components.ConfAyudaBuscador.class })
public interface ConfAyudaBuscadorService {


public ConfAyudaBuscador findConfAyudaBuscadorsByGeoPortal(GeoPortal geoPortal)
;

}