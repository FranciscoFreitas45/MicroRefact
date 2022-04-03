package com.ukefu.webim.service.repository;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ukefu.webim.web.model.OnlineUser;
public interface OnlineUserRepository extends JpaRepository<OnlineUser, String>{


@Query("select avg(ringduration) from StatusEvent where ( discaller = ?1 or discalled = ?1 ) and misscall = true  and orgi = ?2")
public Long avgByRingDurationFromCallCenter(String ani,String orgi)
;

@Query("select count(id) from StatusEvent where orgi = ?1 and calltype = ?2 and ?3 < createtime and createtime < ?4")
public Long countByToadyDirectionFromStatusEvent(String orgi,String calltype,Date begin,Date end)
;

@Query("select count(id) from StatusEvent where orgi = ?1 and calltype = ?2 and discaller = ?3 and ?4 < createtime and createtime < ?5")
public Long countByToadyAniFromStatusEvent(String orgi,String calltype,String ani,Date begin,Date end)
;

@Query("select count(id) from StatusEvent where orgi = ?1 and (discaller = ?2 or discalled = ?3) and ?4 < createtime and createtime < ?5")
public Long countByToadyExtentionFromStatusEvent(String orgi,String ani,String called,Date begin,Date end)
;

@Query("select count(id) from AgentService where orgi = ?1 and status = ?2 and agentno = ?3 and createtime > ?4 and createtime < ?5")
public Long countByAgentForAgentUser(String orgi,String status,String agentno,Date start,Date end)
;

public Page<OnlineUser> findByOrgiAndStatusAndCreatetimeLessThan(String orgi,String status,Date createtime,Pageable paramPageable)
;

@Query("select invitestatus , count(id) as users from OnlineUser where orgi = ?1 and status = ?2 group by invitestatus")
public List<Object> findByOrgiAndStatus(String orgi,String status)
;

@Query("select count(id) as allcount," + "count(case when qualitystatus = 'done' THEN '1' end) as donecount," + "count(case when qualitystatus = 'recheck' THEN '2' end) as recount," + "count(case when qualitystatus = 'no' THEN '3' end) as nocount," + "count(case when qualitystatus = 'appeal' THEN '4' end) as appealcount," + "count(case when qualitystatus = 'abritrate' THEN '5' end) as myabritratecount," + "count(case when qualityuser = ?1 and qualitypass = 0 THEN '6' end) as nopasscount," + "count(case when qualityuser = ?1 and qualityappeal = 1  THEN '7' end) as myappealcount," + "count(case when qualityuser = ?1 and qualityappeal = 1 and qualityarbitrate = 1 THEN '8' end) as myarbitratecount " + "FROM QualityMissionHis " + "where qualitydisuser = ?1 and orgi = ?2")
public Map<String,Long> countQc(String qualityuser,String orgi)
;

@Query("select count(id) from AgentService where orgi = ?1 and status = ?2 and agentno = ?3 and createtime > ?4 and createtime < ?5")
public Long countByAgentForAgentService(String orgi,String status,String agentno,Date start,Date end)
;

public Page<OnlineUser> findByStatusAndCreatetimeLessThan(String status,Date createtime,Pageable paramPageable)
;

@Query("select sum(ringduration) from StatusEvent where ( discaller = ?1 or discalled = ?1 )  and misscall = true and orgi = ?2")
public Long sumByRingDurationFromCallCenter(String ani,String orgi)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualityuser = ?2 and qualitypass = ?3")
public Long countByQualityuserAndQualitypassFromQualityMissionHis(String orgi,String qualityuser,int qualitypass)
;

@Query("select qualityorgan,count(id) as misscount from QualityMissionHis  where orgi = ?1 and qualitystatus=?2  GROUP BY qualityorgan ")
public List<Object> findByQualitystatusGroupbyOrganFromQualityMissionHis(String orgi,String qualitystatus)
;

@Query("select qualityorgan as organid," + "count(id) as misscount," + "count(case when qualitypass = 1 and qualitytype = 'callevent' THEN '1' end) as organpasscallcount," + "count(case when qualitypass = 1 and qualitytype = 'agentservice' THEN '2' end) as organpassagentcount," + "count(case when qualitypass = 1 and qualitytype = 'workorders' THEN '3' end) as organpassworkcount," + "count(case when qualitystatus = 'appeal' THEN '4' end) as disablecount," + "count(case when qualitypass = 0 THEN '5' end) as organnopasscount," + "count(case when qualityappeal = 0 THEN '6' end) as organappealcount," + "count(case when qualityarbitrate = 0 THEN '7' end) as organarbitratecount," + "AVG(date_format(timediff(qualitytime,createtime),'%s')+date_format(timediff(qualitytime,createtime),'%i')*60) as efficiency, " + "count(case when qualitytype = 'callevent' THEN '8' end) as organcallcount," + "count(case when qualitytype = 'agentservice' THEN '9' end) as organagentcount," + "count(case when qualitytype = 'workorders' THEN '10' end) as organworkcount " + "FROM QualityMissionHis " + "where orgi = ?1 GROUP BY qualityorgan")
public List<Map<String,Long>> countQcOrgan(String orgi)
;

@Query("select agentno, count(id) as users from AgentService where orgi = ?1 and userid = ?2 group by agentno")
public List<Object> findByOrgiForDistinctAgent(String orgi,String userid)
;

@Query("select code as dt, count(id) as co from CallMonitor where orgi = ?1 group by code")
public List<Object> findByOrgiAndStatusRangeForAgent(String orgi)
;

@Query("select qualityorgan,count(id) as misscount from QualityMissionHis  where orgi = ?1 and qualityarbitrate=?2  GROUP BY qualityorgan ")
public List<Object> findByQualityarbitrateGroupbyOrganFromQualityMissionHis(String orgi,int qualityarbitrate)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualitydisuser = ?2 and qualitystatus = ?3")
public Long countByQualitydisuserAndQualitystatusFromQualityMissionHis(String orgi,String qualitydisuser,String qualitystatus)
;

@Query("select result , count(id) as records from InviteRecord where orgi = ?1 and agentno = ?2 group by result")
public List<Object> findByOrgiAndUserid(String orgi,String userid)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualityuser = ?2")
public Long countByQualityuserFromQualityMissionHis(String orgi,String qualityuser)
;

@Query("select count(id) from StatusEvent where orgi = ?1 and misscall = ?2 and ?3 < createtime and createtime < ?4")
public Long countByToadyMissCalledFromStatusEvent(String orgi,boolean misscall,Date begin,Date end)
;

@Query("select AVG(duration) from StatusEvent where orgi = ?1 and (discaller = ?2 or discalled = ?3) and ?4 < createtime and createtime < ?5 and misscall = false ")
public Long countByToadyExtDurFromStatusEvent(String orgi,String ani,String called,Date begin,Date end)
;

@Query("select avg(duration) from StatusEvent where ( discaller = ?1 or discalled = ?1 )  and misscall = false and orgi = ?2")
public Long avgByDurationFromCallCenter(String ani,String orgi)
;

@Query("delete from CallOutNames where actid = ?2 AND ORGI = ?1")
public void deleteByOrgiAndActid(String orgi,String actid)
;

@Query("select sum(duration) from StatusEvent where ( discaller = ?1 or discalled = ?1 )  and misscall = false and orgi = ?2")
public Long sumByDurationFromCallCenter(String ani,String orgi)
;

@Query("select count(id) from AgentService where orgi = ?1 and appid = ?2 and createtime > ?3 and createtime < ?4")
public Long countByOrgiAndAppidForCount(String orgi,String appid,Date start,Date end)
;

public OnlineUser findByOrgiAndSessionid(String orgi,String sessionid)
;

@Query("select qualityorgan,count(id) as passcount  from QualityMissionHis  where orgi = ?1 and qualitypass = ?2 and qualitytype=?3 GROUP BY qualityorgan ")
public List<Object> findByQualitypassGroupbyOrganFromQualityMissionHis(String orgi,int qualitypass,String qualitytype)
;

@Query("SELECT e from EkmExperts e WHERE orgi = ?1 AND (bustype = ?2 or bustype = ?3 )")
public Page<Object> findByExperts(String orgi,String exp,String au,Pageable paramPageable)
;

@Query("select avg(sessiontimes) from AgentService where orgi = ?1 and status = ?2 and agentno = ?3 and createtime > ?4 and createtime < ?5")
public Long countByAgentForAvagTime(String orgi,String status,String agentno,Date start,Date end)
;

@Query("select osname, count(id) as users from AgentService where orgi = ?1 and createtime > ?2 and createtime < ?3 and channel = ?4 group by osname")
public List<Object> findByOrgiAndCreatetimeRangeForClient(String orgi,Date start,Date end,String channel)
;

@Query("select count(id) from StatusEvent where (discaller = ?1 or discalled = ?1) and orgi = ?2")
public Long countByAniFromCallCenter(String ani,String orgi)
;

@Query("select topicid,title from ChatMessage c where orgi = ?1 and aiid = ?2 and chatype = 'aireply' and topicid is not null AND title is not null  group by topicid,title order by count(topicid) desc")
public Page<Object> findByOrgiAndMessage(String orgi,String aiid,Pageable paramPageable)
;

public Page<OnlineUser> findByUseridAndOrgi(String userid,String orgi,Pageable page)
;

@Query("select hourstr as dt, count(id) as calls from StatusEvent where orgi = ?1 and datestr = ?2 group by hourstr order by dt asc")
public List<Object> findByOrgiAndDatestrRangeForAgent(String orgi,String start)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualitydisuser = ?2")
public Long countByQualitydisuserFromQualityMissionHis(String orgi,String qualitydisuser)
;

@Query("select qualityorgan,count(id) as misscount from QualityMissionHis  where orgi = ?1 and qualityappeal=?2  GROUP BY qualityorgan ")
public List<Object> findByQualityappealGroupbyOrganFromQualityMissionHis(String orgi,int qualityappeal)
;

@Query("select count(id) from StatusEvent where orgi = ?1 and ?2 < createtime and createtime < ?3")
public Long countByToadyCalledFromStatusEvent(String orgi,Date begin,Date end)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualityuser = ?2 and qualitystatus = ?3")
public Long countByQualityuserAndQualitystatusFromQualityMissionHis(String orgi,String qualityuser,String qualitystatus)
;

public OnlineUser findBySessionidAndOrgi(String paramString,String orgi)
;

@Query("select createdate as dt, count(distinct ip) as ips ,  count(id) as records from UserHistory where orgi = ?1 and model = ?2 and createtime > ?3 and createtime < ?4 group by createdate order by dt asc")
public List<Object> findByOrgiAndCreatetimeRange(String orgi,String model,Date start,Date end)
;

@Query("select s from StatusEvent s  where startrecord<= ?1 AND ORGI = ?2 AND (discalled = ?3 OR discaller= ?4 )")
public List<Object> findByOrgiAndStartrecord(Date startrecord,String orgi,String discalled,String discaller)
;

@Query("select AVG(ringduration) from StatusEvent where orgi = ?1 and (discaller = ?2 or discalled = ?3) and ?4 < createtime and createtime < ?5 and misscall = true ")
public Long countByToadyExtRingFromStatusEvent(String orgi,String ani,String called,Date begin,Date end)
;

@Query("select qualityorgan,AVG(date_format(timediff(qualitytime,createtime),'%s')+date_format(timediff(qualitytime,createtime),'%i')*60) as efficiency  from QualityMissionHis  where orgi = ?1  GROUP BY qualityorgan")
public List<Object> avgByQuanlitytimebyOrganFromQualityMissionHis(String orgi)
;

@Query("select avg(sessiontimes) from AgentService where orgi = ?1 and status = ?2 and userid = ?3")
public Long countByUserForAvagTime(String orgi,String status,String userid)
;

@Query("select count(id) from StatusEvent where discalled = ?1 and orgi = ?2")
public Long countByCalledFromCallCenter(String called,String orgi)
;

@Query("select createdate as dt, count(id) as users from AgentService where orgi = ?1 and createtime > ?2 and createtime < ?3 group by createdate order by dt asc")
public List<Object> findByOrgiAndCreatetimeRangeForAgent(String orgi,Date start,Date end)
;

@Query("select count(id) from StatusEvent where discaller = ?1 and orgi = ?2")
public Long countByCallerFromCallCenter(String caller,String orgi)
;

@Query("select result , count(id) as records from InviteRecord where orgi = ?1 and agentno = ?2 and createtime > ?3 and createtime < ?4 group by result")
public List<Object> findByOrgiAndAgentnoAndCreatetimeRange(String orgi,String agentno,Date start,Date end)
;

@Query("select browser, count(id) as users from AgentService where orgi = ?1 and createtime > ?2 and createtime < ?3 and channel = ?4 group by browser")
public List<Object> findByOrgiAndCreatetimeRangeForBrowser(String orgi,Date start,Date end,String channel)
;

public int countByUseridAndOrgi(String userid,String orgi)
;

@Query("select qualityorgan,count(id) as misscount from QualityMissionHis  where orgi = ?1  GROUP BY qualityorgan ")
public List<Object> findGroupbyOrganFromQualityMissionHis(String orgi)
;

@Query("select count(id) from QualityMissionHis where orgi = ?1 and qualityuser = ?2 and qualityappeal = ?3 and qualityarbitrate = ?4")
public Long countByQualityuserAndQualityappealAndQualityarbitrateFromQualityMissionHis(String orgi,String qualityuser,int qualityappeal,int qualityarbitrate)
;

@Query("select e from EkmKwSearchTag e where orgi = ?1  group by tag order by count(id) desc")
public List<Object> findByOrgiAndTag(String orgi)
;

@Query("select e from EkmKwSearch e where orgi = ?1 and badword = 0 and type = ?2  group by conditions order by count(id) desc")
public List<Object> findByOrgiAndSearch(String orgi,String type)
;

}