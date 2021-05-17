import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class InscripcionGrupoService {

 private InscripcionRep inscripcionrep;


public List<Inscripcion> getInscripciones(int pk_id_grupo){
inscripcionrep.getInscripciones(pk_id_grupo);
}


public void setInscripciones(int pk_id_grupo,List<Inscripcion> inscripciones){
inscripcionrep.setInscripciones(pk_id_grupo,inscripciones);
}


}