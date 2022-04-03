package com.lingxiang2014.controller.admin;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import com.lingxiang2014.Message;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.StaticBonudsRank;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.AreaService;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.CaptchaService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.MemberRankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.MessageService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.util.ChangeDate;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.CaptchaService;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberRankService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.MessageService;
import com.lingxiang2014.DTO.Setting;
import com.lingxiang2014.DTO.Member;
import com.lingxiang2014.DTO.StaticBonudsRank;
import com.lingxiang2014.DTO.Bonuds;
import com.lingxiang2014.DTO.Message;
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware{

@Value("${system.name}")
 private  String systemName;

@Value("${system.version}")
 private  String systemVersion;

@Value("${system.description}")
 private  String systemDescription;

@Value("${system.show_powered}")
 private  Boolean systemShowPowered;

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;

@Resource(name = "captchaServiceImpl")
 private  CaptchaService captchaService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;

@Resource(name = "messageServiceImpl")
 private  MessageService messageService;

 private  ServletContext servletContext;


@RequestMapping(value = "/area", method = RequestMethod.GET)
@ResponseBody
public Map<Long,String> area(Long parentId){
    List<Area> areas = new ArrayList<Area>();
    Area parent = areaService.find(parentId);
    if (parent != null) {
        areas = new ArrayList<Area>(parent.getChildren());
    } else {
        areas = areaService.findRoots();
    }
    Map<Long, String> options = new HashMap<Long, String>();
    for (Area area : areas) {
        options.put(area.getId(), area.getName());
    }
    return options;
}


@RequestMapping(value = "/captcha", method = RequestMethod.GET)
public void image(String captchaId,HttpServletRequest request,HttpServletResponse response){
    if (StringUtils.isEmpty(captchaId)) {
        captchaId = request.getSession().getId();
    }
    String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
    String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
    response.addHeader(pragma, value);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");
    ServletOutputStream servletOutputStream = null;
    try {
        servletOutputStream = response.getOutputStream();
        BufferedImage bufferedImage = captchaService.buildImage(captchaId);
        ImageIO.write(bufferedImage, "jpg", servletOutputStream);
        servletOutputStream.flush();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(servletOutputStream);
    }
}


@RequestMapping(value = "/confirmFenhong", method = RequestMethod.POST)
@ResponseBody
public Message confirmFenhong(){
    List<Member> list = memberService.findAll();
    DateTime now = new DateTime();
    DateTime date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    Date beginDate = date.toDate();
    Date endDate = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 5).toDate();
    BigDecimal inCome = depositService.count(null, com.lingxiang2014.entity.Deposit.Type.memberRecharge, beginDate, endDate);
    inCome = inCome.add(depositService.count(null, com.lingxiang2014.entity.Deposit.Type.adminRecharge, beginDate, endDate));
    BigDecimal percent = new BigDecimal(100);
    Setting setting = SettingUtils.get();
    BigDecimal dividInCome = inCome.multiply(setting.getBoundsRate());
    Integer count = 0;
    if (list == null) {
        count = 1;
    } else {
        count = list.size();
    }
    if (count == 0) {
        count = 1;
    }
    BigDecimal everyInCome = dividInCome.divide(new BigDecimal(count), 10, BigDecimal.ROUND_HALF_UP);
    for (Member member : list) {
        if (member.getIsStaticDevideBonuds()) {
            // 获得该会员的静态分红等级
            StaticBonudsRank staticBonudsRank = member.getStaticBonudsRank();
            // 获取当前会员静态已经分红的金额
            BigDecimal staticDevidedBonuds = member.getStaticDevidedBonuds();
            if (staticBonudsRank == null) {
                continue;
            }
            // 静态分红还没有拿完
            if (staticBonudsRank.getAmount().compareTo(staticDevidedBonuds) > 0 && member.getIsStaticDevideBonuds()) {
                Bonuds bonuds = new Bonuds();
                bonuds.setMember(member);
                bonuds.setOperator("系统");
                bonuds.setType(Type.staticBonuds);
                String memo = "系统静态分红，";
                if (everyInCome.compareTo(staticBonudsRank.getEveryAmount()) > 0) {
                    // 调整今天应得的分红金额
                    everyInCome = staticBonudsRank.getScale();
                }
                member.setStaticDevidedBonuds(member.getStaticDevidedBonuds().add(everyInCome));
                memo = memo + "会员：" + member.getUsername() + "静态分红：" + everyInCome;
                memo = memo + "。分红之前现金账户：" + member.getBalance();
                member.setBalance(member.getBalance().add(everyInCome.multiply(setting.getaRate())));
                memo = memo + "。其中" + setting.getaRate().multiply(percent) + "%进入现金账户：" + everyInCome.multiply(setting.getaRate());
                memo = memo + "。分红之后现金账户：" + member.getBalance();
                memo = memo + "。分红之前复投币账户：" + member.getBalance2();
                member.setBalance2(member.getBalance2().add(everyInCome.multiply(setting.getbRate())));
                memo = memo + "。其中" + setting.getbRate().multiply(percent) + "%进入复投币账户：" + everyInCome.multiply(setting.getbRate());
                memo = memo + "。分红之后复投币账户：" + member.getBalance2();
                memo = memo + "。分红之前理财币账户：" + member.getBalance3();
                member.setBalance3(member.getBalance3().add(everyInCome.multiply(setting.getcRate())));
                memo = memo + "。其中" + setting.getcRate().multiply(percent) + "%进入理财币：" + everyInCome.multiply(setting.getcRate());
                memo = memo + "。分红之后理财币账户：" + member.getBalance3();
                bonuds.setMemo(memo);
                bonuds.setBalance(everyInCome);
                // 清空今日分红数据
                member.setTodayResults(new BigDecimal(0));
                memberService.update(member);
                bonudsService.save(bonuds);
            }
        }
    }
    return Message.success("操作成功！");
}


@RequestMapping("/unauthorized")
public String unauthorized(HttpServletRequest request,HttpServletResponse response){
    String requestType = request.getHeader("X-Requested-With");
    if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
        response.addHeader("loginStatus", "unauthorized");
        try {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    return "/admin/common/unauthorized";
}


public void setServletContext(ServletContext servletContext){
    this.servletContext = servletContext;
}


@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index(ModelMap model){
    DateTime now = new DateTime();
    DateTime date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    Date beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    Date endDate = date.toDate();
    model.addAttribute("systemName", systemName);
    model.addAttribute("systemVersion", systemVersion);
    model.addAttribute("systemDescription", systemDescription);
    model.addAttribute("systemShowPowered", systemShowPowered);
    model.addAttribute("javaVersion", System.getProperty("java.version"));
    model.addAttribute("javaHome", System.getProperty("java.home"));
    model.addAttribute("osName", System.getProperty("os.name"));
    model.addAttribute("osArch", System.getProperty("os.arch"));
    model.addAttribute("serverInfo", servletContext.getServerInfo());
    model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
    model.addAttribute("memberCount", memberService.count());
    model.addAttribute("memberCount1", memberService.count(memberRankService.findDefault()));
    model.addAttribute("memberCount2", memberService.count() - memberService.count(memberRankService.findDefault()));
    model.addAttribute("memberCount3", memberService.count(null, beginDate, endDate));
    model.addAttribute("memberCount4", memberService.count(memberRankService.findDefault(), beginDate, endDate));
    model.addAttribute("memberCount5", memberService.count(null, beginDate, endDate) - memberService.count(memberRankService.findDefault(), beginDate, endDate));
    model.addAttribute("unreadMessageCount", messageService.count(null, false));
    model.addAttribute("memberBalance", memberService.countBalance(null, beginDate, endDate));
    model.addAttribute("memberBalance0", memberService.countBalance(0, beginDate, endDate));
    model.addAttribute("memberBalance1", memberService.countBalance(1, beginDate, endDate));
    model.addAttribute("memberBalance2", memberService.countBalance(2, beginDate, endDate));
    model.addAttribute("memberBalance3", memberService.countBalance(3, beginDate, endDate));
    model.addAttribute("balance", depositService.countBalance(null, null, null));
    model.addAttribute("balance1", bonudsService.countBalance(null, beginDate, endDate));
    model.addAttribute("balance2", depositService.countBalance(null, beginDate, endDate));
    model.addAttribute("balance3", bonudsService.countBalance(null, beginDate, endDate));
    model.addAttribute("balance4", withdrawService.countBalance(null, beginDate, endDate));
    model.addAttribute("balance5", withdrawService.countBalance(Status.success, beginDate, endDate));
    return "/admin/common/index";
}


@RequestMapping(value = "/main", method = RequestMethod.GET)
public String main(){
    return "/admin/common/main";
}


@RequestMapping("/fenhong")
public String fenhong(HttpServletRequest request,HttpServletResponse response){
    return "/admin/common/fenhong";
}


@RequestMapping("/error")
public String error(){
    return "/admin/common/error";
}


}