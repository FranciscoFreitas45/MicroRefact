import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Tipo_curso;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.Tipo_cursoRep;
@Service
public class ProcessorCurso implements ItemProcessor<Curso, Curso>{

 private  Logger log;

 private Tipo_curso tp_curso;

@Autowired
 private CursoRep cursoRep;

@Autowired
 private Tipo_cursoRep tipoCursoRep;

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private  EntityManager em;


@Override
public Curso process(Curso curso){
    String name = curso.getTempTipoCurso();
    String nombreCurso = curso.getNombre();
    System.out.println(nombreCurso);
    Tipo_curso tpC = tipoCursoRep.findByNombreTipo(name);
    if (tpC != null) {
        curso.setFk_id_tipo_curso(tpC);
        String claveCurso = curso.getClave();
        // Curso c = cursoRep.findByUniqueClave(claveCurso);
        Curso c = cursoRep.findByNombre(nombreCurso);
        if (c == null) {
            curso.setStTabla(1);
            return curso;
        } else {
            String mensaje = "El curso: " + nombreCurso + " ya existe actualmente";
            String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
            Query query = em.createNativeQuery(consulta);
            query.executeUpdate();
            return null;
        }
    } else {
        String mensaje = "Error al buscar: " + name + " ";
        String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
        Query query = em.createNativeQuery(consulta);
        query.executeUpdate();
        return null;
    }
// String grupoNombre = curso.getTempGrupo();
// Grupo grupo = grupoRep.findByClaveGrupo(grupoNombre);
// curso.setFk_id_grupo(grupo);
}


}