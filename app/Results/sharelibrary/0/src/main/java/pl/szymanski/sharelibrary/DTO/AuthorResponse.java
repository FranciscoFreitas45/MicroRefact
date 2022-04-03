package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Author;
public class AuthorResponse {

 private  Long id;

 private  String name;

 private  String surname;


public AuthorResponse of(Author author){
    return new AuthorResponse(author.getId(), author.getName(), author.getSurname());
}


}