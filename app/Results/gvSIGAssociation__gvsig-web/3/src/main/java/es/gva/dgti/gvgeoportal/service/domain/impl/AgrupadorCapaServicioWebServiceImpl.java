package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.List;
import javax.persistence.TypedQuery;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaServicioWebService;
public class AgrupadorCapaServicioWebServiceImpl implements AgrupadorCapaServicioWebService{


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebNotInIdListAndGroup(List<Long> idList,AgrupadorCapa agrupadorCapa){
    return AgrupadorCapaServicioWeb.findAgrupadorCapaServicioWebNotInIdListAndGroup(idList, agrupadorCapa);
}


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebInIdList(List<Long> idList){
    return AgrupadorCapaServicioWeb.findAgrupadorCapaServicioWebInIdList(idList);
}


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebByGroup(AgrupadorCapa agrupadorCapa){
    return AgrupadorCapaServicioWeb.findAgrupadorCapaServicioWebByGroup(agrupadorCapa);
}


public TypedQuery<Long> findServicesByGroup(Long agrupadorCapaId){
    return AgrupadorCapaServicioWeb.findServicesByGroup(agrupadorCapaId);
}


}