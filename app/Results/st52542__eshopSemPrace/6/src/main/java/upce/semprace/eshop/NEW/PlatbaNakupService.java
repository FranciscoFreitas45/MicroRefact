package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.PlatbaRepository;
import upce.semprace.eshop.entity.Platba;
@Service
public class PlatbaNakupService {

@Autowired
 private PlatbaRepository platbarepository;


public Platba getPlatba(Long id){
return platbarepository.getPlatba(id);
}


public void setPlatba(Long id,Platba platba){
platbarepository.setPlatba(id,platba);
}


}