package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.ProduccionRepository;
import co.edu.uniquindio.gri.model.Produccion;
@Service
public class ProduccionInvestigadorService {

@Autowired
 private ProduccionRepository produccionrepository;


public void setProducciones(long id,List<Produccion> producciones){
produccionrepository.setProducciones(id,producciones);
}


public List<Produccion> getProducciones(long id){
return produccionrepository.getProducciones(id);
}


}