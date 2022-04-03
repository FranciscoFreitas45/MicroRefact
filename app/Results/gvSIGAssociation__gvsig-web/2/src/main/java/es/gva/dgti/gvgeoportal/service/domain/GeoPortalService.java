package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.GeoPortal.class })
public interface GeoPortalService {


public TypedQuery<GeoPortal> findGeoPortalesByUrlEquals(String url,String sortFieldName,String sortOrder)
;

public Long countFindGeoPortalesByUrlEquals(String url)
;

public Map<String,Object> getComponentsAndInformationByGeoportal(GeoPortal geoportal)
;

public String getFullUrlPortal(String url,String scheme,String serverName,int port,String contextPath)
;

public GeoPortal clone(GeoPortal geoPortal)
;

public List<TipoComponente> checkSelectedComponentes(HttpServletRequest httpServletRequest)
;

public TypedQuery<GeoPortal> findPublicGeoPortalesByUrlEquals(String url)
;

}