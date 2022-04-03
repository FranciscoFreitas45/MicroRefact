package es.gva.dgti.gvgeoportal.service.domain.impl;
 import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfAyudaBuscador;
import es.gva.dgti.gvgeoportal.service.domain.ConfAyudaBuscadorService;
public class ConfAyudaBuscadorServiceImpl implements ConfAyudaBuscadorService{


public ConfAyudaBuscador findConfAyudaBuscadorsByGeoPortal(GeoPortal geoPortal){
    ConfAyudaBuscador result = null;
    TypedQuery<ConfAyudaBuscador> confAyudaBuscadorResult = null;
    confAyudaBuscadorResult = ConfAyudaBuscador.findConfAyudaBuscadorsByGeoPortal(geoPortal);
    if (confAyudaBuscadorResult != null && confAyudaBuscadorResult.getResultList().size() > 0) {
        result = confAyudaBuscadorResult.getSingleResult();
    }
    return result;
}


}