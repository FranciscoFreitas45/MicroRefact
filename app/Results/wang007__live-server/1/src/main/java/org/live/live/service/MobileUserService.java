package org.live.live.service;
 import org.live.app.vo.SimpleUserVo;
import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.live.entity.LiveRoom;
import org.live.live.entity.MobileUser;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface MobileUserService extends BaseService<MobileUser, String>{


public void attentionLiveRoom(MobileUser user,LiveRoom liveRoom)
;

public SimpleUserVo findMobileUserByAccountWithSimple(String account)
;

public void setOutDate(List<String> ids)
;

public MobileUser findMobileUserByAccount(String account)
;

public DataTableModel findPage(HttpServletRequest request)
;

}