package ink.champ.controllers;
 import ink.champ.models;
import ink.champ.service.AppService;
import ink.champ.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ink.champ.Interface.AppService;
import ink.champ.Interface.RepositoryService;
@Controller
public class TeamController {

@Autowired
 private  AppService app;

@Autowired
 private  RepositoryService service;

 private  String page;


@GetMapping("/team/{id}/players/add")
public String teamPlayersAdd(User user,Long id,Model model){
    if (user == null)
        return "redirect:/teams";
    Team team = service.getTeamById(id);
    if (team.getUserRole(user) >= AppService.Role.MANAGER) {
        model.addAttribute("team", team);
        model.addAttribute("players", service.getUserPlayersNotInTeam(user, team));
        app.updateModel(user, model, page, "Команда " + team.getName() + " - добавление игрока");
        return "team/add-player";
    }
    return "redirect:/team/" + id;
}


@GetMapping("/team/{id}/edit")
public String teamEdit(User user,Long id,Model model){
    if (user != null) {
        Team team = service.getTeamById(id);
        if (team != null && team.getUserRole(user) >= AppService.Role.MANAGER) {
            model.addAttribute("team", team);
            app.updateModel(user, model, page, "Команда " + team.getName() + " - редактирование");
            return "team/edit";
        }
    }
    return "redirect:/teams";
}


@GetMapping("/team/{id}/delete/{admin}")
public String teamDelete(User user,Long id,Boolean admin){
    if (user == null)
        return "redirect:/teams";
    Team team = service.getTeamById(id);
    if ((admin && user.isAdmin()) || (!admin && team.getUserRole(user) == AppService.Role.OWNER))
        service.deleteTeam(team);
    if (admin)
        return "redirect:/admin?subpage=teams";
    else
        return "redirect:/teams";
}


@GetMapping("/teams")
public String teams(String subpage,String search,User user,Model model){
    if (subpage != null) {
        app.subpage = subpage;
        return "redirect:/teams";
    }
    if (app.subpage == null)
        app.subpage = AppService.Subpage.GLOBAL;
    String s = "";
    String title = "Команды";
    if (search != null && !search.isEmpty()) {
        s = search;
        title += " - поиск «" + search + "»";
    }
    switch(app.subpage) {
        case AppService.Subpage.GLOBAL:
            model.addAttribute("teams", service.getGlobalTeams(s));
            break;
        case AppService.Subpage.USER_ALL:
            model.addAttribute("teams", service.getUserTeamsAll(user, s));
            break;
        case AppService.Subpage.USER_OWNER:
            model.addAttribute("teams", service.getUserTeamsRole(user, AppService.Role.OWNER, s));
            break;
        case AppService.Subpage.USER_MANAGER:
            model.addAttribute("teams", service.getUserTeamsRole(user, AppService.Role.MANAGER, s));
            break;
        case AppService.Subpage.USER_VIEWER:
            model.addAttribute("teams", service.getUserTeamsRole(user, AppService.Role.VIEWER, s));
            break;
        default:
            return "redirect:/teams?subpage=" + AppService.Subpage.GLOBAL;
    }
    model.addAttribute("headerTitle", title);
    app.updateModel(user, model, page, title);
    return "team/list";
}


@PostMapping("/post/team/{id}/edit")
public String postTeamEdit(User user,Long id,String name,boolean privat){
    if (user != null) {
        Team team = service.getTeamById(id);
        if (team != null && team.getUserRole(user) >= AppService.Role.MANAGER) {
            team.setName(name);
            team.setPrivate(privat);
            service.saveTeam(team);
        }
    }
    return "redirect:/team/" + id;
}


@GetMapping("/team/{id}")
public String teamById(User user,Long id,Model model){
    Team team = service.getTeamById(id);
    if (team == null)
        return "redirect:/teams";
    model.addAttribute("team", team);
    app.updateModel(user, model, page, "Команда " + team.getName());
    return "team/view";
}


@GetMapping("/team/{id}/champs/add")
public String teamChampsAdd(User user,Long id,Model model){
    if (user == null)
        return "redirect:/teams";
    Team team = service.getTeamById(id);
    if (team.getUserRole(user) >= AppService.Role.MANAGER) {
        model.addAttribute("team", team);
        model.addAttribute("champs", service.getUserChampsTeamNotIn(user, team));
        app.updateModel(user, model, page, "Команда " + team.getName() + " - добавление в чемпионат");
        return "team/add-champ";
    }
    return "redirect:/team/" + id;
}


@GetMapping("/team/{id}/roles/{tr}/{action}")
public String teamRoleAction(User user,Long id,Long trId,String action){
    if (user != null) {
        Team team = service.getTeamById(id);
        if (team != null && team.getUserRole(user) == AppService.Role.OWNER) {
            TeamRole teamRole = service.getTeamRoleById(trId);
            switch(action) {
                case "accept":
                    teamRole.setRole(teamRole.getRequest());
                    teamRole.setRequest(AppService.Role.NONE);
                    break;
                case "reject":
                    teamRole.setRequest(AppService.Role.NONE);
                    break;
                case "delete":
                    teamRole.setRole(AppService.Role.VIEWER);
                    break;
            }
            service.saveTeamRole(teamRole);
        }
    }
    return "redirect:/team/" + id;
}


@GetMapping("/team/new")
public String teamNew(User user,Model model){
    app.subpage = AppService.Subpage.USER_OWNER;
    app.updateModel(user, model, page, "Новая команда");
    return "team/new";
}


@GetMapping("/team/{id}/players/{tp}/delete")
public String teamPlayersDelete(User user,Long id,Long tpId){
    if (user == null)
        return "redirect:/teams";
    TeamPlayer teamPlayer = service.getTeamPlayerById(tpId);
    if (teamPlayer.getTeam().getUserRole(user) >= AppService.Role.MANAGER)
        service.deleteTeamPlayer(teamPlayer);
    return "redirect:/team/" + id;
}


@GetMapping("/team/{id}/roles/set")
public String teamRoleSet(User user,Long id,Integer role){
    if (user != null) {
        Team team = service.getTeamById(id);
        TeamRole teamRole = team.getTeamRole(user);
        if (teamRole == null) {
            teamRole = new TeamRole(team, user, AppService.Role.NONE);
            service.addNewTeamRole(teamRole);
        }
        if ((role == 0 || role == 1) && teamRole.getRole() < AppService.Role.OWNER) {
            teamRole.setRole(role);
            teamRole.setRequest(AppService.Role.NONE);
            service.saveTeamRole(teamRole);
        } else if (role == AppService.Role.MANAGER && teamRole.getRole() < AppService.Role.MANAGER) {
            teamRole.setRequest(role);
            service.saveTeamRole(teamRole);
        }
    }
    return "redirect:/team/" + id;
}


@PostMapping("/post/team/{id}/players/add")
public String postTeamPlayerAdd(User user,Long id,Player player,String position){
    if (user != null) {
        Team team = service.getTeamById(id);
        if (team.getUserRole(user) >= AppService.Role.MANAGER && player.getUserRole(user) >= AppService.Role.MANAGER) {
            service.addNewTeamPlayer(new TeamPlayer(team, player, position));
        }
    }
    return "redirect:/team/" + id;
}


@PostMapping("/post/team/add")
public String postTeamAdd(User user,String name,boolean privat){
    if (user != null) {
        Team newTeam = new Team(name, privat, user);
        service.addNewTeam(newTeam);
        service.addNewTeamRole(new TeamRole(newTeam, user, AppService.Role.OWNER));
    }
    return "redirect:/teams?subpage=" + AppService.Subpage.USER_OWNER;
}


@PostMapping("/post/team/{id}/champs/add")
public String postTeamChampAdd(User user,Long id,Champ champ){
    if (user != null) {
        Team team = service.getTeamById(id);
        if (team.getUserRole(user) >= AppService.Role.MANAGER && champ.getUserRole(user) >= AppService.Role.MANAGER) {
            service.addNewChampTeam(new ChampTeam(champ, team));
        }
    }
    return "redirect:/team/" + id;
}


}