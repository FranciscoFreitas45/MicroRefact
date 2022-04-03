package co.edu.uniquindio.gri.webcontroller;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.edu.uniquindio.gri.dao.CentroDAO;
import co.edu.uniquindio.gri.dao.FacultadDAO;
import co.edu.uniquindio.gri.dao.GrupoDAO;
import co.edu.uniquindio.gri.dao.InvestigadorDAO;
import co.edu.uniquindio.gri.dao.ProgramaDAO;
import co.edu.uniquindio.gri.dao.TipoDAO;
import co.edu.uniquindio.gri.model.Centro;
import co.edu.uniquindio.gri.model.Facultad;
import co.edu.uniquindio.gri.model.Grupo;
import co.edu.uniquindio.gri.model.GruposInves;
import co.edu.uniquindio.gri.model.Investigador;
import co.edu.uniquindio.gri.model.Programa;
@Controller
public class TipologiasController {

@Autowired
 private GrupoDAO grupoDAO;

@Autowired
 private InvestigadorDAO investigadorDAO;

@Autowired
 private CentroDAO centroDAO;

@Autowired
 private ProgramaDAO programaDAO;

@Autowired
 private FacultadDAO facultadDAO;


@GetMapping("info")
public String getInfo(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        Investigador inv = investigadorDAO.findOne(Long.parseLong(id));
        List<GruposInves> gruposInves = inv.getGrupos();
        List<Grupo> grupos = new ArrayList<>();
        for (GruposInves grupoInves : gruposInves) {
            grupos.add(grupoInves.getGrupo());
        }
        model.addAttribute("inv", inv);
        model.addAttribute("listaGrupos", grupos);
        model.addAttribute("listaIdiomas", inv.getIdiomas());
        return "investigadores/info";
    } else {
        List<Investigador> integrantes = investigadorDAO.getIntegrantes(type, Long.parseLong(id));
        Map<String, Integer> datosCategoria = new HashMap<>();
        Map<String, Integer> datosFormacion = new HashMap<>();
        for (Investigador investigador : integrantes) {
            String categoria = investigador.getCategoria();
            if (datosCategoria.containsKey(categoria)) {
                int valor = datosCategoria.get(categoria);
                datosCategoria.put(categoria, valor + 1);
            } else {
                datosCategoria.put(categoria, 1);
            }
        }
        for (Investigador investigador : integrantes) {
            String formacion = investigador.getNivelAcademico();
            if (datosFormacion.containsKey(formacion)) {
                int valor = datosFormacion.get(formacion);
                datosFormacion.put(formacion, valor + 1);
            } else {
                datosFormacion.put(formacion, 1);
            }
        }
        model.addAttribute("integrantes", integrantes);
        model.addAttribute("clavesCategoria", datosCategoria.keySet());
        model.addAttribute("datosCategoria", datosCategoria.values());
        model.addAttribute("clavesFormacion", datosFormacion.keySet());
        model.addAttribute("datosFormacion", datosFormacion.values());
        if (!type.equals("g")) {
            model.addAttribute("esGrupo", false);
            model.addAttribute("gruposPertenecientes", grupoDAO.getGruposPertenecientes(Long.parseLong(id), type));
        } else {
            model.addAttribute("esGrupo", true);
        }
        return "grupos/info";
    }
}


@GetMapping("arte")
public String getArte(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/arte";
    } else {
        return "grupos/arte";
    }
}


@GetMapping("tecnica")
public String getTecnica(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/tecnica";
    } else {
        return "grupos/tecnica";
    }
}


@GetMapping("masinfo")
public String getMasInfo(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/masinfo";
    } else {
        return "grupos/masinfo";
    }
}


@GetMapping("evaluador")
public String getEvaluador(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/evaluador";
    } else {
        return "grupos/evaluador";
    }
}


@GetMapping("bibliografica")
public String getBibliografica(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/bibliografica";
    } else {
        return "grupos/bibliografica";
    }
}


public String[] getDatos(String id,String type){
    String[] datos = new String[2];
    if (type.equals("u")) {
        datos[0] = "TIPOLOGÍA DE PRODUCTOS PARA LA UNIVERSIDAD DEL QUINDÍO";
        datos[1] = "card-0";
    } else if (type.equals("f")) {
        Facultad f = facultadDAO.getFacultadById(Long.parseLong(id));
        datos[0] = f.getNombre();
        datos[1] = "card-" + f.getId();
    } else if (type.equals("p")) {
        Programa p = programaDAO.getProgramaById(Long.parseLong(id));
        datos[0] = p.getNombre();
        datos[1] = "card-" + p.getFacultad().getId();
    } else if (type.equals("c")) {
        Centro c = centroDAO.getCentroById(Long.parseLong(id));
        datos[0] = c.getNombre();
        datos[1] = "card-" + c.getFacultad().getId();
    } else if (type.equals("g")) {
        Grupo g = grupoDAO.findOne(Long.parseLong(id));
        datos[0] = g.getNombre();
        datos[1] = "card-" + g.getProgramas().get(0).getFacultad().getId();
    } else if (type.equals("i")) {
        Investigador i = investigadorDAO.findOne(Long.parseLong(id));
        datos[0] = i.getNombre();
        datos[1] = "card-0";
    }
    return datos;
}


@GetMapping("apropiacion")
public String getApropiacion(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/apropiacion";
    } else {
        return "grupos/apropiacion";
    }
}


@GetMapping("formacion")
public String getFormacion(String type,String id,Model model){
    String[] datos = getDatos(id, type);
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    model.addAttribute("nombre", datos[0]);
    model.addAttribute("color", datos[1]);
    if (type.equals("i")) {
        return "investigadores/formacion";
    } else {
        return "grupos/formacion";
    }
}


}