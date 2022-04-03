package DTO;
 import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PositionRepository;
import domain.Position;
import Interface.AdminService;
public class PositionService {

 private  PositionRepository positionRepository;

 private  AdminService adminService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public Collection<Position> getPositions(){
    return this.positionRepository.getPositions();
}


public Collection<Position> getUsedPositions(){
    return this.positionRepository.getUsedPositions();
}


public List<Position> findAll(){
    return this.positionRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Position> aux = restTemplate.getForObject(builder.toUriString(),List<Position>.class);
return aux;
}


}