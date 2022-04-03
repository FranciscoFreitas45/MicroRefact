package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.requests.EditUserRequest;
import pl.szymanski.sharelibrary.requests.LoginRequest;
import pl.szymanski.sharelibrary.requests.RemoveBookFromUserRequest;
import pl.szymanski.sharelibrary.requests.UserRequest;
import pl.szymanski.sharelibrary.response.UserResponse;
import java.util.Arrays;
import java.util.List;
public class UserGenerator {


public RemoveBookFromUserRequest getRemoveBookFromUserRequest(){
    return new RemoveBookFromUserRequest(1L, 1L);
}


public UserResponse getUserResponse(){
    return UserResponse.of(getUser());
}


public User getUser(){
    User user = new User();
    user.setId(1L);
    user.setName("John");
    user.setSurname("Dee");
    user.setUsername("johnDee");
    user.setEmail("john@dee.com");
    user.setPassword(new char[] { 'z', 'a', 'q', '1', '@', 'W', 'S', 'X' });
    user.setCoordinates(CoordinatesGenerator.getCoordinates());
    return user;
}


public LoginRequest getLoginRequest(){
    return new LoginRequest("z", new char[] { 'z' });
}


public EditUserRequest getEditUserRequest(){
    return new EditUserRequest("William", "Johnson", CoordinatesGenerator.getCoordinatesRequest());
}


public UserRequest getUserRequest(){
    UserRequest userRequest = new UserRequest();
    userRequest.setName("John");
    userRequest.setSurname("Dee");
    userRequest.setUsername("johnDee");
    userRequest.setEmail("john@dee.com");
    userRequest.setPassword(new char[] { 'z', 'a', 'q', '1', '@', 'W', 'S', 'X' });
    userRequest.setCoordinates(CoordinatesGenerator.getCoordinatesRequest());
    return userRequest;
}


public User getUserWithBooks(){
    User user = getUser();
    UserBook userBook = new UserBook();
    userBook.setBook(BookGenerator.getBook());
    userBook.setUser(user);
    List<UserBook> userBooks = Arrays.asList(userBook);
    user.setBooks(userBooks);
    return user;
}


}