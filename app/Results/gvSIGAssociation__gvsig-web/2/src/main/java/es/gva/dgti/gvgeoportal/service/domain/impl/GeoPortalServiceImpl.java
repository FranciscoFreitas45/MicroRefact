package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.comparator.OrdenarCapasServicioWebPorNombreCapa;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.components.ConfAyudaBuscador;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
import es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
import es.gva.dgti.gvgeoportal.service.domain.CapasServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.ComponentesService;
import es.gva.dgti.gvgeoportal.service.domain.ConfCapasTematicasService;
import es.gva.dgti.gvgeoportal.service.domain.ConfVistasPredefinidasService;
import es.gva.dgti.gvgeoportal.service.domain.GeoPortalService;
import es.gva.dgti.gvgeoportal.service.domain.ServicioWebService;
import es.gva.dgti.gvgeoportal.util.Constants;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.CapasServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasService;
public class GeoPortalServiceImpl implements GeoPortalService{

@Autowired(required = false)
 private ComponentesService componentesService;

@Autowired(required = false)
 private ServicioWebService servicioWebService;

@Autowired(required = false)
 private CapasServicioWebService capasServicioWebService;

@Autowired(required = false)
 private ConfVistasPredefinidasService confVistasPredefinidasService;

@Autowired(required = false)
 private ConfCapasTematicasService confCapasTematicasService;

@SuppressWarnings("unused")
 private  Logger LOGGER;


public Map<String,Object> obtainInfoForGeoportal(ServicioWeb servicioWeb){
    Map<String, Object> capa = new HashMap<String, Object>();
    capa.put("identificador", Long.toString(servicioWeb.getId()));
    capa.put("nombreCapa", servicioWeb.getNombre());
    capa.put("url", servicioWeb.getUrl());
    capa.put("tipo", servicioWeb.getTipo());
    capa.put("versionProtocolo", servicioWeb.getVersionProtocolo());
    List<CapasServicioWeb> capasServicioWeb = capasServicioWebService.findCapasServicioWebsByServicioWeb(servicioWeb);
    Collections.sort(capasServicioWeb, new OrdenarCapasServicioWebPorNombreCapa());
    capa.put("capasServicioWeb", capasServicioWeb);
    capa.put("capas", servicioWebService.getSelectedInfoLayersNames(capasServicioWeb, true));
    capa.put("estilos", servicioWebService.getSelectedStyles(capasServicioWeb));
    return capa;
}


public TypedQuery<GeoPortal> findGeoPortalesByUrlEquals(String url,String sortFieldName,String sortOrder){
    return GeoPortal.findGeoPortalesByUrlEquals(url, sortFieldName, sortOrder);
}


public Long countFindGeoPortalesByUrlEquals(String url){
    return GeoPortal.countFindGeoPortalesByUrlEquals(url);
}


public Map<String,Object> getComponentsAndInformationByGeoportal(GeoPortal geoportal){
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("geoportal", geoportal);
    List<Componentes> componentes = componentesService.findComponentesByGeoPortal(geoportal);
    for (Componentes componente : componentes) {
        TipoComponente tipo = componente.getTipo();
        if (tipo != null) {
            map.put(tipo.name(), true);
            switch(tipo) {
                case anyadir_grupo_capas:
                    LinkedHashMap<String, List<Map<String, Object>>> grupos = new LinkedHashMap<String, List<Map<String, Object>>>();
                    // Recorremos todas las agrupaciones de capas vinculadas al
                    // geoportal
                    Set<AgrupadorCapa> agrupadoresCapa = geoportal.getAgrupadorCapa();
                    for (AgrupadorCapa agrupadorCapa : agrupadoresCapa) {
                        List<Map<String, Object>> capas = new ArrayList<Map<String, Object>>();
                        grupos.put(agrupadorCapa.getNombre(), capas);
                        // Recorremos los servicios web cartográficos de cada
                        // agrupación
                        Set<AgrupadorCapaServicioWeb> relAgrupadorServiciosWeb = agrupadorCapa.getServicios();
                        for (AgrupadorCapaServicioWeb relAgrupadorServicioWeb : relAgrupadorServiciosWeb) {
                            ServicioWeb servicioWeb = relAgrupadorServicioWeb.getServicioWeb();
                            if (servicioWeb.getCoordenadas().contains(geoportal.getCoordenadas())) {
                                // Obtenemos los datos necesarios del servicio web
                                // para usarlo en el geoportal
                                Map<String, Object> capa = obtainInfoForGeoportal(servicioWeb);
                                capas.add(capa);
                            }
                        }
                    }
                    map.put("grupos", grupos);
                    break;
                case minimapa:
                    // Hacemos cast para obtener la configuración del minimapa.
                    ConfMiniMapa configuracionMinimapa = (ConfMiniMapa) componente;
                    // Obtenemos los datos necesarios del servicio web para usarlo
                    // en el geoportal
                    Map<String, Object> capaMinimapa = obtainInfoForGeoportal(configuracionMinimapa.getServicioWeb());
                    map.put("capaMinimapa", capaMinimapa);
                    break;
                case vistas_predefinidas:
                    List<Map<String, Object>> vistasPredefinidas = new ArrayList<Map<String, Object>>();
                    List<ConfVistasPredefinidas> confVistasPredefinidas = confVistasPredefinidasService.findConfVistasPredefinidasesByGeoPortal(geoportal);
                    for (ConfVistasPredefinidas confVistasPredefinida : confVistasPredefinidas) {
                        Map<String, Object> vistaPredefinida = new HashMap<String, Object>();
                        List<Map<String, Object>> capas = new ArrayList<Map<String, Object>>();
                        vistaPredefinida.put("identificador", StringUtils.replace(confVistasPredefinida.getNombre(), " ", "_"));
                        vistaPredefinida.put("nombre", confVistasPredefinida.getNombre());
                        vistaPredefinida.put("logo", confVistasPredefinida.getLogoString());
                        String idCapasCSV = "";
                        Set<ServicioWeb> serviciosWeb = confVistasPredefinida.getServiciosWeb();
                        for (ServicioWeb servicioWeb : serviciosWeb) {
                            if (servicioWeb.getCoordenadas().contains(geoportal.getCoordenadas())) {
                                if (StringUtils.isNotBlank(idCapasCSV)) {
                                    idCapasCSV = idCapasCSV.concat(",");
                                }
                                idCapasCSV = idCapasCSV.concat(servicioWeb.getId().toString());
                                Map<String, Object> capa = obtainInfoForGeoportal(servicioWeb);
                                capas.add(capa);
                            }
                        }
                        vistaPredefinida.put("idCapas", idCapasCSV);
                        vistaPredefinida.put("capas", capas);
                        vistasPredefinidas.add(vistaPredefinida);
                    }
                    map.put("vistasPredefinidas", vistasPredefinidas);
                    break;
                case ayuda_buscador:
                    ConfAyudaBuscador ayudaBuscador = (ConfAyudaBuscador) componente;
                    String textoAyuda = ayudaBuscador.getTextoAyuda();
                    map.put("textoAyuda", textoAyuda);
                    break;
                case cargar_tematicos:
                    // Declaramos la variable que contendrá los grupos temáticos del geoportal para cargarlos en la vista
                    List<Map<String, Object>> gruposTematicos = new ArrayList<Map<String, Object>>();
                    // Obtenemos los grupos temáticos del geoportal
                    List<ConfCapasTematicas> confCapasTematicas = confCapasTematicasService.findConfCapasTematicasesByGeoPortal(geoportal);
                    // Para cada grupo temático añadimos sus atributos
                    for (ConfCapasTematicas confCapaTematica : confCapasTematicas) {
                        Map<String, Object> grupoTematico = new HashMap<String, Object>();
                        grupoTematico.put("identificador", StringUtils.replace(confCapaTematica.getNombre(), " ", "_"));
                        grupoTematico.put("nombre", confCapaTematica.getNombre());
                        grupoTematico.put("logo", confCapaTematica.getLogoString());
                        // Buscamos los servicios web relacionados con el grupo temático
                        AgrupadorCapa agrupacion = confCapaTematica.getGrupo();
                        Set<AgrupadorCapaServicioWeb> relAgrupadorServiciosWeb = agrupacion.getServicios();
                        // Declaramos la variable que contendrá las capas de cada grupo temático
                        List<Map<String, Object>> capas = new ArrayList<Map<String, Object>>();
                        for (AgrupadorCapaServicioWeb relAgrupadorServicioWeb : relAgrupadorServiciosWeb) {
                            ServicioWeb servicioWeb = relAgrupadorServicioWeb.getServicioWeb();
                            if (servicioWeb.getCoordenadas().contains(geoportal.getCoordenadas())) {
                                // Obtenemos los datos necesarios del servicio web para usarlo en el geoportal
                                Map<String, Object> capa = obtainInfoForGeoportal(servicioWeb);
                                // Añadimos cada capa a la variable de capas de cada grupo temático
                                capas.add(capa);
                            }
                        }
                        // Añadimos la variable capas a su grupo temático
                        grupoTematico.put("capas", capas);
                        // Añadimos el grupo temático a los que se deben mostrar
                        gruposTematicos.add(grupoTematico);
                    }
                    // Añadimos los grupos temáticos al mapa
                    map.put("gruposTematicos", gruposTematicos);
                    break;
                default:
                    break;
            }
        }
    }
    return map;
}


public String getFullUrlPortal(String url,String scheme,String serverName,int port,String contextPath){
    String urlCompleta = "";
    URL serverURL = null;
    // si son los puertos por defecto se setea a -1 para que no aparezca en la url
    if (scheme.equals("http") && port == 80) {
        port = -1;
    } else if (scheme.equals("https") && port == 443) {
        port = -1;
    }
    try {
        serverURL = new URL(scheme, serverName, port, contextPath.concat("/map/").concat(url));
        urlCompleta = serverURL.toString();
    } catch (MalformedURLException e) {
        // si da algun error, creamos nosotros la url de forma manual
        urlCompleta = scheme.concat("://").concat(serverName).concat(":").concat("" + port).concat(contextPath).concat("/map/").concat(url);
    }
    return urlCompleta;
}


public GeoPortal clone(GeoPortal geoPortal){
    // creamos objeto nuevo
    GeoPortal newGeoPortal = new GeoPortal();
    // seteamos valores
    newGeoPortal.setTitulo(geoPortal.getTitulo());
    newGeoPortal.setUrl(geoPortal.getUrl() + Constants.COPIA);
    newGeoPortal.setSubtitulo(geoPortal.getSubtitulo());
    newGeoPortal.setDescripcion(geoPortal.getDescripcion());
    newGeoPortal.setAlias(geoPortal.getAlias());
    newGeoPortal.setCoordenadas(geoPortal.getCoordenadas());
    // seteamos agrupador de capa. Debo crear nuevo set para evitar problema
    // de referencias compartidas.
    Set<AgrupadorCapa> agrupadorCapaViejo = geoPortal.getAgrupadorCapa();
    Set<AgrupadorCapa> agrupadorCapa = new HashSet<AgrupadorCapa>();
    agrupadorCapa.addAll(agrupadorCapaViejo);
    newGeoPortal.setAgrupadorCapa(agrupadorCapa);
    newGeoPortal.setCentro(geoPortal.getCentro());
    newGeoPortal.setZoom(geoPortal.getZoom());
    newGeoPortal.setLogo(geoPortal.getLogo());
    // seteamos siempre a false
    newGeoPortal.setPublicado(false);
    newGeoPortal.setFechaAlta(geoPortal.getFechaAlta());
    newGeoPortal.setFechaBaja(geoPortal.getFechaBaja());
    return newGeoPortal;
}


public void saveGeoPortal(GeoPortal geoPortal){
    geoPortal.persist();
    // Se activan por defecto los componentes "Mostrar TOC" y
    // "Herramienta de zoom"
    Componentes componente = new Componentes();
    componente.setGeoPortal(geoPortal);
    componente.setTipo(TipoComponente.ver_toc);
    componentesService.saveComponentes(componente);
    componente = new Componentes();
    componente.setGeoPortal(geoPortal);
    componente.setTipo(TipoComponente.regla_zoom);
    componentesService.saveComponentes(componente);
}


public TypedQuery<GeoPortal> findPublicGeoPortalesByUrlEquals(String url){
    return GeoPortal.findGeoportalesByUrlAndPublic(url, true);
}


public List<TipoComponente> checkSelectedComponentes(HttpServletRequest request){
    List<TipoComponente> listadoComponentes = new ArrayList<TipoComponente>();
    for (TipoComponente componente : TipoComponente.values()) {
        String conf = request.getParameter(componente.toString());
        if (conf != null && !conf.isEmpty()) {
            listadoComponentes.add(componente);
        }
    }
    return listadoComponentes;
}


}