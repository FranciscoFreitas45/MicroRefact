package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.UserContractDaoImplHelper;
import ar.com.veterinaria.app.entities.user.User;
@Service
public class UserDaoImplHelper implements UserContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<User,Integer> repository,Integer id){
    List<User> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        User aUser = result.get(ini);
        if (aUser.getId().equals(id) && aUser.isDeleted()) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public boolean isDuplicated(JpaRepository<User,Integer> repository,User user){
    List<User> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        User aUser = result.get(ini);
        if (aUser.getEmail().equals(user.getEmail())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public User findByName(JpaRepository<User,Integer> repository,String t){
    List<User> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        User aUser = result.get(ini);
        if (aUser.getEmail().equals(t)) {
            return aUser;
        }
        ini++;
    }
    return null;
}


@Override
public User update(JpaRepository<User,Integer> repository,Integer id,User user){
    List<User> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        User aUser = result.get(ini);
        if (aUser.getId().equals(id)) {
            return aUser;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<User,Integer> repository,Integer id){
    List<User> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        User aUser = result.get(ini);
        if (aUser.getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<User,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<User> result = repository.findAll();
        for (User user : result) {
            map = new HashMap<>();
            if (!user.isDeleted()) {
                map.put(user.getId().toString(), user);
                list.add(map);
            }
        }
        return list;
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


}