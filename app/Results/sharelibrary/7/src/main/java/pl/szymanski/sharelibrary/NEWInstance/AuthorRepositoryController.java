package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorRepositoryController {

 private AuthorRepository authorrepository;


@GetMapping
("/findAuthorByNameOrSurname")
public List<Author> findAuthorByNameOrSurname(@RequestParam(name = "name") String name,@RequestParam(name = "surname") String surname){
  return authorrepository.findAuthorByNameOrSurname(name,surname);
}


@GetMapping
("/findAuthorByNameAndSurname")
public Optional<Author> findAuthorByNameAndSurname(@RequestParam(name = "name") String name,@RequestParam(name = "surname") String surname){
  return authorrepository.findAuthorByNameAndSurname(name,surname);
}


}