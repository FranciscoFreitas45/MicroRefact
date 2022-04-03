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


public List<Area> listFreeAreas(){
    return this.chapterRepository.getFreeAreas();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listFreeAreas"))

;
List<Area> aux = restTemplate.getForObject(builder.toUriString(),List<Area>.class);
return aux;
}


}