package es.gva.dgti.gvgeoportal.service.domain;
 import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa.class })
public interface ConfMiniMapaService {


public ConfMiniMapa findConfMiniMapasByGeoPortal(GeoPortal geoPortal)
;

}