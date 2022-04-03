package org.danyuan.application.softm.sysmenu.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.softm.organization.dao.SysRolesJurisdictionInfoDao;
import org.danyuan.application.softm.organization.po.SysRolesJurisdictionInfo;
import org.danyuan.application.softm.sysmenu.dao.SysMenuDao;
import org.danyuan.application.softm.sysmenu.po.SysMenuInfo;
import org.danyuan.application.softm.sysmenu.vo.AuthorityzTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysRolesJurisdictionInfoDao;
import org.danyuan.application.DTO.SysRolesJurisdictionInfo;
@Service("sysMenuService")
public class SysMenuInfoService extends BaseServiceImpl<SysMenuInfo>implements BaseService<SysMenuInfo>{

@Autowired
 private  SysMenuDao sysMenuDao;

@Autowired
 private  SysRolesJurisdictionInfoDao sysRolesJurisdictionInfoDao;


public List<AuthorityzTreeVO> findzTreeByUser(String id,String userName){
    List<AuthorityzTreeVO> list = null;
    List<SysMenuInfo> listt = null;
    if ("wth".equals(userName)) {
        listt = sysMenuDao.findzTreeByUser(id);
    } else {
        listt = sysMenuDao.findzTreeByUser(id, userName);
    }
    if (listt != null && listt.size() > 0) {
        list = new ArrayList<>();
        for (SysMenuInfo sysMenuInfo : listt) {
            AuthorityzTreeVO vo = new AuthorityzTreeVO();
            vo.setId(sysMenuInfo.getUuid());
            vo.setName(sysMenuInfo.getName());
            vo.setIcon(sysMenuInfo.getIcon());
            vo.setUrl(sysMenuInfo.getUri());
            vo.setHomePage(sysMenuInfo.getHomePage());
            List<AuthorityzTreeVO> listt1 = findzTreeByUser(sysMenuInfo.getUuid(), userName);
            if (listt1 != null) {
                vo.getChildren().addAll(listt1);
            }
            list.add(vo);
        }
    }
    return list;
}


public List<AuthorityzTreeVO> findzTreeByF_ParentId(String id){
    List<AuthorityzTreeVO> list = null;
    List<SysMenuInfo> listt = sysMenuDao.findAllByParentsIdOrderByF_SortCode(id);
    if (listt != null && listt.size() > 0) {
        list = new ArrayList<>();
        for (SysMenuInfo sysMenuInfo : listt) {
            AuthorityzTreeVO vo = new AuthorityzTreeVO();
            vo.setId(sysMenuInfo.getUuid());
            vo.setName(sysMenuInfo.getName());
            vo.setIcon(sysMenuInfo.getIcon());
            vo.setUrl(sysMenuInfo.getUri());
            List<AuthorityzTreeVO> listt1 = findzTreeByF_ParentId(sysMenuInfo.getUuid());
            if (listt1 != null) {
                vo.getChildren().addAll(listt1);
            }
            list.add(vo);
        }
    }
    return list;
}


public Page<SysMenuInfo> findAllBySearchText(int pageNumber,int pageSize,SysMenuInfo info){
    Example<SysMenuInfo> example = Example.of(info);
    Sort sort = Sort.by(new Order(Direction.DESC, "insertDatetime"));
    PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<SysMenuInfo> sourceCodes = sysMenuDao.findAll(example, request);
    return sourceCodes;
}


public List<AuthorityzTreeVO> findzTreeRole(String id,String roleUuid){
    List<AuthorityzTreeVO> list = null;
    List<SysMenuInfo> listt = sysMenuDao.findAllByParentsIdOrderByF_SortCode(id);
    if (listt != null && listt.size() > 0) {
        list = new ArrayList<>();
        for (SysMenuInfo sysMenuInfo : listt) {
            AuthorityzTreeVO vo = new AuthorityzTreeVO();
            vo.setId(sysMenuInfo.getUuid());
            vo.setName(sysMenuInfo.getName());
            vo.setIcon(sysMenuInfo.getIcon());
            vo.setUrl(sysMenuInfo.getUri());
            SysRolesJurisdictionInfo jurisdiction = new SysRolesJurisdictionInfo();
            SysRolesJurisdictionInfo pk = new SysRolesJurisdictionInfo(sysMenuInfo.getUuid(), roleUuid);
            Optional<SysRolesJurisdictionInfo> optional = sysRolesJurisdictionInfoDao.findOne(Example.of(pk));
            if (optional.isPresent()) {
                jurisdiction = optional.get();
                vo.setChecked(jurisdiction.getChecked());
            }
            List<AuthorityzTreeVO> listt1 = findzTreeRole(sysMenuInfo.getUuid(), roleUuid);
            if (listt1 != null) {
                vo.getChildren().addAll(listt1);
            }
            list.add(vo);
        }
    }
    return list;
}


public AuthorityzTreeVO savev(SysMenuInfo authority){
    if (authority.getHomePage()) {
        sysMenuDao.updateSysMenuInfoHomePage();
    }
    sysMenuDao.save(authority);
    AuthorityzTreeVO vo = new AuthorityzTreeVO();
    vo.setId(authority.getUuid());
    vo.setName(authority.getName());
    vo.setIconSkin(authority.getIcon());
    return vo;
}


public void deleteAuthority(SysMenuInfo sysMenuInfo){
    List<AuthorityzTreeVO> list = findzTreeByF_ParentId(sysMenuInfo.getUuid());
    if (list != null) {
        for (AuthorityzTreeVO authorityzTreeVO : list) {
            sysMenuDao.deleteById(authorityzTreeVO.getId());
        }
    }
    sysMenuDao.deleteById(sysMenuInfo.getUuid());
}


public List<SysMenuInfo> findAll(){
    // TODO Auto-generated method stub
    return sysMenuDao.findAll();
}


public AuthorityzTreeVO updateAuthorityName(SysMenuInfo sysMenuInfo){
    sysMenuDao.updateSysMenuInfoName(sysMenuInfo.getName(), sysMenuInfo.getUuid());
    AuthorityzTreeVO vo = new AuthorityzTreeVO();
    vo.setId(sysMenuInfo.getUuid());
    vo.setName(sysMenuInfo.getName());
    vo.setIconSkin(sysMenuInfo.getIcon());
    return vo;
}


public AuthorityzTreeVO onDropAuthority(SysMenuInfo sysMenuInfo){
    String[] str = sysMenuInfo.getUuid().split(",");
    if ("inner".equals(sysMenuInfo.getMoveType())) {
        List<SysMenuInfo> list = sysMenuDao.getSize(sysMenuInfo.getParentsId());
        int num = list.size();
        for (String string : str) {
            sysMenuDao.updateSysMenuInfoName(sysMenuInfo.getParentsId(), num++, string);
        }
    } else {
        SysMenuInfo temp = sysMenuDao.getParentId(sysMenuInfo.getParentsId() == null ? "0" : sysMenuInfo.getParentsId());
        List<SysMenuInfo> list = sysMenuDao.findAllByParentsIdOrderByF_SortCode(temp.getParentsId());
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            for (String string : str) {
                if (string.equals(list.get(i).getUuid())) {
                    list.remove(i);
                    i--;
                }
            }
        }
        if ("prev".equals(sysMenuInfo.getMoveType())) {
            for (SysMenuInfo sysMenuInfo2 : list) {
                if (temp.getUuid().equals(sysMenuInfo2.getUuid())) {
                    for (String string : str) {
                        sysMenuDao.updateSysMenuInfoName(temp.getParentsId(), num++, string);
                    }
                }
                sysMenuDao.updateSysMenuInfoName(temp.getParentsId(), num++, sysMenuInfo2.getUuid());
            }
        } else if ("next".equals(sysMenuInfo.getMoveType())) {
            for (SysMenuInfo authority2 : list) {
                sysMenuDao.updateSysMenuInfoName(temp.getParentsId(), num++, authority2.getUuid());
                if (temp.getUuid().equals(authority2.getUuid())) {
                    for (String string : str) {
                        sysMenuDao.updateSysMenuInfoName(temp.getParentsId(), num++, string);
                    }
                }
            }
        }
    }
    return new AuthorityzTreeVO();
}


}