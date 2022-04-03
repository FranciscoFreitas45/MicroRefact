package es.gva.dgti.gvgeoportal.Interface;
public interface GeoPortalService {

   public TypedQuery<GeoPortal> findPublicGeoPortalesByUrlEquals(String url);
   public TypedQuery<GeoPortal> findGeoPortalesByUrlEquals(String url,String sortFieldName,String sortOrder);
   public Map<String,Object> getComponentsAndInformationByGeoportal(GeoPortal geoportal);
}