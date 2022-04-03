package com.sda.inTeams.configuration;
 import com.sda.inTeams.configuration.entitiesGenerator;
import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.AccountRole;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.Interface.TaskRepository;
import com.sda.inTeams.Interface.CommentRepository;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.AccountRoleRepository;
@Configuration
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent>{

 private  boolean INITIALIZE_ON_STARTUP;

 private  String ROLE_ADMIN;

 private  String ROLE_USER;

 private  String[] AVAILABLE_ROLES;

 private  TeamRepository teamRepository;

 private  UserRepository userRepository;

 private  ProjectRepository projectRepository;

 private  TaskRepository taskRepository;

 private  CommentRepository commentRepository;

 private  PasswordEncoder passwordEncoder;

 private  UserRepository accountRepository;

 private  AccountRoleRepository accountRoleRepository;


public void connectProjectAndTasks(Project project,List<Task> taskList){
    project.setTasks(new HashSet<>(taskList));
    for (Task projectTask : taskList) {
        projectTask.setProject(project);
    }
}


public void connectCommentAndUser(Comment comment,User user){
    comment.setCreator(user);
}


public void addUser(String username,String password,String[] roles){
    Optional<User> optionalAccount = accountRepository.findByUsername(username);
    if (!optionalAccount.isPresent()) {
        User account = User.builder().accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).username(username).password(passwordEncoder.encode(password)).build();
        Set<AccountRole> roleSet = new HashSet<>();
        for (String role : roles) {
            Optional<AccountRole> roleOptional = accountRoleRepository.findByName(role);
            if (roleOptional.isPresent()) {
                roleSet.add(roleOptional.get());
            }
        }
        account.setRoles(roleSet);
        accountRepository.save(account);
    }
}


@Override
public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
    for (String availableRole : AVAILABLE_ROLES) {
        addRole(availableRole);
    }
    addUser("admin", "admin", new String[] { ROLE_ADMIN });
    addUser("user", "user", new String[] { ROLE_USER });
    if (INITIALIZE_ON_STARTUP) {
        generateTeamsAndUsers();
        generateProjects();
        generateTasks();
        generateComments();
    }
}


public void generateTasks(){
    List<Team> teams = teamRepository.findAll();
    for (Team team : teams) {
        List<Project> teamProjects = new ArrayList<>(team.getProjects());
        for (Project project : teamProjects) {
            List<Task> taskList = TaskGenerator.generateTasks(3);
            connectProjectAndTasks(project, taskList);
            taskRepository.saveAll(taskList);
        }
        List<Project> projects = projectRepository.saveAll(teamProjects);
        for (Project project : projects) {
            ArrayList<Task> tasks = new ArrayList<>(taskRepository.findAllByProject(project));
            for (Task task : tasks) {
                List<User> teamMembers = new ArrayList<>(userRepository.findAllByTeamsContaining(team));
                connectTaskAndUser(task, teamMembers);
                userRepository.saveAll(teamMembers);
            }
            taskRepository.saveAll(tasks);
        }
        projects = projectRepository.saveAll(projects);
    }
    teamRepository.saveAll(teams);
}


public void connectTaskAndUser(Task task,List<User> teamUsers){
    User user = UserGenerator.pickRandomUserFromList(teamUsers);
    task.setUserResponsible(user);
    List<Task> userTasks = taskRepository.findAllByUserResponsible(user);
    userTasks.add(task);
    user.setTaskResponsibleFor(new HashSet<>(userTasks));
}


public void connectTaskAndComments(Task task,List<Comment> commentList){
    task.setComments(new HashSet<>(commentList));
    for (Comment comment : commentList) {
        comment.setTask(task);
    }
}


public void generateTeamsAndUsers(){
    List<Team> teams = TeamGenerator.generateTeams(6);
    for (Team team : teams) {
        List<User> users = UserGenerator.generateUsers(6);
        connectTeamAndUsers(team, users);
    }
    teamRepository.saveAll(teams);
}


public void generateProjects(){
    List<Team> teamList = teamRepository.findAll();
    for (Team team : teamList) {
        List<Project> projects = ProjectGenerator.generateProjects(4);
        connectTeamAndProjects(team, projects);
        projectRepository.saveAll(projects);
    }
    teamRepository.saveAll(teamList);
}


public void connectTeamAndProjects(Team team,List<Project> teamProjects){
    for (Project teamProject : teamProjects) {
        teamProject.setProjectOwner(team);
    }
}


public void generateComments(){
    List<Team> teamList = teamRepository.findAll();
    for (Team team : teamList) {
        List<User> users = new ArrayList<>(userRepository.findAllByTeamsContaining(team));
        List<Project> projects = new ArrayList<>(projectRepository.findAllByProjectOwner(team));
        for (Project project : projects) {
            List<Task> taskList = new ArrayList<>(taskRepository.findAllByProject(project));
            for (Task task : taskList) {
                List<Comment> comments = CommentGenerator.generateComments(5);
                connectTaskAndComments(task, comments);
                commentRepository.saveAll(comments);
            }
            taskList = taskRepository.saveAll(taskList);
            for (Task task : taskList) {
                List<Comment> comments = new ArrayList<>(commentRepository.findAllByTask(task));
                for (Comment comment : comments) {
                    User randomUser = UserGenerator.pickRandomUserFromList(users);
                    connectCommentAndUser(comment, randomUser);
                    userRepository.save(randomUser);
                }
                commentRepository.saveAll(comments);
            }
        }
    }
}


public void connectTeamAndUsers(Team team,List<User> userList){
    team.setMembers(new HashSet<>(userList));
    team.setTeamOwner(UserGenerator.pickRandomUserFromList(userList));
}


public void addRole(String availableRole){
    Optional<AccountRole> optionalAccountRole = accountRoleRepository.findByName(availableRole);
    if (!optionalAccountRole.isPresent()) {
        AccountRole accountRole = AccountRole.builder().name(availableRole).build();
        accountRoleRepository.save(accountRole);
    }
}


}