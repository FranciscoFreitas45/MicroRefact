package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.NakoupenaPolozkaRepository;
import upce.semprace.eshop.entity.NakoupenaPolozka;
@Service
public class NakoupenaPolozkaProduktService {

@Autowired
 private NakoupenaPolozkaRepository nakoupenapolozkarepository;


public void setNakoupenaPolozka(Long id,NakoupenaPolozka nakoupenaPolozka){
nakoupenapolozkarepository.setNakoupenaPolozka(id,nakoupenaPolozka);
}


public NakoupenaPolozka getNakoupenaPolozka(Long id){
return nakoupenapolozkarepository.getNakoupenaPolozka(id);
}


}