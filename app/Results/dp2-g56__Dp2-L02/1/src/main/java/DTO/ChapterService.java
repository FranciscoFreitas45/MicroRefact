package DTO;
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
public class ChapterService {

 private  ChapterRepository chapterRepository;

 private  FinderRepository finderRepository;

 private  BoxService boxService;

 private  AreaService areaService;

 private  ProclaimService proclaimService;

 private  MessageService messageService;

 private  ParadeService paradeService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Chapter getChapterByUsername(String username){
    return this.chapterRepository.getChapterByUsername(username);
}


public Chapter findOne(int id){
    return this.chapterRepository.findOne(id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))

.queryParam("id",id)
;
Chapter aux = restTemplate.getForObject(builder.toUriString(),Chapter.class);
return aux;
}


public Chapter loggedChapter(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    return this.chapterRepository.getChapterByUsername(userAccount.getUsername());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedChapter"))

;
Chapter aux = restTemplate.getForObject(builder.toUriString(),Chapter.class);
return aux;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructPersonalData"))

.queryParam("chapter",chapter)
.queryParam("binding",binding)
;
Chapter aux = restTemplate.getForObject(builder.toUriString(),Chapter.class);
return aux;
}


public Chapter update(Chapter chapter){
    Assert.isTrue(chapter.getId() == this.loggedChapter().getId());
    return this.chapterRepository.save(chapter);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))

.queryParam("chapter",chapter)
;
Chapter aux = restTemplate.getForObject(builder.toUriString(),Chapter.class);
return aux;
}


}