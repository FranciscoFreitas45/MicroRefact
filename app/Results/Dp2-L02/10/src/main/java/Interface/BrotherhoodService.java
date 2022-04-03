package Interface;
public interface BrotherhoodService {

   public Brotherhood save(Brotherhood h);
   public Brotherhood securityAndBrotherhood();
   public Brotherhood loggedBrotherhood();
   public void loggedAsBrotherhood();
   public Brotherhood findOne(int id);
   public List<Member> getMembersOfBrotherhood(Brotherhood bro);
   public List<String> getPositions();
   public Enrolment getEnrolment(Member m);
   public List<Enrolment> getPengingEnrolments();
}