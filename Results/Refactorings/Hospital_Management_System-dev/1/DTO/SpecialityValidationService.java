import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.Speciality;
public interface SpecialityValidationService {

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public ModelMap getErrorModelMap()


public SpecialityValidationService setSpeciality(Speciality speciality)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSpeciality"));

.queryParam("speciality",speciality);
SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(),SpecialityValidationService.class);
return aux;
}


public SpecialityValidationService validName()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validName"));

SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(),SpecialityValidationService.class);
return aux;
}


public SpecialityValidationService validLength()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validLength"));

SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(),SpecialityValidationService.class);
return aux;
}


public SpecialityValidationService validPrice()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validPrice"));

SpecialityValidationService aux = restTemplate.getForObject(builder.toUriString(),SpecialityValidationService.class);
return aux;
}


}