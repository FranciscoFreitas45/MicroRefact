package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.List;
import es.gva.dgti.gvgeoportal.domain.GestorCatalogo;
import es.gva.dgti.gvgeoportal.service.domain.GestorCatalogoService;
public class GestorCatalogoServiceImpl implements GestorCatalogoService{


public List<GestorCatalogo> findAllGestorCatalogos(String sortFieldName,String sortOrder){
    return GestorCatalogo.findAllGestorCatalogos(sortFieldName, sortOrder);
}


}