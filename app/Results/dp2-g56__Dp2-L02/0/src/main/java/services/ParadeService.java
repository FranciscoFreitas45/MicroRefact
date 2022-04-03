package services;
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
@Service
@Transactional
public class ParadeService {

@Autowired
 private  ParadeRepository paradeRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  SponsorService sponsorService;

@Autowired
 private  FloatService floatService;

@Autowired
 private  PathService pathService;

@Autowired
 private  SegmentService segmentService;

@Autowired
 private  RequestService requestService;

@Autowired
 private  FinderService finderService;

@Autowired
 private  SponsorshipService sponsorshipService;


public void deleteAllParadesTest(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brother = this.brotherhoodService.loggedBrotherhood();
    List<Parade> paradesToDelete = new ArrayList<Parade>();
    paradesToDelete = brother.getParades();
    Integer size = paradesToDelete.size();
// for (int i = 0; i < size; i++)
// this.deleteOneParade(paradesToDelete.get(0));
}


public Parade reconstructCheckbox(FormObjectParadeFloatCheckbox formObjectParadeFloatCheckbox,BindingResult binding){
    Parade result = new Parade();
    if (formObjectParadeFloatCheckbox.getId() == 0)
        result.setTicker(this.generateTicker());
    else
        result = this.paradeRepository.findOne(formObjectParadeFloatCheckbox.getId());
    Parade result2 = new Parade();
    result2.setId(result.getId());
    result2.setVersion(result.getVersion());
    result2.setTicker(result.getTicker());
    result2.setMoment(formObjectParadeFloatCheckbox.getMoment());
    result2.setTitle(formObjectParadeFloatCheckbox.getTitleParade());
    result2.setDescription(formObjectParadeFloatCheckbox.getDescriptionParade());
    result2.setIsDraftMode(formObjectParadeFloatCheckbox.getIsDraftMode());
    result2.setColumnNumber(formObjectParadeFloatCheckbox.getColumnNumber());
    result2.setRowNumber(formObjectParadeFloatCheckbox.getRowNumber());
    result2.setRejectedReason(result.getRejectedReason());
    result2.setParadeStatus(ParadeStatus.SUBMITTED);
    result2.setFloats(result.getFloats());
    result2.setPath(result.getPath());
    result2.setRequests(result.getRequests());
    // this.validator.validate(result, binding); //YA VIENE VALIDADO
    return result2;
}


public void putOrDeletePath(Integer paradeId){
    Parade parade = this.findOne(paradeId);
    Path path = parade.getPath();
    this.paradeSecurity(parade);
    if (path == null) {
        path = new Path();
        path = this.pathService.save(path);
        parade.setPath(path);
    } else {
        parade.setPath(null);
        this.pathService.delete(path);
    }
    this.save(parade);
}


public Boolean hasArea(Chapter chapter){
    return chapter.getArea() != null;
}


public Parade findOne(int id){
    return this.paradeRepository.findOne(id);
}


public Parade save(Parade parade){
    return this.paradeRepository.save(parade);
}


public void saveFloatAndAssignToParade(Float coach,Parade parade){
    this.brotherhoodService.loggedAsBrotherhood();
    domain.Float savedFloat = this.floatService.save(coach);
    this.saveAssign(parade, savedFloat);
}


public void paradeSecurity(Parade parade){
    this.brotherhoodService.securityAndBrotherhood();
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(brotherhood.getParades().contains(parade));
}


public List<Parade> findAll(){
    return this.paradeRepository.findAll();
}


public void delete(Parade parade){
    this.paradeRepository.delete(parade);
}


public Parade saveAssign(Parade parade,domain.Float newFloat){
    this.brotherhoodService.loggedAsBrotherhood();
    List<domain.Float> floats = new ArrayList<>();
    floats.add(newFloat);
    parade.setFloats(floats);
    Parade saved = new Parade();
    saved = this.paradeRepository.save(parade);
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    brotherhood.getParades().add(saved);
    this.brotherhoodService.save(brotherhood);
    return saved;
}


public void flush(){
    this.paradeRepository.flush();
}


public String generateTicker(){
    String res = "";
    Date date = null;
    String date1;
    String date2 = LocalDate.now().toString();
    String gen = new RandomString(6).nextString();
    List<Parade> lc = this.paradeRepository.findAll();
    SimpleDateFormat df_in = new SimpleDateFormat("yyMMdd");
    SimpleDateFormat df_output = new SimpleDateFormat("yyyy-MM-dd");
    try {
        date = df_output.parse(date2);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    date1 = df_in.format(date);
    res = res + date1 + "-" + gen;
    for (Parade c : lc) if (c.getTicker() == res)
        return this.generateTicker();
    return res;
}


public void deleteParadeWithId(int paradeId){
    Parade parade = this.paradeRepository.findOne(paradeId);
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(parade);
    Assert.isTrue(loggedBrotherhood.getParades().contains(parade));
    Assert.isTrue(parade.getIsDraftMode());
    parade.setFloats(new ArrayList<domain.Float>());
    loggedBrotherhood.getParades().remove(parade);
    this.brotherhoodService.save(loggedBrotherhood);
    this.paradeRepository.delete(parade);
}


public Collection<Parade> getAcceptedParades(){
    return this.paradeRepository.getAcceptedParades();
}


public Parade saveAssignList(Parade parade,List<domain.Float> floats){
    // TERMINADO
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(brotherhood.getArea());
    for (Float f : floats) Assert.isTrue(brotherhood.getFloats().contains(f) && f.getId() > 0);
    if (parade.getId() > 0) {
        Parade paradeFounded = this.findOne(parade.getId());
        Assert.isTrue(paradeFounded.getIsDraftMode() && brotherhood.getParades().contains(paradeFounded));
    }
    parade.setFloats(floats);
    Parade saved = new Parade();
    saved = this.paradeRepository.save(parade);
    // NUEVO
    brotherhood.getParades().remove(parade);
    brotherhood.getParades().add(saved);
    this.brotherhoodService.save(brotherhood);
    return saved;
}


public Parade create(){
    // Asegurar que está logueado como Brotherhood
    // Asegurar que la Brotherhood logueada tiene un área
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getArea());
    Parade parade = new Parade();
    List<Float> floats = new ArrayList<>();
    parade.setPath(null);
    parade.setFloats(floats);
    parade.setColumnNumber(0);
    parade.setDescription("");
    parade.setIsDraftMode(true);
    parade.setMoment(null);
    List<Request> requests = new ArrayList<>();
    parade.setRequests(requests);
    parade.setRowNumber(0);
    String ticker = this.generateTicker();
    parade.setTicker(ticker);
    parade.setTitle("");
    parade.setParadeStatus(ParadeStatus.SUBMITTED);
    return parade;
}


public FormObjectParadeFloatCheckbox prepareFormObjectParadeFloatCheckbox(int paradeId){
    Parade parade = this.paradeRepository.findOne(paradeId);
    Assert.notNull(parade);
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(loggedBrotherhood.getParades().contains(parade));
    Assert.isTrue(parade.getIsDraftMode());
    FormObjectParadeFloatCheckbox result = new FormObjectParadeFloatCheckbox();
    List<Integer> floats = new ArrayList<>();
    for (domain.Float f : parade.getFloats()) floats.add(f.getId());
    result.setColumnNumber(parade.getColumnNumber());
    result.setDescriptionParade(parade.getDescription());
    result.setFloats(floats);
    result.setIsDraftMode(parade.getIsDraftMode());
    result.setMoment(parade.getMoment());
    result.setRowNumber(parade.getRowNumber());
    result.setTitleParade(parade.getTitle());
    result.setId(paradeId);
    return result;
}


public Parade copy(Parade paradeToCopy){
    Parade paradeCopy = this.create();
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(brotherhood.getParades().contains(paradeToCopy) && paradeCopy.getId() == 0);
    Assert.isTrue(paradeToCopy.getFloats().size() >= 0);
    Assert.isTrue(paradeToCopy.getRequests().size() >= 0);
    // Assert.isTrue(paradeToCopy.getPaths().size() >= 0);
    paradeCopy.setColumnNumber(paradeToCopy.getColumnNumber());
    paradeCopy.setRowNumber(paradeToCopy.getRowNumber());
    paradeCopy.setDescription(paradeToCopy.getDescription());
    paradeCopy.setMoment(paradeToCopy.getMoment());
    paradeCopy.setTitle(paradeToCopy.getTitle());
    paradeCopy.setFloats(paradeToCopy.getFloats());
    List<Request> r = new ArrayList<Request>();
    paradeCopy.setRequests(r);
    Path savedPath = null;
    if (paradeToCopy.getPath() != null) {
        Path path = this.pathService.create();
        for (Segment segment : paradeToCopy.getPath().getSegments()) {
            List<Segment> segmentsPath = path.getSegments();
            Segment newSegment = this.segmentService.createSegment();
            newSegment.setDestinationLatitude(segment.getDestinationLatitude());
            newSegment.setDestinationLongitude(segment.getDestinationLongitude());
            newSegment.setOriginLatitude(segment.getOriginLatitude());
            newSegment.setOriginLongitude(segment.getOriginLongitude());
            newSegment.setTime(segment.getTime());
            segmentsPath.add(newSegment);
            path.setSegments(segmentsPath);
        }
        savedPath = this.pathService.save(path);
    }
    this.flush();
    paradeCopy.setPath(savedPath);
    Parade saved = new Parade();
    saved = this.paradeRepository.save(paradeCopy);
    brotherhood.getParades().add(saved);
    this.brotherhoodService.save(brotherhood);
    return saved;
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
}


public void deleteParade(Parade parade){
    // Security
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(!(loggedBrotherhood.getArea().equals(null)));
    Assert.isTrue(parade.getIsDraftMode());
    Assert.isTrue(loggedBrotherhood.getParades().contains(parade));
    // No debería tener Request porque está en Draft mode
    // Tampoco hay que preocuparse por el finder porque no se pueden buscar parades
    // en Draft mode
    List<Float> floats = new ArrayList<>();
    parade.setFloats(floats);
    List<Parade> parades = loggedBrotherhood.getParades();
    parades.remove(parade);
    loggedBrotherhood.setParades(parades);
    this.brotherhoodService.save(loggedBrotherhood);
    this.paradeRepository.delete(parade);
}


public List<Parade> filterParadesChapter(Chapter chapter,String option){
    switch(option) {
        case "REJECTED":
            return this.paradeRepository.getRejectedParadesByChapter(chapter.getArea());
        case "ACCEPTED":
            return this.paradeRepository.getAcceptedParadesByChapter(chapter.getArea());
        case "SUBMITTED":
            return this.paradeRepository.getSubmittedParadesByChapter(chapter.getArea());
        default:
            return this.getParadesByArea(chapter.getArea());
    }
}


public Collection<Parade> listAcceptedParadeIfSponsor(){
    this.sponsorService.securityAndSponsor();
    Collection<Parade> parades = this.getAcceptedParades();
    return parades;
}


public Parade edit(Parade parade,int columnNumber,int rowNumber,String description,boolean isDraftMode,String title,Date moment){
    // Security
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(!(loggedBrotherhood.getArea().equals(null)));
    Assert.isTrue(parade.getIsDraftMode());
    Assert.isTrue(loggedBrotherhood.getParades().contains(parade));
    List<Parade> parades = loggedBrotherhood.getParades();
    parades.remove(parade);
    // parade.setFloats(floats);
    parade.setColumnNumber(columnNumber);
    parade.setDescription(description);
    parade.setIsDraftMode(isDraftMode);
    parade.setMoment(moment);
    // parade.setRequests(requests);
    parade.setRowNumber(rowNumber);
    // parade.setTicker(ticker);
    parade.setTitle(title);
    Parade saved = this.save(parade);
    parades.add(saved);
    loggedBrotherhood.setParades(parades);
    parade.setParadeStatus(ParadeStatus.SUBMITTED);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public Parade reconstruct(FormObjectParadeFloat formObjectParadeCoach,BindingResult binding){
    Parade result = new Parade();
    result.setTitle(formObjectParadeCoach.getTitleParade());
    result.setDescription(formObjectParadeCoach.getDescriptionParade());
    result.setMoment(formObjectParadeCoach.getMoment());
    result.setIsDraftMode(formObjectParadeCoach.getIsDraftMode());
    result.setRowNumber(formObjectParadeCoach.getRowNumber());
    result.setColumnNumber(formObjectParadeCoach.getColumnNumber());
    result.setId(0);
    result.setParadeStatus(ParadeStatus.SUBMITTED);
    result.setTicker(this.generateTicker());
    // this.validator.validate(result, binding);
    return result;
}


public void deleteAllParades(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brother = this.brotherhoodService.loggedBrotherhood();
    List<Parade> paradesToDelete = new ArrayList<Parade>();
    List<domain.Float> emptyFloats = new ArrayList<domain.Float>();
    paradesToDelete = brother.getParades();
    List<Finder> allFinders = new ArrayList<Finder>();
    allFinders = this.finderService.findAll();
    Integer size = paradesToDelete.size();
    for (int i = 0; i < size; i++) {
        for (Finder f : allFinders) f.getParades().remove(paradesToDelete.get(i));
        Path path = paradesToDelete.get(i).getPath();
        // SIN FLOATS
        paradesToDelete.get(i).setFloats(emptyFloats);
        paradesToDelete.get(i).setPath(null);
        this.pathService.delete(path);
        this.requestService.deleteAllRequestParadesBrotherhood(paradesToDelete.get(i));
        brother.getParades().remove(paradesToDelete.get(i));
        this.brotherhoodService.save(brother);
        this.paradeRepository.delete(paradesToDelete.get(i));
        this.flush();
    }
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
}


public List<Parade> filterParadesBrotherhood(Brotherhood bro,String option){
    this.brotherhoodService.loggedAsBrotherhood();
    switch(option) {
        case "REJECTED":
            return this.paradeRepository.getRejectedParadesByBrotherhood(bro.getId());
        case "ACCEPTED":
            return this.paradeRepository.getAcceptedParadesByBrotherhood(bro.getId());
        case "SUBMITTED":
            return this.paradeRepository.getSubmittedParadesByBrotherhood(bro.getId());
        case "DRAFT":
            return this.paradeRepository.getDraftParadesByBrotherhood(bro.getId());
        default:
            return bro.getParades();
    }
}


public List<Parade> getParadesByArea(Area area){
    return this.paradeRepository.getParadesByArea(area);
}


public Collection<Parade> getDraftParades(){
    return this.paradeRepository.getDraftParades();
}


public Parade reconstrucParadeStatus(Parade parade){
    Parade rParade = new Parade();
    rParade = this.findOne(parade.getId());
    Assert.isTrue(rParade.getParadeStatus().equals(ParadeStatus.SUBMITTED) && !rParade.getIsDraftMode());
    rParade.setRejectedReason(parade.getRejectedReason());
    rParade.setParadeStatus(parade.getParadeStatus());
    return rParade;
}


}