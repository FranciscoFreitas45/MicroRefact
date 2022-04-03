package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.User;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Data
public class UserResponse {

 private  Long id;

 private  String email;

 private  String username;

 private  String name;

 private  String surname;

 private  CoordinatesResponse coordinates;

 private  List<BookWithoutUsersResponse> books;


public UserResponse of(User user){
    return new UserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getSurname(), CoordinatesResponse.of(user.getCoordinates()), user.getBooks().stream().map(userBook -> BookWithoutUsersResponse.of(userBook.getBook())).collect(Collectors.toList()));
}


}