package es.gva.dgti.gvgeoportal.web;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.gvnix.web.datatables.query.SearchResults;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.PathBuilder;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoServicio;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.CapasServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.GestorCatalogoService;
import es.gva.dgti.gvgeoportal.service.domain.SistemaCoordenadasService;
import es.gva.dgti.gvgeoportal.util.Constants;
import es.gva.dgti.gvgeoportal.util.SearchUtils;
import es.gva.dgti.gvgeoportal.util.Util;
import es.gva.dgti.gvgeoportal.Interface.GestorCatalogoService;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaServicioWebService;
@RequestMapping("/serviciosweb")
@Controller
@RooWebScaffold(path = "serviciosweb", formBackingObject = ServicioWeb.class)
@GvNIXWebJQuery
@GvNIXDatatables(ajax = true)
@RooWebFinder
public class ServicioWebController {

@Autowired
 private SistemaCoordenadasService sistemaCoordenadasService;

@Autowired
 private GestorCatalogoService gestorCatalogoService;

@Autowired(required = false)
 private AgrupadorCapaServicioWebService agrupadorCapaServicioWeb;

 private  Logger LOGGER;

 private  String AGRUPADOR;


@RequestMapping(method = RequestMethod.GET, produces = "text/html")
public String listDatatables(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    // Get parentId information for details render
    String parentId = params.remove("_dt_parentId");
    if (StringUtils.isNotBlank(parentId)) {
        uiModel.addAttribute("parentId", parentId);
    }
    String rowOnTopIds = params.remove("dtt_row_on_top_ids");
    if (StringUtils.isNotBlank(rowOnTopIds)) {
        uiModel.addAttribute("dtt_row_on_top_ids", rowOnTopIds);
    }
    String tableHashId = params.remove("dtt_parent_table_id_hash");
    if (StringUtils.isNotBlank(tableHashId)) {
        uiModel.addAttribute("dtt_parent_table_id_hash", tableHashId);
    }
    if (!params.isEmpty()) {
        uiModel.addAttribute("baseFilter", params);
    }
    if (!params.isEmpty() && params.containsValue("ByAll")) {
        uiModel.addAttribute("paramsBack", Util.getParamsFinder(params));
        // Used to add a header with the selected criteria to the result
        // of the searches using "filterDescription.tagx".
        uiModel.addAttribute("baseFilterDescriptionMap", getFilterDescription(params, request));
        // Back button of "filterDescription.tagx"
        uiModel.addAttribute("paramsBackMap", params);
    }
    return "serviciosweb/list";
}


@RequestMapping(value = "/showMap/{id}", produces = "text/html")
public String showMap(Long id,Model uiModel){
    addDateTimeFormatPatterns(uiModel);
    ServicioWeb servicioWeb = servicioWebService.findServicioWeb(id);
    uiModel.addAttribute("servicioweb", servicioWeb);
    uiModel.addAttribute("itemId", id);
    // comprobamos si el servicioWeb soporta el crs EPSG:4326. Si es asi lo
    // mandamos para hacer fitbound del mapa.
    if (servicioWebService.containsCrs(servicioWeb.getCoordenadas(), Constants.EPSG_25830)) {
        uiModel.addAttribute("crs", Constants.EPSG_25830);
    } else if (servicioWebService.containsCrs(servicioWeb.getCoordenadas(), Constants.EPSG_4326)) {
        uiModel.addAttribute("crs", Constants.EPSG_4326);
    } else {
        // pasamos un string con un crs cualquiera, en este caso el primero.
        // Este se utilizara en caso de no existir EPSG_4326
        uiModel.addAttribute("crs", servicioWebService.getFirstCrsName(servicioWeb.getCoordenadas()));
    }
    // le paso crs EPSG_4326 para realizar comprobaciones en javascrip
    // uiModel.addAttribute("crs4326", Constants.EPSG_4326);
    // String con las capas seleccionadas
    uiModel.addAttribute("layersSelected", servicioWebService.getSelectedInfoLayersNames(servicioWeb.getCapasServicioWeb(), true));
    // String con los estilos seleccionadas
    uiModel.addAttribute("stylesSelected", servicioWebService.getStylesNames(servicioWeb.getCapasServicioWeb()));
    return "forward:/WEB-INF/views/serviciosweb/showMap.jspx";
}


@RequestMapping(params = "selector", produces = "text/html")
public String showOnlyList(Model uiModel,HttpServletRequest request,String listPath){
    // as we can't get target table configuration lets use standard _ajax_
    // configuration.
    uiModel.asMap().put("datatablesHasBatchSupport", false);
    uiModel.asMap().put("datatablesUseAjax", true);
    uiModel.asMap().put("datatablesInlineEditing", false);
    uiModel.asMap().put("datatablesInlineCreating", false);
    uiModel.asMap().put("datatablesSecurityApplied", false);
    uiModel.asMap().put("datatablesStandardMode", true);
    uiModel.asMap().put("finderNameParam", "ajax_find");
    // Do common datatables operations: get entity list filtered by request
    // parameters
    Map<String, String> params = populateParametersMap(request);
    // Get parentId information for details render
    String parentId = params.remove("_dt_parentId");
    if (StringUtils.isNotBlank(parentId)) {
        uiModel.addAttribute("parentId", parentId);
    } else {
        uiModel.addAttribute("parentId", "ConfCapasTematicas_selector");
    }
    String rowOnTopIds = params.remove("dtt_row_on_top_ids");
    if (StringUtils.isNotBlank(rowOnTopIds)) {
        uiModel.addAttribute("dtt_row_on_top_ids", rowOnTopIds);
    }
    String tableHashId = params.remove("dtt_parent_table_id_hash");
    if (StringUtils.isNotBlank(tableHashId)) {
        uiModel.addAttribute("dtt_parent_table_id_hash", tableHashId);
    }
    params.remove("selector");
    params.remove("path");
    if (!params.isEmpty()) {
        uiModel.addAttribute("baseFilter", params);
    }
    uiModel.addAttribute("dtt_ignoreParams", Arrays.asList("selector", "path"));
    uiModel.addAttribute("dtt_disableEditing", "true");
    // Show only the list fragment (without footer, header, menu, etc.)
    return "forward:/WEB-INF/views/" + listPath + ".jspx";
}


public void populateEditForm(Model uiModel,ServicioWeb servicioWeb){
    uiModel.addAttribute("servicioWeb", servicioWeb);
    addDateTimeFormatPatterns(uiModel);
    uiModel.addAttribute("sistemacoordenadasitems", sistemaCoordenadasService.findAllSistemaCoordenadas());
    uiModel.addAttribute("capasServicioWebitems", servicioWeb.getCapasServicioWeb());
}


@RequestMapping(value = "/{id}", produces = "text/html")
public String show(Long id,Model uiModel){
    addDateTimeFormatPatterns(uiModel);
    ServicioWeb servicioWeb = servicioWebService.findServicioWeb(id);
    uiModel.addAttribute("servicioweb", servicioWeb);
    uiModel.addAttribute("itemId", id);
    // comprobamos si el servicioWeb soporta el crs EPSG:4326. Si es asi lo
    // mandamos para hacer fitbound del mapa.
    if (servicioWebService.containsCrs(servicioWeb.getCoordenadas(), Constants.EPSG_25830) && servicioWeb.getTipo() != TipoServicio.TILE) {
        uiModel.addAttribute("crs", Constants.EPSG_25830);
    } else if (servicioWebService.containsCrs(servicioWeb.getCoordenadas(), Constants.EPSG_4326)) {
        uiModel.addAttribute("crs", Constants.EPSG_4326);
    } else {
        // pasamos un string con un crs cualquiera, en este caso el primero.
        // Este se utilizara en caso de no existir EPSG_4326
        uiModel.addAttribute("crs", servicioWebService.getFirstCrsName(servicioWeb.getCoordenadas()));
    }
    // le paso crs EPSG_4326 para realizar comprobaciones en javascrip
    // uiModel.addAttribute("crs4326", Constants.EPSG_4326);
    // String con las capas seleccionadas
    uiModel.addAttribute("layersSelected", servicioWebService.getSelectedInfoLayersNames(servicioWeb.getCapasServicioWeb(), true));
    // String con los estilos seleccionadas
    uiModel.addAttribute("stylesSelected", servicioWebService.getStylesNames(servicioWeb.getCapasServicioWeb()));
    return "serviciosweb/show";
}


@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
public String update(ServicioWeb servicioWeb,BindingResult bindingResult,Model uiModel,HttpServletRequest request){
    if (bindingResult.hasErrors()) {
        populateEditForm(uiModel, servicioWeb);
        return "serviciosweb/update";
    }
    // obtenemos sistema coordenadas
    String coordenadasSeleccionadas = request.getParameter("coordenadasSeleccionadas");
    // obtenemos tipo
    String tipo = request.getParameter("tipo");
    // solo obtenemos capas, coordenadas y estilos sino es tile
    if (tipo != null && !tipo.equals(TipoServicio.TILE.name())) {
        // si es tipo WMTS llamamos al metodo para obtener CRS con el
        // formato
        // adecuado
        if (tipo.equalsIgnoreCase(TipoServicio.WMTS.name()) && coordenadasSeleccionadas != null && !coordenadasSeleccionadas.isEmpty()) {
            coordenadasSeleccionadas = servicioWebService.transformCrsWmts(coordenadasSeleccionadas);
        }
        // en caso de tener coordenadas las seteamos en el servicio
        if (coordenadasSeleccionadas != null && !coordenadasSeleccionadas.isEmpty()) {
            // almacenamos en el set de coordenadas las coordenadas
            // seleccionadas
            Set<SistemaCoordenadas> coordenadas = servicioWebService.getSelectedCrs(coordenadasSeleccionadas);
            // seteamos servicio web con las coordenadas
            if (coordenadas != null && coordenadas.size() > 0) {
                servicioWeb.setCoordenadas(coordenadas);
            }
        }
        // obtenemos capas
        String capasSeleccionadas = request.getParameter("capasSeleccionadas");
        // obtenemos titulo capas
        String tituloCapasSeleccionadas = request.getParameter("tituloCapasSeleccionadas");
        // obtenemos estilos
        String estiloCapasSeleccionadas = request.getParameter("estiloCapasSeleccionadas");
        // almacenamos en el set de capasServicio con las capas
        // seleccionadas
        Set<CapasServicioWeb> capasServicioWeb = servicioWebService.getSelectedLayersAndStyles(capasSeleccionadas, tituloCapasSeleccionadas, estiloCapasSeleccionadas, tipo, servicioWeb);
        // seteamos servicio web con las coordenadas
        if (capasServicioWeb != null && capasServicioWeb.size() > 0) {
            servicioWeb.getCapasServicioWeb().addAll(capasServicioWeb);
        }
    }
    uiModel.asMap().clear();
    servicioWebService.updateServicioWeb(servicioWeb);
    return "redirect:/serviciosweb/" + encodeUrlPathSegment(servicioWeb.getId().toString(), request);
}


@RequestMapping(headers = "Accept=application/json", value = "/datatables/ajax", produces = "application/json")
@ResponseBody
public DatatablesResponse<Map<String,String>> findAllServiciosWeb(DatatablesCriterias criterias,ServicioWeb servicioWeb,HttpServletRequest request){
    // URL parameters are used as base search filters
    Map<String, Object> baseSearchValuesMap = getPropertyMap(servicioWeb, request);
    SearchResults<ServicioWeb> searchResult = null;
    if (request.getParameter("coordenadasFilter") != null && !request.getParameter("coordenadasFilter").isEmpty()) {
        long coordenadasId = Long.valueOf(request.getParameter("coordenadasFilter"));
        SistemaCoordenadas sistemaCoordenadas = sistemaCoordenadasService.findSistemaCoordenadas(coordenadasId);
        BooleanBuilder basePredicate = new BooleanBuilder();
        PathBuilder<ServicioWeb> entity = new PathBuilder<ServicioWeb>(ServicioWeb.class, "entity");
        basePredicate.and(entity.getCollection("coordenadas", SistemaCoordenadas.class).contains(sistemaCoordenadas));
        searchResult = datatablesUtilsBean_dtt.findByCriteria(entity, criterias, basePredicate);
    } else // Obtiene los servicios web que no están en un agrupador. Los que están
    // en un agrupador se obtienen desde el método
    // "findAllAgrupadorCapaServicioWebs" de
    // "AgrupadorCapaServicioWebController.java".
    if (request.getParameter(AGRUPADOR) != null && !request.getParameter(AGRUPADOR).isEmpty()) {
        Long agrupadorId = null;
        try {
            agrupadorId = Long.valueOf(request.getParameter(AGRUPADOR));
        } catch (Exception ex) {
            LOGGER.warn(ex.getLocalizedMessage(), ex);
        }
        List<Long> servicesIds = new ArrayList<Long>();
        if (agrupadorId != null) {
            servicesIds = agrupadorCapaServicioWeb.findServicesByGroup(agrupadorId).getResultList();
        }
        BooleanBuilder basePredicate = new BooleanBuilder();
        PathBuilder<ServicioWeb> entity = new PathBuilder<ServicioWeb>(ServicioWeb.class, "entity");
        basePredicate.and(entity.get("id").notIn(servicesIds));
        searchResult = datatablesUtilsBean_dtt.findByCriteria(entity, criterias, basePredicate);
    } else {
        setDatatablesBaseFilter(baseSearchValuesMap);
        searchResult = datatablesUtilsBean_dtt.findByCriteria(ServicioWeb.class, criterias, baseSearchValuesMap);
    }
    // Get datatables required counts
    long totalRecords = searchResult.getTotalCount();
    long recordsFound = searchResult.getResultsCount();
    // Entity pk field name
    String pkFieldName = "id";
    org.springframework.ui.Model uiModel = new org.springframework.ui.ExtendedModelMap();
    addDateTimeFormatPatterns(uiModel);
    Map<String, Object> datePattern = uiModel.asMap();
    DataSet<Map<String, String>> dataSet = datatablesUtilsBean_dtt.populateDataSet(searchResult.getResults(), pkFieldName, totalRecords, recordsFound, criterias.getColumnDefs(), datePattern);
    return DatatablesResponse.build(dataSet, criterias);
}


@RequestMapping(value = "/openSearchCatalog", produces = "text/html")
public String openSearchCatalog(Model uiModel,HttpServletRequest request){
    addDateTimeFormatPatterns(uiModel);
    String nombreServicio = request.getParameter("nombreServicio");
    String descripcionServicio = request.getParameter("descripcionServicio");
    uiModel.addAttribute("nombreServicio", nombreServicio);
    uiModel.addAttribute("descripcionServicio", descripcionServicio);
    uiModel.addAttribute("showResult", false);
    uiModel.addAttribute("gestorCatalogos", gestorCatalogoService.findAllGestorCatalogos("nombre", "ASC"));
    return "catalogos/list";
}


@RequestMapping(value = "/getServicioByIdAgrupadorCapaServicioWeb/{id}", produces = "application/json")
public ResponseEntity<Map<String,Object>> getServicioByIdAgrupadorCapaServicioWeb(Long id,Model uiModel){
    Map<String, Object> response = new HashMap<String, Object>();
    // obtenemos objeto agrupadorCapaServicioWeb
    AgrupadorCapaServicioWeb agrupadorCapaServicio = agrupadorCapaServicioWeb.findAgrupadorCapaServicioWeb(id);
    Long idServicioWeb = agrupadorCapaServicio.getServicioWeb().getId();
    response.put("idServicioWeb", idServicioWeb);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
}


@RequestMapping(value = "/showMapFromGroup/{id}", produces = "text/html")
public String showMapFromGroup(Long id,Model uiModel){
    addDateTimeFormatPatterns(uiModel);
    // obtenemos objeto agrupadorCapaServicioWeb
    AgrupadorCapaServicioWeb agrupadorCapaServicio = agrupadorCapaServicioWeb.findAgrupadorCapaServicioWeb(id);
    Long idServicioWeb = agrupadorCapaServicio.getServicioWeb().getId();
    return showMap(idServicioWeb, uiModel);
}


@RequestMapping(params = "form", produces = "text/html")
public String createForm(Model uiModel,HttpServletRequest request){
    String urlServicio = request.getParameter("urlServicio");
    String tipoServicio = request.getParameter("tipoServicio");
    String nombreServicio = request.getParameter("nombreServicio");
    String descripcionServicio = request.getParameter("descripcionServicio");
    if (urlServicio != null && !urlServicio.isEmpty()) {
        uiModel.addAttribute("urlServicio", urlServicio);
    }
    if (nombreServicio != null && !nombreServicio.isEmpty()) {
        uiModel.addAttribute("nombreServicio", nombreServicio);
    }
    if (descripcionServicio != null && !descripcionServicio.isEmpty()) {
        uiModel.addAttribute("descripcionServicio", descripcionServicio);
    }
    if (tipoServicio != null && !tipoServicio.isEmpty()) {
        uiModel.addAttribute("tipoServicio", tipoServicio);
    }
    uiModel.addAttribute("tipos", Arrays.asList(TipoServicio.values()));
    // llamamos al metodo para obtener listado codigos CRS
    String listadoNombresCoordenadas = sistemaCoordenadasService.getListCrsName();
    if (listadoNombresCoordenadas != null && !listadoNombresCoordenadas.isEmpty()) {
        uiModel.addAttribute("listadoCoordenadas", listadoNombresCoordenadas);
    }
    populateEditForm(uiModel, new ServicioWeb());
    return "serviciosweb/create";
}


@RequestMapping(params = { "find=ByAll", "form" }, method = RequestMethod.GET)
public String buscarServiciosWeb(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    if (params.containsKey("back")) {
        // Saves parameters to display when the page reload
        uiModel.addAttribute("params", params);
    }
    uiModel.addAttribute("tipos", Arrays.asList(TipoServicio.values()));
    uiModel.addAttribute("listaCrs", sistemaCoordenadasService.findAllSistemaCoordenadas());
    return "serviciosweb/search";
}


@RequestMapping(method = RequestMethod.POST, produces = "text/html")
public String create(ServicioWeb servicioWeb,BindingResult bindingResult,Model uiModel,HttpServletRequest request){
    if (bindingResult.hasErrors()) {
        populateEditForm(uiModel, servicioWeb);
        return "serviciosweb/create";
    }
    // obtenemos sistema coordenadas
    String coordenadasSeleccionadas = request.getParameter("coordenadasSeleccionadas");
    // obtenemos tipo
    String tipo = request.getParameter("tipo");
    // solo obtenemos capas, coordenadas y estilos sino es tile
    if (tipo != null && !tipo.equals(TipoServicio.TILE.name())) {
        // si es tipo WMTS llamamos al metodo para obtener CRS con el
        // formato
        // adecuado
        if (tipo.equalsIgnoreCase(TipoServicio.WMTS.name()) && coordenadasSeleccionadas != null && !coordenadasSeleccionadas.isEmpty()) {
            coordenadasSeleccionadas = servicioWebService.transformCrsWmts(coordenadasSeleccionadas);
        }
        // en caso de tener coordenadas las seteamos en el servicio
        if (coordenadasSeleccionadas != null && !coordenadasSeleccionadas.isEmpty()) {
            // almacenamos en el set de coordenadas las coordenadas
            // seleccionadas
            Set<SistemaCoordenadas> coordenadas = servicioWebService.getSelectedCrs(coordenadasSeleccionadas);
            // seteamos servicio web con las coordenadas
            if (coordenadas != null && coordenadas.size() > 0) {
                servicioWeb.setCoordenadas(coordenadas);
            }
        }
        // obtenemos capas
        String capasSeleccionadas = request.getParameter("capasSeleccionadas");
        // obtenemos titulo capas
        String tituloCapasSeleccionadas = request.getParameter("tituloCapasSeleccionadas");
        // obtenemos estilos
        String estiloCapasSeleccionadas = request.getParameter("estiloCapasSeleccionadas");
        // almacenamos en el set de capasServicio con las capas
        // seleccionadas
        Set<CapasServicioWeb> capasServicioWeb = servicioWebService.getSelectedLayersAndStyles(capasSeleccionadas, tituloCapasSeleccionadas, estiloCapasSeleccionadas, tipo, servicioWeb);
        // seteamos servicio web con las coordenadas
        if (capasServicioWeb != null && capasServicioWeb.size() > 0) {
            servicioWeb.getCapasServicioWeb().addAll(capasServicioWeb);
        }
    } else if (tipo != null && tipo.equals(TipoServicio.TILE.name())) {
        // Para las tiles se almacenan todos los sistemas de coordenadas
        List<SistemaCoordenadas> sistemasCoordenadas = sistemaCoordenadasService.findAllSistemaCoordenadas();
        for (SistemaCoordenadas sistemaCoordenada : sistemasCoordenadas) {
            coordenadasSeleccionadas = coordenadasSeleccionadas.concat(sistemaCoordenada.getCodigo()).concat(",");
        }
        coordenadasSeleccionadas = coordenadasSeleccionadas.substring(0, coordenadasSeleccionadas.length() - 1);
        // en caso de tener coordenadas las seteamos en el servicio
        if (coordenadasSeleccionadas != null && !coordenadasSeleccionadas.isEmpty()) {
            // almacenamos en el set de coordenadas las coordenadas
            // seleccionadas
            Set<SistemaCoordenadas> coordenadas = servicioWebService.getSelectedCrs(coordenadasSeleccionadas);
            // seteamos servicio web con las coordenadas
            if (coordenadas != null && coordenadas.size() > 0) {
                servicioWeb.setCoordenadas(coordenadas);
            }
        }
    }
    uiModel.asMap().clear();
    servicioWeb.persist();
    return "serviciosweb/list";
}


@RequestMapping(headers = "Accept=application/json", value = "/datatables/ajax", params = "ajax_find=ByAll", produces = "application/json")
@ResponseBody
public DatatablesResponse<Map<String,String>> findByAll(DatatablesCriterias criterias,ServicioWeb servicioWeb,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    PathBuilder<ServicioWeb> entity = new PathBuilder<ServicioWeb>(ServicioWeb.class, "servicioWeb");
    BooleanBuilder baseSearch = new BooleanBuilder();
    SearchResults<ServicioWeb> searchResult;
    // baseSearch para parametro nombre
    if (params.containsKey("nombreFinder") && params.get("nombreFinder") != null) {
        String nombre = (String) params.get("nombreFinder");
        if (!nombre.isEmpty()) {
            baseSearch.and(entity.getString("nombre").toLowerCase().contains(nombre.toLowerCase()));
        }
    }
    // baseSearch para parametro descricpion
    if (params.containsKey("descripcionFinder") && params.get("descripcionFinder") != null) {
        String descripcion = (String) params.get("descripcionFinder");
        if (!descripcion.isEmpty()) {
            baseSearch.and(entity.getString("descripcion").toLowerCase().contains(descripcion.toLowerCase()));
        }
    }
    // baseSearch para parametro url
    if (params.containsKey("urlFinder") && params.get("urlFinder") != null) {
        String url = (String) params.get("urlFinder");
        if (!url.isEmpty()) {
            baseSearch.and(entity.getString("url").toLowerCase().contains(url.toLowerCase()));
        }
    }
    // baseSearch para parametro tipo
    if (params.containsKey("tipoFinder") && params.get("tipoFinder") != null) {
        String tipo = (String) params.get("tipoFinder");
        if (!tipo.isEmpty()) {
            baseSearch = SearchUtils.createPropertyExpression(baseSearch, "tipoFinder", "tipo", TipoServicio.class, params, entity, conversionService_dtt);
        }
    }
    // baseSearch para parametro coordenadas
    if (params.containsKey("coordenadasFinder") && params.get("coordenadasFinder") != null) {
        String coordenadas = (String) params.get("coordenadasFinder");
        if (!coordenadas.isEmpty()) {
            SistemaCoordenadas sistemaCoordenadas = sistemaCoordenadasService.findSistemaCoordenadasByCodigoEquals(coordenadas);
            baseSearch.and(entity.getCollection("coordenadas", SistemaCoordenadas.class).contains(sistemaCoordenadas));
        }
    }
    searchResult = datatablesUtilsBean_dtt.findByCriteria(entity, criterias, baseSearch);
    // Get datatables required counts
    long totalRecords = searchResult.getTotalCount();
    long recordsFound = searchResult.getResultsCount();
    // Entity pk field name
    String pkFieldName = "id";
    org.springframework.ui.Model uiModel = new org.springframework.ui.ExtendedModelMap();
    addDateTimeFormatPatterns(uiModel);
    Map<String, Object> datePattern = uiModel.asMap();
    DataSet<Map<String, String>> dataSet = datatablesUtilsBean_dtt.populateDataSet(searchResult.getResults(), pkFieldName, totalRecords, recordsFound, criterias.getColumnDefs(), datePattern);
    return DatatablesResponse.build(dataSet, criterias);
}


public void addDateTimeFormatPatterns(Model uiModel){
    uiModel.addAttribute("servicioWeb_auditcreation_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    uiModel.addAttribute("servicioWeb_auditlastupdate_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    uiModel.addAttribute("fecha_creacion_finder", "dd-MM-yyyy");
}


@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
public String updateForm(Long id,Model uiModel){
    ServicioWeb servicioWeb = servicioWebService.findServicioWeb(id);
    populateEditForm(uiModel, servicioWeb);
    // llamamos al metodo para obtener listado codigos CRS
    String listadoNombresCoordenadas = sistemaCoordenadasService.getListCrsName();
    if (listadoNombresCoordenadas != null && !listadoNombresCoordenadas.isEmpty()) {
        uiModel.addAttribute("listadoCoordenadas", listadoNombresCoordenadas);
    }
    // pasamos un string con los nombres de los sistemas de coordenadas
    uiModel.addAttribute("crsSelected", servicioWebService.getSelectedCrsNames(servicioWeb.getCoordenadas()));
    // String con las capas seleccionadas
    uiModel.addAttribute("layersSelected", servicioWebService.getSelectedInfoLayersNames(servicioWeb.getCapasServicioWeb(), true));
    // String con los estilos seleccionadas
    uiModel.addAttribute("stylesSelected", servicioWebService.getSelectedInfoLayersNames(servicioWeb.getCapasServicioWeb(), false));
    return "serviciosweb/update";
}


public LinkedHashMap<String,String> getFilterDescription(Map<String,String> params,HttpServletRequest request){
    LinkedHashMap<String, String> orderedParamsMap = new LinkedHashMap<String, String>();
    // Adds additional params
    if (params.containsKey("nombreFinder") && !params.get("nombreFinder").isEmpty()) {
        orderedParamsMap.put("nombreFinder", params.get("nombreFinder"));
    }
    if (params.containsKey("descripcionFinder") && !params.get("descripcionFinder").isEmpty()) {
        orderedParamsMap.put("descripcionFinder", params.get("descripcionFinder"));
    }
    if (params.containsKey("urlFinder") && !params.get("urlFinder").isEmpty()) {
        orderedParamsMap.put("urlFinder", params.get("urlFinder"));
    }
    if (params.containsKey("tipoFinder") && !params.get("tipoFinder").isEmpty()) {
        orderedParamsMap.put("tipoFinder", params.get("tipoFinder"));
    }
    if (params.containsKey("coordenadasFinder") && !params.get("coordenadasFinder").isEmpty()) {
        orderedParamsMap.put("coordenadasFinder", params.get("coordenadasFinder"));
    }
    return orderedParamsMap;
}


}