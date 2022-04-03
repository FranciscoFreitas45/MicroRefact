package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.ProgramaRepository;
import co.edu.uniquindio.gri.model.Programa;
@Service
public class ProgramaFacultadService {

@Autowired
 private ProgramaRepository programarepository;


public void setPrograma(long id,List<Programa> programa){
programarepository.setPrograma(id,programa);
}


public List<Programa> getPrograma(long id){
return programarepository.getPrograma(id);
}


}