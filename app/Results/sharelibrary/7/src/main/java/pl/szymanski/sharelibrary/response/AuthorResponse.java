package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Author;
@AllArgsConstructor
@Data
public class AuthorResponse {

 private  Long id;

 private  String name;

 private  String surname;


public AuthorResponse of(Author author){
    return new AuthorResponse(author.getId(), author.getName(), author.getSurname());
}


}