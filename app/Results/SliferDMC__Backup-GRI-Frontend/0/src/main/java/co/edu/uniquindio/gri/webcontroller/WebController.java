package co.edu.uniquindio.gri.webcontroller;
 import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.edu.uniquindio.gri.dao.CentroDAO;
import co.edu.uniquindio.gri.dao.FacultadDAO;
import co.edu.uniquindio.gri.dao.GrupoDAO;
import co.edu.uniquindio.gri.dao.InvestigadorDAO;
import co.edu.uniquindio.gri.dao.ProduccionDAO;
import co.edu.uniquindio.gri.dao.ProgramaDAO;
import co.edu.uniquindio.gri.model.Centro;
import co.edu.uniquindio.gri.model.Facultad;
import co.edu.uniquindio.gri.model.Grupo;
import co.edu.uniquindio.gri.model.Investigador;
import co.edu.uniquindio.gri.model.Programa;
@Controller
public class WebController {

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

@Autowired
 private ProduccionDAO produccionDAO;


@GetMapping("/login")
public String getLogin(Model model){
    return "login";
}


@GetMapping("/reporteinventario")
public String getReporteInventario(String id,Model model){
    Grupo g = grupoDAO.findOne(Long.parseLong(id));
    model.addAttribute("nombre", g.getNombre());
    model.addAttribute("color", "card-" + g.getProgramas().get(0).getFacultad().getId());
    model.addAttribute("producciones", produccionDAO.getAllProducciones(Long.parseLong(id)));
    return "inventario/reporteinventario";
}


public int calcularTamanio(int tamanio){
    for (int i = 5; i > 1; i--) {
        if ((tamanio % i) == 0) {
            return i;
        }
    }
    return calcularTamanio(tamanio + 1);
}


@GetMapping("/general")
public String getTipologias(String type,String id,Model model){
    model.addAttribute("id", id);
    model.addAttribute("tipo", type);
    if (type.equals("u")) {
        List<Facultad> facultades = facultadDAO.getAllFacultades();
        model.addAttribute("nombre", "TIPOLOGÍA DE PRODUCTOS PARA LA UNIVERSIDAD DEL QUINDÍO");
        model.addAttribute("lista", facultades);
        model.addAttribute("subtipo", "f");
        model.addAttribute("color", "card-0");
        model.addAttribute("tamanio", "ci-" + calcularTamanio(facultades.size()));
    } else if (type.equals("f")) {
        Facultad f = facultadDAO.getFacultadById(Long.parseLong(id));
        List<Programa> programas = programaDAO.getProgramasFacultad(Long.parseLong(id));
        model.addAttribute("nombre", f.getNombre());
        model.addAttribute("lista", programas);
        model.addAttribute("subtipo", "p");
        model.addAttribute("color", "card-" + f.getId());
        model.addAttribute("tamanio", "ci-" + calcularTamanio(programas.size()));
    } else if (type.equals("p")) {
        Programa p = programaDAO.getProgramaById(Long.parseLong(id));
        List<Grupo> grupos = grupoDAO.getGruposPrograma(Long.parseLong(id));
        model.addAttribute("nombre", p.getNombre());
        model.addAttribute("lista", grupos);
        model.addAttribute("subtipo", "g");
        model.addAttribute("color", "card-" + p.getFacultad().getId());
        model.addAttribute("tamanio", "ci-" + calcularTamanio(grupos.size()));
    } else if (type.equals("c")) {
        Centro c = centroDAO.getCentroById(Long.parseLong(id));
        List<Grupo> grupos = grupoDAO.getGruposCentro(Long.parseLong(id));
        model.addAttribute("nombre", c.getNombre());
        model.addAttribute("lista", grupos);
        model.addAttribute("subtipo", "g");
        model.addAttribute("color", "card-" + c.getFacultad().getId());
        model.addAttribute("tamanio", "ci-" + calcularTamanio(grupos.size()));
    } else if (type.equals("g")) {
        Grupo g = grupoDAO.findOne(Long.parseLong(id));
        model.addAttribute("nombre", g.getNombre());
        model.addAttribute("color", "card-" + g.getProgramas().get(0).getFacultad().getId());
    } else if (type.equals("i")) {
        Investigador i = investigadorDAO.findOne(Long.parseLong(id));
        model.addAttribute("nombre", i.getNombre());
        model.addAttribute("color", "card-0");
    }
    return "general";
}


@GetMapping(value = { "/", "inicio" })
public String main(Model model){
    List<BigInteger> stats = facultadDAO.getStats();
    model.addAttribute("cantInves", stats.get(0));
    model.addAttribute("cantGrupos", stats.get(1));
    model.addAttribute("cantCentros", stats.get(2));
    return "index";
}


@GetMapping("/inventario")
public String getInventario(String id,Model model){
    if (id.equals("u")) {
        model.addAttribute("nombre", "Producciones en Custodia");
        model.addAttribute("lista", facultadDAO.getAllFacultades());
        model.addAttribute("tamanio", "ci-4");
        model.addAttribute("color", "card-0");
    } else {
        Facultad f = facultadDAO.getFacultadById(Long.parseLong(id));
        List<Grupo> listaGrupos = grupoDAO.getGruposPertenecientes(Long.parseLong(id), "f");
        model.addAttribute("nombre", f.getNombre());
        model.addAttribute("lista", listaGrupos);
        model.addAttribute("color", "card-" + f.getId());
        model.addAttribute("tamanio", "ci-" + calcularTamanio(listaGrupos.size()));
    }
    return "inventario/inventario";
}


@GetMapping("/investigadores")
public String getInvestigadores(Model model){
    model.addAttribute("listaInvestigadores", investigadorDAO.findAll());
    return "investigadores";
}


@GetMapping("/centros")
public String getCentros(Model model){
    model.addAttribute("listaCentros", centroDAO.getAllCentros());
    return "centros";
}


}