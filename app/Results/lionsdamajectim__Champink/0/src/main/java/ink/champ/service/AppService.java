package ink.champ.service;
 import ink.champ.models.Champ;
import ink.champ.models.ChampEvent;
import ink.champ.models.ChampTeam;
import ink.champ.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import ink.champ.Interface.RepositoryService;
import ink.champ.DTO.User;
@Service
public class AppService implements UserDetailsService{

 public  String GLOBAL;

 public  String USER_ALL;

 public  String USER_OWNER;

 public  String USER_JUDGE;

 public  String USER_MANAGER;

 public  String USER_VIEWER;

 public  int NONE;

 public  int VIEWER;

 public  int JUDGE;

 public  int MANAGER;

 public  int OWNER;

@Autowired
 private  RepositoryService service;

 public  String subpage;


public void updateModel(User user,Model model,String page,String title){
    String name = user != null ? user.getName() : "Гость";
    title = title.equals("") ? "Champink" : "Champink. " + title;
    model.addAttribute("page", page);
    model.addAttribute("subpage", subpage);
    model.addAttribute("title", title);
    model.addAttribute("user", user);
    model.addAttribute("name", name);
}


public void makeChampResult(Model model,Champ champ){
    if (champ.getFormat().equals("Плей-офф"))
        makePlayOff(model, champ);
    else
        makeTable(model, champ);
}


public void makeTable(Model model,Champ champ){
    model.addAttribute("tableType", "T");
    String[] names = new String[champ.getTeamsCount()];
    long[][] stats = new long[names.length][10];
    int i = 0;
    for (ChampTeam team : champ.getTeams()) {
        names[i] = team.getTeam().getName();
        stats[i][0] = team.getTeam().getId();
        stats[i][1] = i;
        i++;
    }
    for (ChampEvent event : champ.getEvents()) {
        long team1Id = event.getTeam1().getId();
        long team2Id = event.getTeam2().getId();
        int id1 = -1;
        int id2 = -1;
        for (i = 0; i < stats.length; i++) {
            if (stats[i][0] == team1Id)
                id1 = i;
            if (stats[i][0] == team2Id)
                id2 = i;
        }
        int gol1 = event.getGol1();
        int gol2 = event.getGol2();
        int pen1 = event.getPen1();
        int pen2 = event.getPen2();
        stats[id1][2]++;
        stats[id2][2]++;
        stats[id1][6] += gol1;
        stats[id2][6] += gol2;
        stats[id1][7] += gol2;
        stats[id2][7] += gol1;
        if (gol1 == gol2 && pen1 == pen2) {
            // Ничья
            stats[id1][4]++;
            stats[id2][4]++;
        } else if (gol1 > gol2 || (gol1 == gol2 && pen1 > pen2)) {
            // Победа 1
            stats[id1][3]++;
            stats[id2][5]++;
        } else if (gol1 < gol2 || (gol1 == gol2 && pen1 < pen2)) {
            // Победа 2
            stats[id1][5]++;
            stats[id2][3]++;
        }
    }
    for (i = 0; i < stats.length; i++) {
        stats[i][8] = stats[i][6] - stats[i][7];
        stats[i][9] = stats[i][3] * 3 + stats[i][4];
    }
    for (i = 0; i < stats.length - 1; i++) {
        for (int j = i + 1; j < stats.length; j++) {
            if (stats[i][9] < stats[j][9]) {
                for (int k = 0; k < stats[i].length; k++) {
                    long buf = stats[i][k];
                    stats[i][k] = stats[j][k];
                    stats[j][k] = buf;
                }
            }
        }
    }
    model.addAttribute("tableNames", names);
    model.addAttribute("tableStats", stats);
}


@Override
public UserDetails loadUserByUsername(String username){
    return service.getUserByUsername(username);
}


public void makePlayOff(Model model,Champ champ){
    model.addAttribute("tableType", "P");
    int[] margins = new int[10];
    for (int i = 0, j = 0; i < margins.length; i++) {
        if (i == 0)
            margins[i] = 10;
        else
            margins[i] = (j / 2) * 50 + (j / 2 + 1) * 70;
        j = j * 2 + 1;
    }
    String[] names = new String[champ.getTeamsCount()];
    // teamId, teamIndex, teamWin, teamLose
    long[][] stats = new long[names.length][4];
    int i = 0;
    for (ChampTeam team : champ.getTeams()) {
        names[i] = team.getTeam().getName();
        stats[i][0] = team.getTeam().getId();
        stats[i][1] = i;
        i++;
    }
    List<List<long[]>> stages = new ArrayList<>();
    for (ChampEvent event : champ.getEvents()) {
        long team1Id = event.getTeam1().getId();
        long team2Id = event.getTeam2().getId();
        int id1 = -1;
        int id2 = -1;
        for (i = 0; i < stats.length; i++) {
            if (stats[i][0] == team1Id)
                id1 = i;
            if (stats[i][0] == team2Id)
                id2 = i;
        }
        int gol1 = event.getGol1();
        int gol2 = event.getGol2();
        int pen1 = event.getPen1();
        int pen2 = event.getPen2();
        int win = 0;
        if (gol1 > gol2 || (gol1 == gol2 && pen1 > pen2))
            win = 1;
        else if (gol1 < gol2 || (gol1 == gol2 && pen1 < pen2))
            win = 2;
        if (stats[id1][2] == stats[id2][2]) {
            int stage = (int) stats[id1][2];
            if (stages.size() <= stage)
                stages.add(new ArrayList<>());
            int margin = margins[stage + 1 - (stages.get(stage).size() == 0 ? 1 : 0)];
            int height = stage == 0 ? 0 : ((int) (120 * Math.pow(2, stage - 1)));
            stages.get(stage).add(new long[] { id1, id2, gol1, gol2, pen1, pen2, win, margin, height });
        }
        if (win == 1) {
            stats[id1][2]++;
            stats[id2][3]++;
        } else if (win == 2) {
            stats[id2][2]++;
            stats[id1][3]++;
        }
    }
    model.addAttribute("tableNames", names);
    model.addAttribute("tableStats", stages);
}


}