package org.live.live.service;
 import org.live.app.vo.ApplyAnchorVo;
import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.live.entity.ApplyAnchor;
import org.live.live.vo.ApplyInfoVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
public interface ApplyAnchorService extends BaseService<ApplyAnchor, String>{


public Date getLastApplyAnchorDate(String userId)
;

public ApplyInfoVo getApplyInfo(String applyId)
;

public void createApplyAnchorForm(ApplyAnchorVo applyAnchorVo)
;

public void saveApplyPassFlag(String applyId,int passFlag,String reason)
;

public DataTableModel findByPage(HttpServletRequest request,boolean auditFlag)
;

public boolean judgeUserApplyCount(String userId,int systemMaxCount)
;

}