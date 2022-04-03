package sn.controller;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.PersonEditRequest;
import sn.api.requests.WallPostRequest;
import sn.api.response;
import sn.service.AccountService;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProfileController {

 private  AccountService accountService;


@GetMapping("/me")
public ResponseEntity<ServiceResponse<AbstractResponse>> getCurrentUser(){
    return accountService.getCurrentUser();
}


@PostMapping("/{id}/wall")
public ResponseEntity<ServiceResponse<AbstractResponse>> addWallPost(long personId,Long publishDate,WallPostRequest wallPostRequest){
    return accountService.addWallPost(personId, publishDate, wallPostRequest);
}


@GetMapping("/{id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> getUserById(long personId){
    return accountService.getUserById(personId);
}


@GetMapping("/{id}/wall")
public ResponseEntity<ServiceResponseDataList<WallPostResponse>> getWallPosts(long personId,int offset,int itemPerPage){
    return accountService.getWallPosts(personId, offset, itemPerPage);
}


@DeleteMapping("/me")
public ResponseEntity<ServiceResponse<AbstractResponse>> deleteCurrentUser(){
    return accountService.deleteUser();
}


@DeleteMapping("/block/{id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> unblockUserById(long personId){
    return accountService.changeUserLockStatus(personId);
}


@PutMapping("/me")
public ResponseEntity<ServiceResponse<AbstractResponse>> editCurrentUser(PersonEditRequest personEditRequest){
    return accountService.editUser(personEditRequest);
}


@GetMapping("/search")
public ResponseEntity<ServiceResponseDataList<PersonResponse>> findUsers(String firstName,String lastName,String city,String country,Integer ageFrom,Integer ageTo,Integer offset,Integer itemPerPage){
    return accountService.findUsers(firstName, lastName, city, country, ageFrom, ageTo, offset, itemPerPage);
}


@PutMapping("/block/{id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> blockUserById(long personId){
    return accountService.changeUserLockStatus(personId);
}


}