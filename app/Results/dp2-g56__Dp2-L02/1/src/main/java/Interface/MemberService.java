package Interface;
public interface MemberService {

   public Member loggedMember();
   public Member reconstruct(Member member,BindingResult binding);
   public Member updateMember(Member member);
}