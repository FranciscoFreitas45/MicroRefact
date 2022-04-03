package ink.champ.Interface;
public interface RepositoryService {

   public Champ getChampById(Long id);
   public List<Sport> getSports(String search);
   public void addNewChampRole(ChampRole role);
   public void saveChampRole(ChampRole role);
   public ChampRole getChampRoleById(Long id);
   public List<Team> getUserTeamsNotInChamp(User user,Champ champ);
   public ChampEvent getChampEventById(Long id);
   public ChampTeam getChampTeamById(Long id);
   public void deleteChampTeam(ChampTeam champTeam);
   public void deleteChampEvent(ChampEvent champEvent);
   public void deleteChamp(Champ champ);
   public Sport getSportById(Long id);
   public void addNewChamp(Champ champ);
   public void addNewChampTeam(ChampTeam champTeam);
   public void addNewChampEvent(ChampEvent event);
   public void saveChampEvent(ChampEvent champEvent);
   public void saveChamp(Champ champ);
}