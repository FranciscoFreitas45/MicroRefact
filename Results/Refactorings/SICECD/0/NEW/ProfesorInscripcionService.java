import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ProfesorInscripcionService {

 private ProfesorRep profesorrep;


public Profesor getFk_id_profesor(int int){
profesorrep.getFk_id_profesor(int);
}


public void setFk_id_profesor(int int,Profesor fk_id_profesor){
profesorrep.setFk_id_profesor(int,fk_id_profesor);
}


}