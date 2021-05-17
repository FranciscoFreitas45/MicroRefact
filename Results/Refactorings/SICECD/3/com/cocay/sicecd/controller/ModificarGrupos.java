import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.GrupoDto;
import com.cocay.sicecd.dto.InscripcionDto;
import com.cocay.sicecd.dto.ProfesorDto;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Estado;
import com.cocay.sicecd.model.Genero;
import com.cocay.sicecd.model.Grado_profesor;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.model.Tipo_curso;
import com.cocay.sicecd.model.Turno;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.EstadoRep;
import com.cocay.sicecd.repo.GeneroRep;
import com.cocay.sicecd.repo.Grado_profesorRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.Tipo_cursoRep;
import com.cocay.sicecd.repo.TurnoRep;
import com.cocay.sicecd.service.Logging;
@Controller
@RequestMapping("AdministracionModificaciones")
@PropertySource("classpath:application.properties")
public class ModificarGrupos {

@Autowired
 private ProfesorRep proRep;

@Autowired
 private InscripcionRep insRep;

@Autowired
 private GrupoRep grRep;

@Autowired
 private CursoRep crRep;

@Autowired
 private GeneroRep genRep;

@Autowired
 private EstadoRep stRep;

@Autowired
 private Grado_profesorRep gpRep;

@Autowired
 private Tipo_cursoRep tcRep;

@Autowired
 private TurnoRep tnRep;

@Autowired
 private Logging log;


@RequestMapping(value = "/listaGrupos", method = RequestMethod.GET)
public ModelAndView consultarGrupos(ModelMap model){
    List<Grupo> list_p1 = grRep.findAll();
    List<GrupoDto> lista = new ArrayList<>();
    for (Grupo g : list_p1) {
        Integer id = g.getPk_id_grupo();
        String clave = g.getClave();
        String fi = null;
        if (g.getFecha_fin() != null) {
            fi = g.getFecha_fin().toString().substring(0, 10);
        }
        String ft = null;
        if (g.getFecha_fin() != null) {
            ft = g.getFecha_fin().toString().substring(0, 10);
        }
        String cc = g.getFk_id_curso().getClave();
        String cm = g.getFk_id_curso().getNombre();
        String rfc = "Sin definir";
        String nombre = null;
        String apaterno = null;
        if (g.getFk_id_profesor() != null) {
            rfc = g.getFk_id_profesor().getRfc();
            nombre = g.getFk_id_profesor().getNombre();
            apaterno = g.getFk_id_profesor().getApellido_paterno();
        }
        GrupoDto nm = new GrupoDto();
        nm.setIdGrupo(id.toString());
        nm.setClave(clave);
        nm.setInicio(fi);
        nm.setTermino(ft);
        nm.setCurso(cm);
        nm.setClaveCurso(cc);
        nm.setAsesor(rfc);
        nm.setAnombre(nombre);
        nm.setApaterno(apaterno);
        lista.add(nm);
    }
    if (!lista.isEmpty()) {
        model.put("grupos", lista);
        return new ModelAndView("ModificarUsuario/listaGrupos", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


@PostMapping(value = "/editargrupo")
public ResponseEntity<String> editarGrupo(GrupoDto grp){
    String id = grp.getIdentificador();
    Integer idP = Integer.parseInt(id);
    Grupo mod = grRep.findById(idP).get();
    String cambios = "";
    if (!mod.getClave().equals(grp.getClave())) {
        cambios += "Clave de " + mod.getClave() + " a " + grp.getClave() + "\n";
        mod.setClave(grp.getClave());
    }
    if (mod.getFecha_inicio() == null) {
        if (grp.getInicio() != "") {
            Date fecha = null;
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(grp.getInicio());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mod.setFecha_inicio(fecha);
        }
    } else {
        if (grp.getInicio() != "") {
            if (!mod.getFecha_inicio().toString().equals(grp.getInicio())) {
                cambios += "Fecha de inicio de " + mod.getFecha_inicio().toString() + " a " + grp.getInicio() + "\n";
                Date fecha = null;
                try {
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(grp.getInicio());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mod.setFecha_inicio(fecha);
            }
        }
    }
    if (mod.getFecha_fin() == null) {
        if (grp.getTermino() != "") {
            Date fecha = null;
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(grp.getTermino());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mod.setFecha_fin(fecha);
        }
    } else {
        if (grp.getTermino() != "") {
            if (!mod.getFecha_fin().toString().equals(grp.getTermino())) {
                cambios += "Fecha de termino de " + mod.getFecha_fin().toString() + " a " + grp.getTermino() + "\n";
                Date fecha = null;
                try {
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(grp.getTermino());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mod.setFecha_fin(fecha);
            }
        }
    }
    Profesor pro = proRep.findByRfc(grp.getAsesor());
    if (pro != null) {
        if (mod.getFk_id_profesor() != null) {
            if (!mod.getFk_id_profesor().getRfc().equals(grp.getAsesor())) {
                mod.setFk_id_profesor(pro);
            }
        } else {
            mod.setFk_id_profesor(pro);
        }
    }
    if (grp.getAsesor().contains("Sin definir")) {
        mod.setFk_id_profesor(null);
    }
    Curso cur = crRep.findForClave(grp.getCurso());
    if (cur != null) {
        if (!mod.getFk_id_curso().getClave().equals(grp.getCurso())) {
            mod.setFk_id_curso(cur);
        }
    }
    System.out.println(cambios);
    log.setTrace(LogTypes.MODIFICAR_GRUPO);
    if (!cambios.equals("")) {
        grRep.save(mod);
    }
    return ResponseEntity.ok("¡Grupo editado con exito!");
}


@GetMapping(value = "/pantallaModificacionG")
public ModelAndView formEditarGrupo(String id){
    Grupo gr = grRep.findById(Integer.parseInt(id)).get();
    List<Curso> list_p1 = crRep.findAll();
    List<Profesor> list_p2 = proRep.findAll();
    List<String> claves = new ArrayList<String>();
    List<String> rfcs = new ArrayList<String>();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder nc = new StringBuilder();
    StringBuilder cc = new StringBuilder();
    for (Curso c : list_p1) {
        claves.add(c.getClave());
        sb1.append(c.getClave() + ",");
    }
    for (Curso c : list_p1) {
        cc.append(c.getNombre() + ",");
        claves.add(cc.toString());
    }
    for (Profesor p : list_p2) {
        rfcs.add(p.getRfc());
        sb2.append(p.getRfc() + ",");
    }
    for (Profesor p : list_p2) {
        nc.append(p.getApellido_paterno() + " " + p.getApellido_materno() + " " + p.getNombre() + ",");
        rfcs.add(nc.toString());
    }
    String re = sb1.toString();
    sb1.setLength(re.length() - 1);
    String nomc = cc.toString();
    cc.setLength(nomc.length() - 1);
    String rep = sb2.toString();
    sb2.setLength(rep.length() - 1);
    String nombresc = nc.toString();
    nc.setLength(nombresc.length() - 1);
    GrupoDto gdto = new GrupoDto();
    gdto.setIdentificador(Integer.toString(gr.getPk_id_grupo()));
    gdto.setClave(gr.getClave());
    if (gr.getFk_id_profesor() != null) {
        gdto.setAsesor(gr.getFk_id_profesor().getRfc());
    } else {
        gdto.setAsesor("Sin definir");
    }
    gdto.setCurso(gr.getFk_id_curso().getClave());
    gdto.setJsonC(sb1.toString());
    gdto.setJsonP(sb2.toString());
    gdto.setJsonNombres(nc.toString());
    gdto.setJsonNombresCurso(cc.toString());
    if (gr.getFecha_inicio() != null) {
        gdto.setInicio(gr.getFecha_inicio().toString().substring(0, 10));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ffecha = formatter.format(gr.getFecha_inicio());
        gdto.setFormatoInicio(ffecha);
    }
    if (gr.getFecha_fin() != null) {
        gdto.setTermino(gr.getFecha_fin().toString().substring(0, 10));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ffecha = formatter.format(gr.getFecha_fin());
        gdto.setFormatoTermino(ffecha);
    }
    ModelAndView model = new ModelAndView("ModificarUsuario/pantallaModificacionG");
    model.addObject("grupo", gdto);
    return model;
}


}