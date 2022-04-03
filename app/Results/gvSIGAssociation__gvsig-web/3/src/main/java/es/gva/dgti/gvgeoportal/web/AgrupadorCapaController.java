package es.gva.dgti.gvgeoportal.web;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.web.mvc.annotations.batch.GvNIXWebJpaBatch;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.gvnix.web.datatables.util.DatatablesUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaBatchService;
import es.gva.dgti.gvgeoportal.domain.AgrupadorCapaServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.service.batch.AgrupadorCapaServicioWebBatchService;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.ServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.SistemaCoordenadasService;
import es.gva.dgti.gvgeoportal.util.Constants;
import es.gva.dgti.gvgeoportal.Interface.SistemaCoordenadasService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
@RequestMapping("/agrupadorcapas")
@Controller
@RooWebScaffold(path = "agrupadorcapas", formBackingObject = AgrupadorCapa.class)
@GvNIXWebJQuery
@GvNIXWebJpaBatch(service = AgrupadorCapaBatchService.class)
@GvNIXDatatables(ajax = true, detailFields = { "servicios" })
public class AgrupadorCapaController {

@Autowired
 private SistemaCoordenadasService sistemaCoordenadasService;

@Autowired(required = false)
 private ServicioWebService servicioWebService;

@Autowired(required = false)
 private AgrupadorCapaServicioWebService agrupadorCapaServicioWebService;

@Autowired(required = false)
 private AgrupadorCapaServicioWebBatchService agrupadorCapaServicioWebBatchService;

 private  String DELETE_COUNT;


@RequestMapping(value = "/copyAgrupadorCapa")
public String copyAGrupadorCapa(String returnParam,Long id,Model uiModel,HttpServletRequest request){
    // obtenemos agrupador Capa con el id
    AgrupadorCapa agrupadorCapa = agrupadorCapaService.findAgrupadorCapa(id);
    // llamamos al metodo para clonar el agrupador
    AgrupadorCapa newAgrupador = agrupadorCapaService.clone(agrupadorCapa);
    // actualizo nombre
    newAgrupador.setNombre(agrupadorCapa.getNombre() + Constants.COPIA);
    // llamo al servicio para guardar la nueva AgrupacionCapa
    agrupadorCapaService.saveAgrupadorCapa(newAgrupador);
    // procedemos ahora a clonar la relacion con serviciosWeb. obtenemos listado relGruposServicioWeb
    List<AgrupadorCapaServicioWeb> relGroupsServices = new ArrayList<AgrupadorCapaServicioWeb>();
    relGroupsServices = agrupadorCapaServicioWebService.findAgrupadorCapaServicioWebByGroup(agrupadorCapa).getResultList();
    List<AgrupadorCapaServicioWeb> agrupadorCapaServicioWebToAdd = new ArrayList<AgrupadorCapaServicioWeb>();
    // recorremos listado de servicios para crear nuevas relaciones con el nuevo grupo creado
    for (Iterator<AgrupadorCapaServicioWeb> iterator = relGroupsServices.iterator(); iterator.hasNext(); ) {
        AgrupadorCapaServicioWeb AgrupadorCapaServicioWebOld = iterator.next();
        AgrupadorCapaServicioWeb agrupadorCapaServicioWebNew = new AgrupadorCapaServicioWeb();
        agrupadorCapaServicioWebNew.setAgrupador(newAgrupador);
        agrupadorCapaServicioWebNew.setServicioWeb(AgrupadorCapaServicioWebOld.getServicioWeb());
        agrupadorCapaServicioWebToAdd.add(agrupadorCapaServicioWebNew);
    }
    agrupadorCapaServicioWebBatchService.create(agrupadorCapaServicioWebToAdd);
    return "redirect:/agrupadorcapas";
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
    // Add attribute available into view with information about each detail
    // datatables
    Map<String, String> details;
    List<Map<String, String>> detailsInfo = new ArrayList<Map<String, String>>(1);
    details = new HashMap<String, String>();
    // Base path for detail datatables entity (to get detail datatables
    // fragment URL)
    details.put("path", "agrupadorcapaserviciowebs");
    details.put("property", "servicios");
    // Property name in detail entity with the relation to master entity
    details.put("mappedBy", "agrupador");
    detailsInfo.add(details);
    uiModel.addAttribute("detailsInfo", detailsInfo);
    // anyadimos parametros para que se muestre opciones para creacion
    // update y duplicate
    uiModel.addAttribute("allowCreate", true);
    uiModel.addAttribute("allowUpdate", true);
    uiModel.addAttribute("allowSelection", false);
    uiModel.addAttribute("allowDuplicate", true);
    uiModel.addAttribute("allowDelete", true);
    uiModel.addAttribute("fromComponents", false);
    return "agrupadorcapas/list";
}


@RequestMapping(produces = "text/html", method = RequestMethod.POST, params = "datatablesRedirect")
public String createDatatablesDetail(String redirect,AgrupadorCapa agrupadorcapa,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    // Do common create operations (check errors, populate, persist, ...)
    String view = create(agrupadorcapa, bindingResult, uiModel, httpServletRequest);
    // If binding errors or no redirect, return common create error view
    // (remain in create form)
    if (bindingResult.hasErrors() || redirect == null || redirect.trim().isEmpty()) {
        return view;
    }
    String editingView = "redirect:".concat("agrupadorcapas/").concat(agrupadorcapa.getId().toString()).concat("?form&datatablesRedirect=").concat(redirect).concat("&dtt_table_id_hash=");
    String[] paramValues = httpServletRequest.getParameterValues("dtt_table_id_hash");
    if (paramValues != null && paramValues.length > 0) {
        redirectModel.addFlashAttribute("dtt_table_id_hash", paramValues[0]);
    } else {
        redirectModel.addFlashAttribute("dtt_table_id_hash", "");
    // editingView = editingView.concat(paramValues[0]);
    }
    redirectModel.addFlashAttribute(DatatablesUtilsBean.ROWS_ON_TOP_IDS_PARAM, agrupadorcapa.getId());
    // Return to editing view form if there aren't errors
    return editingView;
}


public void populateEditForm(Model uiModel,AgrupadorCapa agrupadorCapa){
    uiModel.addAttribute("agrupadorCapa", agrupadorCapa);
    addDateTimeFormatPatterns(uiModel);
}


@RequestMapping(method = RequestMethod.POST, value = "/borrarservicios", params = { "agrupadorCapa" }, produces = "application/json")
public ResponseEntity<Map<String,Object>> removeServices(HttpServletRequest request,AgrupadorCapa agrupadorCapa,boolean all,List<Long> idList,boolean selectedIn){
    Map<String, Object> response = new HashMap<String, Object>();
    List<AgrupadorCapaServicioWeb> relGroupsServicesToDelete = new ArrayList<AgrupadorCapaServicioWeb>();
    if (all) {
        // Borra todas las relaciones que tienen relación con el agrupador
        relGroupsServicesToDelete = agrupadorCapaServicioWebService.findAgrupadorCapaServicioWebByGroup(agrupadorCapa).getResultList();
        response.put("all", true);
    } else if (idList != null && !idList.isEmpty()) {
        if (selectedIn) {
            // Borra todas las relaciones que están en la lista
            relGroupsServicesToDelete = agrupadorCapaServicioWebService.findAgrupadorCapaServicioWebInIdList(idList).getResultList();
        } else {
            // Borra todas las relaciones que no están en la lista pero si
            // están relacionadas con el agrupador
            relGroupsServicesToDelete = agrupadorCapaServicioWebService.findAgrupadorCapaServicioWebNotInIdListAndGroup(idList, agrupadorCapa).getResultList();
        }
        response.put("all", false);
    } else {
        response.put(DELETE_COUNT, Integer.valueOf(0));
    }
    Integer deleteCount = relGroupsServicesToDelete.size();
    response.put(DELETE_COUNT, deleteCount);
    agrupadorCapaServicioWebBatchService.delete(relGroupsServicesToDelete);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
}


@RequestMapping(method = RequestMethod.POST, value = "/anaydirservicios", params = { "agrupadorCapa" }, produces = "application/json")
public ResponseEntity<Map<String,Object>> addServices(HttpServletRequest request,AgrupadorCapa agrupadorCapa,boolean all,List<Long> idList,boolean selectedIn){
    Map<String, Object> response = new HashMap<String, Object>();
    List<ServicioWeb> servicesToAdd = new ArrayList<ServicioWeb>();
    if (all) {
        // Añade todos los servicios que no están en el agrupador
        servicesToAdd = servicioWebService.findServicesByNoGroup(agrupadorCapa).getResultList();
        response.put("all", true);
    } else if (idList != null && !idList.isEmpty()) {
        if (selectedIn) {
            // Añade todos los servicios que están en la lista
            servicesToAdd = servicioWebService.findServicesInIdList(idList).getResultList();
        } else {
            // Añade todos los servicios que no esan en la lista y no están
            // relacionados con el agrupador
            servicesToAdd = servicioWebService.findServicesNotInIdListAndNoGroup(idList, agrupadorCapa).getResultList();
        }
        response.put("all", false);
    } else {
        response.put(DELETE_COUNT, Integer.valueOf(0));
    }
    Integer deleteCount = servicesToAdd.size();
    response.put(DELETE_COUNT, deleteCount);
    List<AgrupadorCapaServicioWeb> agrupadorCapaServicioWebToAdd = new ArrayList<AgrupadorCapaServicioWeb>();
    for (Iterator<ServicioWeb> iterator = servicesToAdd.iterator(); iterator.hasNext(); ) {
        ServicioWeb servicio = iterator.next();
        AgrupadorCapaServicioWeb agrupadorCapaServicioWeb = new AgrupadorCapaServicioWeb();
        agrupadorCapaServicioWeb.setAgrupador(agrupadorCapa);
        agrupadorCapaServicioWeb.setServicioWeb(servicio);
        agrupadorCapaServicioWebToAdd.add(agrupadorCapaServicioWeb);
    }
    agrupadorCapaServicioWebBatchService.create(agrupadorCapaServicioWebToAdd);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
}


@RequestMapping(value = "/componentes/addLayers", method = RequestMethod.GET, produces = "text/html")
public String listDatatablesFromComponents(Model uiModel,HttpServletRequest request){
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
    // Add attribute available into view with information about each detail
    // datatables
    Map<String, String> details;
    List<Map<String, String>> detailsInfo = new ArrayList<Map<String, String>>(1);
    details = new HashMap<String, String>();
    // Base path for detail datatables entity (to get detail datatables
    // fragment URL)
    details.put("path", "agrupadorcapaserviciowebs");
    details.put("property", "servicios");
    // Property name in detail entity with the relation to master entity
    details.put("mappedBy", "agrupador");
    detailsInfo.add(details);
    uiModel.addAttribute("detailsInfo", detailsInfo);
    uiModel.addAttribute("datatablesInlineEditing", false);
    uiModel.addAttribute("datatablesInlineCreating", false);
    // anyadimos parametros para que no se muestre nada de update, create en
    // los datatables
    uiModel.addAttribute("allowCreate", false);
    uiModel.addAttribute("allowUpdate", false);
    // si esta el atributo es que viene de la lupa y no queremos permitir
    // seleccion multiple
    if (uiModel.containsAttribute("allowSelection")) {
        uiModel.addAttribute("allowSelection", false);
    } else {
        uiModel.addAttribute("allowSelection", true);
    }
    uiModel.addAttribute("allowDuplicate", false);
    uiModel.addAttribute("allowDelete", false);
    uiModel.addAttribute("fromComponents", true);
    return "agrupadorcapas/listComponents";
}


}