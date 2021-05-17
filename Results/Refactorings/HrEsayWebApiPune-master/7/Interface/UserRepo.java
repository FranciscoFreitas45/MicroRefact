public interface UserRepo {

   public int updateAccLoc(List<Integer> empIdList,String loc);
   public int updateUserLoginType(List<Integer> empIdList,String loginType);
}