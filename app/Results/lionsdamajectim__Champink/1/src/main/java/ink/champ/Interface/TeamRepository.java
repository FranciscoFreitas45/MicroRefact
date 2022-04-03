package ink.champ.Interface;
public interface TeamRepository {

   public List<Team> findTeamsByNameContainingIgnoreCase(String search,Sort sort);
   public List<Team> findTeamsByUserRoleAndNotInChamp(Champ champ,User user);
   public List<Team> findTeamsByUserRoleAndPlayerNotIn(Player player,User user);
   public List<Team> findTeamsByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort);
   public List<Team> findTeamsByUserAll(User user,String search);
   public List<Team> findTeamsByUserRole(User user,int role,String search);
   public Object findById(Object Object);
   public Object save(Object Object);
   public Object delete(Object Object);
}