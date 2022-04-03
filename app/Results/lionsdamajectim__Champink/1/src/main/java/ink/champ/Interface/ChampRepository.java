package ink.champ.Interface;
public interface ChampRepository {

   public List<Champ> findChampsByNameContainingIgnoreCase(String search,Sort sort);
   public List<Champ> findChampsByUserRoleAndTeamNotIn(Team team,User user);
   public List<Champ> findChampsByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort);
   public List<Champ> findChampsByUserAll(User user,String search);
   public List<Champ> findChampsByUserRole(User user,int role,String search);
   public Object findById(Object Object);
   public Object save(Object Object);
   public Object delete(Object Object);
}