import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EncontroCirculoEncontroService {

 private IEncontroCirculoRepository iencontrocirculorepository;


public List<EncontroCirculo> getEncontroCirculoList(Integer id){
iencontrocirculorepository.getEncontroCirculoList(id);
}


public void setEncontroCirculoList(Integer id,List<EncontroCirculo> encontroCirculoList){
iencontrocirculorepository.setEncontroCirculoList(id,encontroCirculoList);
}


}