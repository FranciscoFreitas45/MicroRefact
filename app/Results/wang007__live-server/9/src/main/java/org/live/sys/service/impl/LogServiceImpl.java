package org.live.sys.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.sys.entity.Log;
import org.live.sys.repository.LogRepository;
import org.live.sys.service.LogService;
import org.live.sys.vo.LogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log, String>implements LogService{

@Resource
 private  LogRepository logRepo;


@Override
public void deleteLogInfo(String[] ids){
    for (String id : ids) {
        Log log = this.findOne(id);
        if (log != null) {
            this.delete(log);
        }
    }
}


@Override
public BaseRepository<Log,String> getRepository(){
    return this.logRepo;
}


@Override
public Page<Log> findLogs(Pageable pageable,LogVo logVo){
    return this.logRepo.findLogInfoAll(pageable, logVo);
}


}