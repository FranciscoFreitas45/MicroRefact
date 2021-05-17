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
public class ModificarCursos {

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


@GetMapping(value = "/pantallaModificacionC")
public ModelAndView formEditarCurso(int id){
    Curso cr = crRep.findById(id).get();
    ModelAndView model = new ModelAndView("ModificarUsuario/pantallaModificacionC");
    model.addObject("curso", cr);
    return model;
}


@RequestMapping(value = "/listaCursos", method = RequestMethod.GET)
public ModelAndView consultarCursos(ModelMap model){
    List<Curso> list_p1 = crRep.findAll();
    for (Curso c : list_p1) {
        if (c.getFk_id_tipo_curso() == null) {
            Optional<Tipo_curso> tp = tcRep.findById(6);
            Tipo_curso tpc = tp.get();
            c.setFk_id_tipo_curso(tpc);
            crRep.save(c);
        }
    }
    if (!list_p1.isEmpty()) {
        model.put("cursos", list_p1);
        return new ModelAndView("ModificarUsuario/listaCursos", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


@PostMapping(value = "/editarcurso")
public ResponseEntity<String> editarCurso(Curso cr){
    int id = cr.getPk_id_curso();
    Curso mod = crRep.findById(id).get();
    String cambios = "";
    if (!mod.getClave().equals(cr.getClave())) {
        cambios += "Clave de " + mod.getClave() + " a " + cr.getClave() + "\n";
        mod.setClave(cr.getClave());
    }
    if (!mod.getFk_id_tipo_curso().getNombre().equals(cr.getFk_id_tipo_curso().getNombre())) {
        cambios += "Tipo curso de " + mod.getFk_id_tipo_curso().getNombre() + " a " + cr.getFk_id_tipo_curso().getNombre() + "\n";
        mod.setFk_id_tipo_curso(cr.getFk_id_tipo_curso());
    }
    if (mod.getHoras() == 0) {
        mod.setHoras(cr.getHoras());
    } else {
        if (mod.getHoras() != cr.getHoras()) {
            cambios += "Horas de " + mod.getHoras() + " a " + cr.getHoras() + "\n";
            mod.setHoras(cr.getHoras());
        }
    }
    if (!mod.getNombre().equals(cr.getNombre())) {
        cambios += "Nombre de " + mod.getNombre() + " a " + cr.getNombre() + "\n";
        mod.setNombre(cr.getNombre());
    }
    System.out.println(cambios);
    log.setTrace(LogTypes.MODIFICAR_CURSO);
    if (!cambios.equals("")) {
        crRep.save(mod);
    }
    return ResponseEntity.ok("¡Curso editado con exito!");
}


}