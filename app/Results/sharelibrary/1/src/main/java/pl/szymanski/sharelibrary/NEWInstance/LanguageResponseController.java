package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import pl.szymanski.sharelibrary.response.*;
 import java.util.*;
  import pl.szymanski.sharelibrary.entity.*;


@RestController
@CrossOrigin
public class LanguageResponseController {

 private LanguageResponse languageresponse;


@GetMapping
("/of")
public LanguageResponse of(@RequestParam(name = "language") Language language){
  return languageresponse.of(language);
}


}