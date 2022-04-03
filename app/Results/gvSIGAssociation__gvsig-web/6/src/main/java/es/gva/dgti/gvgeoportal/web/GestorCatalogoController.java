package es.gva.dgti.gvgeoportal.web;
 import es.gva.dgti.gvgeoportal.domain.GestorCatalogo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import es.gva.dgti.gvgeoportal.service.batch.GestorCatalogoBatchService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.gvnix.addon.web.mvc.annotations.batch.GvNIXWebJpaBatch;
import org.gvnix.addon.datatables.annotations.GvNIXDatatables;
@RequestMapping("/gestorcatalogos")
@Controller
@RooWebScaffold(path = "gestorcatalogos", formBackingObject = GestorCatalogo.class)
@GvNIXWebJQuery
@GvNIXWebJpaBatch(service = GestorCatalogoBatchService.class)
@GvNIXDatatables(ajax = true, inlineEditing = true)
public class GestorCatalogoController {


@RequestMapping(method = RequestMethod.POST, produces = "text/html")
public String create(GestorCatalogo gestorCatalogo,BindingResult bindingResult,Model uiModel,HttpServletRequest httpServletRequest){
    if (bindingResult.hasErrors()) {
        populateEditForm(uiModel, gestorCatalogo);
        return "gestorcatalogos/create";
    }
    uiModel.asMap().clear();
    gestorCatalogoService.saveGestorCatalogo(gestorCatalogo);
    return "redirect:/gestorcatalogos/" + encodeUrlPathSegment(gestorCatalogo.getId().toString(), httpServletRequest);
}


}