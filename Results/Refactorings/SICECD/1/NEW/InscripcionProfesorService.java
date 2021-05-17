import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class InscripcionProfesorService {

 private InscripcionRep inscripcionrep;


public List<Inscripcion> getInscripciones(int pk_id_profesor){
inscripcionrep.getInscripciones(pk_id_profesor);
}


public void setInscripciones(int pk_id_profesor,List<Inscripcion> inscripciones){
inscripcionrep.setInscripciones(pk_id_profesor,inscripciones);
}


}