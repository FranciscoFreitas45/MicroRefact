package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb.class })
public interface AgrupadorCapaServicioWebService {


public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebNotInIdListAndGroup(List<Long> idList,AgrupadorCapa agrupadorCapa)
;

public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebInIdList(List<Long> idList)
;

public TypedQuery<AgrupadorCapaServicioWeb> findAgrupadorCapaServicioWebByGroup(AgrupadorCapa agrupadorCapa)
;

public TypedQuery<Long> findServicesByGroup(Long agrupadorCapaId)
;

}