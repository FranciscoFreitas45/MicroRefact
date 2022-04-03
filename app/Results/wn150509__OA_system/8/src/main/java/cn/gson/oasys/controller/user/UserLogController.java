package cn.gson.oasys.controller.user;
 import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
import cn.gson.oasys.model.dao.scheduledao.ScheduleDao;
import cn.gson.oasys.model.dao.taskdao.TaskDao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.dao.user.UserLogDao;
import cn.gson.oasys.model.dao.user.UserLogRecordDao;
import cn.gson.oasys.model.dao.user.UserLogRecordService;
import cn.gson.oasys.model.dao.user.UserLogService;
import cn.gson.oasys.model.dao.user.UserService;
import cn.gson.oasys.model.entity.schedule.ScheduleList;
import cn.gson.oasys.model.entity.user.LoginRecord;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.model.entity.user.UserLog;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.TaskDao;
import cn.gson.oasys.Interface.ScheduleDao;
@Controller
@RequestMapping("/")
public class UserLogController {

@Autowired
 private  UserLogDao userLogDao;

@Autowired
 private  UserLogService userLogService;

@Autowired
 private  UserDao uDao;

@Autowired
 private  TaskDao taskDao;

@Autowired
 private  UserLogRecordDao userLogRecordDao;

@Autowired
 private  ScheduleDao scheduleDao;

@Autowired
 private  UserLogRecordService userLogRecordService;


@RequestMapping("morelogtable")
public String test3dfrt(int page,HttpSession session,Model model,String basekey,String time,String icon){
    getuserlog(page, session, model, basekey, time, icon);
    return "user/userlogmanagetable";
}


public void setToFirstDay(Calendar calendar){
    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
        calendar.add(Calendar.DATE, -1);
    }
}


public int compare(Map.Entry<String,Integer> obj1,Map.Entry<String,Integer> obj2){
    return obj2.getValue() - obj1.getValue();
}


@RequestMapping("morelogrecordtable")
public String test3dfrt342(int page,HttpSession session,Model model,String basekey,String time,String icon){
    getuserlogrecord(page, session, model, basekey, time, icon);
    return "user/userlogrecordmanagetable";
}


@RequestMapping("morelog")
public String test3df(int page,HttpSession session,Model model,String basekey,String time,String icon){
    System.out.println("11" + basekey);
    getuserlog(page, session, model, basekey, time, icon);
    return "user/userlogmanage";
}


@RequestMapping("morelogrecord")
public String test3df342(int page,HttpSession session,Model model,String basekey,String time,String icon){
    System.out.println("11" + basekey);
    getuserlogrecord(page, session, model, basekey, time, icon);
    return "user/userlogrecordmanage";
}


public void setTwo(Model model,String basekey,Object time,Object icon){
    if (!StringUtils.isEmpty(time)) {
        model.addAttribute("time", time);
        model.addAttribute("icon", icon);
        model.addAttribute("sort", "&time=" + time + "&icon=" + icon);
    }
    if (!StringUtils.isEmpty(basekey)) {
        model.addAttribute("basekey", basekey);
        model.addAttribute("sort", "&basekey=" + basekey);
    }
}


public ArrayList<Map.Entry<String,Integer>> sortMap(Map map){
    List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

        public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
            return obj2.getValue() - obj1.getValue();
        }
    });
    return (ArrayList<Entry<String, Integer>>) entries;
}


public void getuserlogrecord(int page,HttpSession session,Model model,String basekey,String time,String icon){
    long userid = Long.valueOf(session.getAttribute("userId") + "");
    setTwo(model, basekey, time, icon);
    Page<LoginRecord> page4 = userLogRecordService.ulogpaging(page, basekey, userid, time);
    model.addAttribute("page", page4);
    model.addAttribute("userloglist", page4.getContent());
    System.out.println("debug");
    for (LoginRecord loginRecord : page4.getContent()) {
        System.out.println("000" + loginRecord);
    }
    model.addAttribute("url", "morelogrecordtable");
}


public void getuserlog(int page,HttpSession session,Model model,String basekey,String time,String icon){
    long userid = Long.valueOf(session.getAttribute("userId") + "");
    setTwo(model, basekey, time, icon);
    Page<UserLog> page3 = userLogService.ulogpaging(page, basekey, userid, time);
    model.addAttribute("page", page3);
    model.addAttribute("userloglist", page3.getContent());
    model.addAttribute("url", "morelogtable");
}


@RequestMapping("countweeklogin")
public String dfsa(HttpServletResponse response){
    Integer[] r = new Integer[7];
    Calendar calendar = Calendar.getInstance();
    setToFirstDay(calendar);
    for (int i = 0; i < 7; i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        r[i] = userLogRecordDao.countlog(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
    }
    String json = JSONObject.toJSONString(r);
    System.out.println(json);
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/json;charset=UTF-8");
    response.getWriter().write(json);
    return null;
}


}