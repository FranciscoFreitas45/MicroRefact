package sn.service;
 import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.api.requests.PersonEditRequest;
import sn.api.requests.WallPostRequest;
import sn.api.response;
import sn.model.Person;
import sn.model.Post;
import sn.model.dto.account.UserRegistrationRequest;
import sn.repositories.PersonRepository;
import sn.utils.ErrorUtil;
import sn.utils.TimeUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import sn.Interface.PersonRepository;
import sn.Interface.PostService;
import sn.Interface.CommentService;
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

 private  PersonRepository personRepository;

 private  PasswordEncoder passwordEncoder;

 private  Authentication authentication;

 private  MailSenderService mailSenderService;

 private  PostService postService;

 private  CommentService commentService;

@Value("${user.permissions.image}")
 private  String userImagePermissions;

@PersistenceContext
 private  EntityManager entityManager;


public ResponseEntity<ServiceResponse<AbstractResponse>> getCurrentUser(){
    Person person = findCurrentUser();
    if (person == null) {
        log.error("Unauthorized access");
        return ErrorUtil.unauthorized();
    }
    return ResponseEntity.ok(new ServiceResponse<>(getPersonResponse(person)));
}


public Predicate[] createPredicateArray(CriteriaBuilder criteriaBuilder,Root<Person> person,String firstName,String lastName,String city,String country,Integer ageFrom,Integer ageTo){
    Set<Predicate> predicateSet = new HashSet<>();
    LocalDate today = LocalDate.now();
    if (Strings.isNotEmpty(firstName)) {
        predicateSet.add(criteriaBuilder.like(criteriaBuilder.lower(person.get("firstName")), "%" + firstName.toLowerCase() + "%"));
    }
    if (Strings.isNotEmpty(lastName)) {
        predicateSet.add(criteriaBuilder.like(criteriaBuilder.lower(person.get("lastName")), "%" + lastName.toLowerCase() + "%"));
    }
    if (Strings.isNotEmpty(city)) {
        predicateSet.add(criteriaBuilder.like(criteriaBuilder.lower(person.get("city")), "%" + city.toLowerCase() + "%"));
    }
    if (Strings.isNotEmpty(country)) {
        predicateSet.add(criteriaBuilder.like(criteriaBuilder.lower(person.get("country")), "%" + country.toLowerCase() + "%"));
    }
    if (ageFrom != null && ageFrom > 0) {
        predicateSet.add(criteriaBuilder.lessThanOrEqualTo(person.get("birthDate"), today.minusYears(ageFrom)));
    }
    if (ageTo != null && ageTo > 0) {
        predicateSet.add(criteriaBuilder.greaterThanOrEqualTo(person.get("birthDate"), today.minusYears(ageTo)));
    }
    return predicateSet.toArray(new Predicate[] {});
}


public ResponseEntity<ServiceResponse<AbstractResponse>> getUserById(long personId){
    Person person = personRepository.findById(personId).orElse(null);
    if (person == null) {
        log.error("User with ID = {} not found", personId);
        return ErrorUtil.badRequest(String.format("User with ID = %s not found", personId));
    }
    return ResponseEntity.ok(new ServiceResponse<>(getPersonResponse(person)));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> changeUserLockStatus(long personId){
    Person person = personRepository.findById(personId).orElse(null);
    if (person == null) {
        log.warn("User with id {} do not exist. Lock status didn't changed", personId);
        return ErrorUtil.badRequest(String.format("User with ID = %s not found", personId));
    }
    person.setBlocked(!person.isBlocked());
    personRepository.saveAndFlush(person);
    log.info("User with id {} changed lock status", person.getId());
    return ResponseEntity.ok(new ServiceResponse<>(ResponseDataMessage.ok()));
}


public InetAddress getIpAddress(){
    try {
        return InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
        log.error("Can't get ip address.");
        e.printStackTrace();
    }
    return null;
}


public ResponseEntity<ServiceResponseDataList<WallPostResponse>> getWallPosts(long personId,int offset,int itemPerPage){
    Person person = personRepository.findById(personId).orElse(null);
    if (person == null) {
        log.error("User with ID = {} not found", personId);
        return ResponseEntity.badRequest().body(new ServiceResponseDataList<>("User with ID = " + personId + " not found"));
    }
    List<WallPostResponse> wallPosts = new ArrayList<>();
    List<Post> posts = postService.findAllByPersonId(personId, offset, itemPerPage);
    PersonResponse author = getPersonResponse(person);
    for (Post post : posts) {
        List<CommentResponse> comments = commentService.getCommentsByPostId(post.getId());
        WallPostResponse wallPostResponse = postService.getExistsWallPost(post, author, comments);
        wallPosts.add(wallPostResponse);
    }
    int total = postService.getTotalCountPostsByPersonId(personId);
    return ResponseEntity.ok(new ServiceResponseDataList<>(total, offset, itemPerPage, wallPosts));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> editUser(PersonEditRequest personEditRequest){
    Person person = findCurrentUser();
    if (person == null) {
        log.error("Unauthorized access");
        return ErrorUtil.unauthorized();
    }
    person.setFirstName(personEditRequest.getFirstName());
    person.setLastName(personEditRequest.getLastName());
    person.setBirthDate(OffsetDateTime.parse(personEditRequest.getBirthDate()).toLocalDate());
    person.setPhone(personEditRequest.getPhone());
    if (Strings.isNotEmpty(personEditRequest.getPhoto())) {
        person.setPhoto(personEditRequest.getPhoto());
    }
    person.setAbout(personEditRequest.getAbout());
    person.setCity(personEditRequest.getCity());
    person.setCountry(personEditRequest.getCountry());
    person.setMessagesPermission(Strings.isNotEmpty(personEditRequest.getMessagesPermission()) ? personEditRequest.getMessagesPermission() : userImagePermissions);
    person = personRepository.saveAndFlush(person);
    log.info("Update data for user with id {}.", person.getId());
    return ResponseEntity.ok(new ServiceResponse<>(getPersonResponse(person)));
}


public List<Person> searchUsersWithParameters(String firstName,String lastName,String city,String country,Integer ageFrom,Integer ageTo,Pageable pageable){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
    Root<Person> person = criteriaQuery.from(Person.class);
    criteriaQuery.where(createPredicateArray(criteriaBuilder, person, firstName, lastName, city, country, ageFrom, ageTo));
    TypedQuery<Person> searchQuery = entityManager.createQuery(criteriaQuery);
    searchQuery.setFirstResult(pageable.first().getPageNumber());
    searchQuery.setMaxResults(pageable.getPageSize());
    return searchQuery.getResultList();
}


public ResponseEntity<ServiceResponse<AbstractResponse>> addWallPost(long personId,Long publishDate,WallPostRequest wallPostRequest){
    Person person = personRepository.findById(personId).orElse(null);
    if (person == null) {
        log.error("User with ID = {} not found", personId);
        return ErrorUtil.badRequest(String.format("User with ID = %s not found", personId));
    }
    LocalDateTime postTime = publishDate != null ? TimeUtil.getLocalDateTimeFromTimestamp(publishDate) : TimeUtil.now();
    postTime = TimeUtil.beforeNow(postTime) ? TimeUtil.now() : postTime;
    String title = wallPostRequest.getTitle();
    String text = wallPostRequest.getPostText();
    Post post = postService.addPost(person, title, text, postTime);
    PersonResponse author = getPersonResponse(person);
    WallPostResponse newPost = postService.createNewWallPost(post, author);
    return ResponseEntity.ok(new ServiceResponse<>(newPost));
}


public String generateNewPassword(int length){
    int leftLimit = 48;
    int rightLimit = 122;
    Random random = new Random();
    return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
}


@Transactional
public boolean setNewPassword(String password){
    Optional<Person> personOpt = personRepository.findByEmail(authentication.getName());
    if (personOpt.isEmpty()) {
        log.warn("Person not found by email [{}].", authentication.getName());
        return false;
    }
    Person person = personOpt.get();
    person.setPassword(passwordEncoder.encode(password));
    personRepository.save(person);
    log.info("Person password successfully recovered.");
    CompletableFuture.runAsync(() -> mailSenderService.send(person.getEmail(), "Password recovery", new StringBuffer("Your password has been changed successfully from ").append(getIpAddress()).append(" at ").append(LocalDateTime.now()).toString()));
    return true;
}


public PersonResponse getPersonResponse(Person person){
    // TODO: Нет данных, откуда берутся город и страна. Если логика стран и городов заработает - изменить соответствующие поля
    return PersonResponse.builder().id(person.getId()).firstName(person.getFirstName()).lastName(person.getLastName()).regDate(TimeUtil.getTimestampFromLocalDateTime(person.getRegDate())).birthDate(TimeUtil.getTimestampFromLocalDate(person.getBirthDate())).email(person.getEmail()).phone(person.getPhone()).photo(person.getPhoto()).about(person.getAbout()).city(person.getCity()).country(person.getCountry()).messagesPermission(person.getMessagesPermission()).lastOnlineTime(TimeUtil.getTimestampFromLocalDateTime(person.getLastOnlineTime())).isBlocked(person.isBlocked()).build();
}


public boolean recoveryPassword(String email){
    Optional<Person> personOpt = personRepository.findByEmail(email);
    if (personOpt.isEmpty()) {
        log.warn("Person [{}] not found for password recovery.", email);
        return false;
    }
    Person person = personOpt.get();
    String newPassword = generateNewPassword(9);
    person.setPassword(passwordEncoder.encode(newPassword));
    personRepository.save(person);
    log.info("New password set to the person.");
    CompletableFuture.runAsync(() -> mailSenderService.send(person.getEmail(), "Password recovery", "Your new password: " + newPassword));
    return true;
}


public ResponseEntity<ServiceResponse<AbstractResponse>> deleteUser(){
    Person person = findCurrentUser();
    if (person == null) {
        log.error("Unauthorized access");
        return ErrorUtil.unauthorized();
    }
    personRepository.deleteById(person.getId());
    log.info("User with ID {} was deleted", person.getId());
    return ResponseEntity.ok(new ServiceResponse<>(ResponseDataMessage.ok()));
}


public boolean exists(long personId){
    return personRepository.existsById(personId);
}


public ResponseEntity<ServiceResponseDataList<PersonResponse>> findUsers(String firstName,String lastName,String city,String country,Integer ageFrom,Integer ageTo,Integer offset,Integer itemPerPage){
    Pageable pageable = PageRequest.of(offset / itemPerPage, itemPerPage);
    List<Person> personList = this.searchUsersWithParameters(firstName, lastName, city, country, ageFrom, ageTo, pageable);
    List<PersonResponse> searchResult = personList.stream().map(this::getPersonResponse).collect(Collectors.toList());
    int total = personRepository.getTotalCountUsers();
    return ResponseEntity.ok(new ServiceResponseDataList<>(total, offset, itemPerPage, searchResult));
}


@Transactional
public boolean changeEmail(String newEmail){
    Optional<Person> personOpt = personRepository.findByEmail(authentication.getName());
    if (personOpt.isEmpty()) {
        log.warn("Person not found by email [{}].", authentication.getName());
        return false;
    }
    Person person = personOpt.get();
    person.setEmail(newEmail);
    personRepository.save(person);
    log.info("Person email successfully changed.");
    return true;
}


public Person findCurrentUser(){
    if (authentication instanceof AnonymousAuthenticationToken) {
        log.warn("Anonymous user authenticated");
        return null;
    }
    Optional<Person> personOpt = personRepository.findByEmail(authentication.getName());
    if (personOpt.isEmpty()) {
        log.warn("Person not found by email [{}].", authentication.getName());
        return null;
    }
    return personOpt.get();
}


public boolean register(UserRegistrationRequest userRegistrationRequest){
    if (!userRegistrationRequest.getPasswd1().equals(userRegistrationRequest.getPasswd2())) {
        log.warn("Passwords mismatch. Registration cancelled.");
        return false;
    }
    if (personRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent()) {
        log.error("User [{}] is exists.", userRegistrationRequest.getEmail());
        return false;
    }
    Person person = new Person();
    person.setFirstName(userRegistrationRequest.getFirstName());
    person.setLastName(userRegistrationRequest.getLastName());
    person.setPassword(passwordEncoder.encode(userRegistrationRequest.getPasswd1()));
    person.setEmail(userRegistrationRequest.getEmail());
    person.setMessagesPermission(userImagePermissions);
    personRepository.save(person);
    log.info("Person successfully registered");
    return true;
}


}