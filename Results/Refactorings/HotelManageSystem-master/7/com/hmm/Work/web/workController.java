import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.Work.entity.ExtworkForm;
import com.hmm.Work.entity.Work;
import com.hmm.Work.entity.WorkEmpChart;
import com.hmm.Work.entity.BcardDTO;
import com.hmm.Work.entity.ExtTotalworkForm;
import com.hmm.Work.entity.WorkEmpDTO;
import com.hmm.Work.entity.WorkQueryDTO;
import com.hmm.Work.entity.WorkRecord;
import com.hmm.Work.entity.WorkTatalRecord;
import com.hmm.Work.entity.Workchart;
import com.hmm.Work.service.workService;
import com.hmm.activiti.util.WorkflowVariable;
import com.hmm.calendars.entity.Calendar;
import com.hmm.calendars.entity.SchedulEvent;
import com.hmm.calendars.entity.SchedulQueryDTO;
import com.hmm.calendars.service.SchedulEventService;
import com.hmm.common.SessionUtil;
import com.hmm.common.beans.BeanUtils;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.department.entity.Department;
import com.hmm.department.service.IDeptService;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.service.EmployeeService;
import com.hmm.employee.util.ExtForm;
import com.hmm.finance.financeReport.domain.FinanceReport;
import com.hmm.leave.entity.LeaveDTO;
import com.hmm.leave.service.ILeaveService;
import com.hmm.travel.service.TravelService;
@RestController
@RequestMapping("work")
public class workController {

@Autowired
 private  workService workServiceImpl;

@Autowired
 private  EmployeeService employServiceImpl;

@Autowired
 private  IdentityService identityService;

@Autowired
 private  IDeptService iDeptService;

@Autowired
 private  ILeaveService iLeaveServiceimpl;

@Autowired
 private  TravelService travelService;

@Autowired
 private  SchedulEventService schedulEventService;

 private  Logger logger;


@RequestMapping("/deletes")
public ExtAjaxResponse deletes(Long[] ids){
    try {
        if (null != ids) {
            workServiceImpl.deleteByIds(ids);
            return new ExtAjaxResponse(true, "删除成功");
        } else {
            return new ExtAjaxResponse(true, "删除失败");
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return new ExtAjaxResponse(true, "删除失败");
    }
}


@Override
public int compare(Workchart stu1,Workchart stu2){
    return stu1.getQuarter().compareTo(stu2.getQuarter());
}


@RequestMapping("/getEmp")
public Page<WorkEmpDTO> getPageEmp(WorkQueryDTO queryDTO,ExtjsPageRequest pageRequest,HttpSession httpSession){
    String userId = SessionUtil.getUserName(httpSession);
    queryDTO.setUserName(userId);
    return workServiceImpl.findAllByDTO(WorkQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
}


@GetMapping
public Page<WorkEmpDTO> getPage(WorkQueryDTO queryDTO,ExtjsPageRequest pageRequest,HttpSession httpSession){
    String userId = SessionUtil.getUserName(httpSession);
    if (null != userId) {
        if (queryDTO.getDeptName() != null) {
            Department department = iDeptService.findByDeptName(queryDTO.getDeptName());
            queryDTO.setDepartment(department);
        }
        List<Group> groupListuser = identityService.createGroupQuery().groupMember(userId).list();
        String[] groupNames = new String[groupListuser.size()];
        for (int i = 0; i < groupNames.length; i++) {
            groupNames[i] = groupListuser.get(i).getId();
        }
        String groupUserList = ArrayUtils.toString(groupNames);
        String getRequestType = queryDTO.getRequestType();
        if (null != getRequestType) {
            if (queryDTO.getRequestType().indexOf("personal") != -1) {
                queryDTO.setUserName(userId);
                return workServiceImpl.findAllByDTO(WorkQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
            } else {
                if (groupUserList.indexOf("Manager") != -1 || groupUserList.indexOf("Admin") != -1) {
                    return workServiceImpl.findAllByDTO(WorkQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
                } else {
                    queryDTO.setUserName(userId);
                    return workServiceImpl.findAllByDTO(WorkQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
                }
            }
        } else {
            return workServiceImpl.findAllByDTO(WorkQueryDTO.getWhereClause(queryDTO), pageRequest.getPageable());
        }
    } else {
        return null;
    }
}


@RequestMapping("Update")
@ResponseBody
public ExtAjaxResponse updateWork(HttpSession httpSession){
    String userId = SessionUtil.getUserName(httpSession);
    return workServiceImpl.UpdateWork(userId);
}


@RequestMapping("tatalrecord")
public ExtTotalworkForm findtotalperson(){
    // 
    int totalPerson = schedulEventService.findTotalPerson();
    int exactlyPerson = workServiceImpl.findExactlyPerson();
    // 补卡
    int tatalPersonNomal = workServiceImpl.findTatalPersonNomal();
    int tatalPersonLate = workServiceImpl.findTatalPersonLate();
    int tatalPersonleaveEarly = workServiceImpl.findTatalPersonleaveEarly();
    int tatalPersonOvertime = workServiceImpl.findTatalPersonOvertime();
    int tatalPersonLeave = iLeaveServiceimpl.findTatalPersonLeave();
    int tatalPersonTravel = travelService.findTatalPersonTravel();
    WorkTatalRecord record = new WorkTatalRecord();
    record.setExactlyPerson(exactlyPerson);
    record.setTatalPersonLate(tatalPersonLate);
    record.setTatalPersonLeave(tatalPersonLeave);
    record.setTatalPersonNomal(tatalPersonNomal);
    record.setTatalPersonOvertime(tatalPersonOvertime);
    record.setTatalPersonTravel(tatalPersonTravel);
    record.setTotalPerson(totalPerson);
    record.setTatalPersonleaveEarly(tatalPersonleaveEarly);
    return new ExtTotalworkForm(true, record);
}


@PutMapping(value = "{workid}")
public ExtAjaxResponse update(Long workid,WorkEmpDTO empDTO){
    try {
        Work work = workServiceImpl.findById(workid).get();
        BeanUtils.copyProperties(empDTO, work);
        workServiceImpl.save(work);
        return new ExtAjaxResponse(true, "修改成功");
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return new ExtAjaxResponse(false, "修改失败");
    }
}


@RequestMapping("workEmpChart")
public List<WorkEmpChart> findWorkEmpcahrt(Integer year,HttpSession httpSession){
    try {
        String userName = SessionUtil.getUserName(httpSession);
        List<WorkEmpChart> workcharts = new ArrayList<>();
        List<Map<Object, Object>> listleave = iLeaveServiceimpl.findByyearAndOntudytimeleave(year, userName);
        List<Map<Object, Object>> listtravel = travelService.findByyearAndOntudytimetravel(year, userName);
        List<Map<Object, Object>> listlackcard = workServiceImpl.findByyearAndOntudytimelackcard(year, userName);
        List<Map<Object, Object>> listlate = workServiceImpl.findByyearAndOntudytimelate(year, userName);
        List<Map<Object, Object>> listleaveEarly = workServiceImpl.findByyearAndOntudytimeleaveEary(year, userName);
        for (int i = 1; i <= 12; i++) {
            WorkEmpChart workchart = new WorkEmpChart();
            workchart.setQuarter(i);
            for (Map<Object, Object> map1 : listleave) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("leave") != -1) {
                                    workchart.setLeave((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLeave((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listtravel) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("travel") != -1) {
                                    workchart.setTravel((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setTravel((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listlackcard) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("lackcard") != -1) {
                                    workchart.setLackcard((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLackcard((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listlate) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("late") != -1) {
                                    workchart.setLate((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLate((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listleaveEarly) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("leaveEarly") != -1) {
                                    workchart.setLeaveEarly((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLeaveEarly((long) 0);
                        }
                    }
                }
            }
            workcharts.add(workchart);
        }
        workcharts.sort(new Comparator<WorkEmpChart>() {

            @Override
            public int compare(WorkEmpChart stu1, WorkEmpChart stu2) {
                return stu1.getQuarter().compareTo(stu2.getQuarter());
            }
        });
        return workcharts;
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return null;
    }
}


@RequestMapping("workChart")
public List<Workchart> findWorkcahrt(Integer year){
    try {
        // List<Workchart> list = new ArrayList<>();
        // List<Integer> existMonth = new ArrayList<>();
        // SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = new Date();
        // Integer year = 2018;
        // 
        List<Workchart> workcharts = new ArrayList<>();
        List<Map<Object, Object>> listleave = iLeaveServiceimpl.findleave(year);
        List<Map<Object, Object>> listtravel = travelService.findtravel(year);
        List<Map<Object, Object>> listlackcard = workServiceImpl.findlackcard(year);
        List<Map<Object, Object>> listlate = workServiceImpl.findlate(year);
        List<Map<Object, Object>> listleaveEarly = workServiceImpl.findleaveEarly(year);
        for (int i = 1; i <= 12; i++) {
            Workchart workchart = new Workchart();
            workchart.setQuarter(i);
            for (Map<Object, Object> map1 : listleave) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("leave") != -1) {
                                    workchart.setLeave((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLeave((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listtravel) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("travel") != -1) {
                                    workchart.setTravel((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setTravel((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listlackcard) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("lackcard") != -1) {
                                    workchart.setLackcard((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLackcard((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listlate) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("late") != -1) {
                                    workchart.setLate((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLate((long) 0);
                        }
                    }
                }
            }
            for (Map<Object, Object> map1 : listleaveEarly) {
                for (Object k : map1.keySet()) {
                    if (k.toString().indexOf("quarter") != -1) {
                        if ((Integer) map1.get(k) == i) {
                            for (Object k1 : map1.keySet()) {
                                if (k1.toString().indexOf("leaveEarly") != -1) {
                                    workchart.setLeaveEarly((Long) map1.get(k1));
                                }
                            }
                        } else {
                            workchart.setLeaveEarly((long) 0);
                        }
                    }
                }
            }
            workcharts.add(workchart);
        }
        workcharts.sort(new Comparator<Workchart>() {

            @Override
            public int compare(Workchart stu1, Workchart stu2) {
                return stu1.getQuarter().compareTo(stu2.getQuarter());
            }
        });
        return workcharts;
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return null;
    }
}


@DeleteMapping(value = "{workid}")
public ExtAjaxResponse delete(Long workid){
    try {
        Work work = workServiceImpl.findById(workid).get();
        if (null != work) {
            workServiceImpl.deleteById(workid);
            return new ExtAjaxResponse(true, "删除成功");
        } else {
            return new ExtAjaxResponse(false, "数据错误");
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return new ExtAjaxResponse(false, "异常错误");
    }
}


@RequestMapping("record")
public ExtworkForm attenceTotalTime(HttpSession httpSession){
    String userName = SessionUtil.getUserName(httpSession);
    if (null != userName) {
        WorkRecord record = new WorkRecord();
        int totalLate = workServiceImpl.findByEmployAndOntudytimelate(userName);
        int totalleaveEarly = workServiceImpl.findByEmployAndOntudytimeleaveEarly(userName);
        int totalCard = workServiceImpl.findByEmployAndOntudytimelackCard(userName);
        // Integer totalnormal = workServiceImpl.findByEmployAndOntudytimenormal(userName);
        float overtime = workServiceImpl.findattenceTotalovertime(userName);
        float worktime = workServiceImpl.findattenceTotalworktime(userName);
        float ExactlyTime = worktime + overtime;
        float attenceTotalTime = schedulEventService.findattenceTotalTime(userName);
        float totalday = schedulEventService.findWorkTotalDay(userName);
        float leaveTimes = iLeaveServiceimpl.findTotalLeaveTimes(userName);
        float travelAttence = travelService.findTotalTravelAllowance(userName);
        // Long attenceTotalTime = map.get("attenceTotalTime");
        float attenceTotalTime2 = ((attenceTotalTime) / 60) / 60 - totalday;
        record.setAttenceTotalTime(attenceTotalTime2);
        record.setExactlyTime(ExactlyTime);
        record.setOvertime(overtime);
        record.setTotalCard(totalCard);
        record.setTotalLate(totalLate);
        record.setTotalleaveEarly(totalleaveEarly);
        record.setWorktime(worktime);
        record.setLeaveTimes(leaveTimes);
        record.setTravelAttence(travelAttence);
        return new ExtworkForm(true, record);
    } else {
        return null;
    }
}


@RequestMapping("/test")
public List<Map<Object,Object>> find(){
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    Integer aString = 2018;
    String year = formatter2.format(date);
    // year = formatter2.parse(aString);
    List<Map<Object, Object>> listleave = workServiceImpl.findlate(2018);
    return listleave;
}


@RequestMapping("add")
@ResponseBody
public ExtAjaxResponse saveWork(HttpSession httpSession){
    String userId = SessionUtil.getUserName(httpSession);
    return workServiceImpl.save(userId);
}


}