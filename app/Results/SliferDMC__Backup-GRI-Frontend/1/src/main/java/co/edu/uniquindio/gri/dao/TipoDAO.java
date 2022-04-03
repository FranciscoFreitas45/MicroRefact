package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Tipo;
import co.edu.uniquindio.gri.repository.TipoRepository;
@Service
public class TipoDAO {

@Autowired
 private TipoRepository tipoRepository;


public Tipo getTipoById(Long tipoId){
    return tipoRepository.findOne(tipoId);
}


public List<Tipo> getAllTipos(){
    return tipoRepository.findAll();
}


}