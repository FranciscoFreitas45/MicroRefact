package es.gva.dgti.gvgeoportal.Interface;
public interface GestorCatalogoService {

   public List<GestorCatalogo> findAllGestorCatalogos(String sortFieldName,String sortOrder);
}