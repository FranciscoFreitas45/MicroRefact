import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.repo.ProfesorRep;
@Service
public class ProcessorInscripcion implements ItemProcessor<Inscripcion, Inscripcion>{

 private  Logger log;

@Autowired
 private ProfesorRep profesorRep;

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private CursoRep cursoRep;

@Autowired
 private InscripcionRep inscripcionRep;

@Autowired
 private  EntityManager em;


@Override
public Inscripcion process(Inscripcion inscripcion){
    System.out.println("Grupo: " + inscripcion.getTempGrupo());
    System.out.println("Curso: " + inscripcion.getTempCurso());
    System.out.println("Profesor: " + inscripcion.getTempProfesor());
    String cdGrupo = inscripcion.getTempGrupo();
    String cdProfesor = inscripcion.getTempProfesor();
    Curso curso = cursoRep.findByNombre(inscripcion.getTempCurso());
    Grupo grupo = grupoRep.findByClaveGrupoIdCurso(cdGrupo, curso);
    Profesor profesor = profesorRep.findByRfc(cdProfesor);
    if (curso != null && grupo != null && profesor != null) {
        inscripcion.setFk_id_grupo(grupo);
        inscripcion.setFk_id_profesor(profesor);
        inscripcion.setStTabla(1);
        Inscripcion ins = inscripcionRep.findByP(cdProfesor);
        if (ins != null) {
            if (!ins.getTempGrupo().equals(cdGrupo)) {
                return inscripcion;
            } else {
                String mensaje = "El profesor con rfc: " + cdProfesor + " se encuentra inscrito en el grupo: " + cdGrupo;
                String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
                Query query = em.createNativeQuery(consulta);
                query.executeUpdate();
                return null;
            }
        } else {
            return inscripcion;
        }
    } else {
        String mensaje = "Error al inscribir el profesor: " + cdProfesor + " en el grupo: " + cdGrupo;
        String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
        Query query = em.createNativeQuery(consulta);
        query.executeUpdate();
        return null;
    }
}


}