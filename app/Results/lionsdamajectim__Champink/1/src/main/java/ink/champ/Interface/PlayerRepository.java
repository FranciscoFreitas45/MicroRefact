package ink.champ.Interface;
public interface PlayerRepository {

   public List<Player> findPlayersByNameContainingIgnoreCase(String search,Sort sort);
   public List<Player> findPlayersByUserRoleAndNotInTeam(Team team,User user);
   public List<Player> findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort);
   public List<Player> findPlayersByUserAll(User user,String search);
   public List<Player> findPlayersByUserRole(User user,int role,String search);
   public Object findById(Object Object);
   public Object save(Object Object);
   public Object delete(Object Object);
}