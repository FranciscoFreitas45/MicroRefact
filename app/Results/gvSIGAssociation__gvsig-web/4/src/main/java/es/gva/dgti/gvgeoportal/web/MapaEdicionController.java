package es.gva.dgti.gvgeoportal.web;
 import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.gvnix.addon.geo.annotations.GvNIXMapViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.domain.ServicioWeb;
import es.gva.dgti.gvgeoportal.service.domain.GeoportalServicioWebService;
import es.gva.dgti.gvgeoportal.service.domain.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
@Controller
@RequestMapping("/mapaedicion")
@GvNIXMapViewer(entityLayers = {}, projection = "EPSG3857")
public class MapaEdicionController {

@Autowired(required = false)
 private GeoportalServicioWebService geoportalServicioWebService;

@Autowired(required = false)
 private ServicioWebService servicioWebService;

 private  Logger LOGGER;


@RequestMapping(method = RequestMethod.POST, value = "/obtenerinformacionservicioweb", params = { "servicioWeb", "geoportal" }, produces = "application/json")
public ResponseEntity<Map<String,String>> getServiceWebInformation(HttpServletRequest httpServletRequest,ServicioWeb servicioWeb,GeoPortal geoportal){
    Map<String, String> response = new HashMap<String, String>();
    List<GeoportalServicioWeb> geoportalServicioWebList = geoportalServicioWebService.findGeoportalServicioWebByGeoportalAndServicioWeb(geoportal, servicioWeb).getResultList();
    // if (geoportalServicioWebList.isEmpty()) {
    response.put("id", servicioWeb.getId().toString());
    response.put("name", servicioWeb.getNombre());
    response.put("type", servicioWeb.getTipo().toString().toLowerCase());
    response.put("url", servicioWeb.getUrl());
    response.put("version", servicioWeb.getVersionProtocolo());
    response.put("imageFormat", servicioWeb.getFormatoImagen());
    response.put("crs", geoportal.getCoordenadas().getCodigo());
    Map<String, String> map = servicioWebService.getLayersAndStylesOrderByLayersName(servicioWeb);
    response.put("layers", map.get("nombresCapas"));
    response.put("styles", map.get("estilosCapas"));
    return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
// }
// else {
// return new ResponseEntity<Map<String, String>>(response,
// HttpStatus.NOT_ACCEPTABLE);
// }
}


@RequestMapping(method = RequestMethod.GET, produces = "text/html")
public String showMap(Model uiModel,HttpServletRequest request){
    return "resourceNotFound";
}


@RequestMapping(method = RequestMethod.GET, value = "/{id}")
public String loadGeoPortalById(Model uiModel,HttpServletRequest request,GeoPortal geoportal){
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
        return "mapaedicion/show";
    } else {
        return "resourceNotFound";
    }
}


@RequestMapping(method = RequestMethod.POST, value = "/guardartoc", params = { "jsonMapEditionStatus", "geoportal" }, produces = "application/json")
public ResponseEntity<Map<String,Boolean>> updateToc(HttpServletRequest httpServletRequest,String jsonMapEditionStatusString,GeoPortal geoportal){
    Map<String, Boolean> response = new HashMap<String, Boolean>();
    ObjectMapper mapper = new ObjectMapper();
    try {
        @SuppressWarnings("unchecked")
        Map<String, Object> // Se obtiene el localStorage como un map.
        jsonMapEditionStatus = mapper.readValue(jsonMapEditionStatusString, Map.class);
        return geoportalServicioWebService.updateTocByGeoportal(geoportal, jsonMapEditionStatus);
    } catch (JsonParseException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        response.put("update", false);
        return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (JsonMappingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        response.put("update", false);
        return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (IOException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
        response.put("update", false);
        return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}