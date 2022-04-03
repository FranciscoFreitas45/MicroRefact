package com.cocay.sicecd.controller;
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
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.GeneroRep;
import com.cocay.sicecd.Interface.EstadoRep;
import com.cocay.sicecd.Interface.Grado_profesorRep;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.TurnoRep;
import com.cocay.sicecd.Interface.Logging;
@Controller
@RequestMapping("AdministracionModificaciones")
@PropertySource("classpath:application.properties")
public class ModificarInscripcion {

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


@PostMapping(value = "/editarinscripcion")
public ResponseEntity<String> editarInsc(InscripcionDto ins){
    int id = Integer.parseInt(ins.getIdentificador());
    Inscripcion mod = insRep.findById(id).get();
    String cambios = "";
    if (mod.getCalif() == null) {
        mod.setCalif(ins.getCalificacion());
    } else {
        if (!mod.getCalif().equals(ins.getCalificacion())) {
            mod.setCalif(ins.getCalificacion());
        }
    }
    Grupo gpo = grRep.findForClave(ins.getIdGrupo());
    if (gpo != null) {
        if (!mod.getFk_id_grupo().getClave().equals(ins.getIdGrupo())) {
            mod.setFk_id_grupo(gpo);
        }
    }
    String grupo = ins.getIdGrupo();
    Grupo grupop = grRep.findForClave(grupo);
    ArrayList<String> rfcs = new ArrayList<String>();
    if (grupop.getFk_id_profesor() != null) {
        rfcs.add(grupop.getFk_id_profesor().getRfc());
    }
    Profesor pro = proRep.findByRfc(ins.getIdProfesor());
    if (!rfcs.isEmpty() && rfcs.contains(pro.getRfc())) {
        System.out.println("Sí lo contengo! es:");
        System.out.println(pro.getRfc());
        return ResponseEntity.ok("Error : El participante ya habia sido registrado con este grupo");
    }
    if (pro != null) {
        if (!mod.getFk_id_profesor().getNombre().equals(ins.getIdProfesor())) {
            mod.setFk_id_profesor(pro);
        }
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("¡Asesor no encontrado!");
    }
    mod.setAprobado(ins.isAprobado());
    System.out.println(cambios);
    log.setTrace(LogTypes.MODIFICAR_INSCRIPCION);
    if (!cambios.equals("")) {
        insRep.save(mod);
    }
    return ResponseEntity.ok("¡Inscripcion editada con exito!");
}


@RequestMapping(value = "/listaInscripciones", method = RequestMethod.GET)
public ModelAndView consultarInscripciones(ModelMap model){
    List<Inscripcion> list_p1 = insRep.findAll();
    if (!list_p1.isEmpty()) {
        model.put("inscripciones", list_p1);
        return new ModelAndView("ModificarUsuario/listaInscripciones", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


@GetMapping(value = "/pantallaModificacionI")
public ModelAndView formEditarInscripcion(int id){
    Inscripcion cambio = insRep.findById(id).get();
    List<Grupo> list_p1 = grRep.findAll();
    List<Profesor> list_p2 = proRep.findAll();
    List<String> grupos = new ArrayList<String>();
    List<String> rfcs = new ArrayList<String>();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder nc = new StringBuilder();
    for (Grupo g : list_p1) {
        grupos.add(g.getClave());
        sb1.append(g.getClave() + ",");
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
    String rep = sb2.toString();
    sb2.setLength(rep.length() - 1);
    String nombresc = nc.toString();
    nc.setLength(nombresc.length() - 1);
    InscripcionDto insi = new InscripcionDto();
    insi.setIdentificador(Integer.toString(cambio.getPk_id_inscripcion()));
    insi.setCalificacion(cambio.getCalif());
    insi.setAprobado(cambio.isAprobado());
    insi.setIdGrupo(cambio.getFk_id_grupo().getClave());
    insi.setIdProfesor(cambio.getFk_id_profesor().getRfc());
    insi.setJsonG(sb1.toString());
    insi.setJsonP(sb2.toString());
    insi.setJsonNombres(nc.toString());
    ModelAndView model = new ModelAndView("ModificarUsuario/pantallaModificacionI");
    model.addObject("inscripcion", insi);
    return model;
}


}