package es.gva.dgti.gvgeoportal.service.domain;
 import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.ServicioWeb.class })
public interface ServicioWebService {


public boolean containsCrs(Collection<SistemaCoordenadas> sistemasCoordenadas,String crs)
;

public String getSelectedInfoLayersNames(Collection<CapasServicioWeb> listadoCapasServicioWeb,boolean isLayerName)
;

public String findTitleLayer(List<String> tituloCapas,String nombreCapa)
;

public String transformCrsWmts(String selectedCrs)
;

public String findStyleLayer(List<String> estilos,String nombreCapa)
;

public Set<SistemaCoordenadas> getSelectedCrs(String coordenadasSeleccionadas)
;

public String getStylesNames(Collection<CapasServicioWeb> listadoCapasServicioWeb)
;

public TypedQuery<ServicioWeb> findServicesByNoGroup(AgrupadorCapa agrupadorCapa)
;

public String getFirstCrsName(Collection<SistemaCoordenadas> sistemasCoordenadas)
;

public String getSelectedStyles(Collection<CapasServicioWeb> listadoCapasServicioWeb)
;

public String getSelectedCrsNames(Collection<SistemaCoordenadas> sistemasCoordenadas)
;

public Set<CapasServicioWeb> getSelectedLayersAndStyles(String capasSeleccionadas,String tituloCapasSeleccionadas,String estiloCapasSeleccionadas,String tipo,ServicioWeb servicioWeb)
;

public Map<String,String> getLayersAndStylesOrderByLayersName(ServicioWeb servicioWeb)
;

public TypedQuery<ServicioWeb> findServicesInIdList(List<Long> idList)
;

public TypedQuery<ServicioWeb> findServicesNotInIdListAndNoGroup(List<Long> idList,AgrupadorCapa agrupadorCapa)
;

}