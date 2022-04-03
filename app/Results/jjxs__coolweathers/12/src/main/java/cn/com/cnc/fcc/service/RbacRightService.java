package cn.com.cnc.fcc.service;
 import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.domain.RbacRight;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
@Service
public interface RbacRightService {


public Integer updateRight(List<String> menuList,RbacRight rbacRight)
;

public Integer createRight(List<String> menuList,RbacRight rbacRight)
;

public List<RbacRightDTO> getRightMenuInfo(Integer rightId)
;

public List<RbacMenu> getMenuInfo()
;

}