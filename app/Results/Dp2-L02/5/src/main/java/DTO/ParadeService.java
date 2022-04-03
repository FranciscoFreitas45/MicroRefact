package DTO;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import domain.Area;
import domain.Brotherhood;
import domain.Chapter;
import domain.Finder;
import domain.Float;
import domain.Member;
import domain.Parade;
import domain.ParadeStatus;
import domain.Path;
import domain.Request;
import domain.Segment;
import domain.Sponsorship;
import forms.FormObjectParadeFloat;
import forms.FormObjectParadeFloatCheckbox;
import repositories.ParadeRepository;
import utilities.RandomString;
import Interface.BrotherhoodService;
import Interface.SponsorService;
import Interface.PathService;
import Interface.SegmentService;
import Interface.FinderService;
import Interface.SponsorshipService;
import DTO.BrotherhoodService;
import DTO.Brotherhood;
import DTO.PathService;
import DTO.Chapter;
import DTO.Path;
import DTO.SegmentService;
import DTO.Segment;
import DTO.FinderService;
import DTO.SponsorshipService;
import DTO.SponsorService;
public class ParadeService {

 private  ParadeRepository paradeRepository;

 private  BrotherhoodService brotherhoodService;

 private  SponsorService sponsorService;

 private  FloatService floatService;

 private  PathService pathService;

 private  SegmentService segmentService;

 private  RequestService requestService;

 private  FinderService finderService;

 private  SponsorshipService sponsorshipService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Collection<Parade> getAcceptedParades(){
    return this.paradeRepository.getAcceptedParades();
}


public List<Parade> getParadesByArea(Area area){
    return this.paradeRepository.getParadesByArea(area);
}


public Collection<Parade> getDraftParades(){
    return this.paradeRepository.getDraftParades();
}


public List<Parade> findAll(){
    return this.paradeRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Parade> aux = restTemplate.getForObject(builder.toUriString(),List<Parade>.class);
return aux;
}


}