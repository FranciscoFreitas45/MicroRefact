package cn.gson.oasys.controller.daymanage;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.alibaba.fastjson.JSONObject;
import cn.gson.oasys.model.dao.daymanagedao.DaymanageDao;
import cn.gson.oasys.model.dao.system.StatusDao;
import cn.gson.oasys.model.dao.system.TypeDao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.schedule.ScheduleList;
import cn.gson.oasys.model.entity.system.SystemStatusList;
import cn.gson.oasys.model.entity.system.SystemTypeList;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.services.daymanage.DaymanageServices;
import cn.gson.oasys.services.process.ProcessService;
import cn.gson.oasys.Interface.DaymanageDao;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.ProcessService;
@Controller
@RequestMapping("/")
public class DaymanageController {

@Autowired
 private DaymanageDao daydao;

@Autowired
 private UserDao udao;

@Autowired
 private DaymanageServices dayser;

@Autowired
 private StatusDao statusdao;

@Autowired
 private TypeDao typedao;

@Autowired
 private ProcessService ps;


@RequestMapping("addandchangeday")
public String addandchangeday(ScheduleList scheduleList,String shareuser,BindingResult br,Long userid){
    User user = udao.findOne(userid);
    System.out.println(shareuser);
    List<User> users = new ArrayList<>();
    System.out.println(users.size());
    StringTokenizer st = new StringTokenizer(shareuser, ";");
    while (st.hasMoreElements()) {
        users.add(udao.findByUserName(st.nextToken()));
    }
    scheduleList.setUser(user);
    if (users.size() > 0) {
        scheduleList.setUsers(users);
    }
    System.out.println(scheduleList);
    daydao.save(scheduleList);
    return "/daymanage";
}


@RequestMapping("mycalendarload")
@ResponseBody
public List<ScheduleList> mycalendarload(Long userid,HttpServletResponse response){
    List<ScheduleList> se = dayser.aboutmeschedule(userid);
    return se;
}


@RequestMapping("daymanagepaging")
public String daymanagepaging(Long userid,Model model,int page,int size){
    List<SystemTypeList> types = typedao.findByTypeModel("aoa_schedule_list");
    List<SystemStatusList> statuses = statusdao.findByStatusModel("aoa_schedule_list");
    Sort sort = new Sort(new Order(Direction.ASC, "user"));
    Pageable pa = new PageRequest(page, size, sort);
    User user = udao.findOne(userid);
    Page<ScheduleList> myday = daydao.findByUser(user, pa);
    List<ScheduleList> scheduleLists = myday.getContent();
    model.addAttribute("types", types);
    model.addAttribute("statuses", statuses);
    model.addAttribute("schedules", scheduleLists);
    model.addAttribute("page", myday);
    model.addAttribute("url", "daymanagepaging");
    model.addAttribute("ismyday", 1);
    return "daymanage/daymanagepaging";
}


@RequestMapping("aboutmeday")
public String aboutmeday(Long userid,Model model,int page,int size){
    List<SystemTypeList> types = typedao.findByTypeModel("aoa_schedule_list");
    List<SystemStatusList> statuses = statusdao.findByStatusModel("aoa_schedule_list");
    Sort sort = new Sort(new Order(Direction.ASC, "user"));
    Pageable pa = new PageRequest(page, size, sort);
    User user = udao.findOne(userid);
    List<User> users = new ArrayList<>();
    users.add(user);
    Page<ScheduleList> aboutmeday = daydao.findByUsers(users, pa);
    List<ScheduleList> scheduleLists = aboutmeday.getContent();
    model.addAttribute("schedules", scheduleLists);
    model.addAttribute("types", types);
    model.addAttribute("statuses", statuses);
    model.addAttribute("page", aboutmeday);
    model.addAttribute("url", "aboutmedaypaging");
    return "daymanage/daymanage";
}


@RequestMapping("daymanage")
public String daymanage(Long userid,Model model,int page,int size){
    List<SystemTypeList> types = typedao.findByTypeModel("aoa_schedule_list");
    List<SystemStatusList> statuses = statusdao.findByStatusModel("aoa_schedule_list");
    List<Order> orders = new ArrayList<>();
    orders.add(new Order(Direction.DESC, "statusId"));
    orders.add(new Order(Direction.DESC, "createTime"));
    Sort sort = new Sort(orders);
    Pageable pa = new PageRequest(page, size, sort);
    User user = udao.findOne(userid);
    Page<ScheduleList> myday = daydao.findByUser(user, pa);
    List<ScheduleList> scheduleLists = myday.getContent();
    model.addAttribute("schedules", scheduleLists);
    model.addAttribute("types", types);
    model.addAttribute("statuses", statuses);
    model.addAttribute("page", myday);
    model.addAttribute("url", "daymanagepaging");
    model.addAttribute("ismyday", 1);
    return "daymanage/daymanage";
}


@RequestMapping("dayremove")
public String dayremove(Long rcid){
    ScheduleList rc = daydao.findOne(rcid);
    daydao.delete(rc);
    return "/daymanage";
}


@RequestMapping("dayedit")
public String dayedit(Long rcid,int page,int size,Model model){
    ps.user(page, size, model);
    List<SystemTypeList> types = typedao.findByTypeModel("aoa_schedule_list");
    List<SystemStatusList> statuses = statusdao.findByStatusModel("aoa_schedule_list");
    ScheduleList rc = null;
    if (rcid != null) {
        rc = daydao.findOne(rcid);
        System.out.println(rc);
    }
    model.addAttribute("types", types);
    model.addAttribute("statuses", statuses);
    model.addAttribute("rc", rc);
    return "daymanage/editday";
}


@RequestMapping("daycalendar")
public String daycalendar(){
    return "daymanage/daycalendar";
}


@RequestMapping("aboutmedaypaging")
public String aboutmedaypaging(Long userid,Model model,int page,int size){
    List<SystemTypeList> types = typedao.findByTypeModel("aoa_schedule_list");
    List<SystemStatusList> statuses = statusdao.findByStatusModel("aoa_schedule_list");
    Sort sort = new Sort(new Order(Direction.ASC, "user"));
    Pageable pa = new PageRequest(page, size, sort);
    User user = udao.findOne(userid);
    List<User> users = new ArrayList<>();
    users.add(user);
    Page<ScheduleList> aboutmeday = daydao.findByUsers(users, pa);
    List<ScheduleList> scheduleLists = aboutmeday.getContent();
    model.addAttribute("schedules", scheduleLists);
    model.addAttribute("types", types);
    model.addAttribute("statuses", statuses);
    model.addAttribute("page", aboutmeday);
    model.addAttribute("url", "aboutmedaypaging");
    return "daymanage/daymanagepaging";
}


}