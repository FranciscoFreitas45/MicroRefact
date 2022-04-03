package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.ProduccionRepository;
import co.edu.uniquindio.gri.model.Produccion;
@Service
public class ProduccionTipoService {

@Autowired
 private ProduccionRepository produccionrepository;


public List<Produccion> getProduccion(long id){
return produccionrepository.getProduccion(id);
}


public void setProduccion(long id,List<Produccion> produccion){
produccionrepository.setProduccion(id,produccion);
}


}