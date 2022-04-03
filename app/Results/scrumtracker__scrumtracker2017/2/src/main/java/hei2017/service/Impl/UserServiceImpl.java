package hei2017.service.Impl;
 import hei2017.dao.ProjectDAO;
import hei2017.dao.TaskDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Project;
import hei2017.entity.Task;
import hei2017.entity.User;
import hei2017.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;
import hei2017.Interface.ProjectDAO;
import hei2017.Interface.TaskDAO;
import hei2017.Interface.UserDAO;
@Named
@Transactional
public class UserServiceImpl implements UserService{

@Inject
 private ProjectDAO projectDAO;

@Inject
 private TaskDAO taskDAO;

@Inject
 private UserDAO userDAO;


@Override
public User findOneById(Long id){
    return userDAO.findOne(id);
}


@Override
public User save(User user){
    return userDAO.save(user);
}


@Override
public long count(){
    return userDAO.count();
}


@Override
public Boolean exists(String email){
    return null != userDAO.findOneByEmail(email);
}


@Override
public User findOneByIdWithAll(Long id){
    User user = userDAO.findOneById(id);
    if (null != user) {
        Set<Task> userTasks = taskDAO.findByTaskUsersId(user.getId());
        user.setUserTasks(userTasks);
    }
    return user;
}


@Override
public List<User> findAllWithAll(){
    List<User> users = userDAO.findAll();
    for (User user : users) {
        Set<Task> userTasks = taskDAO.findByTaskUsersId(user.getId());
        user.setUserTasks(userTasks);
    }
    return users;
}


@Override
public User findOneByNomAndPrenom(String nom,String prenom){
    return userDAO.findOneByNomAndPrenom(nom, prenom);
}


@Override
public User findOneByPseudo(String pseudo){
    return userDAO.findOneByPseudo(pseudo);
}


@Override
public List<User> findAll(){
    return userDAO.findAll();
}


@Override
public void delete(User user){
    userDAO.delete(user);
}


@Override
public void deleteOneById(Long id){
    userDAO.delete(id);
}


}