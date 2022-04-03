package co.edu.uniquindio.gri.dao;
 import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Facultad;
import co.edu.uniquindio.gri.repository.FacultadRepository;
@Service
public class FacultadDAO {

@Autowired
 private FacultadRepository facultadRepository;


public List<BigInteger> getStats(){
    return facultadRepository.getStats();
}


public Facultad getFacultadById(Long facultadId){
    return facultadRepository.findOne(facultadId);
}


public List<Facultad> getAllFacultades(){
    return facultadRepository.findAll();
}


}