package com.cocay.sicecd.step;
 import java.util.Date;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.ProfesorRep;
@Service
public class ProcessorGrupo implements ItemProcessor<Grupo, Grupo>{

 private  Logger log;

@Autowired
 private CursoRep cursoRep;

@Autowired
 private ProfesorRep profesorRep;

@Autowired
 private GrupoRep grupoRep;

@Autowired
 private  EntityManager em;


@Override
public Grupo process(Grupo grupo){
    String cdCurso = grupo.getTempCurso();
    String cdProfesor = grupo.getTempProfesor();
    System.out.println(cdCurso);
    System.out.println(cdProfesor);
    /*
		 * Marca error Null
		 */
    Profesor profesor = profesorRep.findByRfc(cdProfesor);
    Curso curso = cursoRep.findByNombre(cdCurso);
    String clave = grupo.getClave();
    Grupo grp = grupoRep.findByClaveGrupo(clave);
    System.out.println("clave" + clave);
    System.out.println("grp" + grp);
    if (curso != null) {
        if (grp == null) {
            grupo.setFk_id_curso(curso);
            grupo.setFk_id_profesor(profesor);
            grupo.setStTabla(1);
            System.out.println("Objeto convertido a grupo ");
            return grupo;
        } else {
            String mensaje = "El grupo: " + clave + " ya existe actualmente";
            String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
            Query query = em.createNativeQuery(consulta);
            query.executeUpdate();
            return null;
        }
    } else {
        String mensaje = "Error al buscar: " + cdCurso + " (Verifique los datos)";
        String consulta = "INSERT INTO errores (mensaje, estado) VALUES ('" + mensaje + "', 1)";
        Query query = em.createNativeQuery(consulta);
        query.executeUpdate();
        return null;
    }
}


}