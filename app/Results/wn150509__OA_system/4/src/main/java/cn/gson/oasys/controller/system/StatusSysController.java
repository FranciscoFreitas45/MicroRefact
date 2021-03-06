package cn.gson.oasys.controller.system;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.gson.oasys.common.formValid.BindingResultVOUtil;
import cn.gson.oasys.common.formValid.MapToList;
import cn.gson.oasys.common.formValid.ResultEnum;
import cn.gson.oasys.common.formValid.ResultVO;
import cn.gson.oasys.model.dao.system.StatusDao;
import cn.gson.oasys.model.entity.system.SystemStatusList;
import cn.gson.oasys.services.system.StatusService;
@Controller
@RequestMapping("/")
public class StatusSysController {

 private Logger log;

@Autowired
 private  StatusDao statusDao;

@Autowired
 private  StatusService statusService;


@RequestMapping("statustable")
public String statusTable(HttpServletRequest req){
    if (!StringUtils.isEmpty(req.getParameter("name"))) {
        String name = "%" + req.getParameter("name") + "%";
        req.setAttribute("statusList", statusDao.findByStatusNameLikeOrStatusModelLike(name, name));
    } else {
        Iterable<SystemStatusList> statusList = statusDao.findAll();
        req.setAttribute("statusList", statusList);
    }
    return "systemcontrol/statustable";
}


@RequestMapping("testsysstatus")
public String testsysstatus(HttpServletRequest req){
    Iterable<SystemStatusList> statusList = statusDao.findAll();
    req.setAttribute("statusList", statusList);
    return "systemcontrol/statusmanage";
}


@RequestMapping("deletestatus")
public String deleteStatus(HttpServletRequest req){
    Long statusId = Long.parseLong(req.getParameter("id"));
    statusService.deleteStatus(statusId);
    return "forward:/testsysstatus";
}


@RequestMapping("statuscheck")
public String testMess(HttpServletRequest req,SystemStatusList menu,BindingResult br){
    req.setAttribute("menuObj", menu);
    System.out.println(menu);
    // ????????????ResultVO??????????????????????????????ResultEnum.SUCCESS.getCode()???????????????200??????????????????????????????
    ResultVO res = BindingResultVOUtil.hasErrors(br);
    // ????????????
    if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
        List<Object> list = new MapToList<>().mapToList(res.getData());
        req.setAttribute("errormess", list.get(0).toString());
        // ??????????????????????????????????????????????????????
        System.out.println("list???????????????????????????" + menu);
        System.out.println("list????????????:" + list);
        System.out.println("list???????????????:" + list.get(0));
        System.out.println("?????????????????????????????????" + list.get(0).toString());
        // ?????????info?????????????????????????????????
        log.info("getData:{}", res.getData());
        log.info("getCode:{}", res.getCode());
        log.info("getMsg:{}", res.getMsg());
    } else // ?????????????????????????????????????????????
    {
        HttpSession session = req.getSession();
        // ????????????????????????????????????????????????"session.setAttribute("getId",getId);",?????????????????????remove??????
        if (!StringUtils.isEmpty(session.getAttribute("statusid"))) {
            // ???????????????????????????menuID???
            Long menuId = (Long) session.getAttribute("statusid");
            menu.setStatusId(menuId);
            session.removeAttribute("statusid");
        }
        // ??????????????????
        statusService.save(menu);
        System.out.println("?????????????????????");
        req.setAttribute("success", "??????????????????");
    }
    System.out.println("???????????????????????????????????????" + menu);
    return "systemcontrol/statusedit";
}


@RequestMapping("statusedit")
public String typeEdit(HttpServletRequest req){
    if (!StringUtils.isEmpty(req.getParameter("statusid"))) {
        Long statusid = Long.parseLong(req.getParameter("statusid"));
        SystemStatusList statusList = statusDao.findOne(statusid);
        req.setAttribute("status", statusList);
        HttpSession session = req.getSession();
        session.setAttribute("statusid", statusid);
    }
    return "systemcontrol/statusedit";
}


}