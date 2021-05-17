import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.model.Certificado;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CertificadoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.ProfesorRep;
@Controller
public class ConsultaProfesorController {

@Autowired
 private ConsultaProfesorController controller;

@Autowired
 private ProfesorRep profesorRep;

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private CertificadoRep certRep;

 private  Logger LOGGER;


public boolean existeCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
    boolean res = false;
    Certificado certificado = certRep.findCertificado(id_profesor, id_curso, id_grupo);
    if (certificado != null) {
        res = true;
    }
    return res;
}


@RequestMapping(value = "/ficha_profesor", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView fichaProfesor(int id_profesor,ModelMap model){
    Profesor p = profesorRep.findByID(id_profesor);
    List<Grupo> grupos = grupoRep.findByIdAsesor(p.getPk_id_profesor());
    String nombre = formatoCadena(p.getNombre(), 1);
    String apellido_paterno = formatoCadena(p.getApellido_paterno(), 1);
    String apellido_materno = formatoCadena(p.getApellido_materno(), 1);
    model.addAttribute("id_profesor", p.getPk_id_profesor());
    model.addAttribute("nombre", nombre);
    model.addAttribute("apellido_paterno", apellido_paterno);
    model.addAttribute("apellido_materno", apellido_materno);
    if (p.getGenero() == null) {
        model.addAttribute("genero", "");
    } else {
        model.addAttribute("genero", p.getGenero().getGenero());
    }
    if (p.getFechaNac() == null) {
        model.addAttribute("fecha_nac", "");
    } else {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        model.addAttribute("fecha_nac", formatter.format(p.getFechaNac()));
    }
    String plantel = formatoCadena(p.getPlantel(), 1);
    model.addAttribute("planel", plantel);
    model.addAttribute("clave_plantel", formatoCadena(p.getClave_plantel(), 1));
    model.addAttribute("curp", formatoCadena(p.getCurp(), 2));
    model.addAttribute("correo", formatoCadena(p.getCorreo(), 3));
    model.addAttribute("telefono", p.getTelefono());
    String localidad = "";
    if (p.getCiudad_localidad() == null && p.getFk_id_estado() == null) {
        localidad = "";
    } else if (p.getCiudad_localidad() == null && p.getFk_id_estado() != null) {
        localidad = p.getFk_id_estado().getNombre();
    } else if (p.getCiudad_localidad() != null && p.getFk_id_estado() == null) {
        localidad = formatoCadena(p.getCiudad_localidad(), 1);
    } else {
        localidad = formatoCadena(p.getCiudad_localidad(), 1) + ", " + p.getFk_id_estado().getNombre();
    }
    model.addAttribute("localidad", localidad);
    model.addAttribute("rfc", formatoCadena(p.getRfc(), 2));
    if (p.getFk_id_grado_profesor() == null) {
        model.addAttribute("escolaridad", "");
    } else {
        model.addAttribute("escolaridad", p.getFk_id_grado_profesor().getNombre());
    }
    if (p.getFk_id_turno() == null) {
        model.addAttribute("turno", "");
    } else {
        model.addAttribute("turno", p.getFk_id_turno().getNombre());
    }
    model.put("grupo", grupos);
    model.put("ins", p.getInscripciones());
    model.put("controller", controller);
    return new ModelAndView("/ConsultarProfesor/ficha_profesor");
}


public String formatoCadena(String cadena,int tipo){
    String oracion = "";
    if (cadena == null) {
        return oracion;
    }
    if (cadena.length() < 2) {
        return cadena;
    }
    switch(tipo) {
        case 1:
            String[] cadenas = cadena.split(" ");
            for (int i = 0; i < cadenas.length - 1; i++) {
                oracion += cadenas[i].substring(0, 1).toUpperCase() + cadenas[i].substring(1).toLowerCase() + " ";
            }
            oracion += cadenas[cadenas.length - 1].substring(0, 1).toUpperCase() + cadenas[cadenas.length - 1].substring(1).toLowerCase();
            break;
        case 2:
            oracion = cadena.toUpperCase();
            break;
        case 3:
            oracion = cadena.toLowerCase();
            break;
    }
    return oracion;
}


public String normalizar(String cadena){
    if (cadena == null) {
        return "";
    }
    cadena = cadena.replace('ñ', '\001');
    return Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace('\001', 'ñ');
}


public boolean existeDocumento(Integer id_profesor,Integer tipo){
    boolean res = false;
    Profesor profesor = profesorRep.findByID(id_profesor);
    switch(tipo) {
        case 1:
            if (profesor.getCurp_doc() != null)
                res = true;
            break;
        case 2:
            if (profesor.getRfc_doc() != null)
                res = true;
            break;
        case 3:
            if (profesor.getComprobante_doc() != null)
                res = true;
            break;
    }
    return res;
}


@RequestMapping(value = "/consultaProfesor", method = RequestMethod.POST)
public ModelAndView consultaProfesor(ModelMap model,HttpServletRequest request){
    try {
        String rfc = request.getParameter("rfc").toUpperCase().trim();
        String nombre = normalizar(request.getParameter("nombre")).toUpperCase().trim();
        String apellido_paterno = normalizar(request.getParameter("apellido_paterno")).toUpperCase().trim();
        String apellido_materno = normalizar(request.getParameter("apellido_materno")).toUpperCase().trim();
        Integer id_grado = Integer.parseInt(request.getParameter("grado_estudios"));
        Integer id_genero = Integer.parseInt(request.getParameter("genero"));
        Integer id_estado = Integer.parseInt(request.getParameter("estados"));
        Integer id_turno = Integer.parseInt(request.getParameter("turno"));
        List<Profesor> profesores = new ArrayList<Profesor>();
        // List<Profesor> profesores2 = new ArrayList<Profesor>();
        if (rfc == "" && nombre == "" && apellido_paterno == "" && apellido_materno == "" && id_estado == 33) {
            profesores = profesorRep.findAll();
        } else if (id_estado != 33) {
            profesores = profesorRep.findByParams(rfc, nombre, apellido_paterno, apellido_materno, id_estado);
        } else {
            profesores = profesorRep.findByParams(rfc, nombre, apellido_paterno, apellido_materno);
        }
        List<Profesor> profesores2 = new ArrayList<Profesor>(profesores);
        // Filtrando por grado de estudios
        if (id_grado != 5) {
            for (Profesor p : profesores2) {
                if (p.getFk_id_grado_profesor() == null || p.getFk_id_grado_profesor().getPk_id_grado_profesor() != id_grado) {
                    profesores.remove(p);
                }
            }
        }
        // Filtrando por género
        if (id_genero != 3) {
            for (Profesor p : profesores2) {
                if (p.getId_genero() == null || p.getId_genero().getPk_id_genero() != id_genero) {
                    profesores.remove(p);
                }
            }
        }
        // Filtrando por turno
        if (id_turno != 4) {
            for (Profesor p : profesores2) {
                if (p.getFk_id_turno() == null || p.getFk_id_turno().getPk_id_turno() != id_turno) {
                    profesores.remove(p);
                }
            }
        }
        if (!profesores.isEmpty() || profesores.size() > 0) {
            model.put("profesores", profesores);
            model.put("controller", controller);
            return new ModelAndView("/ConsultarProfesor/muestraListaProfesor", model);
        } else {
            model.addAttribute("mensaje", "Tu búsqueda no arrojo ningún resultado");
            return new ModelAndView("/Avisos/ErrorBusqueda");
        }
    } catch (NullPointerException e) {
        LOGGER.error("En la Tabla Profesor se encuentra una columna con todos sus datos con valor " + e.getMessage() + " que provoca el error.");
        model.addAttribute("mensaje", "¡Lo sentimos!\nEn nuestra base de datos no tenemos datos con el cual comparar la información que ingresaste");
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


public int getCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
    Certificado certificado = certRep.findCertificado(id_profesor, id_curso, id_grupo);
    return certificado.getPk_id_certificado();
}


}