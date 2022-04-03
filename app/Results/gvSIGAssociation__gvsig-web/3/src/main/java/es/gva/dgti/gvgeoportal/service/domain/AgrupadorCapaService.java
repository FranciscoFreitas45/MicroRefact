package es.gva.dgti.gvgeoportal.service.domain;
 import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.AgrupadorCapa.class })
public interface AgrupadorCapaService {


public AgrupadorCapa clone(AgrupadorCapa agrupadorCapa)
;

}