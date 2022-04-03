package es.gva.dgti.gvgeoportal.web;
 import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.gvnix.web.datatables.query.SearchResults;
import org.gvnix.web.datatables.util.DatatablesUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.PathBuilder;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.Componentes;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
import es.gva.dgti.gvgeoportal.domain.components.ConfAyudaBuscador;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
import es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa;
import es.gva.dgti.gvgeoportal.domain.components.ConfVistasPredefinidas;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaService;
import es.gva.dgti.gvgeoportal.service.domain.ComponentesService;
import es.gva.dgti.gvgeoportal.service.domain.ConfAyudaBuscadorService;
import es.gva.dgti.gvgeoportal.service.domain.ConfCapasTematicasService;
import es.gva.dgti.gvgeoportal.service.domain.ConfMiniMapaService;
import es.gva.dgti.gvgeoportal.service.domain.ConfVistasPredefinidasService;
import es.gva.dgti.gvgeoportal.service.domain.GeoportalServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.ServicioWebService;
import es.gva.dgti.gvgeoportal.util.Constants;
import es.gva.dgti.gvgeoportal.util.StringPool;
import es.gva.dgti.gvgeoportal.util.Util;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasService;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.GeoportalServicioWebService;
@RequestMapping("/geoportales")
@Controller
@RooWebScaffold(path = "geoportales", formBackingObject = GeoPortal.class)
@GvNIXWebJQuery
@GvNIXDatatables(ajax = true)
public class GeoPortalController {

@Autowired
 private ComponentesService componentesService;

@Autowired
 private ConfAyudaBuscadorService confAyudaBuscadorService;

@Autowired
 private ConfMiniMapaService confMiniMapaService;

@Autowired
 private ConfCapasTematicasService confCapasTematicasService;

@Autowired
 private ConfVistasPredefinidasService confVistasPredefinidasService;

@Autowired
 private AgrupadorCapaService agrupadorCapaService;

@Autowired
 private ServicioWebService servicioWebService;

@Autowired
 private GeoportalServicioWebService geoportalServicioWebService;

@Autowired
 public  MessageSource messageSource_dtt;


@RequestMapping(produces = "text/html", method = RequestMethod.PUT, params = "datatablesRedirect")
public String updateDatatablesDetail(String redirect,GeoPortal geoportal,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    // Do common update operations (check errors, populate, merge, ...)
    String view = update(geoportal, bindingResult, uiModel, httpServletRequest, redirectModel);
    // If binding errors or no redirect, return common update error view
    // (remain in update form)
    if (bindingResult.hasErrors() || redirect == null || redirect.trim().isEmpty()) {
        return view;
    }
    String[] paramValues = httpServletRequest.getParameterValues("dtt_table_id_hash");
    Map<String, String> params = populateParametersMap(httpServletRequest);
    if (params.containsKey("openConfigureTocRedirect")) {
        if (paramValues != null && paramValues.length > 0) {
            return "redirect:".concat("/mapaedicion/").concat(geoportal.getId().toString()).concat("?datatablesRedirect=").concat(redirect).concat("&dtt_table_id_hash=").concat(paramValues[0]);
        } else {
            return "redirect:".concat("/mapaedicion/").concat(geoportal.getId().toString()).concat("?datatablesRedirect=").concat(redirect);
        }
    }
    if (paramValues != null && paramValues.length > 0) {
        redirectModel.addFlashAttribute("dtt_table_id_hash", paramValues[0]);
    } else {
        redirectModel.addFlashAttribute("dtt_table_id_hash", "");
    }
    redirectModel.addFlashAttribute(DatatablesUtilsBean.ROWS_ON_TOP_IDS_PARAM, geoportal.getId());
    // If update success, redirect to given URL: master datatables
    return "redirect:".concat(redirect);
}


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
    return "geoportales/list";
}


@RequestMapping(produces = "text/html", method = RequestMethod.POST, params = "datatablesRedirect")
public String createDatatablesDetail(String redirect,GeoPortal geoportal,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    // Do common create operations (check errors, populate, persist, ...)
    String view = create(geoportal, bindingResult, uiModel, httpServletRequest);
    // If binding errors or no redirect, return common create error view
    // (remain in create form)
    if (bindingResult.hasErrors() || redirect == null || redirect.trim().isEmpty()) {
        return view;
    }
    String editingView = "redirect:".concat("geoportales/").concat(geoportal.getId().toString()).concat("?form");
    String[] paramValues = httpServletRequest.getParameterValues("dtt_table_id_hash");
    if (paramValues != null && paramValues.length > 0) {
        redirectModel.addFlashAttribute("dtt_table_id_hash", paramValues[0]);
    // editingView = editingView.concat(paramValues[0]);
    } else {
        redirectModel.addFlashAttribute("dtt_table_id_hash", "");
    }
    redirectModel.addFlashAttribute(DatatablesUtilsBean.ROWS_ON_TOP_IDS_PARAM, geoportal.getId());
    // If create success, redirect to given URL: master datatables
    return editingView;
}


@RequestMapping(value = "/{id}", produces = "text/html")
public String show(Long id,Model uiModel){
    GeoPortal geoportal = geoPortalService.findGeoPortal(id);
    if (geoportal != null) {
        Set<GeoportalServicioWeb> serviciosToc = geoportal.getServiciosToc();
        Iterator<GeoportalServicioWeb> iterator = serviciosToc.iterator();
        while (iterator.hasNext()) {
            GeoportalServicioWeb geoportalServicioWeb = iterator.next();
            ServicioWeb servicioWeb = geoportalServicioWeb.getServicioWeb();
            Map<String, String> map = servicioWebService.getLayersAndStylesOrderByLayersName(servicioWeb);
            servicioWeb.setNombresCapas(map.get("nombresCapas"));
            servicioWeb.setEstilosCapas(map.get("estilosCapas"));
        }
        uiModel.addAttribute("geoportal", geoportal);
        // atributo para controlar si se muestra el titulo en el toc y el boton volver atras
        uiModel.addAttribute("isPublic", false);
        uiModel.addAttribute("cssPublicPortal", "noPublicPortal");
        uiModel.addAllAttributes(geoPortalService.getComponentsAndInformationByGeoportal(geoportal));
        return "map/show";
    } else {
        return "resourceNotFound";
    }
}


@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
public String update(GeoPortal geoPortal,BindingResult bindingResult,Model uiModel,HttpServletRequest httpServletRequest,RedirectAttributes redirectModel){
    Map<String, String> params = populateParametersMap(httpServletRequest);
    // Añade errores personalizados al binding
    bindingResult = createErrorMessagesForGeoPortal(bindingResult, geoPortal);
    if (bindingResult.hasErrors()) {
        populateEditForm(uiModel, geoPortal);
        // obtenemos listado componentes
        uiModel.addAttribute("listadoComponentes", Arrays.asList(TipoComponente.values()));
        // obtenemos conf_ayuda_buscador
        ConfAyudaBuscador confAyudaBuscador = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
        if (confAyudaBuscador != null) {
            String textoAyuda = confAyudaBuscador.getTextoAyuda();
            uiModel.addAttribute("textoAyuda", textoAyuda);
        }
        // obtenemos conf_minimapa
        ConfMiniMapa confMiniMapa = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
        if (confMiniMapa != null) {
            Long idServicioWeb = confMiniMapa.getServicioWeb().getId();
            uiModel.addAttribute("miniMapa", idServicioWeb);
        }
        // obtenemos listado seleccionados
        List<Componentes> listadoComponentesSeleccionados = componentesService.findComponentesByGeoPortal(geoPortal);
        uiModel.addAttribute("listadoComponentesSeleccionados", listadoComponentesSeleccionados);
        // Se recupera el logo nuevo.
        if (params.containsKey("newLogo")) {
            uiModel.addAttribute("newLogo", params.get("newLogo"));
        }
        String[] paramValues = httpServletRequest.getParameterValues("dtt_table_id_hash");
        String[] redirect = httpServletRequest.getParameterValues("datatablesRedirect");
        redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.geoportal", bindingResult);
        redirectModel.addFlashAttribute("geoPortal", geoPortal);
        return "redirect:".concat(httpServletRequest.getRequestURL().toString()).concat("/").concat(geoPortal.getId().toString()).concat("?formError").concat("&datatablesRedirect=").concat(redirect[0]).concat("&dtt_table_id_hash=").concat(paramValues[0]);
    }
    // Se almacenará el nuevo logo
    if (params.containsKey("newLogo")) {
        geoPortal.setLogo(params.get("newLogo").getBytes());
    }
    // borramos si existe configuracion ayuda buscador
    ConfAyudaBuscador confAyudaBuscadorAntiguo = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
    if (confAyudaBuscadorAntiguo != null) {
        confAyudaBuscadorService.deleteConfAyudaBuscador(confAyudaBuscadorAntiguo);
    }
    // borramos seteo previo de agrupador capas del geoPortal
    geoPortal.setAgrupadorCapa(null);
    // borramos primero datos previos de componentes
    List<Componentes> listadoComponentesAntiguos = componentesService.findComponentesByGeoPortal(geoPortal);
    boolean teniaCapasTematicas = false;
    boolean teniaVistasPredefinidas = false;
    boolean teniaMiniMapa = false;
    for (int i = 0; i < listadoComponentesAntiguos.size(); i++) {
        Componentes comp = listadoComponentesAntiguos.get(i);
        // en caso de cargas_tematicas, vistas_predefidas y mini_mapa no se
        // borra desde
        // aqui
        if (comp.getTipo().equals(TipoComponente.cargar_tematicos)) {
            teniaCapasTematicas = true;
            continue;
        }
        if (comp.getTipo().equals(TipoComponente.vistas_predefinidas)) {
            teniaVistasPredefinidas = true;
            continue;
        }
        if (comp.getTipo().equals(TipoComponente.minimapa)) {
            teniaMiniMapa = true;
            continue;
        }
        componentesService.deleteComponentes(comp);
    }
    // obtemos listado de componentes seleccionado
    List<TipoComponente> listadoComponentesActualizados = geoPortalService.checkSelectedComponentes(httpServletRequest);
    boolean tieneCapasTematicas = false;
    boolean tieneVistasPredefinidas = false;
    boolean tieneMiniMapa = false;
    if (listadoComponentesActualizados != null && listadoComponentesActualizados.size() > 0) {
        for (int i = 0; i < listadoComponentesActualizados.size(); i++) {
            TipoComponente componente = listadoComponentesActualizados.get(i);
            // comprobamos en primer lugar si es componente de anyadir capas
            // para actualizar relacion de geoPortal con capas
            if (componente.equals(TipoComponente.anyadir_grupo_capas)) {
                String selectedGroupLayer = httpServletRequest.getParameter("selectedGroupLayersHidden");
                if (selectedGroupLayer != null) {
                    selectedGroupLayer = selectedGroupLayer.replaceAll(StringPool.QUOTE, StringPool.BLANK);
                    selectedGroupLayer = selectedGroupLayer.replaceAll("\\" + StringPool.OPEN_CURLY_BRACE, StringPool.BLANK);
                    selectedGroupLayer = selectedGroupLayer.replaceAll("\\" + StringPool.CLOSE_CURLY_BRACE, StringPool.BLANK);
                    if (!StringUtils.isBlank(selectedGroupLayer)) {
                        String[] groups = selectedGroupLayer.split(",");
                        Set<AgrupadorCapa> agrupadorCapas = new HashSet<AgrupadorCapa>();
                        List<String> listGroupLayers = Arrays.asList(groups);
                        for (int j = 0; j < listGroupLayers.size(); j++) {
                            String element = listGroupLayers.get(j);
                            String idGroupLayer = element.substring(0, element.indexOf(StringPool.COLON));
                            // obtenemos agrupador de capa
                            AgrupadorCapa agrupadorCapa = agrupadorCapaService.findAgrupadorCapa(Long.parseLong(idGroupLayer));
                            // actualizamos set de agrupador capas
                            agrupadorCapas.add(agrupadorCapa);
                        }
                        // si agrupador capas tiene elementos actualizamos
                        // al
                        // geoPortal
                        if (agrupadorCapas != null && agrupadorCapas.size() > 0) {
                            geoPortal.setAgrupadorCapa(agrupadorCapas);
                        }
                    }
                }
            }
            // si es ayuda buscador buscamos texto
            if (componente.equals(TipoComponente.ayuda_buscador)) {
                String textoAyuda = httpServletRequest.getParameter("ayudaBuscadorHidden");
                ConfAyudaBuscador confAyudaBuscador = new ConfAyudaBuscador();
                confAyudaBuscador.setGeoPortal(geoPortal);
                confAyudaBuscador.setTipo(componente);
                confAyudaBuscador.setTextoAyuda(textoAyuda);
                confAyudaBuscadorService.saveConfAyudaBuscador(confAyudaBuscador);
            } else // si es mini mapa
            if (componente.equals(TipoComponente.minimapa)) {
                // no se hace nada dado que ya se ha guardado
                tieneMiniMapa = true;
                continue;
            } else if (componente.equals(TipoComponente.cargar_tematicos)) {
                // no se hace nada dado que ya se ha guardado
                tieneCapasTematicas = true;
                continue;
            } else if (componente.equals(TipoComponente.vistas_predefinidas)) {
                // no se hace nada dado que ya se ha guardado
                tieneVistasPredefinidas = true;
                continue;
            } else {
                Componentes componentes = new Componentes();
                componentes.setGeoPortal(geoPortal);
                componentes.setTipo(componente);
                componentesService.saveComponentes(componentes);
            }
        }
        // comprobamos ahora si antes tenia componente cargar_tematicas
        // o vistas_predefinidas y ahora no. Si es asi..se debe borrar
        // componente
        if (teniaCapasTematicas && !tieneCapasTematicas) {
            // borramos configuracion si existe de capas tematicas
            List<ConfCapasTematicas> listadoConfCapasTematicasAntiguo = confCapasTematicasService.findConfCapasTematicasesByGeoPortal(geoPortal);
            if (listadoConfCapasTematicasAntiguo != null) {
                for (int j = 0; j < listadoConfCapasTematicasAntiguo.size(); j++) {
                    ConfCapasTematicas confCapasTematicas = listadoConfCapasTematicasAntiguo.get(j);
                    confCapasTematicasService.deleteConfCapasTematicas(confCapasTematicas);
                }
            }
        }
        if (teniaVistasPredefinidas && !tieneVistasPredefinidas) {
            // borrramos configuracion si existe de vistas preliminares
            List<ConfVistasPredefinidas> listadoconfVistasPredefinidasAntiguo = confVistasPredefinidasService.findConfVistasPredefinidasesByGeoPortal(geoPortal);
            if (listadoconfVistasPredefinidasAntiguo != null) {
                for (int i = 0; i < listadoconfVistasPredefinidasAntiguo.size(); i++) {
                    ConfVistasPredefinidas confVistasPredefinidas = listadoconfVistasPredefinidasAntiguo.get(i);
                    confVistasPredefinidasService.deleteConfVistasPredefinidas(confVistasPredefinidas);
                }
            }
        }
        if (teniaMiniMapa && !tieneMiniMapa) {
            // borramos si existe configuracion de mini mapa
            ConfMiniMapa confMiniMapaAntiguo = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
            if (confMiniMapaAntiguo != null) {
                confMiniMapaService.deleteConfMiniMapa(confMiniMapaAntiguo);
            }
        }
    }
    uiModel.asMap().clear();
    geoPortalService.updateGeoPortal(geoPortal);
    String[] paramValues = httpServletRequest.getParameterValues("dtt_table_id_hash");
    // si viene dado el parametro es que hay que abrir el toc
    if (params.containsKey("openConfigureTocRedirect")) {
        String[] redirect = httpServletRequest.getParameterValues("datatablesRedirect");
        if (paramValues != null && paramValues.length > 0) {
            return "redirect:".concat("/mapaedicion/").concat(geoPortal.getId().toString()).concat("?datatablesRedirect=").concat(redirect[0]).concat("&dtt_table_id_hash=").concat(paramValues[0]);
        } else {
            return "redirect:".concat("/mapaedicion/").concat(geoPortal.getId().toString()).concat("?datatablesRedirect=").concat(redirect[0]);
        }
    }
    return "redirect:/geoportales";
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
public String delete(Long id,Integer page,Integer size,Model uiModel){
    GeoPortal geoPortal = geoPortalService.findGeoPortal(id);
    // borramos si existe configuracion ayuda buscador
    ConfAyudaBuscador confAyudaBuscador = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
    if (confAyudaBuscador != null) {
        confAyudaBuscadorService.deleteConfAyudaBuscador(confAyudaBuscador);
    }
    // borramos datos de componentes del portal
    List<Componentes> listadoComponentesAntiguos = componentesService.findComponentesByGeoPortal(geoPortal);
    for (int i = 0; i < listadoComponentesAntiguos.size(); i++) {
        Componentes comp = listadoComponentesAntiguos.get(i);
        componentesService.deleteComponentes(comp);
    }
    geoPortalService.deleteGeoPortal(geoPortal);
    uiModel.asMap().clear();
    uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
    uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
    return "redirect:/geoportales";
}


@RequestMapping(params = { "find=ByAll", "form" }, method = RequestMethod.GET)
public String buscarGeoPortales(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    uiModel.addAttribute("sistemacoordenadasitems", sistemaCoordenadasService.findAllSistemaCoordenadas());
    if (params.containsKey("back")) {
        // Saves parameters to display when the page reload
        uiModel.addAttribute("params", params);
    }
    return "geoportales/search";
}


@RequestMapping(value = "/{id}", params = "formError", produces = "text/html")
public String updateFormError(Long id,GeoPortal geoPortal,BindingResult bindingResult,Model uiModel,HttpServletRequest httpServletRequest){
    if (uiModel.containsAttribute("org.springframework.validation.BindingResult.geoportal")) {
        bindingResult = createErrorMessagesForGeoPortal(bindingResult, geoPortal);
    }
    // cuando no tenemos la informacion del geoportal la obtenemos por id
    if (geoPortal == null || geoPortal.getTitulo() == null) {
        // obtenemos geoPortal con el id
        geoPortal = geoPortalService.findGeoPortal(id);
    }
    populateEditForm(uiModel, geoPortal);
    // obtenemos listado componentes
    uiModel.addAttribute("listadoComponentes", Arrays.asList(TipoComponente.values()));
    // obtenemos conf_ayuda_buscador
    ConfAyudaBuscador confAyudaBuscador = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
    if (confAyudaBuscador != null) {
        String textoAyuda = confAyudaBuscador.getTextoAyuda();
        uiModel.addAttribute("textoAyuda", textoAyuda);
    }
    // obtenemos conf_minimapa
    ConfMiniMapa confMiniMapa = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
    if (confMiniMapa != null) {
        Long idServicioWeb = confMiniMapa.getServicioWeb().getId();
        uiModel.addAttribute("miniMapa", idServicioWeb);
    }
    // obtenemos conf capas tematicas
    List<ConfCapasTematicas> confCapasTematicas = confCapasTematicasService.findConfCapasTematicasesByGeoPortal(geoPortal);
    if (confCapasTematicas != null && confCapasTematicas.size() > 0) {
        uiModel.addAttribute("cargarTematico", true);
    }
    // obtenemos conf vistas_previas
    List<ConfVistasPredefinidas> confVistasPredefinidas = confVistasPredefinidasService.findConfVistasPredefinidasesByGeoPortal(geoPortal);
    if (confVistasPredefinidas != null && confVistasPredefinidas.size() > 0) {
        uiModel.addAttribute("vistasPredefinidas", true);
    }
    // obtenemos listado seleccionados
    List<Componentes> listadoComponentesSeleccionados = componentesService.findComponentesByGeoPortal(geoPortal);
    uiModel.addAttribute("listadoComponentesSeleccionados", listadoComponentesSeleccionados);
    // obtenemos agrupadores capas seleccionados
    if (geoPortal.getAgrupadorCapa() != null && geoPortal.getAgrupadorCapa().size() > 0) {
        // obtenemos grupos capas seleccionadas para el geoPortal
        String selectedGroupLayers = Util.getSelectedLayersString(geoPortal.getAgrupadorCapa());
        if (selectedGroupLayers != null && !selectedGroupLayers.isEmpty()) {
            uiModel.addAttribute("selectedGroupLayers", StringEscapeUtils.escapeHtml4(selectedGroupLayers));
        }
    }
    // Se añade el logo actual como logo nuevo.
    uiModel.addAttribute("newLogo", geoPortal.getLogoString());
    // anyadimos mensaje ayuda de url
    String textoUrlEdicion = messageSource_dtt.getMessage("message_help_url_geo_portal_edit", null, LocaleContextHolder.getLocale());
    // llamamos al metodo que devuelve la url completa del portal
    String urlCompleta = geoPortalService.getFullUrlPortal(geoPortal.getUrl(), httpServletRequest.getScheme(), httpServletRequest.getServerName(), httpServletRequest.getServerPort(), httpServletRequest.getContextPath());
    // concatenamos al mensaje de ayuda la url completa
    String textoAyudaUrlEdicion = textoUrlEdicion.concat(":").concat("<br/>").concat(urlCompleta);
    uiModel.addAttribute("textoAyudaUrlEdicion", textoAyudaUrlEdicion);
    return "geoportales/update";
}


@RequestMapping(headers = "Accept=application/json", value = "/datatables/ajax", produces = "application/json")
@ResponseBody
public DatatablesResponse<Map<String,String>> findAllGeoPortales(DatatablesCriterias criterias,GeoPortal geoPortal,HttpServletRequest request){
    // URL parameters are used as base search filters
    Map<String, Object> baseSearchValuesMap = getPropertyMap(geoPortal, request);
    setDatatablesBaseFilter(baseSearchValuesMap);
    SearchResults<GeoPortal> searchResult = datatablesUtilsBean_dtt.findByCriteria(GeoPortal.class, criterias, baseSearchValuesMap);
    // Get datatables required counts
    long totalRecords = searchResult.getTotalCount();
    long recordsFound = searchResult.getResultsCount();
    // Entity pk field name
    String pkFieldName = "id";
    org.springframework.ui.Model uiModel = new org.springframework.ui.ExtendedModelMap();
    addDateTimeFormatPatterns(uiModel);
    Map<String, Object> datePattern = uiModel.asMap();
    // recorremos listado de geoPortales para setear campo urlCompleta
    List<GeoPortal> listadoGeoPortales = searchResult.getResults();
    for (int i = 0; i < listadoGeoPortales.size(); i++) {
        GeoPortal geoPortalTmp = listadoGeoPortales.get(i);
        // llamamos al metodo que devuelve la url completa del portal
        String urlCompleta = geoPortalService.getFullUrlPortal(geoPortalTmp.getUrl(), request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath());
        geoPortalTmp.setUrlCompleta(urlCompleta);
    }
    DataSet<Map<String, String>> dataSet = datatablesUtilsBean_dtt.populateDataSet(listadoGeoPortales, pkFieldName, totalRecords, recordsFound, criterias.getColumnDefs(), datePattern);
    return DatatablesResponse.build(dataSet, criterias);
}


@RequestMapping(params = "form", produces = "text/html")
public String createForm(Model uiModel){
    populateEditForm(uiModel, new GeoPortal());
    uiModel.addAttribute("listadoComponentes", Arrays.asList(TipoComponente.values()));
    return "geoportales/create";
}


public BindingResult createErrorMessagesForGeoPortal(BindingResult bindingResult,GeoPortal geoPortal){
    // Valida que la url es única
    GeoPortal geoportalDB = null;
    List<GeoPortal> geoportales = geoPortalService.findGeoPortalesByUrlEquals(geoPortal.getUrl()).getResultList();
    if (!geoportales.isEmpty()) {
        geoportalDB = geoportales.get(0);
    }
    if (geoportalDB != null && !ObjectUtils.equals(geoPortal.getId(), geoportalDB.getId())) {
        bindingResult.rejectValue("url", "error_unique_url", "La Url introducida ya existe");
    }
    // Valida el formato de la url
    String url = geoPortal.getUrl();
    if (url != null && !url.matches("[-a-zA-Z0-9_-]{1,255}")) {
        bindingResult.rejectValue("url", "error_invalid_format", "Formato no válido");
    }
    return bindingResult;
}


@RequestMapping(value = "/copyGeoPortal")
public String copyGeoPortal(String returnParam,Long id,Model uiModel,HttpServletRequest request){
    // obtenemos geoPortal con el id
    GeoPortal geoPortal = geoPortalService.findGeoPortal(id);
    // llamamos al metodo para clonar el geoPortal
    GeoPortal newGeoPortal = geoPortalService.clone(geoPortal);
    // actualizo nombre
    newGeoPortal.setTitulo(geoPortal.getTitulo() + Constants.COPIA);
    // llamo al servicio para guardar el nuevo geoPortal
    geoPortalService.saveGeoPortal(newGeoPortal);
    // procedemos a copiar componentes
    // obtemos listado de componentes del antiguo portal
    List<Componentes> listadoComponentes = componentesService.findComponentesByGeoPortal(geoPortal);
    // recorremos listado componentes antiguo portal para crear nuevos
    // componentes asociados al nuevo
    if (listadoComponentes != null && listadoComponentes.size() > 0) {
        for (int i = 0; i < listadoComponentes.size(); i++) {
            // obtenemos antiguo componente
            Componentes componenteViejo = listadoComponentes.get(i);
            // si es ayuda buscador buscamos texto
            if (componenteViejo.getTipo().toString().equals(TipoComponente.ayuda_buscador.toString())) {
                // buscamos configuracion texto ayuda antigua
                ConfAyudaBuscador confAyudaBuscadorViejo = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
                if (confAyudaBuscadorViejo != null) {
                    ConfAyudaBuscador confAyudaBuscador = new ConfAyudaBuscador();
                    confAyudaBuscador.setGeoPortal(newGeoPortal);
                    confAyudaBuscador.setTipo(componenteViejo.getTipo());
                    confAyudaBuscador.setTextoAyuda(confAyudaBuscadorViejo.getTextoAyuda());
                    confAyudaBuscadorService.saveConfAyudaBuscador(confAyudaBuscador);
                }
            } else if (componenteViejo.getTipo().toString().equals(TipoComponente.cargar_tematicos.toString())) {
                // buscamos configuracion cargas tematicas antigua
                ConfCapasTematicas confCapasTematicasViejo = confCapasTematicasService.findConfCapasTematicas(componenteViejo.getId());
                if (confCapasTematicasViejo != null) {
                    ConfCapasTematicas confCapasTematicas = new ConfCapasTematicas();
                    confCapasTematicas.setGeoPortal(newGeoPortal);
                    confCapasTematicas.setTipo(componenteViejo.getTipo());
                    confCapasTematicas.setNombre(confCapasTematicasViejo.getNombre());
                    confCapasTematicas.setGrupo(confCapasTematicasViejo.getGrupo());
                    confCapasTematicas.setLogo(confCapasTematicasViejo.getLogo());
                    confCapasTematicas.setLogoString(confCapasTematicasViejo.getLogoString());
                    confCapasTematicasService.saveConfCapasTematicas(confCapasTematicas);
                }
            } else if (componenteViejo.getTipo().toString().equals(TipoComponente.vistas_predefinidas.toString())) {
                // buscamos configuracion vistas predefinidas
                ConfVistasPredefinidas confVistasPredefinidasViejo = confVistasPredefinidasService.findConfVistasPredefinidas(componenteViejo.getId());
                if (confVistasPredefinidasViejo != null) {
                    ConfVistasPredefinidas confVistasPredefinidas = new ConfVistasPredefinidas();
                    confVistasPredefinidas.setGeoPortal(newGeoPortal);
                    confVistasPredefinidas.setTipo(componenteViejo.getTipo());
                    confVistasPredefinidas.setNombre(confVistasPredefinidasViejo.getNombre());
                    Set<ServicioWeb> serviciosWebViejo = confVistasPredefinidasViejo.getServiciosWeb();
                    Set<ServicioWeb> serviciosWeb = new HashSet<ServicioWeb>();
                    for (ServicioWeb servicioWeb : serviciosWebViejo) {
                        serviciosWeb.add(servicioWeb);
                    }
                    confVistasPredefinidas.setServiciosWeb(serviciosWeb);
                    confVistasPredefinidas.setLogo(confVistasPredefinidasViejo.getLogo());
                    confVistasPredefinidas.setLogoString(confVistasPredefinidasViejo.getLogoString());
                    confVistasPredefinidasService.saveConfVistasPredefinidas(confVistasPredefinidas);
                }
            } else if (componenteViejo.getTipo().toString().equals(TipoComponente.minimapa.toString())) {
                // buscamos configuracion mini mapa antiguo
                ConfMiniMapa confMiniMapaAntiguo = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
                if (confMiniMapaAntiguo != null) {
                    ConfMiniMapa confMiniMapa = new ConfMiniMapa();
                    confMiniMapa.setGeoPortal(newGeoPortal);
                    confMiniMapa.setTipo(componenteViejo.getTipo());
                    confMiniMapa.setServicioWeb(confMiniMapaAntiguo.getServicioWeb());
                    confMiniMapaService.saveConfMiniMapa(confMiniMapa);
                }
            } else {
                Componentes componenteNuevo = new Componentes();
                componenteNuevo.setGeoPortal(newGeoPortal);
                componenteNuevo.setTipo(componenteViejo.getTipo());
                componentesService.saveComponentes(componenteNuevo);
            }
        }
    }
    // copiamos configuracion que se almacena en el toc
    List<GeoportalServicioWeb> listadoGeoportalServicioWeb = geoportalServicioWebService.findGeoportalServicioWebsByGeoportal(geoPortal);
    if (listadoGeoportalServicioWeb != null && listadoGeoportalServicioWeb.size() > 0)
        for (int i = 0; i < listadoGeoportalServicioWeb.size(); i++) {
            GeoportalServicioWeb geoportalServicioWebOld = listadoGeoportalServicioWeb.get(i);
            // setamos nuevos valores
            GeoportalServicioWeb geoportalServicioWebNew = new GeoportalServicioWeb();
            geoportalServicioWebNew.setActivo(geoportalServicioWebOld.isActivo());
            geoportalServicioWebNew.setGeoportal(newGeoPortal);
            geoportalServicioWebNew.setHabilitado(geoportalServicioWebOld.isHabilitado());
            geoportalServicioWebNew.setOpacidad(geoportalServicioWebOld.getOpacidad());
            geoportalServicioWebNew.setPosicion(geoportalServicioWebOld.getPosicion());
            geoportalServicioWebNew.setServicioWeb(geoportalServicioWebOld.getServicioWeb());
            // guardamos configuracion nueva
            geoportalServicioWebService.saveGeoportalServicioWeb(geoportalServicioWebNew);
        }
    return "redirect:/geoportales";
}


@RequestMapping(method = RequestMethod.POST, produces = "text/html")
public String create(GeoPortal geoPortal,BindingResult bindingResult,Model uiModel,HttpServletRequest httpServletRequest){
    // Añade errores personalizados al binding
    bindingResult = createErrorMessagesForGeoPortal(bindingResult, geoPortal);
    if (bindingResult.hasErrors()) {
        populateEditForm(uiModel, geoPortal);
        return "geoportales/create";
    }
    uiModel.asMap().clear();
    geoPortalService.saveGeoPortal(geoPortal);
    return "redirect:/geoportales/" + encodeUrlPathSegment(geoPortal.getId().toString(), httpServletRequest);
}


@RequestMapping(headers = "Accept=application/json", value = "/datatables/ajax", params = "ajax_find=ByAll", produces = "application/json")
@ResponseBody
public DatatablesResponse<Map<String,String>> findByAll(DatatablesCriterias criterias,GeoPortal geoPortal,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    PathBuilder<GeoPortal> entity = new PathBuilder<GeoPortal>(GeoPortal.class, "geoPortal");
    BooleanBuilder baseSearch = new BooleanBuilder();
    SearchResults<GeoPortal> searchResult;
    // baseSearch para parametro titulo
    if (params.containsKey("tituloFinder") && params.get("tituloFinder") != null) {
        String titulo = (String) params.get("tituloFinder");
        if (!titulo.isEmpty()) {
            baseSearch.and(entity.getString("titulo").toLowerCase().contains(titulo.toLowerCase()));
        }
    }
    // baseSearch para parametro subtitulo
    if (params.containsKey("subTituloFinder") && params.get("subTituloFinder") != null) {
        String subtitulo = (String) params.get("subTituloFinder");
        if (!subtitulo.isEmpty()) {
            baseSearch.and(entity.getString("subtitulo").toLowerCase().contains(subtitulo.toLowerCase()));
        }
    }
    // baseSearch para parametro descripcion
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
    // baseSearch para parametro publicado
    if (params.containsKey("publicadoFinder") && params.get("publicadoFinder") != null) {
        String publicado = (String) params.get("publicadoFinder");
        if (publicado != null && !publicado.equals("empty")) {
            baseSearch.and(entity.getBoolean("publicado").eq(Boolean.parseBoolean(publicado)));
        }
    }
    // baseSearch para parametro coordenadas
    if (params.containsKey("coordenadasFinder") && params.get("coordenadasFinder") != null) {
        String coordenadas = (String) params.get("coordenadasFinder");
        if (!coordenadas.isEmpty()) {
            SistemaCoordenadas sistemaCoordenadas = sistemaCoordenadasService.findSistemaCoordenadasByCodigoEquals(coordenadas);
            baseSearch.and(entity.get("coordenadas").eq(sistemaCoordenadas));
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


@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
public String updateForm(Long id,Model uiModel,HttpServletRequest httpServletRequest){
    GeoPortal geoPortal = geoPortalService.findGeoPortal(id);
    populateEditForm(uiModel, geoPortal);
    // obtenemos listado componentes
    uiModel.addAttribute("listadoComponentes", Arrays.asList(TipoComponente.values()));
    // obtenemos conf_ayuda_buscador
    ConfAyudaBuscador confAyudaBuscador = confAyudaBuscadorService.findConfAyudaBuscadorsByGeoPortal(geoPortal);
    if (confAyudaBuscador != null) {
        String textoAyuda = confAyudaBuscador.getTextoAyuda();
        uiModel.addAttribute("textoAyuda", textoAyuda);
    }
    // obtenemos conf_minimapa
    ConfMiniMapa confMiniMapa = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
    if (confMiniMapa != null) {
        Long idServicioWeb = confMiniMapa.getServicioWeb().getId();
        uiModel.addAttribute("miniMapa", idServicioWeb);
    }
    // obtenemos conf capas tematicas
    List<ConfCapasTematicas> confCapasTematicas = confCapasTematicasService.findConfCapasTematicasesByGeoPortal(geoPortal);
    if (confCapasTematicas != null && confCapasTematicas.size() > 0) {
        uiModel.addAttribute("cargarTematico", true);
    }
    // obtenemos conf vistas_previas
    List<ConfVistasPredefinidas> confVistasPredefinidas = confVistasPredefinidasService.findConfVistasPredefinidasesByGeoPortal(geoPortal);
    if (confVistasPredefinidas != null && confVistasPredefinidas.size() > 0) {
        uiModel.addAttribute("vistasPredefinidas", true);
    }
    // obtenemos listado seleccionados
    List<Componentes> listadoComponentesSeleccionados = componentesService.findComponentesByGeoPortal(geoPortal);
    uiModel.addAttribute("listadoComponentesSeleccionados", listadoComponentesSeleccionados);
    // obtenemos agrupadores capas seleccionados
    if (geoPortal.getAgrupadorCapa() != null && geoPortal.getAgrupadorCapa().size() > 0) {
        // obtenemos grupos capas seleccionadas para el geoPortal
        String selectedGroupLayers = Util.getSelectedLayersString(geoPortal.getAgrupadorCapa());
        if (selectedGroupLayers != null && !selectedGroupLayers.isEmpty()) {
            uiModel.addAttribute("selectedGroupLayers", StringEscapeUtils.escapeHtml4(selectedGroupLayers));
        }
    }
    // Se añade el logo actual como logo nuevo.
    uiModel.addAttribute("newLogo", geoPortal.getLogoString());
    // anyadimos mensaje ayuda de url
    String textoUrlEdicion = messageSource_dtt.getMessage("message_help_url_geo_portal_edit", null, LocaleContextHolder.getLocale());
    // montamos url completa
    int port = httpServletRequest.getServerPort();
    if (httpServletRequest.getScheme().equals("http") && port == 80) {
        port = -1;
    } else if (httpServletRequest.getScheme().equals("https") && port == 443) {
        port = -1;
    }
    URL serverURL = null;
    String url = "";
    try {
        serverURL = new URL(httpServletRequest.getScheme(), httpServletRequest.getServerName(), port, httpServletRequest.getContextPath().concat("/map/").concat(geoPortal.getUrl()));
        url = serverURL.toString();
    } catch (MalformedURLException e) {
        // si da algun error, creamos nosotros la url de forma manual
        url = httpServletRequest.getScheme().concat("://").concat(httpServletRequest.getServerName()).concat(":").concat("" + httpServletRequest.getServerPort()).concat(httpServletRequest.getContextPath()).concat("/map/").concat(geoPortal.getUrl());
    }
    String textoAyudaUrlEdicion = textoUrlEdicion.concat(":").concat("<br/>").concat(url);
    uiModel.addAttribute("textoAyudaUrlEdicion", textoAyudaUrlEdicion);
    return "geoportales/update";
}


public LinkedHashMap<String,String> getFilterDescription(Map<String,String> params,HttpServletRequest request){
    LinkedHashMap<String, String> orderedParamsMap = new LinkedHashMap<String, String>();
    // Adds additional params
    if (params.containsKey("tituloFinder") && !params.get("tituloFinder").isEmpty()) {
        orderedParamsMap.put("tituloFinder", params.get("tituloFinder"));
    }
    if (params.containsKey("subTituloFinder") && !params.get("subTituloFinder").isEmpty()) {
        orderedParamsMap.put("subTituloFinder", params.get("subTituloFinder"));
    }
    if (params.containsKey("descripcionFinder") && !params.get("descripcionFinder").isEmpty()) {
        orderedParamsMap.put("descripcionFinder", params.get("descripcionFinder"));
    }
    if (params.containsKey("urlFinder") && !params.get("urlFinder").isEmpty()) {
        orderedParamsMap.put("urlFinder", params.get("urlFinder"));
    }
    if (params.containsKey("coordenadasFinder") && !params.get("coordenadasFinder").isEmpty()) {
        orderedParamsMap.put("coordenadasFinder", params.get("coordenadasFinder"));
    }
    if (params.containsKey("publicadoFinder") && !params.get("publicadoFinder").isEmpty() && !params.get("publicadoFinder").equals("empty")) {
        orderedParamsMap.put("publicadoFinder", params.get("publicadoFinder"));
    }
    return orderedParamsMap;
}


}