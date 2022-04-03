package org.gliderwiki.rest.service;
 import java.util.List;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WePatch;
public interface PatchService {


public WePatch getWePatchInfo(WePatch wePatchModel)
;

public WeInstallUser getInstallUserInfo(WeInstallUser installVo)
;

public WeFunction[] getCurrentFunctionList(String version,String extendYn)
;

}