package org.gliderwiki.web.common.service;
 import java.util.List;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeUser;
public interface CommonInfoService {


public List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity,Integer weUserIdx)
;

public List<WeMenu> getHeaderMenuListAjax(Integer weMenuIdx,Integer weUserIdx)
;

}