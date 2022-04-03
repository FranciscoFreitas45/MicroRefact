package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.enums.BookStatus;
import java.util.List;
import java.util.stream.Collectors;
import pl.szymanski.sharelibrary.Interface.LanguageResponse;
public class UserBookResponse {

 private  Long id;

 private  String title;

 private  List<AuthorResponse> authors;

 private  List<CategoryResponse> categories;

 private  BookStatus status;

 private  UserResponse atUser;

 private  LanguageResponse language;

 private  BookCondition condition;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public UserBookResponse of(UserBook user){
    return new UserBookResponse(user.getBook().getId(), user.getBook().getTitle(), user.getBook().getAuthors().stream().map(AuthorResponse::of).collect(Collectors.toList()), user.getBook().getCategories().stream().map(CategoryResponse::of).collect(Collectors.toList()), user.getStatus(), (user.getAtUser() != null) ? UserResponse.of(user.getAtUser()) : null, LanguageResponse.of(user.getBook().getLanguage()), user.getBook().getCondition());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("user",user)
;
UserBookResponse aux = restTemplate.getForObject(builder.toUriString(),UserBookResponse.class);
return aux;
}


}