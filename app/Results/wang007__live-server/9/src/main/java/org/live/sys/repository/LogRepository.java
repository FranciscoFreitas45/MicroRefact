package org.live.sys.repository;
 import org.live.common.base.BaseRepository;
import org.live.sys.entity.Log;
import org.live.sys.vo.LogVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface LogRepository extends BaseRepository<Log, String>{


public Page<Log> findLogInfoAll(Pageable pageable,LogVo log)
;

}