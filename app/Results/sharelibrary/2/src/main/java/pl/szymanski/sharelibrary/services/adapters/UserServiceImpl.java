package pl.szymanski.sharelibrary.services.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymanski.sharelibrary.converters.RequestConverter;
import pl.szymanski.sharelibrary.entity.Book;
import pl.szymanski.sharelibrary.entity.Coordinates;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.entity.UserBook;
import pl.szymanski.sharelibrary.enums.BookStatus;
import pl.szymanski.sharelibrary.exceptions.auth.InvalidUsernameEmailOrPassword;
import pl.szymanski.sharelibrary.exceptions.books.BookDoesNotExist;
import pl.szymanski.sharelibrary.exceptions.users.EmailAlreadyExist;
import pl.szymanski.sharelibrary.exceptions.users.UserNotFoundById;
import pl.szymanski.sharelibrary.exceptions.users.UsernameAlreadyExists;
import pl.szymanski.sharelibrary.repositories.ports.BookRepository;
import pl.szymanski.sharelibrary.repositories.ports.CoordinatesRepository;
import pl.szymanski.sharelibrary.repositories.ports.UserRepository;
import pl.szymanski.sharelibrary.requests.EditUserRequest;
import pl.szymanski.sharelibrary.requests.LoginRequest;
import pl.szymanski.sharelibrary.security.JwtAuthenticationResponse;
import pl.szymanski.sharelibrary.security.JwtTokenProvider;
import pl.szymanski.sharelibrary.services.ports.UserService;
import pl.szymanski.sharelibrary.utilities.Utils;
import java.util;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

 private  PasswordEncoder passwordEncoder;

 private  UserRepository userRepository;

 private  BookRepository bookRepository;

 private  CoordinatesRepository coordinatesRepository;

 private  AuthenticationManager authenticationManager;

 private  JwtTokenProvider tokenProvider;


@Override
public User getUserByEmailOrUserName(String user){
    return userRepository.getUserByUsernameOrEmail(user, user).orElseThrow(InvalidUsernameEmailOrPassword::new);
}


@Override
public User getUserById(Long id){
    return userRepository.getUserById(id).orElseThrow(() -> new UserNotFoundById(id));
}


@Transactional
@Override
public User withdrawBookFromUser(Long userId,Long bookId){
    User user = getUserById(userId);
    List<UserBook> userBooks = user.getBooks();
    System.out.println(userBooks);
    userBooks.removeIf(it -> it.getBook().getId().equals(bookId));
    user.setBooks(userBooks);
    return user;
}


public User prepareEditedUserDetails(Long id,EditUserRequest editUserRequest){
    User user = userRepository.getUserById(id).orElseThrow(() -> new UserNotFoundById(id));
    if (editUserRequest.getName() != null) {
        user.setName(editUserRequest.getName());
    }
    if (editUserRequest.getSurname() != null) {
        user.setSurname(editUserRequest.getSurname());
    }
    if (editUserRequest.getCoordinates().getLatitude() != null || editUserRequest.getCoordinates().getLongitude() != null) {
        Coordinates coordinatesToEdit = RequestConverter.coordinatesRequestToCoordinates(editUserRequest.getCoordinates());
        Coordinates coordinates = checkIfCoordinatesExist(coordinatesToEdit).orElse(coordinatesToEdit);
        user.setCoordinates(coordinates);
    }
    return user;
}


@Override
public Optional<User> getUserByEmail(String email){
    return userRepository.getUserByEmail(email);
}


public Optional<Coordinates> checkIfCoordinatesExist(Coordinates coordinates){
    return coordinatesRepository.findByLatitudeAndLongitude(coordinates.getLatitude(), coordinates.getLongitude());
}


@Override
@Transactional
public User changeUserDetails(Long id,EditUserRequest editUserRequest){
    User user = prepareEditedUserDetails(id, editUserRequest);
    return userRepository.saveUser(user);
}


@Override
public List<User> getUsersWithBooksWhereAtUserIs(Long userId){
    Set<User> result = new HashSet<>();
    List<User> users = userRepository.getUsers();
    users.forEach(user -> {
        if (user.getBooks() != null && !user.getBooks().isEmpty()) {
            for (UserBook userBook : user.getBooks()) {
                if (userBook.getAtUser() != null && userBook.getAtUser().getId().equals(userId)) {
                    result.add(user);
                    break;
                }
            }
        }
    });
    result.forEach(user -> {
        List<UserBook> books = (user.getBooks().stream().filter(ub -> ub.getAtUser() != null && ub.getAtUser().getId().equals(userId)).collect(Collectors.toList()));
        user.setBooks(books);
    });
    return new ArrayList<>(result);
}


public void validateUser(User user){
    if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
        throw new EmailAlreadyExist(user.getEmail());
    } else if (userRepository.getUserByUsername(user.getUsername()).isPresent()) {
        throw new UsernameAlreadyExists(user.getUsername());
    }
    Utils.validateEmailAddress(user.getEmail());
}


@Override
public Set<User> getUsers(){
    return new HashSet<>(userRepository.getUsers());
}


@Override
public JwtAuthenticationResponse getJwt(LoginRequest loginRequest){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserNameOrEmail(), String.valueOf(loginRequest.getPassword())));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return new JwtAuthenticationResponse(jwt);
}


@Override
@Transactional
public User assignBookToUser(Long userId,Long bookId){
    User user = userRepository.getUserById(userId).orElseThrow(() -> new UserNotFoundById(userId));
    Book book = bookRepository.getBookById(bookId).orElseThrow(() -> new BookDoesNotExist(bookId));
    List<UserBook> usersBooks = new ArrayList<UserBook>();
    if (user.getBooks() != null) {
        usersBooks = user.getBooks();
    }
    UserBook userBook = new UserBook(user, book, BookStatus.AT_OWNER, null);
    usersBooks.add(userBook);
    user.setBooks(usersBooks);
    return userRepository.saveUser(user);
}


@Override
@Transactional
public User saveUser(User user){
    validateUser(user);
    user.setPassword(passwordEncoder.encode(String.valueOf(user.getPassword())).toCharArray());
    Optional<Coordinates> coordinates = checkIfCoordinatesExist(user.getCoordinates());
    coordinates.ifPresent(user::setCoordinates);
    return userRepository.saveUser(user);
}


}