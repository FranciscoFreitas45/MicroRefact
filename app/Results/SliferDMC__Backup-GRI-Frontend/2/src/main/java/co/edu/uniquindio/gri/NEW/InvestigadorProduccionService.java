package co.edu.uniquindio.gri.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.repository.InvestigadorRepository;
import co.edu.uniquindio.gri.model.Investigador;
@Service
public class InvestigadorProduccionService {

@Autowired
 private InvestigadorRepository investigadorrepository;


public Investigador getInvestigador(long id){
return investigadorrepository.getInvestigador(id);
}


public void setInvestigador(long id,Investigador investigador){
investigadorrepository.setInvestigador(id,investigador);
}


}