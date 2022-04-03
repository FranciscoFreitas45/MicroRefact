package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import pl.szymanski.sharelibrary.entity.*;
 import pl.szymanski.sharelibrary.repositories.ports.*;
 import java.util.*;
@RestController
@CrossOrigin
public class LanguageRepositoryController {

 private LanguageRepository languagerepository;


@GetMapping
("/getLanguageById")
public Optional<Language> getLanguageById(@RequestParam(name = "id") Integer id){
  return languagerepository.getLanguageById(id);
}


@GetMapping
("/getAll")
public Set<Language> getAll(){
  return languagerepository.getAll();
}


}