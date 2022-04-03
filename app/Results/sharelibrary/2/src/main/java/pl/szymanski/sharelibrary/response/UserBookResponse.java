package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.enums.BookStatus;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
public class UserBookResponse {

 private  Long id;

 private  String title;

 private  List<AuthorResponse> authors;

 private  List<CategoryResponse> categories;

 private  BookStatus status;

 private  UserResponse atUser;

 private  LanguageResponse language;

 private  BookCondition condition;


public UserBookResponse of(UserBook user){
    return new UserBookResponse(user.getBook().getId(), user.getBook().getTitle(), user.getBook().getAuthors().stream().map(AuthorResponse::of).collect(Collectors.toList()), user.getBook().getCategories().stream().map(CategoryResponse::of).collect(Collectors.toList()), user.getStatus(), (user.getAtUser() != null) ? UserResponse.of(user.getAtUser()) : null, LanguageResponse.of(user.getBook().getLanguage()), user.getBook().getCondition());
}


}