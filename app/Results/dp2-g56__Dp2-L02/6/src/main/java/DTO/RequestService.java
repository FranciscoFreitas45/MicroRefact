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


public List<Request> findAll(){
    return this.requestRepository.findAll();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))

;
List<Request> aux = restTemplate.getForObject(builder.toUriString(),List<Request>.class);
return aux;
}


}