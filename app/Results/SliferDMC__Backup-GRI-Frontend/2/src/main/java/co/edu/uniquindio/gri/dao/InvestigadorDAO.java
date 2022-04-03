package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Investigador;
import co.edu.uniquindio.gri.repository.InvestigadorRepository;
@Service
public class InvestigadorDAO {

@Autowired
 private InvestigadorRepository investigadorRepository;


public Investigador findOne(Long invId){
    return investigadorRepository.findOne(invId);
}


public List<Investigador> findAll(){
    return investigadorRepository.findAll();
}


public List<Investigador> getIntegrantes(String tipo,Long id){
    if (tipo.equals("g")) {
        return investigadorRepository.integrantesGrupo(id);
    } else if (tipo.equals("p")) {
        return investigadorRepository.integrantesPrograma(id);
    } else if (tipo.equals("c")) {
        return investigadorRepository.integrantesCentro(id);
    } else if (tipo.equals("f")) {
        return investigadorRepository.integrantesFacultad(id);
    } else {
        return investigadorRepository.integrantesGeneral();
    }
}


}