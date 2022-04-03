package services;
 import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import domain.Area;
import domain.Box;
import domain.Chapter;
import domain.Parade;
import domain.ParadeStatus;
import domain.Proclaim;
import domain.SocialProfile;
import forms.FormObjectChapter;
import repositories.ChapterRepository;
import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.FinderRepository;
import DTO.FormObjectChapter;
@Service
@Transactional
public class ChapterService {

@Autowired
 private  ChapterRepository chapterRepository;

@Autowired
 private  FinderRepository finderRepository;

@Autowired
 private  BoxService boxService;

@Autowired
 private  AreaService areaService;

@Autowired
 private  ProclaimService proclaimService;

@Autowired
 private  MessageService messageService;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  Validator validator;


public Chapter createChapter(){
    Chapter chapter = new Chapter();
    // Se crean las listas vacías
    // ACTOR
    List<SocialProfile> socialProfiles = new ArrayList<>();
    List<Box> boxes = new ArrayList<>();
    List<Proclaim> proclaims = new ArrayList<Proclaim>();
    // CHAPTER
    // TODO Lista de Proclaim
    chapter.setArea(null);
    UserAccount userAccount = new UserAccount();
    userAccount.setUsername("");
    userAccount.setPassword("");
    // Actor
    chapter.setAddress("");
    chapter.setProclaims(proclaims);
    chapter.setBoxes(boxes);
    chapter.setEmail("");
    chapter.setHasSpam(false);
    chapter.setMiddleName("");
    chapter.setName("");
    chapter.setPhoneNumber("");
    chapter.setPhoto("");
    chapter.setPolarity(0.0);
    chapter.setSocialProfiles(socialProfiles);
    chapter.setSurname("");
    List<Authority> authorities = new ArrayList<Authority>();
    Authority authority = new Authority();
    authority.setAuthority(Authority.CHAPTER);
    authorities.add(authority);
    userAccount.setAuthorities(authorities);
    userAccount.setIsNotLocked(true);
    chapter.setUserAccount(userAccount);
    return chapter;
}


public String SocialProfilesToString(){
    String res = "";
    Chapter chapter = this.loggedChapter();
    List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
    StringBuilder sb = new StringBuilder();
    socialProfiles = chapter.getSocialProfiles();
    Integer cont = 1;
    for (SocialProfile f : socialProfiles) {
        sb.append("Profile" + cont + " Name: " + f.getName() + " Nick: " + f.getNick() + " Profile link: " + f.getProfileLink()).append(System.getProperty("line.separator"));
        cont++;
    }
    return sb.toString();
}


public List<Area> listFreeAreas(){
    return this.chapterRepository.getFreeAreas();
}


public Chapter findOne(int id){
    return this.chapterRepository.findOne(id);
}


public Chapter save(Chapter chapter){
    return this.chapterRepository.save(chapter);
}


public Chapter reconstruct(FormObjectChapter formObjectChapter,BindingResult binding){
    Chapter result = new Chapter();
    result.setAddress(formObjectChapter.getAddress());
    result.setEmail(formObjectChapter.getEmail());
    result.setHasSpam(false);
    result.setArea(formObjectChapter.getArea());
    result.setMiddleName(formObjectChapter.getMiddleName());
    result.setName(formObjectChapter.getName());
    result.setPhoneNumber(formObjectChapter.getPhoneNumber());
    result.setPhoto(formObjectChapter.getPhoto());
    result.setSurname(formObjectChapter.getSurname());
    result.setTitle(formObjectChapter.getTitle());
    // USER ACCOUNT
    UserAccount userAccount = new UserAccount();
    // Authorities
    List<Authority> authorities = new ArrayList<Authority>();
    Authority authority = new Authority();
    authority.setAuthority(Authority.CHAPTER);
    authorities.add(authority);
    userAccount.setAuthorities(authorities);
    // locked
    userAccount.setIsNotLocked(true);
    // Username
    userAccount.setUsername(formObjectChapter.getUsername());
    // Password
    Md5PasswordEncoder encoder;
    encoder = new Md5PasswordEncoder();
    userAccount.setPassword(encoder.encodePassword(formObjectChapter.getPassword(), null));
    result.setUserAccount(userAccount);
    if (formObjectChapter.getEmail().matches("[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+"))
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
            binding.addError(new FieldError("chapter", "email", formObjectChapter.getEmail(), false, null, null, "No sigue el patron ejemplo@dominio.asd o alias <ejemplo@dominio.asd>"));
        else
            binding.addError(new FieldError("chapter", "email", formObjectChapter.getEmail(), false, null, null, "Dont follow the pattern example@domain.asd or alias <example@domain.asd>"));
    if (!formObjectChapter.getTermsAndConditions())
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
            binding.addError(new FieldError("formObjectChapter", "termsAndConditions", formObjectChapter.getTermsAndConditions(), false, null, null, "Debe aceptar los terminos y condiciones"));
        else
            binding.addError(new FieldError("formObjectChapter", "termsAndConditions", formObjectChapter.getTermsAndConditions(), false, null, null, "You must accept the terms and conditions"));
    if (!formObjectChapter.getPassword().equals(formObjectChapter.getConfirmPassword()))
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
            binding.addError(new FieldError("formObjectChapter", "password", formObjectChapter.getPassword(), false, null, null, "Las contrasenas no coinciden"));
        else
            binding.addError(new FieldError("formObjectChapter", "password", formObjectChapter.getPassword(), false, null, null, "Passwords don't match"));
    return result;
}


public Chapter update(Chapter chapter){
    Assert.isTrue(chapter.getId() == this.loggedChapter().getId());
    return this.chapterRepository.save(chapter);
}


public List<Area> listOccupiedAreas(){
    List<Area> areas = this.areaService.findAll();
    areas.removeAll(this.chapterRepository.getFreeAreas());
    return areas;
}


public void deleteAccountChapter(){
    this.loggedAsChapter();
    Chapter chapter = this.loggedChapter();
    this.messageService.updateSendedMessageByLogguedActor();
    this.messageService.updateReceivedMessageToLogguedActor();
    this.messageService.deleteAllMessageFromActor();
    for (Proclaim p : chapter.getProclaims()) this.proclaimService.delete(p);
    this.chapterRepository.delete(chapter);
}


public void paradeSecurity(Parade parade){
    Chapter chapter = this.loggedChapter();
    this.loggedAsChapter();
    List<Parade> parades = this.paradeService.getParadesByArea(chapter.getArea());
    Parade paradeDB = this.paradeService.findOne(parade.getId());
    Assert.isTrue(parades.contains(paradeDB));
}


public Chapter reconstructArea(Chapter chapter,BindingResult binding){
    Chapter result;
    Chapter result2 = this.createChapter();
    result = this.chapterRepository.findOne(chapter.getId());
    result2.setAddress(result.getAddress());
    result2.setArea(chapter.getArea());
    result2.setBoxes(result.getBoxes());
    result2.setEmail(result.getEmail());
    result2.setHasSpam(result.getHasSpam());
    result2.setMiddleName(result.getMiddleName());
    result2.setName(result.getName());
    result2.setPhoneNumber(result.getPhoneNumber());
    result2.setPhoto(result.getPhoto());
    result2.setPolarity(result.getPolarity());
    result2.setProclaims(result.getProclaims());
    result2.setSocialProfiles(result.getSocialProfiles());
    result2.setSurname(result.getSurname());
    result2.setTitle(result.getTitle());
    result2.setUserAccount(result.getUserAccount());
    result2.setVersion(result.getVersion());
    result2.setId(result.getId());
    this.validator.validate(result2, binding);
    return result2;
}


public List<Chapter> findAll(){
    return this.chapterRepository.findAll();
}


public void delete(Chapter chapter){
    this.messageService.updateSendedMessageByLogguedActor();
    this.messageService.deleteAllMessageFromActor();
    for (Proclaim p : chapter.getProclaims()) this.proclaimService.delete(p);
    this.chapterRepository.delete(chapter);
}


public Chapter updateChapterArea(Chapter chapter){
    Chapter c = this.loggedChapter();
    Assert.isTrue(chapter.getId() != 0 && c.getId() == chapter.getId() && c.getArea() == null && !this.listOccupiedAreas().contains(chapter.getArea()));
    return this.chapterRepository.save(chapter);
}


public Chapter saveCreate(Chapter chapter){
    List<Box> boxes = new ArrayList<>();
    // Se crean las listas vacías
    // ACTOR
    List<SocialProfile> socialProfiles = new ArrayList<>();
    chapter.setSocialProfiles(socialProfiles);
    // CHAPTER
    // TODO Lista de Proclaim
    // Boxes
    Box box1 = this.boxService.createSystem();
    box1.setName("INBOX");
    Box saved1 = this.boxService.saveSystem(box1);
    boxes.add(saved1);
    Box box2 = this.boxService.createSystem();
    box2.setName("OUTBOX");
    Box saved2 = this.boxService.saveSystem(box2);
    boxes.add(saved2);
    Box box3 = this.boxService.createSystem();
    box3.setName("TRASHBOX");
    Box saved3 = this.boxService.saveSystem(box3);
    boxes.add(saved3);
    Box box4 = this.boxService.createSystem();
    box4.setName("SPAMBOX");
    Box saved4 = this.boxService.saveSystem(box4);
    boxes.add(saved4);
    Box box5 = this.boxService.createSystem();
    box5.setName("NOTIFICATIONBOX");
    Box saved5 = this.boxService.saveSystem(box5);
    boxes.add(saved5);
    chapter.setBoxes(boxes);
    Chapter saved = new Chapter();
    Assert.isTrue(chapter.getArea() == null || this.listFreeAreas().contains(chapter.getArea()));
    /**
     * The Asserts done for the alternative workaround driver are commented (or
     * uncommented) below, and "replace" the otherwise expected Domain tag errors.
     */
    /*
		 * Assert.isTrue(!chapter.getName().trim().isEmpty());
		 * Assert.isTrue(!chapter.getUserAccount().getUsername().trim().isEmpty());
		 * Assert.isTrue(!chapter.getTitle().trim().isEmpty());
		 * Assert.isTrue(!chapter.getSurname().trim().isEmpty());
		 * Assert.isTrue(chapter.getPhoto().isEmpty() ||
		 * this.isUrl(chapter.getPhoto())); Assert.isTrue(chapter.getEmail().matches(
		 * "[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<+[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+"
		 * ));
		 */
    saved = this.chapterRepository.save(chapter);
    return saved;
}


public Chapter getChapterByUsername(String username){
    return this.chapterRepository.getChapterByUsername(username);
}


public Parade changeParadeStatus(Parade parade){
    Assert.isTrue(!parade.getIsDraftMode());
    Chapter chapter = this.loggedChapter();
    this.paradeSecurity(parade);
    if (parade.getParadeStatus().equals(ParadeStatus.REJECTED)) {
        Assert.notNull(parade.getRejectedReason());
        Assert.isTrue(!(parade.getRejectedReason().trim().equals("")));
    }
    parade = this.paradeService.save(parade);
    return parade;
}


public Chapter loggedChapter(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    return this.chapterRepository.getChapterByUsername(userAccount.getUsername());
}


public void flush(){
    this.chapterRepository.flush();
}


public Chapter reconstructPersonalData(Chapter chapter,BindingResult binding){
    Chapter result;
    Chapter chapter2;
    result = chapter;
    chapter2 = this.chapterRepository.findOne(chapter.getId());
    result.setUserAccount(chapter2.getUserAccount());
    result.setBoxes(chapter2.getBoxes());
    result.setHasSpam(chapter2.getHasSpam());
    result.setSocialProfiles(chapter2.getSocialProfiles());
    result.setPolarity(chapter2.getPolarity());
    result.setArea(chapter2.getArea());
    this.validator.validate(result, binding);
    if (chapter.getEmail().matches("[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+"))
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
            binding.addError(new FieldError("chapter", "email", chapter.getEmail(), false, null, null, "No sigue el patron ejemplo@dominio.asd o alias <ejemplo@dominio.asd>"));
        else
            binding.addError(new FieldError("chapter", "email", chapter.getEmail(), false, null, null, "Dont follow the pattern example@domain.asd or alias <example@domain.asd>"));
    return result;
}


public Boolean isUrl(String url){
    try {
        new URL(url).toURI();
        return true;
    } catch (Exception e) {
        return false;
    }
}


public void loggedAsChapter(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    final List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("CHAPTER"));
}


}