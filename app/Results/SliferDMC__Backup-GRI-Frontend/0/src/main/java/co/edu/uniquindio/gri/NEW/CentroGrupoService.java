package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.CentroRepository;
import co.edu.uniquindio.gri.model.Centro;
@Service
public class CentroGrupoService {

@Autowired
 private CentroRepository centrorepository;


public Centro getCentro(long id){
return centrorepository.getCentro(id);
}


public void setCentro(long id,Centro centro){
centrorepository.setCentro(id,centro);
}


}