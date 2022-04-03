package hei2017.service;
 import hei2017.entity.User;
import java.util.List;
public interface UserService {


public User findOneById(Long id)
;

public long count()
;

public User save(User user)
;

public Boolean exists(String email)
;

public User findOneByIdWithAll(Long id)
;

public List<User> findAllWithAll()
;

public User findOneByNomAndPrenom(String nom,String prenom)
;

public User findOneByPseudo(String pseudo)
;

public void delete(User user)
;

public List<User> findAll()
;

public void deleteOneById(Long id)
;

}