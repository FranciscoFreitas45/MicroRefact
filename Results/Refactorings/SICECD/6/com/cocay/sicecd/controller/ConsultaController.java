import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Tipo_curso;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.repo.Tipo_cursoRep;
@Controller
public class ConsultaController {

@Autowired
 private ProfesorRep profesor;

@Autowired
 private CursoRep cursoRep;

@Autowired
 private Tipo_cursoRep tipo_cursoRep;


@RequestMapping(value = "/consultas", method = RequestMethod.GET)
public String show(Model model){
    // model.addAttribute("profesores", profesor.findAll());
    return "consultas/consultas";
}


@RequestMapping(value = "/resultadosProfesores", method = { RequestMethod.GET, RequestMethod.POST })
public String resultadosProfesores(Model model,HttpServletRequest request){
    String nombre = request.getParameter("nombre");
    String apellido_paterno = request.getParameter("apellido_paterno");
    String apellido_materno = request.getParameter("apellido_materno");
    if (nombre == null) {
        nombre = "";
    } else {
        nombre = nombre.toUpperCase();
    }
    if (apellido_paterno == null) {
        apellido_paterno = "";
    } else {
        apellido_paterno = apellido_paterno.toUpperCase();
    }
    if (apellido_materno == null) {
        apellido_materno = "";
    } else {
        apellido_materno = apellido_materno.toUpperCase();
    }
    System.out.println(nombre + "-___-" + apellido_paterno + "-___-" + apellido_materno);
    model.addAttribute("profesores", profesor.findByCompleteNameList(nombre, apellido_paterno, apellido_materno));
    return "consultas/resultados-profesores";
}


@RequestMapping(value = "/consultasProfesores", method = RequestMethod.GET)
public String consultasProfesores(Model model){
    // *
    Tipo_curso tipo_curso = tipo_cursoRep.findByNombre("Curso").get(0);
    System.out.println("====================");
    System.out.println(tipo_curso.toString());
    System.out.println(tipo_curso.getPk_id_tipo_curso());
    System.out.println(tipo_curso.getNombre());
    tipo_cursoRep.save(tipo_curso);
    Curso curso = new Curso();
    curso.setClave("100");
    curso.setNombre("TEST");
    curso.setHoras(20);
    curso.setFk_id_tipo_curso(tipo_curso);
    System.out.println(curso.toString());
    System.out.println(curso.getFk_id_tipo_curso().getPk_id_tipo_curso());
    System.out.println(curso.getFk_id_tipo_curso().getNombre());
    cursoRep.save(curso);
    // */
    // cursoRep.insertCurso("001", "TEST", 1, 20);
    return "consultas/profesores";
}


}