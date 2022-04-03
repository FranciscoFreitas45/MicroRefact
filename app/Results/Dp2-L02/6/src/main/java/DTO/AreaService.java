package DTO;
 import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Area;
import domain.Brotherhood;
import repositories.AreaRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
public class AreaService {

 private  AreaRepository areaRepository;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public List<Area> findAll(){
    return this.areaRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Area> aux = restTemplate.getForObject(builder.toUriString(),List<Area>.class);
return aux;
}


}