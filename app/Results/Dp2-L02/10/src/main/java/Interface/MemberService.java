package Interface;
public interface MemberService {

   public Member loggedMember();
   public Member save(Member member);
   public void loggedAsMember();
   public List<Member> findAll();
   public Member findOne(int id);
}