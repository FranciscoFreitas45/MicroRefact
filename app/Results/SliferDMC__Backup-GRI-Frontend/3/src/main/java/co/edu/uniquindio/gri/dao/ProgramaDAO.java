package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Programa;
import co.edu.uniquindio.gri.repository.ProgramaRepository;
@Service
public class ProgramaDAO {

@Autowired
 private ProgramaRepository programaRepository;


public List<Programa> getAllProgramas(){
    return programaRepository.findAll();
}


public Programa getProgramaById(Long programaId){
    return programaRepository.findOne(programaId);
}


public List<Programa> getProgramasFacultad(Long facultadId){
    return programaRepository.getProgramasFacultad(facultadId);
}


}