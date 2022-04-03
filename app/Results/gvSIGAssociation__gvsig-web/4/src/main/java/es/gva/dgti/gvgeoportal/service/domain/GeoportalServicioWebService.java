package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb.class })
public interface GeoportalServicioWebService {


public TypedQuery<GeoportalServicioWeb> findGeoportalServicioWebByGeoportalAndServicioWeb(GeoPortal geoportal,ServicioWeb servicioWeb)
;

public ResponseEntity<Map<String,Boolean>> updateTocByGeoportal(GeoPortal geoportal,Map<String,Object> jsonMapEditionStatus)
;

public List<GeoportalServicioWeb> findGeoportalServicioWebsByGeoportal(GeoPortal geoPortal)
;

}