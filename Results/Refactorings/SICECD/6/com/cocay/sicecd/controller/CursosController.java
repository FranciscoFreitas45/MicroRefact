import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cocay.sicecd.LogTypes;
import com.cocay.sicecd.dto.CursoDto;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Tipo_curso;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.Tipo_cursoRep;
import com.cocay.sicecd.service.Logging;
@Controller
@RequestMapping("AdministracionRegistroManual")
public class CursosController {

@Autowired
 private CursoRep cursoRep;

@Autowired
 private Logging log;

@Autowired
 private  Tipo_cursoRep tpRep;


@RequestMapping(value = "/registrarCursos2")
public String registrarCur(Model model,HttpServletRequest request){
    return "CursosController/registrarCursos";
}


@RequestMapping(value = "/registrarCursos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> agregarCurso(CursoDto cr){
    Curso curso = new Curso();
    String clave = cr.getClave();
    String tipo = cr.getTipo();
    String horas = cr.getHoras();
    String nombre = cr.getNombre();
    curso.setClave(clave);
    List<Tipo_curso> cursos = tpRep.findByNombre(tipo);
    if (!cursos.isEmpty()) {
        curso.setFk_id_tipo_curso(cursos.get(0));
    }
    if (horas != "") {
        curso.setHoras(Integer.valueOf(horas));
    }
    curso.setNombre(nombre);
    log.setTrace(LogTypes.REGISTRAR_CURSO);
    cursoRep.save(curso);
    return ResponseEntity.ok("{\"message\":\"¡Curso agregado con exito!\"}");
}


}