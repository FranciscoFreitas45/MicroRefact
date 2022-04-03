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
import ink.champ.DTO.User;
@Controller
public class PlayerController {

@Autowired
 private  AppService app;

@Autowired
 private  RepositoryService service;

 private  String page;


@GetMapping("/player/{id}/teams/add")
public String playerTeamsAdd(User user,Long id,Model model){
    if (user == null)
        return "redirect:/players";
    Player player = service.getPlayerById(id);
    if (player.getUserRole(user) >= AppService.Role.MANAGER) {
        model.addAttribute("player", player);
        model.addAttribute("teams", service.getUserTeamsPlayerNotIn(user, player));
        app.updateModel(user, model, page, "Игрок " + player.getName() + " - добавление в команду");
        return "player/add-team";
    }
    return "redirect:/player/" + id;
}


@GetMapping("/player/{id}/roles/{pr}/{action}")
public String teamRoleAction(User user,Long id,Long prId,String action){
    if (user != null) {
        Player player = service.getPlayerById(id);
        if (player != null && player.getUserRole(user) == AppService.Role.OWNER) {
            PlayerRole playerRole = service.getPlayerRoleById(prId);
            switch(action) {
                case "accept":
                    playerRole.setRole(playerRole.getRequest());
                    playerRole.setRequest(AppService.Role.NONE);
                    break;
                case "reject":
                    playerRole.setRequest(AppService.Role.NONE);
                    break;
                case "delete":
                    playerRole.setRole(AppService.Role.VIEWER);
                    break;
            }
            service.savePlayerRole(playerRole);
        }
    }
    return "redirect:/player/" + id;
}


@GetMapping("/player/{id}/delete/{admin}")
public String playerDelete(User user,Long id,Boolean admin){
    if (user == null)
        return "redirect:/players";
    Player player = service.getPlayerById(id);
    if ((admin && user.isAdmin()) || (!admin && player.getUserRole(user) == AppService.Role.OWNER))
        service.deletePlayer(player);
    if (admin)
        return "redirect:/admin?subpage=players";
    else
        return "redirect:/players";
}


@PostMapping("/post/player/{id}/teams/add")
public String postPlayerTeamAdd(User user,Long id,Team team,String position){
    if (user != null) {
        Player player = service.getPlayerById(id);
        if (player.getUserRole(user) >= AppService.Role.MANAGER && team.getUserRole(user) >= AppService.Role.MANAGER) {
            service.addNewTeamPlayer(new TeamPlayer(team, player, position));
        }
    }
    return "redirect:/player/" + id;
}


@GetMapping("/players")
public String players(String subpage,String search,User user,Model model){
    if (subpage != null) {
        app.subpage = subpage;
        return "redirect:/players";
    }
    if (app.subpage == null)
        app.subpage = AppService.Subpage.GLOBAL;
    String s = "";
    String title = "Игроки";
    if (search != null && !search.isEmpty()) {
        s = search;
        title += " - поиск «" + search + "»";
    }
    switch(app.subpage) {
        case AppService.Subpage.GLOBAL:
            model.addAttribute("players", service.getGlobalPlayers(s));
            break;
        case AppService.Subpage.USER_ALL:
            model.addAttribute("players", service.getUserPlayersAll(user, s));
            break;
        case AppService.Subpage.USER_OWNER:
            model.addAttribute("players", service.getUserPlayersRole(user, AppService.Role.OWNER, s));
            break;
        case AppService.Subpage.USER_MANAGER:
            model.addAttribute("players", service.getUserPlayersRole(user, AppService.Role.MANAGER, s));
            break;
        case AppService.Subpage.USER_VIEWER:
            model.addAttribute("players", service.getUserPlayersRole(user, AppService.Role.VIEWER, s));
            break;
        default:
            return "redirect:/players?subpage=" + AppService.Subpage.GLOBAL;
    }
    model.addAttribute("headerTitle", title);
    app.updateModel(user, model, page, title);
    return "player/list";
}


@GetMapping("/player/{id}")
public String champById(User user,Long id,Model model){
    Player player = service.getPlayerById(id);
    if (player == null)
        return "redirect:/players";
    model.addAttribute("player", player);
    app.updateModel(user, model, page, "Игрок " + player.getName());
    return "player/view";
}


@PostMapping("/post/player/{id}/edit")
public String postPlayerEdit(User user,Long id,String name,boolean privat){
    if (user != null) {
        Player player = service.getPlayerById(id);
        if (player != null && player.getUserRole(user) >= AppService.Role.MANAGER) {
            player.setName(name);
            player.setPrivate(privat);
            service.savePlayer(player);
        }
    }
    return "redirect:/player/" + id;
}


@GetMapping("/player/new")
public String champNew(User user,Model model){
    app.subpage = AppService.Subpage.USER_OWNER;
    app.updateModel(user, model, page, "Новый игрок");
    return "player/new";
}


@GetMapping("/player/{id}/roles/set")
public String playerRoleSet(User user,Long id,Integer role){
    if (user != null) {
        Player player = service.getPlayerById(id);
        PlayerRole playerRole = player.getPlayerRole(user);
        if (playerRole == null) {
            playerRole = new PlayerRole(player, user, AppService.Role.NONE);
            service.addNewPlayerRole(playerRole);
        }
        if ((role == 0 || role == 1) && playerRole.getRole() < AppService.Role.OWNER) {
            playerRole.setRole(role);
            playerRole.setRequest(AppService.Role.NONE);
            service.savePlayerRole(playerRole);
        } else if (role == AppService.Role.MANAGER && playerRole.getRole() < AppService.Role.MANAGER) {
            playerRole.setRequest(role);
            service.savePlayerRole(playerRole);
        }
    }
    return "redirect:/player/" + id;
}


@PostMapping("/post/player/add")
public String postPlayerAdd(User user,String name,boolean privat){
    if (user != null) {
        Player newPlayer = new Player(name, privat, user);
        service.addNewPlayer(newPlayer);
        service.addNewPlayerRole(new PlayerRole(newPlayer, user, AppService.Role.OWNER));
    }
    return "redirect:/players?subpage=" + AppService.Subpage.USER_OWNER;
}


@GetMapping("/player/{id}/edit")
public String playerEdit(User user,Long id,Model model){
    if (user != null) {
        Player player = service.getPlayerById(id);
        if (player != null && player.getUserRole(user) >= AppService.Role.MANAGER) {
            model.addAttribute("player", player);
            app.updateModel(user, model, page, "Игрок " + player.getName() + " - редактирование");
            return "player/edit";
        }
    }
    return "redirect:/players";
}


}