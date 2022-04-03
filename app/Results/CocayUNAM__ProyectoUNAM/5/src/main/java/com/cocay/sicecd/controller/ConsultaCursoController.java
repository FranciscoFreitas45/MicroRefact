package com.cocay.sicecd.controller;
 import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CursoRep;
@Controller
public class ConsultaCursoController {

@Autowired
 private CursoRep curso;

 private  Logger LOGGER;


public String normalizar(String cadena){
    if (cadena == null) {
        return "";
    }
    cadena = cadena.replace('ñ', '\001');
    return Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace('\001', 'ñ');
}


@RequestMapping(value = "/consultaCurso", method = RequestMethod.POST)
public ModelAndView consultaCurso(ModelMap model,HttpServletRequest request){
    try {
        String nombre_curso_acento = request.getParameter("nombre_curso").toUpperCase().trim();
        String nombre_curso = normalizar(nombre_curso_acento);
        String clave_curso = request.getParameter("clave_curso").toUpperCase().trim();
        Integer id_tipo = Integer.parseInt(request.getParameter("tipos_cursos"));
        List<Curso> cursos = new ArrayList<Curso>();
        if (id_tipo == 0) {
            cursos = curso.findByParams(nombre_curso, clave_curso);
        } else {
            cursos = curso.findByParams(nombre_curso, clave_curso, id_tipo);
        }
        if (!cursos.isEmpty() || cursos.size() > 0) {
            model.put("cursos", cursos);
            return new ModelAndView("ConsultarCurso/muestraListaCurso", model);
        } else {
            model.addAttribute("mensaje", "Tu búsqueda no arrojo ningún resultado");
            return new ModelAndView("/Avisos/ErrorBusqueda");
        }
    } catch (NullPointerException e) {
        LOGGER.error("En la Tabla Curso se encuentra una columna con todos sus datos con valor " + e.getMessage() + " que provoca el error.");
        model.addAttribute("mensaje", "¡Lo sentimos!\nEn nuestra base de datos no tenemos datos con el cual comparar la información que ingresaste");
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


}