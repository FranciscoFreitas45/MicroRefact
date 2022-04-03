package cn.gson.oasys.services.address;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gson.oasys.model.dao.address.AddressUserDao;
import cn.gson.oasys.model.entity.note.DirectorUser;
@Service
@Transactional
public class AddreddUserService {

@Autowired
 private AddressUserDao addressUserDao;


public void deleteObj(DirectorUser directorUser){
    addressUserDao.delete(directorUser);
}


public DirectorUser save(DirectorUser directorUser){
    return addressUserDao.save(directorUser);
}


public List<DirectorUser> savaList(List<DirectorUser> dus){
    return addressUserDao.save(dus);
}


}