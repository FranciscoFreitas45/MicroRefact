package es.gva.dgti.gvgeoportal.Interface;
public interface ConfVistasPredefinidasService {

   public Object findConfVistasPredefinidas(Object Object);
   public Object saveConfVistasPredefinidas(Object Object);
   public List<ConfVistasPredefinidas> findConfVistasPredefinidasesByGeoPortal(GeoPortal geoPortal);
   public Object deleteConfVistasPredefinidas(Object Object);
}