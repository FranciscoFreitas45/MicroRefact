package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.UzivatelRepository;
import upce.semprace.eshop.entity.Uzivatel;
@Service
public class UzivatelNakupService {

@Autowired
 private UzivatelRepository uzivatelrepository;


public void setUzivatel(Long id,Uzivatel uzivatel){
uzivatelrepository.setUzivatel(id,uzivatel);
}


public Uzivatel getUzivatel(Long id){
return uzivatelrepository.getUzivatel(id);
}


}