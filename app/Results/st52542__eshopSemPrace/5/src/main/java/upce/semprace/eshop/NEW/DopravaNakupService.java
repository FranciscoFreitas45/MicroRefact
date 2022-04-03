package upce.semprace.eshop.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.repository.DopravaRepository;
import upce.semprace.eshop.entity.Doprava;
@Service
public class DopravaNakupService {

@Autowired
 private DopravaRepository dopravarepository;


public void setDoprava(Long id,Doprava doprava){
dopravarepository.setDoprava(id,doprava);
}


public Doprava getDoprava(Long id){
return dopravarepository.getDoprava(id);
}


}