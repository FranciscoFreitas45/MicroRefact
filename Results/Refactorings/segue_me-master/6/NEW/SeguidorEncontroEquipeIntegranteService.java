import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class SeguidorEncontroEquipeIntegranteService {

 private ISeguidorRepository iseguidorrepository;


public Seguidor getSeguidor(Integer Integer){
iseguidorrepository.getSeguidor(Integer);
}


public void setSeguidor(Integer Integer,Seguidor seguidor){
iseguidorrepository.setSeguidor(Integer,seguidor);
}


}