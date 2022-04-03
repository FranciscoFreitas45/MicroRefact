package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.List;
import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
import es.gva.dgti.gvgeoportal.service.domain.ConfCapasTematicasService;
public class ConfCapasTematicasServiceImpl implements ConfCapasTematicasService{


public List<ConfCapasTematicas> findConfCapasTematicasesByGeoPortal(GeoPortal geoPortal){
    List<ConfCapasTematicas> result = null;
    TypedQuery<ConfCapasTematicas> confCapasTematicasResult = null;
    confCapasTematicasResult = ConfCapasTematicas.findConfCapasTematicasesByGeoPortal(geoPortal);
    if (confCapasTematicasResult != null && confCapasTematicasResult.getResultList().size() > 0) {
        result = confCapasTematicasResult.getResultList();
    }
    return result;
}


}