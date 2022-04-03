package es.gva.dgti.gvgeoportal.service.domain.impl;
 import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa;
import es.gva.dgti.gvgeoportal.service.domain.ConfMiniMapaService;
public class ConfMiniMapaServiceImpl implements ConfMiniMapaService{


public ConfMiniMapa findConfMiniMapasByGeoPortal(GeoPortal geoPortal){
    ConfMiniMapa result = null;
    TypedQuery<ConfMiniMapa> confMiniMapaResult = null;
    confMiniMapaResult = ConfMiniMapa.findConfMiniMapasByGeoPortal(geoPortal);
    if (confMiniMapaResult != null && confMiniMapaResult.getResultList().size() > 0) {
        result = confMiniMapaResult.getSingleResult();
    }
    return result;
}


}