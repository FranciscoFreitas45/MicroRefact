package org.gliderwiki.web.user.service;
 import java.util.List;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
public interface UserAlarmService {


public String addCheckUserAlarmDWR(String arrValue,int userIdx)
;

public List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo)
;

public List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo)
;

}