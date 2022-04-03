package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.enums.BookCondition;
import java.util.Set;
import java.util.stream.Collectors;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookWithoutUsersResponse {

 private  Long id;

 private  String title;

 private  Set<AuthorResponse> authors;

 private  Set<CategoryResponse> categories;

 private  LanguageResponse language;

 private  BookCondition condition;


public BookWithoutUsersResponse of(Book book){
    return new BookWithoutUsersResponse(book.getId(), book.getTitle(), book.getAuthors().stream().map(AuthorResponse::of).collect(Collectors.toSet()), book.getCategories().stream().map(CategoryResponse::of).collect(Collectors.toSet()), LanguageResponse.of(book.getLanguage()), book.getCondition());
}


}