package org.gliderwiki.web.user.service;
 import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.ProfileVo;
public interface UserProfileService {


public BaseObjectBean updateUserProfile(ProfileVo profileVo,HttpServletRequest request)
;

public int updateAwayUser(Integer weUserIdx)
;

}