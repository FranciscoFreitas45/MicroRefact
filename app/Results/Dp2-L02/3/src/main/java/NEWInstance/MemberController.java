package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberController {

 private Member member;

 private Member member;


@PutMapping
("/setRequests")
public void setRequests(@RequestParam(name = "requests") List<Request> requests){
member.setRequests(requests);
}


}