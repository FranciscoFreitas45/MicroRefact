package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.enums.BookCondition;
import java.util.Set;
import java.util.stream.Collectors;
import pl.szymanski.sharelibrary.Interface.LanguageResponse;
public class BookWithoutUsersResponse {

 private  Long id;

 private  String title;

 private  Set<AuthorResponse> authors;

 private  Set<CategoryResponse> categories;

 private  LanguageResponse language;

 private  BookCondition condition;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public BookWithoutUsersResponse of(Book book){
    return new BookWithoutUsersResponse(book.getId(), book.getTitle(), book.getAuthors().stream().map(AuthorResponse::of).collect(Collectors.toSet()), book.getCategories().stream().map(CategoryResponse::of).collect(Collectors.toSet()), LanguageResponse.of(book.getLanguage()), book.getCondition());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("book",book)
;
BookWithoutUsersResponse aux = restTemplate.getForObject(builder.toUriString(),BookWithoutUsersResponse.class);
return aux;
}


}