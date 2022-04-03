package Interface;
public interface RequestService {

   public void delete(Request request);
   public Collection<Request> getRequestsByMember(Member member);
   public Collection<Request> getRequestsByMemberAndStatus(Member member,Status status);
   public void deleteRequestAsMember(Member member,int requestId);
   public boolean canRequest(Member member,int paradeId);
   public void createRequestAsMember(Member member,int paradeId);
}