package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.FacultadRepository;
import co.edu.uniquindio.gri.model.Facultad;
@Service
public class FacultadProgramaService {

@Autowired
 private FacultadRepository facultadrepository;


public void setFacultad(long id,Facultad facultad){
facultadrepository.setFacultad(id,facultad);
}


public Facultad getFacultad(long id){
return facultadrepository.getFacultad(id);
}


}