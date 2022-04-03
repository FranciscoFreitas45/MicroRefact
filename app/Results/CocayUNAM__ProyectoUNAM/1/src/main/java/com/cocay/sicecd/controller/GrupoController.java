package com.cocay.sicecd.controller;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.GrupoDto;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.service.Logging;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.Logging;
@Controller
@RequestMapping("AdministracionRegistroManual")
public class GrupoController {

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private CursoRep cursoRep;

@Autowired
 private ProfesorRep profRep;

@Autowired
 private Logging log;


@RequestMapping(value = "/registrarGrupo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> agregarGrupo(GrupoDto gr){
    Grupo grupo = new Grupo();
    String clave = gr.getClave();
    String curso = gr.getCurso();
    String asesor = gr.getAsesor();
    String fInicio = gr.getInicio();
    String fTermino = gr.getTermino();
    Date fechaI = null;
    if (fInicio != "") {
        try {
            fechaI = new SimpleDateFormat("dd/MM/yyyy").parse(fInicio);
            grupo.setFecha_inicio(fechaI);
        } catch (ParseException e1) {
            e1.printStackTrace();
            grupo.setFecha_inicio(null);
        }
    }
    Date fechaT = null;
    if (fTermino != "") {
        try {
            fechaT = new SimpleDateFormat("dd/MM/yyyy").parse(fTermino);
            grupo.setFecha_fin(fechaT);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            grupo.setFecha_fin(null);
        }
    }
    grupo.setClave(clave);
    System.out.println(curso);
    Curso cursop = cursoRep.findForClave(curso);
    if (cursop != null) {
        grupo.setFk_id_curso(cursop);
    }
    Profesor profe = profRep.findByRfc(asesor);
    if (profe != null) {
        grupo.setFk_id_profesor(profe);
    }
    log.setTrace(LogTypes.REGISTRAR_GRUPO);
    grupoRep.save(grupo);
    return ResponseEntity.ok("{\"message\":\"¡Grupo agregado con exito!\"}");
}


@RequestMapping(value = "/registrarGrupo2", method = RequestMethod.GET)
public ModelAndView registrarInscripcion(ModelMap model){
    List<Curso> list_p1 = cursoRep.findAll();
    List<Profesor> list_p2 = profRep.findAll();
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
    GrupoDto gp = new GrupoDto();
    gp.setJsonC(sb1.toString());
    gp.setJsonP(sb2.toString());
    gp.setJsonNombres(nc.toString());
    gp.setJsonNombresCurso(cc.toString());
    if (!list_p1.isEmpty()) {
        model.put("datos", gp);
        return new ModelAndView("GrupoController/registrarGrupo", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


}