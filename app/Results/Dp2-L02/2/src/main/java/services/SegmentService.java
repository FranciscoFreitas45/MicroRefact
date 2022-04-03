package services;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Brotherhood;
import domain.Parade;
import domain.ParadeStatus;
import domain.Path;
import domain.Segment;
import repositories.SegmentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.ParadeService;
import Interface.BrotherhoodService;
import Interface.ChapterService;
import Interface.ActorService;
import DTO.ParadeService;
import DTO.Parade;
import DTO.BrotherhoodService;
import DTO.Brotherhood;
import DTO.ActorService;
import DTO.UserAccount;
import DTO.Authority;
import DTO.ChapterService;
@Service
@Transactional
public class SegmentService {

@Autowired
 private  SegmentRepository segmnentRepository;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  PathService pathService;

@Autowired
 private  ChapterService chapterService;

@Autowired
 private  ActorService actorService;

@Autowired
 private  Validator validator;


public boolean isLast(Segment segment,Path path){
    List<Segment> segments = path.getSegments();
    if (segments.size() != 0)
        return segments.indexOf(segment) == segments.size() - 1;
    else
        return true;
}


public Segment findOne(Integer id){
    return this.segmnentRepository.findOne(id);
}


public Segment save(Segment segment){
    return this.segmnentRepository.save(segment);
}


public Segment editSegment(Segment segment,Integer paradeId){
    this.segmentSecurity(paradeId);
    Parade parade = this.paradeService.findOne(paradeId);
    Path path = parade.getPath();
    List<Segment> segments = path.getSegments();
    Boolean isNew = segment.getId() == 0;
    if (!isNew)
        segment = this.saveSegment(segment);
    if (!this.isLast(segment, path) && !isNew) {
        Segment segmentBD = this.findOne(segment.getId());
        Segment segmentAfter = segments.get(segments.indexOf(segmentBD) + 1);
        segment = this.recontruct(segment, paradeId, null);
        segmentAfter.setOriginLatitude(segment.getDestinationLatitude());
        segmentAfter.setOriginLongitude(segment.getDestinationLongitude());
        this.save(segmentAfter);
    }
    if (isNew) {
        segments.add(segment);
        path.setSegments(segments);
        parade.setPath(path);
        this.paradeService.save(parade);
    }
    return segment;
}


public List<Segment> findAll(){
    return this.segmnentRepository.findAll();
}


public void delete(Segment segment){
    this.segmnentRepository.delete(segment);
}


public Segment recontruct(Segment segmentForm,Integer paradeId,BindingResult binding){
    Segment result = new Segment();
    Parade parade = this.paradeService.findOne(paradeId);
    List<Segment> segments = parade.getPath().getSegments();
    Boolean isNew = segmentForm.getId() == 0;
    if (!this.isOrigin(segmentForm, paradeId) && isNew) {
        Segment segmentBefore = segments.get(segments.size() - 1);
        result.setOriginLatitude(segmentBefore.getDestinationLatitude());
        result.setOriginLongitude(segmentBefore.getDestinationLongitude());
    } else if (this.isOrigin(segmentForm, paradeId)) {
        result.setOriginLatitude(segmentForm.getOriginLatitude());
        result.setOriginLongitude(segmentForm.getOriginLongitude());
    } else {
        Segment segmentOld = this.findOne(segmentForm.getId());
        result.setOriginLatitude(segmentOld.getOriginLatitude());
        result.setOriginLongitude(segmentOld.getOriginLongitude());
    }
    result.setId(segmentForm.getId());
    result.setDestinationLatitude(segmentForm.getDestinationLatitude());
    result.setDestinationLongitude(segmentForm.getDestinationLongitude());
    result.setTime(segmentForm.getTime());
    this.validator.validate(result, binding);
    return result;
}


public Segment recontructTest(Segment segmentForm,Integer paradeId){
    Segment result = new Segment();
    Parade parade = this.paradeService.findOne(paradeId);
    List<Segment> segments = parade.getPath().getSegments();
    Boolean isNew = segmentForm.getId() == 0;
    if (!this.isOrigin(segmentForm, paradeId) && isNew) {
        Segment segmentBefore = segments.get(segments.size() - 1);
        result.setOriginLatitude(segmentBefore.getDestinationLatitude());
        result.setOriginLongitude(segmentBefore.getDestinationLongitude());
    } else if (this.isOrigin(segmentForm, paradeId)) {
        result.setOriginLatitude(segmentForm.getOriginLatitude());
        result.setOriginLongitude(segmentForm.getOriginLongitude());
    } else {
        Segment segmentOld = this.findOne(segmentForm.getId());
        result.setOriginLatitude(segmentOld.getOriginLatitude());
        result.setOriginLongitude(segmentOld.getOriginLongitude());
    }
    result.setId(segmentForm.getId());
    result.setDestinationLatitude(segmentForm.getDestinationLatitude());
    result.setDestinationLongitude(segmentForm.getDestinationLongitude());
    result.setTime(segmentForm.getTime());
    return result;
}


public void flush(){
    this.segmnentRepository.flush();
}


public void segmentSecurity(Integer paradeId){
    Parade parade = this.paradeService.findOne(paradeId);
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(brotherhood.getParades().contains(parade));
}


public void deleteSegment(Segment segment,Integer paradeId){
    System.out.println("inicio");
    Parade parade = this.paradeService.findOne(paradeId);
    Path path = parade.getPath();
    List<Segment> segments = path.getSegments();
    segment = this.findOne(segment.getId());
    this.segmentSecurity(paradeId);
    if (!this.isOrigin(segment, paradeId) && !this.isLast(segment, path)) {
        Segment segmentBefore = segments.get(segments.indexOf(segment) - 1);
        Segment segmentAfter = segments.get(segments.indexOf(segment) + 1);
        segmentAfter.setOriginLatitude(segmentBefore.getDestinationLatitude());
        segmentAfter.setOriginLongitude(segmentBefore.getDestinationLongitude());
        this.save(segmentAfter);
    }
    segments.remove(segment);
    path.setSegments(segments);
    parade.setPath(path);
    this.paradeService.save(parade);
    this.delete(segment);
}


public Segment saveSegment(Segment segmentForm){
    Segment segmentBD = this.findOne(segmentForm.getId());
    segmentBD.setDestinationLatitude(segmentForm.getDestinationLatitude());
    segmentBD.setDestinationLongitude(segmentForm.getDestinationLongitude());
    segmentBD.setOriginLatitude(segmentForm.getOriginLatitude());
    segmentBD.setOriginLongitude(segmentForm.getOriginLongitude());
    segmentBD.setTime(segmentForm.getTime());
    return this.save(segmentBD);
}


public Segment createSegment(){
    Segment segment = new Segment();
    segment.setDestinationLatitude(0.);
    segment.setDestinationLongitude(0.);
    segment.setOriginLatitude(0.);
    segment.setOriginLongitude(0.);
    segment.setTime(0);
    return segment;
}


public List<Segment> getSegmentByParade(Integer paradeId){
    Boolean logguedBro = false;
    Boolean logguedChapter = false;
    Parade parade = this.paradeService.findOne(paradeId);
    if (this.actorService.loggedAsActorBoolean()) {
        UserAccount userAccount = LoginService.getPrincipal();
        List<Authority> auth = (List<Authority>) userAccount.getAuthorities();
        logguedBro = auth.get(0).toString().equals("BROTHERHOOD");
        logguedChapter = auth.get(0).toString().equals("CHAPTER");
    }
    if (parade.getIsDraftMode() && logguedBro)
        Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getParades().contains(parade));
    else if (logguedChapter && (parade.getParadeStatus().equals(ParadeStatus.SUBMITTED) || parade.getParadeStatus().equals(ParadeStatus.REJECTED)))
        this.chapterService.paradeSecurity(parade);
    else if ((!parade.getIsDraftMode() && parade.getParadeStatus().equals(ParadeStatus.SUBMITTED)) || (!parade.getIsDraftMode() && parade.getParadeStatus().equals(ParadeStatus.REJECTED)))
        Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getParades().contains(parade));
    else
        Assert.isTrue(parade.getParadeStatus().equals(ParadeStatus.ACCEPTED));
    return parade.getPath().getSegments();
}


public boolean isOrigin(Segment segmentForm,Integer paradeId){
    Path path = this.paradeService.findOne(paradeId).getPath();
    List<Segment> segments = path.getSegments();
    Segment segment = this.findOne(segmentForm.getId());
    return segments.indexOf(segment) == 0 || segments.size() == 0;
}


}