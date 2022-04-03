package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RequestServiceController {

 private RequestService requestservice;


@PutMapping
("/delete")
public void delete(@RequestParam(name = "request") Request request){
requestservice.delete(request);
}


@GetMapping
("/getRequestsByMember")
public Collection<Request> getRequestsByMember(@RequestParam(name = "member") Member member){
  return requestservice.getRequestsByMember(member);
}


@GetMapping
("/getRequestsByMemberAndStatus")
public Collection<Request> getRequestsByMemberAndStatus(@RequestParam(name = "member") Member member,@RequestParam(name = "status") Status status){
  return requestservice.getRequestsByMemberAndStatus(member,status);
}


@PutMapping
("/deleteRequestAsMember")
public void deleteRequestAsMember(@RequestParam(name = "member") Member member,@RequestParam(name = "requestId") int requestId){
requestservice.deleteRequestAsMember(member,requestId);
}


@GetMapping
("/canRequest")
public boolean canRequest(@RequestParam(name = "member") Member member,@RequestParam(name = "paradeId") int paradeId){
  return requestservice.canRequest(member,paradeId);
}


@PutMapping
("/createRequestAsMember")
public void createRequestAsMember(@RequestParam(name = "member") Member member,@RequestParam(name = "paradeId") int paradeId){
requestservice.createRequestAsMember(member,paradeId);
}


@GetMapping
("/findAll")
public List<Request> findAll(){
  return requestservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return requestservice.isEmpty(Object);
}


}