package io.delivery.dao;
 import io.delivery.entity.User;
import java.util.List;
public interface UserDao extends BasicDao<User>{


public List<User> findByLogin(String login)
;

}