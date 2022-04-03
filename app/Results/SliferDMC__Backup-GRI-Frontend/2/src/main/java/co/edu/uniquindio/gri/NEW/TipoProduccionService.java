package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.TipoRepository;
import co.edu.uniquindio.gri.model.Tipo;
@Service
public class TipoProduccionService {

@Autowired
 private TipoRepository tiporepository;


public void setTipo(long id,Tipo tipo){
tiporepository.setTipo(id,tipo);
}


public Tipo getTipo(long id){
return tiporepository.getTipo(id);
}


}