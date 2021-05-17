import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.InscripcionDto;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.service.Logging;
@Controller
@RequestMapping("AdministracionRegistroManual")
public class InscripcionesController {

@Autowired
 private InscripcionRep insRep;

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private ProfesorRep profRep;

@Autowired
 private Logging log;


@RequestMapping(value = "/registrarInscripcion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> agregarIns(InscripcionDto ins){
    Inscripcion inst = new Inscripcion();
    String grupo = ins.getIdGrupo();
    Grupo grupop = grupoRep.findForClave(grupo);
    String par = ins.getIdProfesor();
    Profesor profe = profRep.findByRfc(par);
    String cal = ins.getCalificacion();
    Boolean ap = ins.isAprobado();
    ArrayList<String> rfcs = new ArrayList<String>();
    if (grupop.getFk_id_profesor() != null) {
        System.out.println("Si encontre un profesor!!!!!!!!");
        rfcs.add(grupop.getFk_id_profesor().getRfc());
    }
    if (!rfcs.isEmpty() && rfcs.contains(profe.getRfc())) {
        System.out.println("Sí lo contengo! es:");
        System.out.println(profe.getRfc());
        return ResponseEntity.ok("{\"message\":\"¡El participante ya había sido registrado con este grupo!\"}");
    }
    inst.setFk_id_grupo(grupop);
    inst.setFk_id_profesor(profe);
    inst.setCalif(cal);
    inst.setAprobado(ap);
    log.setTrace(LogTypes.REGISTRAR_INSCRIPCION);
    insRep.save(inst);
    return ResponseEntity.ok("{\"message\":\"¡Inscripcion agregada con exito!\"}");
}


@RequestMapping(value = "/registrarInscripcion2", method = RequestMethod.GET)
public ModelAndView registrarInscripcion(ModelMap model){
    List<Grupo> list_p1 = grupoRep.findAll();
    List<Profesor> list_p2 = profRep.findAll();
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
    InscripcionDto in = new InscripcionDto();
    in.setJsonG(sb1.toString());
    in.setJsonP(sb2.toString());
    in.setJsonNombres(nc.toString());
    if (!list_p1.isEmpty()) {
        model.put("datos", in);
        return new ModelAndView("InscripcionesController/registrarInscripcion", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


}