package org.live.sys.service;
 import org.live.common.base.BaseService;
import org.live.sys.entity.Log;
import org.live.sys.vo.LogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface LogService extends BaseService<Log, String>{


public void deleteLogInfo(String[] ids)
;

public Page<Log> findLogs(Pageable pageable,LogVo log)
;

}