package es.gva.dgti.gvgeoportal.web;
 import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
import org.gvnix.addon.loupefield.annotations.GvNIXLoupeController;
import org.gvnix.addon.web.mvc.annotations.batch.GvNIXWebJpaBatch;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.gva.dgti.gvgeoportal.domain.GeoPortal;
import es.gva.dgti.gvgeoportal.domain.GeoportalServicioWeb;
import es.gva.dgti.gvgeoportal.service.batch.GeoportalServicioWebBatchService;
@RequestMapping("/geoportalserviciowebs")
@Controller
@RooWebScaffold(path = "geoportalserviciowebs", formBackingObject = GeoportalServicioWeb.class, update = false, delete = false, create = false)
@GvNIXWebJQuery
@GvNIXWebJpaBatch(service = GeoportalServicioWebBatchService.class)
@GvNIXDatatables(ajax = true)
@GvNIXLoupeController
public class GeoportalServicioWebController {


@RequestMapping(value = "dialogoanyadircapa/{geoportal}", method = RequestMethod.GET, produces = "text/html")
public String addLayerDialog(Model uiModel,HttpServletRequest request,GeoPortal geoportal){
    uiModel.addAttribute("geoPortal", geoportal);
    uiModel.addAttribute("geoportalServicioWeb", new GeoportalServicioWeb());
    return "geoportalserviciowebs/search";
}


@RequestMapping(produces = "text/html", method = RequestMethod.PUT, params = "datatablesRedirect")
public String updateDatatablesDetail(String redirect,GeoportalServicioWeb geoportalServicioWeb,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return null;
}


@RequestMapping(produces = "text/html", method = RequestMethod.POST, params = "datatablesRedirect")
public String createDatatablesDetail(String redirect,GeoportalServicioWeb geoportalServicioWeb,BindingResult bindingResult,Model uiModel,RedirectAttributes redirectModel,HttpServletRequest httpServletRequest){
    return null;
}


@RequestMapping(produces = "text/html", method = RequestMethod.DELETE, params = "datatablesRedirect", value = "/{id}")
public String deleteDatatablesDetail(String redirect,Long id,Integer page,Integer size,Model uiModel){
    return null;
}


}