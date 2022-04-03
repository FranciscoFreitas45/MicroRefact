package ink.champ.Interface;
public interface RepositoryService {

   public Team getTeamById(Long id);
   public void addNewTeamRole(TeamRole role);
   public void saveTeamRole(TeamRole role);
   public TeamRole getTeamRoleById(Long id);
   public List<Player> getUserPlayersNotInTeam(User user,Team team);
   public List<Champ> getUserChampsTeamNotIn(User user,Team team);
   public TeamPlayer getTeamPlayerById(Long id);
   public void deleteTeamPlayer(TeamPlayer teamPlayer);
   public void deleteTeam(Team team);
   public void addNewTeam(Team team);
   public void addNewTeamPlayer(TeamPlayer teamPlayer);
   public void addNewChampTeam(ChampTeam champTeam);
   public void saveTeam(Team team);
}