package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.ProduktRepository;
import upce.semprace.eshop.entity.Produkt;
@Service
public class ProduktNakoupenaPolozkaService {

@Autowired
 private ProduktRepository produktrepository;


public void setProdukt(Long id,Produkt produkt){
produktrepository.setProdukt(id,produkt);
}


public Produkt getProdukt(Long id){
return produktrepository.getProdukt(id);
}


}