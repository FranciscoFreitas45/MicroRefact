package Interface;
public interface MemberService {

   public Member createMember();
   public Member reconstruct(Member member,BindingResult binding);
   public Member saveCreate(Member member);
}