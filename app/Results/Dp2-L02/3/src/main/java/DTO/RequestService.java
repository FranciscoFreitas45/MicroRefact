package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.RequestRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Brotherhood;
import domain.Member;
import domain.Message;
import domain.Parade;
import domain.ParadeStatus;
import domain.Request;
import domain.Status;
import Interface.MemberService;
import Interface.ActorService;
import Interface.MessageService;
import DTO.UserAccount;
import DTO.ActorService;
import DTO.MessageService;
import DTO.MemberService;
public class RequestService {

 private  RequestRepository requestRepository;

 private  MemberService memberService;

 private  ParadeService paradeService;

 private  Validator validator;

 private  BrotherhoodService brotherhoodService;

 private  ActorService actorService;

 private  MessageService messageService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Collection<Request> getRequestApprovedByBrotherhoodAndParade(Brotherhood brotherhood,Parade parade){
    return this.requestRepository.getRequestApprovedByBrotherhoodAndParade(brotherhood, parade);
}


public Request getRequestByBrotherhoodAndRequestId(Brotherhood brotherhood,Request request){
    return this.requestRepository.getRequestByBrotherhoodAndRequestId(brotherhood, request);
}


public Collection<Request> getRequestsByBrotherhoodAndStatus(Brotherhood brotherhood,Status status){
    return this.requestRepository.getRequestsByBrotherhoodAndStatus(brotherhood, status);
}


public Collection<Request> getRequestsByMember(Member member){
    return this.requestRepository.getRequestsByMember(member);
}


public List<Request> getRequestsByParadeAndStatus(Parade parade,Status status){
    return this.requestRepository.getRequestsByParadeAndStatus(parade, status);
}


public List<Integer> getFreePosition(Request request){
    List<Integer> position = new ArrayList<>();
    Parade parade = request.getParade();
    Brotherhood brotherhood = this.brotherhoodService.securityAndBrotherhood();
    List<Request> requests = (List<Request>) this.getRequestApprovedByBrotherhoodAndParade(brotherhood, parade);
    List<String> occupedPositions = new ArrayList<>();
    for (Request r : requests) if (r.getRowNumber() > 0 && r.getColumnNumber() > 0)
        occupedPositions.add(r.getRowNumber() + "-" + r.getColumnNumber());
    Integer row = 0;
    Integer col = 0;
    if (occupedPositions.size() != 0) {
        List<String> allPositions = new ArrayList<>();
        for (int i = 1; i <= parade.getRowNumber(); i++) for (int j = 1; j <= parade.getColumnNumber(); j++) allPositions.add(i + "-" + j);
        for (String all : allPositions) if (!occupedPositions.contains(all)) {
            String[] poss = all.split("-");
            row = new Integer(poss[0]);
            col = new Integer(poss[1]);
            break;
        }
    } else {
        row = 1;
        col = 1;
    }
    position.add(row);
    position.add(col);
    return position;
}


public Collection<Request> getRequestsByMemberAndStatus(Member member,Status status){
    return this.requestRepository.getRequestsByMemberAndStatus(member, status);
}


public Collection<Request> getRequestsByBrotherhood(Brotherhood brotherhood){
    return this.requestRepository.getRequestsByBrotherhood(brotherhood);
}


public void delete(Request request){
    this.requestRepository.delete(request);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))

.queryParam("request",request)
;
restTemplate.put(builder.toUriString(),null);
}


public boolean canRequest(Member member,int paradeId){
    boolean res = true;
    Parade parade = this.paradeService.findOne(paradeId);
    List<Request> requests = parade.getRequests();
    if (parade.getIsDraftMode() == true)
        res = false;
    if (res == true)
        for (Request r : requests) if (r.getMember().equals(member)) {
            res = false;
            break;
        }
    return res;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/canRequest"))

.queryParam("member",member)
.queryParam("paradeId",paradeId)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void createRequestAsMember(Member member,int paradeId){
    Parade parade = this.paradeService.findOne(paradeId);
    List<Request> requests = parade.getRequests();
    Assert.isTrue(parade.getIsDraftMode() == false && parade.getParadeStatus().equals(ParadeStatus.ACCEPTED));
    for (Request r : requests) Assert.isTrue(!r.getMember().equals(member));
    Request newRequest = this.createRequest(member, parade);
    Request saveRequest = this.save(newRequest);
    List<Request> requests2 = member.getRequests();
    requests2.add(saveRequest);
    member.setRequests(requests2);
    this.memberService.save(member);
    List<Request> requests3 = parade.getRequests();
    requests3.add(saveRequest);
    parade.setRequests(requests3);
    this.paradeService.save(parade);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createRequestAsMember"))

.queryParam("member",member)
.queryParam("paradeId",paradeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void deleteRequestAsMember(Member member,int requestId){
    Request request = this.findOne(requestId);
    Assert.isTrue(this.getRequestsByMember(member).contains(request));
    Assert.isTrue(request.getStatus().equals(Status.PENDING));
    Parade parade = request.getParade();
    List<Request> requests = parade.getRequests();
    requests.remove(request);
    parade.setRequests(requests);
    this.paradeService.save(parade);
    List<Request> requests2 = member.getRequests();
    requests2.remove(request);
    member.setRequests(requests2);
    this.memberService.save(member);
    this.delete(request);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteRequestAsMember"))

.queryParam("member",member)
.queryParam("requestId",requestId)
;
restTemplate.put(builder.toUriString(),null);
}


}