package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.GrupoRepository;
import co.edu.uniquindio.gri.model.Grupo;
@Service
public class GrupoCentroService {

@Autowired
 private GrupoRepository gruporepository;


public void setGrupo(long id,List<Grupo> grupo){
gruporepository.setGrupo(id,grupo);
}


public List<Grupo> getGrupo(long id){
return gruporepository.getGrupo(id);
}


}