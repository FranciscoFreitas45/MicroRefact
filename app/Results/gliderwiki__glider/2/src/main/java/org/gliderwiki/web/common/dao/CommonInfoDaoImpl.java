package org.gliderwiki.web.common.dao;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeMenu;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("commonInfoDao")
public class CommonInfoDaoImpl extends SqlSessionDaoSupportimplements CommonInfoDao{


@SuppressWarnings("unchecked")
@Override
public List<WeMenu> getSubMenuByAuth(Integer weMenuIdx,Integer weUserIdx,Integer weGrade){
    Map<String, Integer> mapper = new HashMap<String, Integer>();
    mapper.put("weMenuIdx", weMenuIdx);
    mapper.put("weUserIdx", weUserIdx);
    mapper.put("weGrade", weGrade);
    return (List<WeMenu>) getSqlSession().selectList("CommonManage.getSubMenuByAuth", mapper);
}


@Override
public List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity,Integer weUserIdx,Integer we_grade){
    Map<String, String> mapper = new HashMap<String, String>();
    mapper.put("weMenuGroup", menuEntity.getWe_menu_group());
    mapper.put("weMenuType", menuEntity.getWe_menu_type());
    mapper.put("weUserIdx", weUserIdx + "");
    mapper.put("weGrade", we_grade + "");
    return (List<WeMenu>) getSqlSession().selectList("CommonManage.getTitleMenuByAuth", mapper);
}


}