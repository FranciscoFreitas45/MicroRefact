package sn.controller;
 import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.api.requests.IsFriendsRequest;
import sn.api.response.IsFriendResponse;
import sn.api.response.PersonResponse;
import sn.api.response.ResponseDataMessage;
import sn.api.response.ServiceResponse;
import sn.api.response.ServiceResponseDataList;
import sn.model.Person;
import sn.service.AccountService;
import sn.service.FriendService;
import sn.Interface.AccountService;
@RestController
public class FriendController {

@Autowired
 private  FriendService friendService;

@Autowired
 private  AccountService accountService;


@PostMapping("/friends/{friendId}")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> addFriend(long friendId){
    return friendService.addFriend(accountService.findCurrentUser().getId(), friendId) ? ResponseEntity.ok(new ServiceResponse<>(new ResponseDataMessage("ok"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


@GetMapping("/friends/request")
public ResponseEntity<ServiceResponseDataList<PersonResponse>> getFriendRequestList(String name,int offset,int itemPerPage){
    List<Person> requestList = friendService.getFriendRequestList(accountService.findCurrentUser().getId(), name, offset, itemPerPage);
    return ResponseEntity.ok(new ServiceResponseDataList<>(friendService.getTotalCountOfRequest(accountService.findCurrentUser().getId()), offset, itemPerPage, requestList.stream().map(accountService::getPersonResponse).collect(Collectors.toList())));
}


@GetMapping("/friends/recommendations")
public ResponseEntity<ServiceResponseDataList<PersonResponse>> getFriendRecommendationList(int offset,int itemPerPage){
    List<Person> recommendationList = friendService.getFriendRecommendationList(accountService.findCurrentUser().getId(), accountService.findCurrentUser().getCity(), offset, itemPerPage);
    List<PersonResponse> responseList = new ArrayList<>();
    recommendationList.forEach(p -> responseList.add(accountService.getPersonResponse(p)));
    int total = friendService.getTotalCountOfRecommendationList(accountService.findCurrentUser().getId(), accountService.findCurrentUser().getCity());
    return ResponseEntity.ok(new ServiceResponseDataList<>(total, offset, itemPerPage, responseList));
}


@GetMapping("/friends")
public ResponseEntity<ServiceResponseDataList<PersonResponse>> getFriendList(String name,int offset,int itemPerPage){
    List<Person> friendList = friendService.getFriendList(accountService.findCurrentUser().getId(), name, offset, itemPerPage);
    return ResponseEntity.ok(new ServiceResponseDataList<>(friendService.getFriendsCount(accountService.findCurrentUser().getId()), offset, itemPerPage, friendList.stream().map(accountService::getPersonResponse).collect(Collectors.toList())));
}


@DeleteMapping("/friends/{friendId}")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> deleteFriend(long friendId){
    return friendService.deleteFriend(accountService.findCurrentUser().getId(), friendId) ? ResponseEntity.ok(new ServiceResponse<>(new ResponseDataMessage("ok"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


@PostMapping("/is/friends")
public ResponseEntity<ServiceResponseDataList<IsFriendResponse>> isFriend(IsFriendsRequest request){
    return (request == null || request.getUserIds().size() == 0) ? ResponseEntity.badRequest().body(new ServiceResponseDataList<>("Service unavailable")) : ResponseEntity.ok(new ServiceResponseDataList<>(friendService.isFriend(accountService.findCurrentUser().getId(), request)));
}


}