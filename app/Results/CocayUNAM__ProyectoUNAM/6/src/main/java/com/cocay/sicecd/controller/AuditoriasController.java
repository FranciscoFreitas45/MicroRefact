package com.cocay.sicecd.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.model.Log_sys;
import com.cocay.sicecd.repo.Log_sysRep;
@Controller
public class AuditoriasController {

@Autowired
 private Log_sysRep sysRep;


@RequestMapping(value = "/listaAuditorias", method = RequestMethod.GET)
public ModelAndView consultarEventoSys(ModelMap model){
    List<Log_sys> list_p1 = sysRep.findEv();
    if (!list_p1.equals(null)) {
        model.put("auditorias", list_p1);
        return new ModelAndView("Auditorias/listaAuditorias", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


}