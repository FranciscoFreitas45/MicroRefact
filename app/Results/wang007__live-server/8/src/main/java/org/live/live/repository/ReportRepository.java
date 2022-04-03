package org.live.live.repository;
 import org.live.common.base.BaseRepository;
import org.live.live.entity.Report;
import org.live.live.vo.ReportVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Map;
public interface ReportRepository extends BaseRepository<Report, String>{


public Page<ReportVo> findReports(PageRequest pageRequest,Map<String,Object> filter,boolean handleType)
;

@Query("select re from Report re where re.user.id=:userId and re.liveRoom.id=:liveRoomId" + " and re.createTime in (select max(re0.createTime) " + "from Report re0 where re0.user.id=:userId and re0.liveRoom.id=:liveRoomId)")
public Report getRecentlyReport(String userId,String liveRoomId)
;

public void setUser(String id,MobileUser user);

public void setLiveRoom(String id,LiveRoom liveRoom);

public void setReportNum(String id,String reportNum);

public void setCreateTime(String id,Date createTime);

public void setHandleType(String id,boolean handleType);

}