package com.lingxiang2014.controller.shop.member;
 import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Setting;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.MemberRank;
import com.lingxiang2014.entity.StaticBonudsRank;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.MemberRankService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.MessageService;
import com.lingxiang2014.service.StaticBonudsRankService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.util.ChangeDate;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MessageService;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.WithdrawService;
@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "messageServiceImpl")
 private  MessageService messageService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "staticBonudsRankServiceImpl")
 private  StaticBonudsRankService staticBonudsRankService;

@Resource(name = "memberRankServiceImpl")
 private  MemberRankService memberRankService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;


@RequestMapping(value = "/check_memberRank", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> checkMemberRank(Long memberRankId){
    Member member = memberService.getCurrent();
    Map<String, Object> data = new HashMap<String, Object>();
    MemberRank memberRank = memberRankService.find(memberRankId);
    if (memberRank == null) {
        data.put("message", "系统不存在该会员等级！");
        return data;
    }
    if (member == null) {
        data.put("message", "登陆超时，请从新登陆！");
        return data;
    }
    if (memberRank.getAmount().compareTo(member.getBalance().multiply(new BigDecimal(2))) > 0) {
        data.put("message", "现金币余额不足，无法激活该等级会员！");
        return data;
    }
    if (memberRank.getAmount().compareTo(member.getBalance1().multiply(new BigDecimal(2))) > 0) {
        data.put("message", "报单币余额不足，无法激活该等级会员！");
        return data;
    }
    data.put("message", "ok");
    return data;
}


public int compare(String s1,String s2){
    return s1.hashCode() - s2.hashCode();
}


@RequestMapping(value = "/myPeople", method = RequestMethod.GET)
public String myPeople(Integer pageNumber,ModelMap model){
    Member member = memberService.getCurrent();
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    model.addAttribute("page", memberService.findChildrenPage(member, pageable));
    return "shop/member/member/myPeople";
}


public void devideManagerBonuds(Member member1,Member member2,BigDecimal money1,Integer leve){
    BigDecimal percent = new BigDecimal(100);
    Member top = member1.getTop();
    Integer index = leve;
    Setting setting = SettingUtils.get();
    if (top != null && index >= 0) {
        BigDecimal money = money1.multiply(setting.getManagerBonuds());
        memberService.save(top);
        String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getSalesBonuds().multiply(percent) + "%的见点奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
        // 把平衡奖的金额添加到现金币账户中
        memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + top.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        top.setBalance(top.getBalance().add(money.multiply(setting.getaRate())));
        memo = memo + "进入之后现金币账户余额：" + top.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
        memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + top.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        top.setBalance2(top.getBalance2().add(money.multiply(setting.getbRate())));
        memo = memo + "进入之后复投币账户余额：" + top.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + top.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        top.setBalance3(top.getBalance3().add(money.multiply(setting.getcRate())));
        memo = memo + "进入之后理财币账户余额：" + top.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memberService.update(top);
        memo = memo + "(" + top.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + top.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + top.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + top.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
        Bonuds bonuds = new Bonuds();
        bonuds.setBalance(money);
        bonuds.setMember(top);
        bonuds.setMemo(memo);
        bonuds.setOperator("系统");
        bonuds.setType(Type.managerBonuds);
        bonudsService.save(bonuds);
        index--;
        devideManagerBonuds(top, member1, money1, index);
    }
}


public void devideLeaderBonuds(Member member1,BigDecimal duiPengBonuds){
    BigDecimal percent = new BigDecimal(100);
    Setting setting = SettingUtils.get();
    BigDecimal money = new BigDecimal(0);
    Member parent = member1.getParent();
    if (parent == null) {
        return;
    }
    // 第一代
    parent = parent.getParent();
    if (parent != null && parent.getIsLeaderBonuds() && !parent.getMemberRank().getIsDefault()) {
        if (parent.getMyPeople() > 0) {
            // 至少推荐一个
            money = duiPengBonuds.multiply(setting.getLeaderBonuds());
            String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getLeaderBonuds().multiply(percent) + "%的领导奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
            // 把平衡奖的金额添加到现金币账户中
            memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
            memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
            memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
            memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
            memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memberService.update(parent);
            memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
            Bonuds bonuds = new Bonuds();
            bonuds.setBalance(money);
            bonuds.setMember(parent);
            bonuds.setMemo(memo);
            bonuds.setOperator("系统");
            bonuds.setType(Type.leaderBonuds);
            bonudsService.save(bonuds);
            parent.setTodayDuiPengBonuds(parent.getTodayDuiPengBonuds().add(money));
            memberService.update(parent);
        }
    }
    // 第二代
    if (parent == null) {
        return;
    }
    parent = parent.getParent();
    if (parent != null) {
        if (parent.getMyPeople() >= 2 && parent.getIsLeaderBonuds()) {
            // 至少推荐2个
            money = duiPengBonuds.multiply(setting.getLeaderBonuds());
            String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getLeaderBonuds().multiply(percent) + "%的领导奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
            // 把平衡奖的金额添加到现金币账户中
            memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
            memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
            memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
            memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
            memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memberService.update(parent);
            memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
            Bonuds bonuds = new Bonuds();
            bonuds.setBalance(money);
            bonuds.setMember(parent);
            bonuds.setMemo(memo);
            bonuds.setOperator("系统");
            bonuds.setType(Type.leaderBonuds);
            bonudsService.save(bonuds);
            parent.setTodayDuiPengBonuds(parent.getTodayDuiPengBonuds().add(money));
            memberService.update(parent);
        }
    }
    // 第三代
    if (parent == null) {
        return;
    }
    parent = parent.getParent();
    if (parent != null && !parent.getMemberRank().getIsDefault()) {
        if (parent.getMyPeople() >= 3 && parent.getIsLeaderBonuds()) {
            // 至少直推3个
            money = duiPengBonuds.multiply(setting.getLeaderBonuds());
            String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getLeaderBonuds().multiply(percent) + "%的领导奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
            // 把平衡奖的金额添加到现金币账户中
            memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
            memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
            memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
            memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
            memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memberService.update(parent);
            memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
            Bonuds bonuds = new Bonuds();
            bonuds.setBalance(money);
            bonuds.setMember(parent);
            bonuds.setMemo(memo);
            bonuds.setOperator("系统");
            bonuds.setType(Type.leaderBonuds);
            bonudsService.save(bonuds);
            parent.setTodayDuiPengBonuds(parent.getTodayDuiPengBonuds().add(money));
            memberService.update(parent);
        }
    }
    // 第4代
    if (parent == null) {
        return;
    }
    parent = parent.getParent();
    if (parent != null && !parent.getMemberRank().getIsDefault()) {
        if (parent.getMyPeople() >= 4 && parent.getIsLeaderBonuds()) {
            // 直推4个或者多余4个
            money = duiPengBonuds.multiply(setting.getLeaderBonuds());
            String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getLeaderBonuds().multiply(percent) + "%的领导奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
            // 把平衡奖的金额添加到现金币账户中
            memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
            memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
            memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
            memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
            memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memberService.update(parent);
            memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
            Bonuds bonuds = new Bonuds();
            bonuds.setBalance(money);
            bonuds.setMember(parent);
            bonuds.setMemo(memo);
            bonuds.setOperator("系统");
            bonuds.setType(Type.leaderBonuds);
            bonudsService.save(bonuds);
            parent.setTodayDuiPengBonuds(parent.getTodayDuiPengBonuds().add(money));
            memberService.update(parent);
        }
    }
}


@RequestMapping(value = "/groupStructure", method = RequestMethod.GET)
public String groupStructure(ModelMap model,Long id){
    Member member = memberService.getCurrent();
    Member member1 = memberService.find(id);
    if (member1 != null) {
        member = member1;
    }
    model.addAttribute("member1", member);
    return "shop/member/member/groupStructure";
}


public BigDecimal devideSalesBonuds(Member member1,BigDecimal money1){
    BigDecimal percent = new BigDecimal(100);
    // 给推荐人分红
    Setting setting = SettingUtils.get();
    Member parent = member1.getParent();
    // 计算应获取的销售金额
    BigDecimal money = money1.multiply(setting.getSalesBonuds());
    if (parent != null && parent.getIsSalesBonuds()) {
        String memo = "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getSalesBonuds().multiply(percent) + "%的销售提成。金额：" + money1.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ".";
        // 把平衡奖的金额添加到现金币账户中
        memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
        memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
        memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
        memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
        memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memberService.update(parent);
        memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
        Bonuds bonuds = new Bonuds();
        bonuds.setBalance(money);
        bonuds.setMember(parent);
        bonuds.setMemo(memo);
        bonuds.setOperator("系统");
        bonuds.setType(Type.salesBonuds);
        bonudsService.save(bonuds);
    }
    return money1;
}


@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index(Integer pageNumber,ModelMap model,String type,String number){
    Member member = memberService.getCurrent();
    if ("admin".equals(type)) {
        member = memberService.findByNumber(number);
    }
    DateTime now = new DateTime();
    DateTime date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    Date beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    Date endDate = date.toDate();
    try {
        member.setAllBonuds(bonudsService.count(member, null));
        model.addAttribute("duiPengBonuds", bonudsService.count(member, Type.duiPengBonuds));
        model.addAttribute("huzhuBonuds", bonudsService.count(member, Type.huzhuBonuds));
        model.addAttribute("jianDianBonuds", bonudsService.count(member, Type.jianDianBonuds));
        model.addAttribute("leaderBonuds", bonudsService.count(member, Type.leaderBonuds));
        model.addAttribute("salesBonuds", bonudsService.count(member, Type.salesBonuds));
        model.addAttribute("serviceBonuds", bonudsService.count(member, Type.serviceBonuds));
        model.addAttribute("signInBonuds", bonudsService.count(member, Type.signInBonuds));
        model.addAttribute("staticBonuds", bonudsService.count(member, Type.staticBonuds));
        model.addAttribute("newBonuds", bonudsService.findList(member, null, beginDate, endDate));
    } catch (Exception e) {
        e.printStackTrace();
    }
    Map<String, BigDecimal> map = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    // 当天
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上一天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上2天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上3天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上4天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上5天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上6天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    // 上7天
    now = ChangeDate.getPrevDay(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 1);
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    beginDate = date.toDate();
    date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
    endDate = date.toDate();
    map.put(now.getMonthOfYear() + "月" + (now.getDayOfMonth() < 10 ? ("0" + now.getDayOfMonth()) : now.getDayOfMonth()), bonudsService.count(member, null, beginDate, endDate));
    model.addAttribute("map", map);
    return "shop/member/index";
}


@RequestMapping(value = "/active", method = RequestMethod.GET)
public String active(ModelMap model,Long id){
    Member member = memberService.getCurrent();
    Member member1 = memberService.find(id);
    if (member1 == null) {
        member1 = member;
    }
    model.addAttribute("memberRanks", memberRankService.findAll());
    model.addAttribute("member1", member);
    model.addAttribute("member1", member1);
    return "shop/member/member/active";
}


@RequestMapping(value = "/confirmActive", method = RequestMethod.POST)
public String confirmActive(Long memberRankId,Long memberId,HttpServletRequest request,RedirectAttributes redirectAttributes){
    Member member1 = memberService.find(memberId);
    Member member = memberService.getCurrent();
    BigDecimal money = member1.getMemberRank().getAmount();
    MemberRank memberRank = memberRankService.find(memberRankId);
    if (member == null || memberRank == null || member1 == null) {
        addFlashMessage(redirectAttributes, ERROR_MESSAGE);
        return "redirect:active.jhtml?memberRankId=" + memberRankId;
    }
    if (memberRank.getAmount().compareTo(member.getBalance().multiply(new BigDecimal(2))) > 0) {
        addFlashMessage(redirectAttributes, ERROR_MESSAGE);
        return "redirect:active.jhtml?memberRankId=" + memberRankId;
    }
    if (memberRank.getAmount().compareTo(member.getBalance1().multiply(new BigDecimal(2))) > 0) {
        addFlashMessage(redirectAttributes, ERROR_MESSAGE);
        return "redirect:active.jhtml?memberRankId=" + memberRankId;
    }
    if (member1.getMemberRank() == memberRank) {
        addFlashMessage(redirectAttributes, Message.error("该会员已是该级别会员，激活失败！"));
        return "redirect:active.jhtml?memberRankId=" + memberRankId;
    }
    if (member1 != null) {
        member1.setActiveDate(new Date());
        member1.setMemberRank(memberRank);
        member1.setStaticBonudsRank(staticBonudsRankService.findDefault());
        member1.setIsDuiPengBonuds(true);
        member1.setIsHuzhuBonuds(true);
        member1.setIsJianDianBonuds(true);
        member1.setIsLeaderBonuds(true);
        member1.setIsSalesBonuds(true);
        member1.setIsServiceBonuds(true);
        member1.setIsSignInBonuds(true);
        member1.setIsStaticBonuds(true);
        member1.setIsStaticDevideBonuds(true);
        memberService.update(member1);
        Member parent = member1.getParent();
        if (parent != null) {
            // 更新推荐人数
            parent.setMyPeople(parent.getMyPeople() + 1);
            // 更新分红等级
            List<StaticBonudsRank> list = staticBonudsRankService.findLTEQ(parent.getMyPeople());
            parent.setStaticBonudsRank(list.get(0));
            memberService.update(parent);
        }
        /*
			 * 更新位置
			 */
        changePosition(member1);
    }
    member.setBalance1(member.getBalance1().subtract(memberRank.getAmount().divide(new BigDecimal(2))));
    member.setBalance(member.getBalance().subtract(memberRank.getAmount().divide(new BigDecimal(2))));
    // 计算多少金额的业绩
    money = member1.getMemberRank().getAmount().subtract(money);
    memberService.update(member);
    /**
     * 更新业绩
     */
    updateResults(member1, money);
    // 销售奖（直推奖）销售提成10%，每单100；
    devideSalesBonuds(member1, money);
    // 平衡奖 拿小区业绩的8%（日封顶1000）
    BigDecimal duiPengBonuds = devideDuiPengBonuds(member1);
    // 领导奖 直接销售1单，拿直推1代对碰奖的5% 直接销售2单，拿直推2代对碰奖的5% 直接销售3单，拿直推3代对碰奖的5%
    // 直接销售4单，拿直推4代对碰奖的5%
    devideLeaderBonuds(member1, duiPengBonuds);
    // 见点奖 1-14层会员见点1%
    devideManagerBonuds(member1, member1, money, 14);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:index.jhtml";
}


public void updateResults(Member member,BigDecimal money){
    // 获取该激活会员的上一层会员
    Member top = member.getTop();
    // 上层会员存在
    if (top != null) {
        // 放在top的左边
        if (top.getLeftChildren() != null && top.getLeftChildren() == member) {
            // 增加top的左边业绩
            top.setLeftResults(top.getLeftResults().add(money));
            top.setLeftRemainResults(top.getLeftRemainResults().add(money));
            if (top.getLeftMember() == null) {
                top.setLeftMember(0);
            }
            top.setLeftMember(top.getLeftMember() + 1);
        } else if (top.getRightChildren() != null && top.getRightChildren() == member) {
            // 放在右边
            // 增加右边的业绩
            top.setRightResults(top.getRightResults().add(money));
            top.setRightRemainResults(top.getRightRemainResults().add(money));
            if (top.getRightMember() == null) {
                top.setRightMember(0);
            }
            top.setRightMember(top.getRightMember() + 1);
        }
        // 更新今日新增业绩
        top.setTodayResults(top.getTodayResults().add(money));
        memberService.update(top);
        updateResults(top, money);
    }
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long id){
    Member member = memberService.find(id);
    // 会员已经激活了。
    if (member == null || (member != null && !member.getMemberRank().getIsDefault())) {
        return ERROR_MESSAGE;
    }
    Member leftMember = member.getLeftChildren();
    Member rightMember = member.getRightChildren();
    if (leftMember == null && rightMember == null) {
        Member left = memberService.findLeftChild(member);
        if (left != null) {
            left.setLeftChildren(null);
            left.setIsLeaf(true);
            memberService.update(left);
        }
        Member right = memberService.findRightChild(member);
        if (right != null) {
            right.setRightChildren(null);
            right.setIsLeaf(true);
            memberService.update(right);
        }
        memberService.delete(id);
    } else {
        if (leftMember != null && rightMember == null) {
            if (leftMember.getMemberRank().getIsDefault()) {
                // 左边会员没有激活
                delete(leftMember.getId());
            } else {
                // 左边会员已经激活
                leftMember.setTop(member.getTop());
                memberService.update(leftMember);
            }
        } else if (leftMember == null && rightMember != null) {
            if (rightMember.getMemberRank().getIsDefault()) {
                // 右边会员没有激活
                delete(rightMember.getId());
            } else {
                // 右边会员已经激活
                rightMember.setTop(member.getTop());
                memberService.update(rightMember);
            }
        } else {
            if (leftMember.getMemberRank().getIsDefault() && rightMember.getMemberRank().getIsDefault()) {
                // 左右会员没有激活
                delete(leftMember.getId());
                delete(rightMember.getId());
            } else if (!leftMember.getMemberRank().getIsDefault() && rightMember.getMemberRank().getIsDefault()) {
                // 左边会员已经激活
                leftMember.setTop(member.getTop());
                memberService.update(leftMember);
            } else if (leftMember.getMemberRank().getIsDefault() && !rightMember.getMemberRank().getIsDefault()) {
                // 右边会员已经激活
                rightMember.setTop(member.getTop());
                memberService.update(rightMember);
            } else {
                // 两边会员都激活了
                if (leftMember.getActiveDate().compareTo(rightMember.getActiveDate()) >= 0) {
                    // 右边先激活的
                    rightMember.setTop(member.getTop());
                    memberService.update(rightMember);
                    leftMember.setTop(memberService.findLeaf(leftMember.getParent(), 1, null));
                    memberService.update(leftMember);
                } else {
                    // 左边先激活
                    leftMember.setTop(member.getTop());
                    memberService.update(leftMember);
                    rightMember.setTop(memberService.findLeaf(rightMember.getParent(), 1, null));
                    memberService.update(rightMember);
                }
            }
        }
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/statices", method = RequestMethod.GET)
public String statices(Date month,Model model){
    Member member = memberService.getCurrent();
    if (month == null) {
        month = new Date();
    }
    Map<String, BigDecimal> map0 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map1 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map2 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map3 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map4 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map5 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map6 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map7 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map8 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map9 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    Map<String, BigDecimal> map10 = new TreeMap<String, BigDecimal>(new Comparator<String>() {

        public int compare(String s1, String s2) {
            return s1.hashCode() - s2.hashCode();
        }
    });
    BigDecimal money0 = new BigDecimal(0);
    BigDecimal money1 = new BigDecimal(0);
    BigDecimal money2 = new BigDecimal(0);
    BigDecimal money3 = new BigDecimal(0);
    BigDecimal money4 = new BigDecimal(0);
    BigDecimal money5 = new BigDecimal(0);
    BigDecimal money6 = new BigDecimal(0);
    BigDecimal money7 = new BigDecimal(0);
    BigDecimal money8 = new BigDecimal(0);
    BigDecimal money9 = new BigDecimal(0);
    BigDecimal money10 = new BigDecimal(0);
    BigDecimal money00 = new BigDecimal(0);
    BigDecimal money11 = new BigDecimal(0);
    BigDecimal money22 = new BigDecimal(0);
    BigDecimal money33 = new BigDecimal(0);
    BigDecimal money44 = new BigDecimal(0);
    BigDecimal money55 = new BigDecimal(0);
    BigDecimal money66 = new BigDecimal(0);
    BigDecimal money77 = new BigDecimal(0);
    BigDecimal money88 = new BigDecimal(0);
    BigDecimal money99 = new BigDecimal(0);
    BigDecimal money100 = new BigDecimal(0);
    Calendar beginCalendar = DateUtils.toCalendar(month);
    Integer days = beginCalendar.getActualMaximum(Calendar.DATE);
    DateTime date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
    Date beginDate = date.toDate();
    date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
    Date endDate = date.toDate();
    for (int i = 1; i <= days; i++) {
        money00 = depositService.count(member, Deposit.Type.memberRecharge, beginDate, endDate);
        money00 = money00.add(depositService.count(null, Deposit.Type.adminRecharge, beginDate, endDate));
        money11 = bonudsService.count(member, null, beginDate, endDate);
        money22 = bonudsService.count(member, Type.duiPengBonuds, beginDate, endDate);
        money33 = bonudsService.count(member, Type.huzhuBonuds, beginDate, endDate);
        money44 = bonudsService.count(member, Type.jianDianBonuds, beginDate, endDate);
        money55 = bonudsService.count(member, Type.leaderBonuds, beginDate, endDate);
        money66 = bonudsService.count(member, Type.salesBonuds, beginDate, endDate);
        money77 = bonudsService.count(member, Type.serviceBonuds, beginDate, endDate);
        money88 = bonudsService.count(member, Type.signInBonuds, beginDate, endDate);
        money99 = bonudsService.count(member, Type.staticBonuds, beginDate, endDate);
        money100 = withdrawService.count(member, Status.success, beginDate, endDate);
        map0.put((i) + "", money00);
        map1.put((i) + "", money11);
        map2.put((i) + "", money22);
        map3.put((i) + "", money33);
        map4.put((i) + "", money44);
        map5.put((i) + "", money55);
        map6.put((i) + "", money66);
        map7.put((i) + "", money77);
        map8.put((i) + "", money88);
        map9.put((i) + "", money99);
        map10.put((i) + "", money100);
        money0 = money0.add(money00);
        money1 = money1.add(money11);
        money2 = money2.add(money22);
        money3 = money3.add(money33);
        money4 = money4.add(money44);
        money5 = money5.add(money55);
        money6 = money6.add(money66);
        money7 = money7.add(money77);
        money8 = money8.add(money88);
        money9 = money9.add(money99);
        money10 = money10.add(money100);
        if (i + 1 <= days) {
            date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, (i + 1), 0, 0, 0);
            beginDate = date.toDate();
            date = new DateTime(beginCalendar.get(Calendar.YEAR), beginCalendar.get(Calendar.MONTH) + 1, (i + 1), 23, 59, 59);
            endDate = date.toDate();
        }
    }
    model.addAttribute("month", month);
    model.addAttribute("map0", map0);
    model.addAttribute("map1", map1);
    model.addAttribute("map2", map2);
    model.addAttribute("map3", map3);
    model.addAttribute("map4", map4);
    model.addAttribute("map5", map5);
    model.addAttribute("map6", map6);
    model.addAttribute("map7", map7);
    model.addAttribute("map8", map8);
    model.addAttribute("map9", map9);
    model.addAttribute("map10", map10);
    model.addAttribute("money0", money0);
    model.addAttribute("money1", money1);
    model.addAttribute("money2", money2);
    model.addAttribute("money3", money3);
    model.addAttribute("money4", money4);
    model.addAttribute("money5", money5);
    model.addAttribute("money6", money6);
    model.addAttribute("money7", money7);
    model.addAttribute("money8", money8);
    model.addAttribute("money9", money9);
    model.addAttribute("money10", money10);
    return "/shop/member/member/statices";
}


public BigDecimal devideDuiPengBonuds(Member member1){
    BigDecimal percent = new BigDecimal(100);
    BigDecimal zero = new BigDecimal(0);
    // 左边剩余业绩
    BigDecimal leftRemainResults = new BigDecimal(0);
    // 右边剩余业绩
    BigDecimal rightRemainResults = new BigDecimal(0);
    // 今天已经获得的平衡奖
    BigDecimal todayDuiPengBonuds = new BigDecimal(0);
    Setting setting = SettingUtils.get();
    // 获取系统设置的每天最高平衡奖金额
    BigDecimal maxTodayDuiPengBonuds = setting.getTodayDuiPengBonuds();
    // 获取今天剩余可获得平衡奖
    BigDecimal remainTodayDuiPengBonuds = new BigDecimal(0);
    // 如果设置的每天最高平衡奖金额小于等于0，把每天最高平衡奖金额设置成1000000000，表明无限制
    if (maxTodayDuiPengBonuds.compareTo(zero) <= 0) {
        maxTodayDuiPengBonuds = new BigDecimal(1000000000);
    }
    Member parent = member1.getParent();
    devideDuiPengBonuds1(member1, parent);
    if (parent != null && parent.getIsDuiPengBonuds()) {
        // 获取左边剩余业绩
        leftRemainResults = parent.getLeftRemainResults();
        // 获取右边剩余业绩
        rightRemainResults = parent.getRightRemainResults();
        // 获取今天已经获得的平衡奖
        todayDuiPengBonuds = parent.getTodayDuiPengBonuds();
        // 获取系统设置的每天最高平衡奖金额
        maxTodayDuiPengBonuds = setting.getTodayDuiPengBonuds();
        // 获取今天剩余可获得平衡奖
        remainTodayDuiPengBonuds = new BigDecimal(0);
        remainTodayDuiPengBonuds = maxTodayDuiPengBonuds.subtract(todayDuiPengBonuds);
        String memo = "";
        // 计算可获得的平衡奖
        BigDecimal money = new BigDecimal(0);
        if (leftRemainResults.compareTo(rightRemainResults) > 0 && leftRemainResults.compareTo(zero) > 0 && rightRemainResults.compareTo(zero) > 0) {
            // 右边业绩是小区
            memo = memo + "领取奖金之前，右边业绩是小区。右边业绩：" + rightRemainResults + ",左边业绩：" + leftRemainResults;
            money = rightRemainResults.multiply(setting.getDuiPengBonuds());
            parent.setLeftRemainResults(leftRemainResults.subtract(rightRemainResults));
            parent.setRightRemainResults(new BigDecimal(0));
            memo = memo + "领取奖金之后，右边业绩：" + parent.getRightRemainResults() + ",左边业绩：" + parent.getLeftRemainResults() + ".";
        } else if (leftRemainResults.compareTo(zero) > 0 && rightRemainResults.compareTo(zero) > 0) {
            // 左边业绩是小区
            memo = memo + "领取奖金之前，左边业绩是小区。左边边业绩：" + leftRemainResults + ",右边业绩：" + rightRemainResults;
            money = leftRemainResults.multiply(setting.getDuiPengBonuds());
            parent.setRightRemainResults(rightRemainResults.subtract(leftRemainResults));
            parent.setLeftRemainResults(new BigDecimal(0));
            memo = memo + "领取奖金之后，右边业绩：" + parent.getRightRemainResults() + ",左边业绩：" + parent.getLeftRemainResults() + ".";
        }
        if (remainTodayDuiPengBonuds.compareTo(money) < 0) {
            money = remainTodayDuiPengBonuds;
        }
        // 把平衡奖的金额添加到现金币账户中
        memo = memo + "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getDuiPengBonuds().multiply(percent) + "%的平衡奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
        // 把平衡奖的金额添加到现金币账户中
        memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
        memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
        memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
        memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
        memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
        memberService.update(parent);
        memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
        Bonuds bonuds = new Bonuds();
        bonuds.setBalance(money);
        bonuds.setMember(parent);
        bonuds.setMemo(memo);
        bonuds.setOperator("系统");
        bonuds.setType(Type.duiPengBonuds);
        bonudsService.save(bonuds);
        return money;
    }
    return new BigDecimal(0);
}


public void changePosition(Member member){
    Member top = member.getTop();
    if (top != null && top.getMemberRank().getIsDefault()) {
        // 节点人没有激活
        Member topTop = top.getTop();
        Member topLeft = top.getLeftChildren();
        Member topRight = top.getRightChildren();
        Member memberLeft = member.getLeftChildren();
        Member memberRight = member.getRightChildren();
        if (topTop.getLeftChildren() == top) {
            topTop.setLeftChildren(member);
        } else {
            topTop.setRightChildren(member);
        }
        memberService.update(topTop);
        member.setTop(topTop);
        member.setLeve(topTop.getLeve() + 1);
        if (top.getLeftChildren() == member) {
            member.setLeftChildren(top);
            member.setRightChildren(topRight);
        } else {
            member.setRightChildren(top);
            member.setLeftChildren(topLeft);
        }
        memberService.update(member);
        top.setTop(member);
        top.setLeve(member.getLeve() + 1);
        top.setLeftChildren(memberLeft);
        top.setRightChildren(memberRight);
        ;
        memberService.update(top);
        if (member.getTop().getMemberRank().getIsDefault()) {
            changePosition(member);
        }
    }
}


@RequestMapping(value = "/signIn", method = RequestMethod.POST)
@ResponseBody
public Message signIn(){
    Member member = memberService.getCurrent();
    if (member == null) {
        return ERROR_MESSAGE;
    }
    if (!member.getIsSignInBonuds()) {
        return Message.error("您不能签到，请联系您的推荐人！");
    }
    Setting setting = SettingUtils.get();
    BigDecimal signInMoney = setting.getZero();
    // 获取签到金额发放的方式
    Integer signInType = setting.getSignInType();
    if (setting.getIsSignIn()) {
        // 获取今天签到记录
        List<Bonuds> list = bonudsService.findToday(Type.signInBonuds, member);
        if (list == null || list.size() == 0) {
            // 今天没有签到
            if (signInType == 1) {
                // 1:按金额领取
                signInMoney = setting.getSignInMoney();
            } else if (signInType == 2) {
                // 2:按比率领取
                signInMoney = member.getMemberRank().getAmount().multiply(setting.getSignInMoneyRate());
            } else if (signInType == 3) {
                // 3：随机领取
                signInMoney = setting.getMinSignInMoney().add(new BigDecimal(Math.random() * ((setting.getMaxSignInMoney().subtract(setting.getMinSignInMoney()).doubleValue()))));
            }
            signInMoney = signInMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            // 把平衡奖的金额添加到现金币账户中
            String memo = "会员：" + member.getNumber() + "领取签到奖。金额：" + signInMoney + ".";
            // 把平衡奖的金额添加到现金币账户中
            memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + member.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            member.setBalance(member.getBalance().add(signInMoney.multiply(setting.getaRate())));
            memo = memo + "进入之后现金币账户余额：" + member.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
            memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + member.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            member.setBalance2(member.getBalance2().add(signInMoney.multiply(setting.getbRate())));
            memo = memo + "进入之后复投币账户余额：" + member.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + member.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            member.setBalance3(member.getBalance3().add(signInMoney.multiply(setting.getcRate())));
            memo = memo + "进入之后理财币账户余额：" + member.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
            memberService.update(member);
            memo = memo + "(" + member.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + member.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + member.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + member.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
            Bonuds bonuds = new Bonuds();
            bonuds.setBalance(signInMoney);
            bonuds.setMember(member);
            bonuds.setMemo(memo);
            bonuds.setOperator("系统");
            bonuds.setType(Type.signInBonuds);
            bonudsService.save(bonuds);
            memberService.update(member);
            return Message.success("签到成功，领取金额：" + setting.getCurrencySign() + signInMoney);
        } else {
            return Message.success("今天已经签到了，本次签到无效");
        }
    } else {
        return Message.success("该功能正在开发中！！！");
    }
}


public void devideDuiPengBonuds1(Member member1,Member parent1){
    List<Member> list = memberService.findAll();
    BigDecimal percent = new BigDecimal(100);
    BigDecimal zero = new BigDecimal(0);
    // 获取今天剩余可获得平衡奖
    BigDecimal remainTodayDuiPengBonuds = new BigDecimal(0);
    // 左边剩余业绩
    BigDecimal leftRemainResults = new BigDecimal(0);
    // 右边剩余业绩
    BigDecimal rightRemainResults = new BigDecimal(0);
    // 今天已经获得的平衡奖
    BigDecimal todayDuiPengBonuds = new BigDecimal(0);
    Setting setting = SettingUtils.get();
    // 获取系统设置的每天最高平衡奖金额
    BigDecimal maxTodayDuiPengBonuds = setting.getTodayDuiPengBonuds();
    // 如果设置的每天最高平衡奖金额小于等于0，把每天最高平衡奖金额设置成1000000000，表明无限制
    if (maxTodayDuiPengBonuds.compareTo(zero) <= 0) {
        maxTodayDuiPengBonuds = new BigDecimal(1000000000);
    }
    if (list != null && list.size() > 0) {
        for (Member parent : list) {
            if (parent != null && parent.getIsDuiPengBonuds() && parent != parent1 && parent != member1) {
                // 获取左边剩余业绩
                leftRemainResults = parent.getLeftRemainResults();
                // 获取右边剩余业绩
                rightRemainResults = parent.getRightRemainResults();
                // 获取今天已经获得的平衡奖
                todayDuiPengBonuds = parent.getTodayDuiPengBonuds();
                remainTodayDuiPengBonuds = maxTodayDuiPengBonuds.subtract(todayDuiPengBonuds);
                String memo = "";
                // 计算可获得的平衡奖
                BigDecimal money = new BigDecimal(0);
                if (leftRemainResults.compareTo(rightRemainResults) > 0 && leftRemainResults.compareTo(zero) > 0 && rightRemainResults.compareTo(zero) > 0) {
                    // 右边业绩是小区
                    memo = memo + "领取奖金之前，右边业绩是小区。右边业绩：" + rightRemainResults + ",左边业绩：" + leftRemainResults;
                    money = rightRemainResults.multiply(setting.getDuiPengBonuds());
                    parent.setLeftRemainResults(leftRemainResults.subtract(rightRemainResults));
                    parent.setRightRemainResults(new BigDecimal(0));
                    memo = memo + "领取奖金之后，右边业绩：" + parent.getRightRemainResults() + ",左边业绩：" + parent.getLeftRemainResults() + ".";
                } else if (leftRemainResults.compareTo(zero) > 0 && rightRemainResults.compareTo(zero) > 0) {
                    // 左边业绩是小区
                    memo = memo + "领取奖金之前，左边业绩是小区。左边边业绩：" + leftRemainResults + ",右边业绩：" + rightRemainResults;
                    money = leftRemainResults.multiply(setting.getDuiPengBonuds());
                    parent.setRightRemainResults(rightRemainResults.subtract(leftRemainResults));
                    parent.setLeftRemainResults(new BigDecimal(0));
                    memo = memo + "领取奖金之后，右边业绩：" + parent.getRightRemainResults() + ",左边业绩：" + parent.getLeftRemainResults() + ".";
                }
                if (remainTodayDuiPengBonuds.compareTo(money) < 0) {
                    money = remainTodayDuiPengBonuds;
                }
                // 把平衡奖的金额添加到现金币账户中
                memo = memo + "会员：" + member1.getNumber() + ",激活：" + member1.getMemberRank().getName() + "身份，领取" + setting.getDuiPengBonuds().multiply(percent) + "%的平衡奖。金额：" + money.setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP);
                // 把平衡奖的金额添加到现金币账户中
                memo = memo + "其中" + setting.getaRate().multiply(setting.getPercent()) + "%进入现金币账户。进入之前现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
                parent.setBalance(parent.getBalance().add(money.multiply(setting.getaRate())));
                memo = memo + "进入之后现金币账户余额：" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "；";
                memo = memo + "其中" + setting.getbRate().multiply(setting.getPercent()) + "%进入复投币账户。进入之前复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
                parent.setBalance2(parent.getBalance2().add(money.multiply(setting.getbRate())));
                memo = memo + "进入之后复投币账户余额：" + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
                memo = memo + "其中" + setting.getcRate().multiply(setting.getPercent()) + "%进入理财币账户。进入之前理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
                parent.setBalance3(parent.getBalance3().add(money.multiply(setting.getcRate())));
                memo = memo + "进入之后理财币账户余额：" + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ",";
                memberService.update(parent);
                memo = memo + "(" + parent.getBalance().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance1().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance2().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + "," + parent.getBalance3().setScale(setting.getPriceScale(), BigDecimal.ROUND_HALF_UP) + ")";
                Bonuds bonuds = new Bonuds();
                bonuds.setBalance(money);
                bonuds.setMember(parent);
                bonuds.setMemo(memo);
                bonuds.setOperator("系统");
                bonuds.setType(Type.duiPengBonuds);
                bonudsService.save(bonuds);
                parent.setTodayDuiPengBonuds(parent.getTodayDuiPengBonuds().add(money));
                memberService.update(parent);
            }
        }
    }
}


public void createBonudsRecord(Member member,BigDecimal money,String description,Type type){
}


}