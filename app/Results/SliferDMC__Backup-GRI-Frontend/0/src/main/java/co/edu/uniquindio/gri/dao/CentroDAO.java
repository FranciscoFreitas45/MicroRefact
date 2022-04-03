package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Centro;
import co.edu.uniquindio.gri.repository.CentroRepository;
@Service
public class CentroDAO {

@Autowired
 private CentroRepository centroRepository;


public List<Centro> getAllCentros(){
    return centroRepository.findAll();
}


public Centro getCentroById(Long centroId){
    return centroRepository.findOne(centroId);
}


}