package de.metas.ui.web.session;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.metas.i18n.Language;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.json.JSONUserSession;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
@RestController
@RequestMapping(value = UserSessionRestController.ENDPOINT)
public class UserSessionRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  UserSessionRepository userSessionRepo;


@GetMapping("/language")
public JSONLookupValue getLanguage(){
    final Language language = userSession.getLanguage();
    return JSONLookupValue.of(language.getAD_Language(), language.getName());
}


@GetMapping
public JSONUserSession getAll(){
    return JSONUserSession.of(userSession);
}


@PutMapping("/language")
public JSONLookupValue setLanguage(JSONLookupValue value){
    final String adLanguage = value.getKey();
    userSessionRepo.setAD_Language(userSession.getLoggedUserId(), adLanguage);
    return getLanguage();
}


}