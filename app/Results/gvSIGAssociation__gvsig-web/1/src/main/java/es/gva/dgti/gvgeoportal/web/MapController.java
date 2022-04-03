package es.gva.dgti.gvgeoportal.web;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.gvnix.addon.geo.annotations.GvNIXMapViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.security.SafeUser;
import es.gva.dgti.gvgeoportal.service.domain.GeoPortalService;
import es.gva.dgti.gvgeoportal.util.SecurityUtils;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
@Controller
@RequestMapping("/map")
@GvNIXMapViewer(entityLayers = {}, projection = "EPSG3857")
public class MapController {

 private  Logger LOGGER;

@Autowired(required = false)
 private GeoPortalService geoportalService;


@RequestMapping(method = RequestMethod.GET, produces = "text/html")
public String showMap(Model uiModel,HttpServletRequest request){
    return "resourceNotFound";
}


@RequestMapping(method = RequestMethod.GET, value = "/{url}")
public String loadGeoPortalByUrl(Model uiModel,HttpServletRequest request,String url){
    // obtenemos en primer lugar si el usuario esta autenticado
    SafeUser safeUser = null;
    try {
        safeUser = SecurityUtils.getCurrentAuthentication();
    } catch (Exception ex) {
        LOGGER.error("No se ha podido recuperar el usuario logeado.");
    }
    List<GeoPortal> geoportalList = null;
    // si el usuario esta logeado, obtenemos el portal sin tener en cuenta si esta publicado o no
    if (safeUser != null) {
        geoportalList = geoportalService.findGeoPortalesByUrlEquals(url, "titulo", "ASC").getResultList();
    } else {
        geoportalList = geoportalService.findPublicGeoPortalesByUrlEquals(url).getResultList();
    }
    if (!geoportalList.isEmpty()) {
        GeoPortal geoportal = geoportalList.get(0);
        uiModel.addAllAttributes(geoportalService.getComponentsAndInformationByGeoportal(geoportal));
        // atributo para controlar si se muestra el titulo en el toc y el boton volver atras
        uiModel.addAttribute("isPublic", true);
        uiModel.addAttribute("cssPublicPortal", "publicPortal");
    } else {
        return "resourceNotFound";
    }
    return "map/showPublic";
}


}