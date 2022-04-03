package Interface;
public interface BrotherhoodService {

   public Brotherhood findOne(int id);
   public List<Brotherhood> findAll();
   public List<Parade> getParadesByBrotherhoodFinal(Brotherhood b);
   public List<Member> getMembersOfBrotherhood(Brotherhood bro);
   public List<Float> getFloatsByBrotherhood(Brotherhood b);
   public int getBrotherhoodIdByLegalRecord(int legalRecordId);
   public int getBrotherhoodIdByInceptionRecord(int inceptionRecordId);
   public int getBrotherhoodIdByPeriodRecord(int periodRecordId);
   public List<Brotherhood> getBrotherhoodsByArea(Integer areaId);
   public Brotherhood loggedBrotherhood();
   public Brotherhood reconstructBrotherhood(Brotherhood brotherhood,BindingResult binding);
   public Brotherhood save(Brotherhood h);
   public Brotherhood addPicture(String picture,Brotherhood brotherhood);
   public void loggedAsBrotherhood();
}