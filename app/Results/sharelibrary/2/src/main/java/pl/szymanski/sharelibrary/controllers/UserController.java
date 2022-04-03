package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import pl.szymanski.sharelibrary.converters.RequestConverter;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.requests.AssignBookRequest;
import pl.szymanski.sharelibrary.requests.EditUserRequest;
import pl.szymanski.sharelibrary.requests.RemoveBookFromUserRequest;
import pl.szymanski.sharelibrary.requests.UserRequest;
import pl.szymanski.sharelibrary.response.UserResponse;
import pl.szymanski.sharelibrary.response.UserWithoutBooksResponse;
import pl.szymanski.sharelibrary.services.ports.UserService;
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

 private  UserService userService;


@PostMapping("/register")
public ResponseEntity<UserWithoutBooksResponse> add(UserRequest userRequest){
    User user = RequestConverter.userRequestToUser(userRequest);
    return new ResponseEntity<>(UserWithoutBooksResponse.of(userService.saveUser(user)), HttpStatus.CREATED);
}


@PostMapping("/assignment")
public ResponseEntity<UserResponse> assignBook(AssignBookRequest assignBookRequest){
    return new ResponseEntity<>(UserResponse.of(userService.assignBookToUser(assignBookRequest.getUserId(), assignBookRequest.getBookId())), HttpStatus.OK);
}


@PostMapping("/withdrawal")
public ResponseEntity<UserResponse> withdrawBookFromUser(RemoveBookFromUserRequest removeBookFromUserRequest){
    return new ResponseEntity<>(UserResponse.of(userService.withdrawBookFromUser(removeBookFromUserRequest.getUserId(), removeBookFromUserRequest.getBookId())), HttpStatus.OK);
}


@GetMapping("/{id}")
public ResponseEntity<UserWithoutBooksResponse> getUser(Long id){
    return new ResponseEntity<>(UserWithoutBooksResponse.of(userService.getUserById(id)), HttpStatus.OK);
}


@PutMapping("/{id}")
public ResponseEntity<UserWithoutBooksResponse> editUser(Long id,EditUserRequest editUserRequest){
    return new ResponseEntity<>(UserWithoutBooksResponse.of(userService.changeUserDetails(id, editUserRequest)), HttpStatus.OK);
}


}