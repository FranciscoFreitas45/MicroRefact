import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhAdmin;
public interface PhAdminService {


public void deleteAdmin(String ids)


public void resetPwd(Long id)


public boolean editPwd(Long id,String password,String newpassword)


public void save(PhAdmin phAdmin,Long[] roleIds,String[] brandIds)


public PhAdmin findOne(Long id)


public Page<PhAdmin> findByPage(String username,String nickname,Pageable page)


}