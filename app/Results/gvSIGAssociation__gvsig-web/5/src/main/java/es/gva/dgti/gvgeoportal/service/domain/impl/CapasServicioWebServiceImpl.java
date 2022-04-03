package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.List;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.service.domain.CapasServicioWebService;
public class CapasServicioWebServiceImpl implements CapasServicioWebService{


public List<CapasServicioWeb> findCapasServicioWebsByServicioWeb(ServicioWeb servicioWeb){
    return CapasServicioWeb.findCapasServicioWebsByServicioWeb(servicioWeb).getResultList();
}


}