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


public void deleteInFinal(Parade p){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brother = this.brotherhoodService.loggedBrotherhood();
    // DELETE PATH------------------
    if (p.getPath() != null) {
        Path path = new Path();
        path = p.getPath();
        p.setPath(null);
        this.pathService.delete(path);
    }
    // -----------------------------
    // UNASSIGN LIST FLOAT--------------------------------------------
    List<domain.Float> emptyFloats = new ArrayList<domain.Float>();
    p.setFloats(emptyFloats);
    // -------------------------------------------------------------
    // UNASSIGN FINDERS-----------------------------------
    List<Finder> allFinders = new ArrayList<Finder>();
    allFinders = this.finderService.findAll();
    for (Finder f : allFinders) f.getParades().remove(p);
    // ----------------------------------------------------
    // DELETE REQUEST------------------------------------------
    List<Request> requests = p.getRequests();
    Integer size = requests.size();
    for (int i = 0; i < size; i++) {
        Member me = requests.get(i).getMember();
        if (me.getRequests().contains(requests.get(i)))
            me.getRequests().remove(requests.get(i));
    // parades.get(0).getRequests().remove(requests.get(0));
    }
    p.setRequests(new ArrayList<Request>());
    this.requestService.deleteInBatch(requests);
    // -----------------------------------------------------------
    // SPONSORSHIPS------------------------------------------------
    List<Sponsorship> sponsorships = new ArrayList<Sponsorship>();
    sponsorships = this.sponsorshipService.findAll();
    for (Sponsorship s : sponsorships) if (s.getParade().equals(p))
        s.setParade(null);
    // ------------------------------------------------------
    brother.getParades().remove(p);
    this.save(p);
    this.paradeRepository.delete(p);
    this.flush();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteInFinal"))

.queryParam("p",p)
;
restTemplate.put(builder.toUriString(),null);
}


public void deleteParadetest(Parade parade){
    // Security
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    // Assert.isTrue(!(loggedBrotherhood.getArea().equals(null)));
    Assert.isTrue(parade.getIsDraftMode());
    Assert.isTrue(loggedBrotherhood.getParades().contains(parade));
    // No debería tener Request porque está en Draft mode
    // Tampoco hay que preocuparse por el finder porque no se pueden buscar parades
    // en Draft mode
    List<Float> floats = new ArrayList<>();
    parade.setFloats(floats);
    if (parade.getPath() != null) {
        Path path = new Path();
        path = parade.getPath();
        parade.setPath(null);
        this.pathService.delete(path);
    }
    List<Parade> parades = loggedBrotherhood.getParades();
    parades.remove(parade);
    loggedBrotherhood.setParades(parades);
    this.brotherhoodService.save(loggedBrotherhood);
    this.paradeRepository.delete(parade);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteParadetest"))

.queryParam("parade",parade)
;
restTemplate.put(builder.toUriString(),null);
}


}