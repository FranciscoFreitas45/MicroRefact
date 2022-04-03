package cn.gson.oasys.model.dao.address;
 import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cn.gson.oasys.model.entity.note.Director;
import cn.gson.oasys.model.entity.note.DirectorUser;
import cn.gson.oasys.model.entity.user.User;
public interface AddressUserDao extends JpaRepository<DirectorUser, Long>{


public List<DirectorUser> findByCatalogNameAndUser(String catalog,User user)
;

@Query("from DirectorUser du where (du.director.userName like ?1 or du.director.phoneNumber like ?1 or du.user.userName like ?1) and du.user =?2 and du.shareuser is NOT null")
public Page<DirectorUser> findBaseKey(String baseKey,User user,Pageable pa)
;

public List<DirectorUser> findByUserAndShareuserNotNullAndHandle(User user,Boolean boo)
;

public Page<DirectorUser> findByUserAndShareuserNotNull(User user,Pageable pa)
;

public DirectorUser findByDirectorAndUser(Director director,User user)
;

@Query("select d.catalogName from DirectorUser d where d.user= ?1 and (d.catalogName !=null and d.catalogName !='')")
public Set<String> findByUser(User user)
;

public Page<DirectorUser> findByShareuser(User user,Pageable pa)
;

public List<DirectorUser> findByDirector(Director director)
;

}