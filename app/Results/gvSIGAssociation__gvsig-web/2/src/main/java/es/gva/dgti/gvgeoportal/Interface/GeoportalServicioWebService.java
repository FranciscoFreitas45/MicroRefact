package es.gva.dgti.gvgeoportal.Interface;
public interface GeoportalServicioWebService {

   public List<GeoportalServicioWeb> findGeoportalServicioWebsByGeoportal(GeoPortal geoPortal);
   public Object saveGeoportalServicioWeb(Object Object);
}