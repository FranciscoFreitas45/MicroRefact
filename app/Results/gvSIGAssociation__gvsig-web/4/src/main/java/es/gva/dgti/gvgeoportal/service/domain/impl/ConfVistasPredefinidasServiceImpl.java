package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
import es.gva.dgti.gvgeoportal.service.domain.ConfVistasPredefinidasService;
public class ConfVistasPredefinidasServiceImpl implements ConfVistasPredefinidasService{


public void deleteConfVistasPredefinidasServicioWebByGeoPortal(GeoportalServicioWeb geoPortalServicioWeb){
    ServicioWeb servicioWebToDelete = geoPortalServicioWeb.getServicioWeb();
    List<ConfVistasPredefinidas> listConfVistasPredefinidas = findConfVistasPredefinidasesByGeoPortal(geoPortalServicioWeb.getGeoportal());
    if (listConfVistasPredefinidas != null && listConfVistasPredefinidas.size() > 0) {
        for (ConfVistasPredefinidas confVistasPredefinidas : listConfVistasPredefinidas) {
            Set<ServicioWeb> serviciosWeb = confVistasPredefinidas.getServiciosWeb();
            if (serviciosWeb.contains(servicioWebToDelete)) {
                serviciosWeb.remove(servicioWebToDelete);
                // if webservices from predefinied view is empty, delete the view
                if (serviciosWeb.isEmpty()) {
                    deleteConfVistasPredefinidas(confVistasPredefinidas);
                } else {
                    // is not empty, delete only the web service
                    confVistasPredefinidas.setServiciosWeb(serviciosWeb);
                    saveConfVistasPredefinidas(confVistasPredefinidas);
                }
            }
        }
    }
}


public List<ConfVistasPredefinidas> findConfVistasPredefinidasesByGeoPortal(GeoPortal geoPortal){
    List<ConfVistasPredefinidas> result = null;
    TypedQuery<ConfVistasPredefinidas> confVistasPredefinidasResult = null;
    confVistasPredefinidasResult = ConfVistasPredefinidas.findConfVistasPredefinidasesByGeoPortal(geoPortal);
    if (confVistasPredefinidasResult != null && confVistasPredefinidasResult.getResultList().size() > 0) {
        result = confVistasPredefinidasResult.getResultList();
    }
    return result;
}


}