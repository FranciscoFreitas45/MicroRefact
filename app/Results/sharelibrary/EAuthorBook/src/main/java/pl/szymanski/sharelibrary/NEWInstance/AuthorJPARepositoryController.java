package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorJPARepositoryController {

 private AuthorJPARepository authorjparepository;


@GetMapping
("/findAuthorByNameIgnoreCaseAndSurnameIgnoreCase")
public Optional<Author> findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(@RequestParam(name = "name") String name,@RequestParam(name = "surname") String surname){
  return authorjparepository.findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(name,surname);
}


@GetMapping
("/findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase")
public List<Author> findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(@RequestParam(name = "name") String name,@RequestParam(name = "surname") String surname){
  return authorjparepository.findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(name,surname);
}


}