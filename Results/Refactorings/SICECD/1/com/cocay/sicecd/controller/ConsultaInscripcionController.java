import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cocay.sicecd.model.Certificado;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CertificadoRep;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
@Controller
public class ConsultaInscripcionController {

@Autowired
 private ConsultaInscripcionController controller;

@Autowired
 private InscripcionRep ins_rep;

@Autowired
 private ProfesorRep profesorRep;

@Autowired
 private CursoRep curso_rep;

@Autowired
 private GrupoRep grupo_rep;

@Autowired
 private CertificadoRep certRep;


@RequestMapping(value = "/consultaInscripciones", method = RequestMethod.POST)
public ModelAndView consultaInscripciones(ModelMap model,HttpServletRequest request){
    /* Datos del profesor */
    String rfc = request.getParameter("rfc").toUpperCase().trim();
    String nombre = normalizar(request.getParameter("nombre")).toUpperCase().trim();
    String apellido_paterno = normalizar(request.getParameter("apellido_paterno")).toUpperCase().trim();
    Integer id_grado = Integer.parseInt(request.getParameter("grado_estudios"));
    Integer id_genero = Integer.parseInt(request.getParameter("genero"));
    Integer id_turno = Integer.parseInt(request.getParameter("turno"));
    /* Datos del curso */
    String nombre_curso_acento = request.getParameter("nombre_curso").toUpperCase().trim();
    String nombre_curso = normalizar(nombre_curso_acento);
    String clave_curso = request.getParameter("clave_curso").toUpperCase().trim();
    Integer id_tipo = Integer.parseInt(request.getParameter("tipos"));
    /* Datos del grupo */
    String clave_grupo = request.getParameter("clave_grupo").toUpperCase().trim();
    /* Intervalo de tiempo */
    String fecha_1 = request.getParameter("fecha_1");
    String fecha_2 = request.getParameter("fecha_2");
    List<Inscripcion> ins_profes = new ArrayList<Inscripcion>();
    List<Inscripcion> ins_cursos_grupos = new ArrayList<Inscripcion>();
    // Se obtienen las inscripciones pertenecientes al profesor buscado
    ins_profes = obtenerInsProfes(rfc, nombre, apellido_paterno, id_grado, id_genero, id_turno);
    // Se obtienen las inscripciones pertenecientes al curso y grupo buscado
    ins_cursos_grupos = obtenerInsCursosGrupos(clave_curso, nombre_curso, id_tipo, clave_grupo, fecha_1, fecha_2);
    // Merge entre cursos, grupos y profes
    List<Inscripcion> inscripciones = obtenerIns(ins_cursos_grupos, ins_profes);
    if (inscripciones != null || inscripciones.size() > 0) {
        model.put("ins", inscripciones);
        model.put("controller", controller);
        return new ModelAndView("ConsultarInscripcion/muestraListaIns", model);
    } else {
        return new ModelAndView("/Avisos/ErrorBusqueda");
    }
}


public boolean existeCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
    boolean res = false;
    Certificado certificado = certRep.findCertificado(id_profesor, id_curso, id_grupo);
    if (certificado != null) {
        res = true;
    }
    return res;
}


public List<Inscripcion> obtenerIns(List<Inscripcion> ins_cursos_grupos,List<Inscripcion> ins_profes){
    List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
    if (ins_cursos_grupos.size() > 0 && ins_profes.size() == 0) {
        inscripciones = ins_cursos_grupos;
    } else if (ins_cursos_grupos.size() == 0 && ins_profes.size() > 0) {
        inscripciones = ins_profes;
    } else {
        for (Inscripcion ins : ins_cursos_grupos) {
            if (ins_profes.contains(ins)) {
                inscripciones.add(ins);
            }
        }
    }
    return inscripciones;
}


public String normalizar(String cadena){
    if (cadena == null) {
        return "";
    }
    cadena = cadena.replace('ñ', '\001');
    return Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace('\001', 'ñ');
}


public String formatoNombre(String profesor){
    String nombre = "";
    if (profesor.isBlank()) {
        return nombre;
    }
    String[] cadenas = profesor.split(" ");
    for (int i = 0; i < cadenas.length - 1; i++) {
        nombre += cadenas[i].substring(0, 1).toUpperCase() + cadenas[i].substring(1).toLowerCase() + " ";
    }
    nombre += cadenas[cadenas.length - 1].substring(0, 1).toUpperCase() + cadenas[cadenas.length - 1].substring(1).toLowerCase();
    return nombre;
}


public List<Inscripcion> obtenerInsCursosGrupos(String clave_curso,String nombre_curso,Integer id_tipo,String clave_grupo,String fecha_inicio_1,String fecha_inicio_2){
    List<Inscripcion> ins = new ArrayList<Inscripcion>();
    List<Curso> cursos = new ArrayList<Curso>();
    List<Grupo> grupos = new ArrayList<Grupo>();
    // Obteniendo cursos
    if (id_tipo == 0) {
        cursos = curso_rep.findByParams(nombre_curso, clave_curso);
    } else {
        cursos = curso_rep.findByParams(nombre_curso, clave_curso, id_tipo);
    }
    // Obteniendo grupos
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date fecha_ini_1, fecha_ini_2;
    if (fecha_inicio_1 != "" && fecha_inicio_2 != "") {
        fecha_ini_1 = format.parse(fecha_inicio_1);
        fecha_ini_2 = format.parse(fecha_inicio_2);
        grupos = grupo_rep.findByFechaInicio(fecha_ini_1, fecha_ini_2, clave_grupo);
    // } else if (clave_grupo != ""){
    } else {
        grupos = grupo_rep.findByClave(clave_grupo);
    }
    // Obteniendo inscripciones
    for (Curso c : cursos) {
        for (Grupo g : grupos) {
            if (g.getFk_id_curso().getPk_id_curso() == c.getPk_id_curso()) {
                ins.addAll(g.getInscripciones());
            }
        }
    }
    return ins;
}


public List<Inscripcion> obtenerInsProfes(String rfc,String nombre,String apellido_paterno,Integer id_grado,Integer id_genero,Integer id_turno){
    List<Inscripcion> ins_profes = new ArrayList<Inscripcion>();
    List<Profesor> profesores = new ArrayList<Profesor>();
    List<Profesor> profesores2 = new ArrayList<Profesor>();
    // Caso: Búsqueda por RFC
    if (rfc != "") {
        Profesor p = profesorRep.findByRfc(rfc);
        ins_profes.addAll(p.getInscripciones());
    // Caso: Búsqueda por los filtros restantes
    } else {
        List<Profesor> profes = new ArrayList<Profesor>();
        List<Profesor> profes2 = new ArrayList<Profesor>();
        if (nombre != "" || apellido_paterno != "") {
            profes = profesorRep.findByName(nombre, apellido_paterno);
            profes2 = new ArrayList<Profesor>(profes);
        }
        // Filtrando por grado de estudios
        if (id_grado != 5) {
            for (Profesor p : profes2) {
                if (p.getFk_id_grado_profesor().getPk_id_grado_profesor() != id_grado) {
                    profes.remove(p);
                }
            }
        }
        // Filtrando por género
        if (id_genero != 3) {
            for (Profesor p : profes2) {
                if (p.getId_genero().getPk_id_genero() != id_genero) {
                    profes.remove(p);
                }
            }
        }
        // Filtrando por turno
        if (id_turno != 4) {
            for (Profesor p : profes2) {
                if (p.getFk_id_turno().getPk_id_turno() != id_turno) {
                    profes.remove(p);
                }
            }
        }
        // Se agregan las inscripciones de cada uno de los profesores
        for (Profesor p : profes) {
            ins_profes.addAll(p.getInscripciones());
        }
    }
    return ins_profes;
}


@RequestMapping(value = "/consultaInscripcion", method = RequestMethod.GET)
public String consultaCurso(Model model){
    return "ConsultarInscripcion/consultaInscripcion";
}


public int getCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
    Certificado certificado = certRep.findCertificado(id_profesor, id_curso, id_grupo);
    return certificado.getPk_id_certificado();
}


}