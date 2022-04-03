package es.gva.dgti.gvgeoportal.service.domain.impl;
 import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaService;
public class AgrupadorCapaServiceImpl implements AgrupadorCapaService{


public AgrupadorCapa clone(AgrupadorCapa agrupadorCapa){
    // creamos objeto nuevo
    AgrupadorCapa newAgrupador = new AgrupadorCapa();
    // seteamos valores
    newAgrupador.setNombre(agrupadorCapa.getNombre());
    newAgrupador.setDescripcion(agrupadorCapa.getDescripcion());
    return newAgrupador;
}


}