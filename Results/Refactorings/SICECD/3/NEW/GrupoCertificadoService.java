import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class GrupoCertificadoService {

 private GrupoRep gruporep;


public void setFk_id_grupo(int int,Grupo fk_id_grupo){
gruporep.setFk_id_grupo(int,fk_id_grupo);
}


public Grupo getFk_id_grupo(int int){
gruporep.getFk_id_grupo(int);
}


}