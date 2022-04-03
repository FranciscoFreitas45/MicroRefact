package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
public interface UserAlarmDao {


public List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo)
;

public List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo)
;

}