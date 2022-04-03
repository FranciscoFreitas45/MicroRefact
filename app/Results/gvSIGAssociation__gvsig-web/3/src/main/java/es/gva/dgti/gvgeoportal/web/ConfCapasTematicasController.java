package es.gva.dgti.gvgeoportal.web;
 import es.gva.dgti.gvgeoportal.domain.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.components.ConfCapasTematicas;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import es.gva.dgti.gvgeoportal.service.batch.ConfCapasTematicasBatchService;
import es.gva.dgti.gvgeoportal.service.domain.AgrupadorCapaService;
import es.gva.dgti.gvgeoportal.service.domain.GeoPortalService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.gvnix.addon.web.mvc.annotations.batch.GvNIXWebJpaBatch;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.loupefield.annotations.GvNIXLoupeController;
import org.gvnix.web.json.JsonResponse;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
@RequestMapping("/confcapastematicases")
@Controller
@RooWebScaffold(path = "confcapastematicases", formBackingObject = ConfCapasTematicas.class)
@GvNIXWebJQuery
@GvNIXWebJpaBatch(service = ConfCapasTematicasBatchService.class)
@GvNIXDatatables(ajax = true)
@GvNIXLoupeController
public class ConfCapasTematicasController {

@Autowired
 private GeoPortalService geoPortalService;

@Autowired
 private AgrupadorCapaService agrupadorCapaService;


@RequestMapping(method = RequestMethod.GET, produces = "text/html")
public String listDatatables(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    // Get parentId information for details render
    String parentId = params.remove("_dt_parentId");
    if (StringUtils.isNotBlank(parentId)) {
        uiModel.addAttribute("parentId", parentId);
    }
    String parentTableHashId = params.remove("dtt_parent_table_id_hash");
    if (StringUtils.isNotBlank(parentTableHashId)) {
        uiModel.addAttribute("dtt_parent_table_id_hash", parentTableHashId);
    }
    String tableHashId = params.remove("dtt_table_id_hash");
    if (StringUtils.isNotBlank(tableHashId) && !uiModel.containsAttribute("dtt_table_id_hash")) {
        uiModel.addAttribute("dtt_table_id_hash", tableHashId);
    }
    String rowOnTopIds = params.remove("dtt_row_on_top_ids");
    if (StringUtils.isNotBlank(rowOnTopIds)) {
        uiModel.addAttribute("dtt_row_on_top_ids", rowOnTopIds);
    }
    if (!params.isEmpty()) {
        uiModel.addAttribute("baseFilter", params);
    }
    return "confcapastematicases/list";
}


public void populateEditForm(Model uiModel,ConfCapasTematicas confCapasTematicas){
    uiModel.addAttribute("confCapasTematicas", confCapasTematicas);
    addDateTimeFormatPatterns(uiModel);
    uiModel.addAttribute("agrupadorcapas", agrupadorCapaService.findAllAgrupadorCapas());
    uiModel.addAttribute("geoportales", geoPortalService.findAllGeoPortales());
    uiModel.addAttribute("tipocomponentes", Arrays.asList(TipoComponente.values()));
}


@RequestMapping(value = "/componentes/cargarTematicas", method = RequestMethod.GET, produces = "text/html")
public String listDatatablesFromComponents(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    ConfCapasTematicas confCapasTematicas = new ConfCapasTematicas();
    // Get parentId information for details render
    String parentId = params.remove("_dt_parentId");
    if (StringUtils.isNotBlank(parentId)) {
        uiModel.addAttribute("parentId", parentId);
    }
    String parentTableHashId = params.remove("dtt_parent_table_id_hash");
    if (StringUtils.isNotBlank(parentTableHashId)) {
        uiModel.addAttribute("dtt_parent_table_id_hash", parentTableHashId);
    }
    String tableHashId = params.remove("dtt_table_id_hash");
    if (StringUtils.isNotBlank(tableHashId) && !uiModel.containsAttribute("dtt_table_id_hash")) {
        uiModel.addAttribute("dtt_table_id_hash", tableHashId);
    }
    String rowOnTopIds = params.remove("dtt_row_on_top_ids");
    if (StringUtils.isNotBlank(rowOnTopIds)) {
        uiModel.addAttribute("dtt_row_on_top_ids", rowOnTopIds);
    }
    if (params.containsKey("geoPortal")) {
        uiModel.addAttribute("geoPortal", params.get("geoPortal"));
    }
    if (!params.isEmpty()) {
        uiModel.addAttribute("baseFilter", params);
    }
    populateEditForm(uiModel, confCapasTematicas);
    return "confcapastematicases/listComponents";
}


@RequestMapping(headers = "Accept=application/json", value = "createOnLine", produces = "application/json")
@ResponseBody
public JsonResponse<List<ConfCapasTematicas>> createOnLine(Long geoPortalId,String nombre,Long grupoId,byte[] logo){
    JsonResponse<List<ConfCapasTematicas>> jsonResponse = new JsonResponse<List<ConfCapasTematicas>>();
    ConfCapasTematicas confCapasTematicas = new ConfCapasTematicas();
    try {
        confCapasTematicas.setNombre(nombre);
        confCapasTematicas.setTipo(TipoComponente.cargar_tematicos);
        GeoPortal geoPortal = GeoPortal.findGeoPortal(geoPortalId);
        confCapasTematicas.setGeoPortal(geoPortal);
        AgrupadorCapa agrupadorCapa = AgrupadorCapa.findAgrupadorCapa(grupoId);
        confCapasTematicas.setGrupo(agrupadorCapa);
        confCapasTematicas.setLogo(logo);
        confCapasTematicasService.saveConfCapasTematicas(confCapasTematicas);
    } catch (Exception ex) {
        jsonResponse.setStatus("ERROR");
        jsonResponse.setExceptionMessage(ex.getLocalizedMessage());
        return jsonResponse;
    }
    jsonResponse.setStatus("SUCCESS");
    return jsonResponse;
}


}