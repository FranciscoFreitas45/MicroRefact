package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.service.domain.ComponentesService;
public class ComponentesServiceImpl implements ComponentesService{


public List<Componentes> findComponentesByGeoPortal(GeoPortal geoPortal){
    List<Componentes> listComponentsSelec = new ArrayList<Componentes>();
    // obtenemos componentes dado un geoPortal
    TypedQuery<Componentes> componentesAntiguos = Componentes.findComponentesesByGeoPortal(geoPortal);
    if (componentesAntiguos != null) {
        listComponentsSelec = componentesAntiguos.getResultList();
    }
    return listComponentsSelec;
}


}