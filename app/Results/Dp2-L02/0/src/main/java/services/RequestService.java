package services;
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
@Service
@Transactional
public class RequestService {

@Autowired
 private  RequestRepository requestRepository;

@Autowired
 private  MemberService memberService;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  Validator validator;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  ActorService actorService;

@Autowired
 private  MessageService messageService;


public void sendMessagesToActorsInvolved(Request request){
    // Messages
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    String userName = userAccount.getUsername();
    Actor brotherhood = this.actorService.getActorByUsername(userAccount.getUsername());
    String statusES = "";
    String statusEN = "";
    if (request.getStatus().equals(Status.APPROVED)) {
        statusES = "APROBADA";
        statusEN = "APPROVED";
    } else {
        statusES = "RECHAZADA";
        statusEN = "REJECTED";
    }
    String subject = "Request updated: " + statusEN + " / Solicitud actualizada: " + statusES;
    String body = "Request associated to the parade: " + request.getParade().getTicker() + ", " + request.getParade().getTitle() + " has been updated" + " / " + "Solicitud asociada al desfile: " + request.getParade().getTicker() + ", " + request.getParade().getTitle() + " ha sido actualizada";
    Message messageB = this.messageService.createNotification(subject, body, "NEUTRAL", "Notification, Request", brotherhood);
    Message messageM = this.messageService.createNotification(subject, body, "NEUTRAL", "Notification, Request", request.getMember());
    this.messageService.sendMessageAnotherSender(messageB);
    this.messageService.sendMessageAnotherSender(messageM);
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
}


public Request createRequest(Member member,Parade parade){
    Request res = new Request();
    res.setStatus(Status.PENDING);
    res.setColumnNumber(null);
    res.setRowNumber(null);
    res.setReasonDescription(null);
    res.setMember(member);
    res.setParade(parade);
    return res;
}


public Collection<Request> getRequestApprovedByBrotherhoodAndParade(Brotherhood brotherhood,Parade parade){
    return this.requestRepository.getRequestApprovedByBrotherhoodAndParade(brotherhood, parade);
}


public Request findOne(int id){
    return this.requestRepository.findOne(id);
}


public Request save(Request request){
    return this.requestRepository.save(request);
}


public Request getRequestByBrotherhoodAndRequestId(Brotherhood brotherhood,Request request){
    return this.requestRepository.getRequestByBrotherhoodAndRequestId(brotherhood, request);
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
}


public List<Request> findAll(){
    return this.requestRepository.findAll();
}


public void delete(Request request){
    this.requestRepository.delete(request);
}


public void deleteAllRequestParadesBrotherhood(Parade p){
    this.brotherhoodService.loggedAsBrotherhood();
    List<Request> requestToDelete = p.getRequests();
    List<Member> members = this.memberService.findAll();
    p.setRequests(new ArrayList<Request>());
    for (Request re : requestToDelete) {
        Member me = re.getMember();
        me.getRequests().remove(re);
        p.getRequests().remove(re);
        re.setMember(null);
        re.setParade(null);
        this.delete(re);
    }
/*
		 * for (Member m : members) {
		 * List<Request> requestOfMember = m.getRequests();
		 * requestOfMember.removeAll(requestToDelete);
		 * m.setRequests(requestOfMember);
		 * this.memberService.save(m);
		 * }
		 */
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


public void deleteInBatch(List<Request> requests){
    this.requestRepository.deleteInBatch(requests);
}


public void flush(){
    this.requestRepository.flush();
}


public Collection<Request> getRequestsByMemberAndStatus(Member member,Status status){
    return this.requestRepository.getRequestsByMemberAndStatus(member, status);
}


public Request reconstructRequest(Request request,BindingResult binding){
    this.brotherhoodService.securityAndBrotherhood();
    Request result = this.requestRepository.findOne(request.getId());
    Boolean approved;
    if (result.getStatus().equals(Status.APPROVED))
        approved = true;
    else
        approved = false;
    Request result2 = new Request();
    result2.setId(result.getId());
    result2.setMember(result.getMember());
    result2.setParade(result.getParade());
    result2.setVersion(result.getVersion());
    Integer col = request.getColumnNumber();
    Integer row = request.getRowNumber();
    if (approved == false) {
        if (request.getStatus().equals(Status.APPROVED)) {
            result2.setColumnNumber(col);
            result2.setRowNumber(row);
            result2.setReasonDescription(null);
            result2.setStatus(request.getStatus());
        } else if (request.getStatus().equals(Status.REJECTED)) {
            result2.setColumnNumber(null);
            result2.setRowNumber(null);
            result2.setReasonDescription(request.getReasonDescription());
            result2.setStatus(request.getStatus());
        } else {
            result2.setColumnNumber(null);
            result2.setRowNumber(null);
            result2.setReasonDescription(null);
            result2.setStatus(request.getStatus());
        }
    } else {
        result2.setStatus(result.getStatus());
        result2.setReasonDescription(result.getReasonDescription());
        result2.setColumnNumber(col);
        result2.setRowNumber(row);
    }
    this.validator.validate(result, binding);
    return result2;
}


public Collection<Request> getRequestsByBrotherhood(Brotherhood brotherhood){
    return this.requestRepository.getRequestsByBrotherhood(brotherhood);
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
}


public Request saveRequestWithPreviousChecking(Request request){
    Request requestSaved;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood logguedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<Request> brotherhoodRequest = (List<Request>) this.getRequestsByBrotherhood(logguedBrotherhood);
    Assert.isTrue(brotherhoodRequest.contains(request));
    if (request.getStatus().equals(Status.APPROVED)) {
        Assert.notNull(request.getColumnNumber());
        Assert.notNull(request.getRowNumber());
        Parade parade = request.getParade();
        Collection<Request> requests = parade.getRequests();
        Integer col = request.getColumnNumber();
        Integer row = request.getRowNumber();
        Request requestFound = this.findOne(request.getId());
        if (request.getColumnNumber() != null && request.getRowNumber() != null)
            requests.remove(requestFound);
        Boolean isFree = true;
        for (Request req : requests) if (req.getColumnNumber() == col && req.getRowNumber() == row) {
            isFree = false;
            break;
        }
        Boolean respectMaxAndMin = col <= parade.getColumnNumber() && row <= parade.getRowNumber() && col >= 1 && row >= 1;
        Assert.isTrue(isFree && respectMaxAndMin);
    } else if (request.getStatus().equals(Status.REJECTED)) {
        Assert.notNull(request.getReasonDescription());
        Assert.isTrue(!request.getReasonDescription().trim().equals(""));
    }
    requestSaved = this.save(request);
    this.sendMessagesToActorsInvolved(requestSaved);
    return requestSaved;
}


}