package org.gliderwiki.rest.service;
 import java.util.List;
import org.gliderwiki.rest.dao.PatchServiceDao;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WePatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("patchService")
public class PatchServiceImpl implements PatchService{

@Autowired
 private  PatchServiceDao patchServiceDao;


@Override
public WePatch getWePatchInfo(WePatch wePatchModel){
    return patchServiceDao.getWePatchInfo(wePatchModel);
}


@Override
public WeInstallUser getInstallUserInfo(WeInstallUser installVo){
    return patchServiceDao.getInstallUserInfo(installVo);
}


@Override
public WeFunction[] getCurrentFunctionList(String version,String extendYn){
    return patchServiceDao.getCurrentFunctionList(version, extendYn);
}


}