package es.gva.dgti.gvgeoportal.web;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.loupefield.annotations.GvNIXLoupeController;
import org.gvnix.addon.web.mvc.annotations.batch.GvNIXWebJpaBatch;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.gva.dgti.gvgeoportal.domain.CapasServicioWeb;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.domain.components.ConfMiniMapa;
import es.gva.dgti.gvgeoportal.domain.enumerated.TipoComponente;
import es.gva.dgti.gvgeoportal.service.batch.ConfMiniMapaBatchService;
import es.gva.dgti.gvgeoportal.service.domain.CapasServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.GeoPortalService;
import es.gva.dgti.gvgeoportal.service.domain.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
@RequestMapping("/confminimapas")
@Controller
@RooWebScaffold(path = "confminimapas", formBackingObject = ConfMiniMapa.class, update = false, delete = false, create = false)
@GvNIXWebJQuery
@GvNIXWebJpaBatch(service = ConfMiniMapaBatchService.class)
@GvNIXDatatables(ajax = true)
@GvNIXLoupeController
public class ConfMiniMapaController {

@Autowired
 private ServicioWebService servicioWebService;

@Autowired(required = false)
 private CapasServicioWebService capasServicioWebService;

@Autowired
 private GeoPortalService geoPortalService;


@RequestMapping(produces = "text/html", method = RequestMethod.PUT, params = "datatablesRedirect")
public String updateDatatablesDetail(String redirect,ConfMiniMapa confminimapa,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return null;
}


@RequestMapping(value = "componentes/cargarMiniMapa", method = RequestMethod.GET, produces = "text/html")
public String loadSearchFromComponent(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    ConfMiniMapa confMiniMapa = null;
    if (params.containsKey("geoPortal")) {
        String idGeoPortal = params.get("geoPortal");
        if (!StringUtils.isBlank(idGeoPortal)) {
            GeoPortal geoPortal = geoPortalService.findGeoPortal(Long.parseLong(idGeoPortal));
            uiModel.addAttribute("geoPortal", geoPortal);
            confMiniMapa = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
        }
    }
    if (confMiniMapa == null) {
        confMiniMapa = new ConfMiniMapa();
    }
    uiModel.addAttribute("confMiniMapa", confMiniMapa);
    ServicioWeb servicioWeb = confMiniMapa.getServicioWeb();
    String layersSelected = "";
    String stylesSelected = "";
    if (servicioWeb != null) {
        List<CapasServicioWeb> listCapasServicioWeb = capasServicioWebService.findCapasServicioWebsByServicioWeb(servicioWeb);
        // obtenemos informacion sobre las capas y estilos
        if (servicioWeb != null && listCapasServicioWeb != null && listCapasServicioWeb.size() > 0) {
            layersSelected = servicioWebService.getSelectedInfoLayersNames(listCapasServicioWeb, true);
            stylesSelected = servicioWebService.getStylesNames(listCapasServicioWeb);
        }
    }
    // String con las capas seleccionadas
    uiModel.addAttribute("layersSelected", layersSelected);
    // String con los estilos seleccionadas
    uiModel.addAttribute("stylesSelected", stylesSelected);
    return "confminimapas/search";
}


@RequestMapping(produces = "text/html", method = RequestMethod.DELETE, params = "datatablesRedirect", value = "/{id}")
public String deleteDatatablesDetail(String redirect,Long id,Integer page,Integer size,Model uiModel){
    return null;
}


@RequestMapping(produces = "text/html", method = RequestMethod.POST, params = "datatablesRedirect")
public String createDatatablesDetail(String redirect,ConfMiniMapa confminimapa,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return null;
}


@RequestMapping(value = "componentes/guardarMiniMapa", method = RequestMethod.GET, produces = "text/html")
public String create(Model uiModel,HttpServletRequest request){
    Map<String, String> params = populateParametersMap(request);
    String idGeoPortal = params.get("geoPortal");
    String idServicioWeb = params.get("servicioWeb");
    // si vienen los dos parametros informados procedemos a borrar el
    // servicioWeb previamente borrado y anyadir el nuevo
    if (!StringUtils.isBlank(idGeoPortal) && !StringUtils.isBlank(idServicioWeb)) {
        GeoPortal geoPortal = geoPortalService.findGeoPortal(Long.parseLong(idGeoPortal));
        ServicioWeb servicioWeb = servicioWebService.findServicioWeb(Long.parseLong(idServicioWeb));
        // borramos si existe configuracion de mini mapa
        ConfMiniMapa confMiniMapaAntiguo = confMiniMapaService.findConfMiniMapasByGeoPortal(geoPortal);
        if (confMiniMapaAntiguo != null) {
            confMiniMapaService.deleteConfMiniMapa(confMiniMapaAntiguo);
        }
        ConfMiniMapa confMiniMapa = new ConfMiniMapa();
        confMiniMapa.setGeoPortal(geoPortal);
        confMiniMapa.setTipo(TipoComponente.minimapa);
        confMiniMapa.setServicioWeb(servicioWeb);
        confMiniMapaService.saveConfMiniMapa(confMiniMapa);
    }
    return "redirect:/geoportales";
}


}