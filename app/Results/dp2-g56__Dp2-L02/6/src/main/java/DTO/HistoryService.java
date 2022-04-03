package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HistoryRepository;
import domain.Brotherhood;
import domain.History;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.MiscellaneousRecord;
import domain.PeriodRecord;
public class HistoryService {

 private  HistoryRepository historyRepository;

 private  BrotherhoodService brotherhoodService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<History> findAll(){
    return this.historyRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<History> aux = restTemplate.getForObject(builder.toUriString(),List<History>.class);
return aux;
}


}