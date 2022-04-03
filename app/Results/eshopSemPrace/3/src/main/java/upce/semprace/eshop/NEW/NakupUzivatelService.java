package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.NakupRepository;
import upce.semprace.eshop.entity.Nakup;
import java.util.*;
@Service
public class NakupUzivatelService {

@Autowired
 private NakupRepository nakuprepository;


public Set<Nakup> getNakup(Long id){
return nakuprepository.getNakup(id);
}


public void setNakup(Long id,Set<Nakup> nakup){
nakuprepository.setNakup(id,nakup);
}


}