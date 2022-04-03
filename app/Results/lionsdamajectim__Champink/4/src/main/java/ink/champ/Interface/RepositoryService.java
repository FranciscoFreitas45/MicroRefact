package ink.champ.Interface;
public interface RepositoryService {

   public Player getPlayerById(Long id);
   public void addNewPlayerRole(PlayerRole role);
   public void savePlayerRole(PlayerRole role);
   public PlayerRole getPlayerRoleById(Long id);
   public List<Team> getUserTeamsPlayerNotIn(User user,Player player);
   public void deletePlayer(Player player);
   public void addNewPlayer(Player player);
   public void addNewTeamPlayer(TeamPlayer teamPlayer);
   public void savePlayer(Player player);
}