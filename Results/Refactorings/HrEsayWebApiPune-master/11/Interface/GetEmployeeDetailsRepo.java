public interface GetEmployeeDetailsRepo {

   public List<GetEmployeeDetails> getEmpDetailListByBonusIdAssignedBonus(int bonusId,List<Integer> locId);
   public List<GetEmployeeDetails> getEmpDetailListByBonusId(int bonusId,List<Integer> locId);
   public List<GetEmployeeDetails> getEmpDetailList();
}