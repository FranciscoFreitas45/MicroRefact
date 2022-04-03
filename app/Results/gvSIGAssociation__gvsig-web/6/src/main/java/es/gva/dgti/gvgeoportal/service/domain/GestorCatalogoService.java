package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.GestorCatalogo;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.GestorCatalogo.class })
public interface GestorCatalogoService {


public List<GestorCatalogo> findAllGestorCatalogos(String sortFieldName,String sortOrder)
;

}