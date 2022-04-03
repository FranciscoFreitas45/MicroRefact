package org.gliderwiki.rest.dao;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WePatch;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("patchServiceDao")
public class PatchServiceDaoImpl extends SqlSessionDaoSupportimplements PatchServiceDao{


@Override
public WePatch getWePatchInfo(WePatch wePatchModel){
    return (WePatch) getSqlSession().selectOne("AdminManage.getWePatchInfo", wePatchModel);
}


@Override
public WeInstallUser getInstallUserInfo(WeInstallUser installVo){
    return (WeInstallUser) getSqlSession().selectOne("AdminManage.getInstallUserInfo", installVo);
}


@Override
public WeFunction[] getCurrentFunctionList(String version,String extendYn){
    Map<String, String> mapper = new HashMap<String, String>();
    mapper.put("version", version);
    mapper.put("we_extend_yn", extendYn);
    List<WeFunction> selectList = getSqlSession().selectList("AdminManage.getCurrentFunctionList", mapper);
    WeFunction[] list = selectList.toArray(new WeFunction[selectList.size()]);
    return list;
}


}