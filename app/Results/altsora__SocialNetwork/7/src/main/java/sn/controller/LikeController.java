package sn.controller;
 import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.LikeRequest;
import sn.api.response;
import sn.model.Person;
import sn.model.enums.LikeType;
import sn.repositories.PersonRepository;
import sn.service.AccountService;
import sn.service.LikeService;
import java.util.List;
import java.util.Optional;
import sn.Interface.AccountService;
@RestController
public class LikeController {

@Autowired
 private  AccountService accountService;

@Autowired
 private  LikeService likeService;

@Autowired
 private  PersonRepository personRepository;


@GetMapping("/liked")
public ResponseEntity<Boolean> userHasLiked(long personId,long itemId,LikeType type){
    Person person = personRepository.findById(personId).orElse(null);
    if (person == null) {
        return ResponseEntity.badRequest().body(false);
    }
    Boolean likes = likeService.likeExists(person, itemId, type);
    return ResponseEntity.ok(likes);
}


@PutMapping("/likes")
public ResponseEntity<?> putLike(LikeRequest lk){
    boolean result = likeService.putLike(accountService.findCurrentUser(), lk.getItemId(), lk.getType());
    if (result) {
        List<Long> usersId = likeService.getUsersOfLike(lk.getItemId(), lk.getType());
        return ResponseEntity.ok(new LikeCountResponse(usersId.size(), usersId));
    } else {
        return ResponseEntity.badRequest().body("User have like on this item");
    }
}


@GetMapping("/likes")
public ResponseEntity<LikeCountResponse> getLikes(long itemId,LikeType type){
    List<Long> usersId = likeService.getUsersOfLike(itemId, type);
    return ResponseEntity.ok(new LikeCountResponse(usersId.size(), usersId));
}


@DeleteMapping("/likes")
public ResponseEntity<LikeCountResponse> removeLike(long itemId,LikeType type){
    likeService.removeLike(accountService.findCurrentUser(), itemId, type);
    List<Long> usersId = likeService.getUsersOfLike(itemId, type);
    return ResponseEntity.ok(new LikeCountResponse(usersId.size(), usersId));
}


}