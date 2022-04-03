package cn.gson.oasys.controller.user;
 import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.gson.oasys.model.dao.user.DeptDao;
import cn.gson.oasys.model.dao.user.PositionDao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.Dept;
import cn.gson.oasys.model.entity.user.Position;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Interface.UserDao;
@Controller
@RequestMapping("/")
public class DeptController {

@Autowired
 private DeptDao deptdao;

@Autowired
 private UserDao udao;

@Autowired
 private PositionDao pdao;


@RequestMapping(value = "deptedit", method = RequestMethod.GET)
public String changedept(Long deptId,Model model){
    if (deptId != null) {
        Dept dept = deptdao.findOne(deptId);
        model.addAttribute("dept", dept);
    }
    return "user/deptedit";
}


@RequestMapping("deletdept")
public String deletdept(Long deletedeptid){
    Dept dept = deptdao.findOne(deletedeptid);
    List<Position> ps = pdao.findByDeptid(deletedeptid);
    for (Position position : ps) {
        System.out.println(position);
        pdao.delete(position);
    }
    deptdao.delete(dept);
    return "/deptmanage";
}


@RequestMapping(value = "deptedit", method = RequestMethod.POST)
public String adddept(Dept dept,String xg,BindingResult br,Model model){
    System.out.println(br.hasErrors());
    System.out.println(br.getFieldError());
    if (!br.hasErrors()) {
        System.out.println("没有错误");
        Dept adddept = deptdao.save(dept);
        if ("add".equals(xg)) {
            System.out.println("新增拉");
            Position jinli = new Position();
            jinli.setDeptid(adddept.getDeptId());
            jinli.setName("经理");
            Position wenyuan = new Position();
            wenyuan.setDeptid(adddept.getDeptId());
            wenyuan.setName("文员");
            pdao.save(jinli);
            pdao.save(wenyuan);
        }
        if (adddept != null) {
            System.out.println("插入成功");
            model.addAttribute("success", 1);
            return "/deptmanage";
        }
    }
    System.out.println("有错误");
    model.addAttribute("errormess", "错误！~");
    return "user/deptedit";
}


@RequestMapping("readdept")
public String readdept(Long deptId,Model model){
    Dept dept = deptdao.findOne(deptId);
    User deptmanage = null;
    if (dept.getDeptmanager() != null) {
        deptmanage = udao.findOne(dept.getDeptmanager());
        model.addAttribute("deptmanage", deptmanage);
    }
    List<Dept> depts = (List<Dept>) deptdao.findAll();
    List<Position> positions = pdao.findByDeptidAndNameNotLike(1L, "%经理");
    System.out.println(deptmanage);
    List<User> formaluser = new ArrayList<>();
    List<User> deptusers = udao.findByDept(dept);
    for (User deptuser : deptusers) {
        Position position = deptuser.getPosition();
        System.out.println(deptuser.getRealName() + ":" + position.getName());
        if (!position.getName().endsWith("经理")) {
            formaluser.add(deptuser);
        }
    }
    System.out.println(deptusers);
    model.addAttribute("positions", positions);
    model.addAttribute("depts", depts);
    model.addAttribute("deptuser", formaluser);
    model.addAttribute("dept", dept);
    model.addAttribute("isread", 1);
    return "user/deptread";
}


@RequestMapping("deptandpositionchange")
public String deptandpositionchange(Long positionid,Long changedeptid,Long userid,Long deptid,Model model){
    User user = udao.findOne(userid);
    Dept changedept = deptdao.findOne(changedeptid);
    Position position = pdao.findOne(positionid);
    user.setDept(changedept);
    user.setPosition(position);
    udao.save(user);
    System.out.println(deptid);
    model.addAttribute("deptid", deptid);
    return "/readdept";
}


@RequestMapping("deptmanage")
public String deptmanage(Model model){
    List<Dept> depts = (List<Dept>) deptdao.findAll();
    System.out.println(depts);
    model.addAttribute("depts", depts);
    return "user/deptmanage";
}


@RequestMapping("deptmanagerchange")
public String deptmanagerchange(Long positionid,Long changedeptid,Long oldmanageid,Long newmanageid,Long deptid,Model model){
    System.out.println("oldmanageid:" + oldmanageid);
    System.out.println("newmanageid:" + newmanageid);
    Dept deptnow = deptdao.findOne(deptid);
    if (oldmanageid != null) {
        User oldmanage = udao.findOne(oldmanageid);
        Position namage = oldmanage.getPosition();
        Dept changedept = deptdao.findOne(changedeptid);
        Position changeposition = pdao.findOne(positionid);
        oldmanage.setDept(changedept);
        oldmanage.setPosition(changeposition);
        udao.save(oldmanage);
        if (newmanageid != null) {
            User newmanage = udao.findOne(newmanageid);
            newmanage.setPosition(namage);
            deptnow.setDeptmanager(newmanageid);
            deptdao.save(deptnow);
            udao.save(newmanage);
        } else {
            deptnow.setDeptmanager(null);
            deptdao.save(deptnow);
        }
    } else {
        User newmanage = udao.findOne(newmanageid);
        Position manage = pdao.findByDeptidAndNameLike(deptid, "%经理").get(0);
        newmanage.setPosition(manage);
        deptnow.setDeptmanager(newmanageid);
        deptdao.save(deptnow);
        udao.save(newmanage);
    }
    model.addAttribute("deptid", deptid);
    return "/readdept";
}


}