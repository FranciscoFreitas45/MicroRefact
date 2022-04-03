package com.lingxiang2014.controller.admin;
 import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Withdraw.Status;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.WithdrawService;
@Controller("adminSalesController")
@RequestMapping("/admin/sales")
public class SalesController extends BaseController{

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Date month,Model model){
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
        money00 = depositService.count(null, Deposit.Type.memberRecharge, beginDate, endDate);
        money00 = money00.add(depositService.count(null, Deposit.Type.adminRecharge, beginDate, endDate));
        money11 = bonudsService.count(null, null, beginDate, endDate);
        money22 = bonudsService.count(null, Type.duiPengBonuds, beginDate, endDate);
        money33 = bonudsService.count(null, Type.huzhuBonuds, beginDate, endDate);
        money44 = bonudsService.count(null, Type.jianDianBonuds, beginDate, endDate);
        money55 = bonudsService.count(null, Type.leaderBonuds, beginDate, endDate);
        money66 = bonudsService.count(null, Type.salesBonuds, beginDate, endDate);
        money77 = bonudsService.count(null, Type.serviceBonuds, beginDate, endDate);
        money88 = bonudsService.count(null, Type.signInBonuds, beginDate, endDate);
        money99 = bonudsService.count(null, Type.staticBonuds, beginDate, endDate);
        money100 = withdrawService.count(null, Status.success, beginDate, endDate);
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
    return "/admin/sales/view";
}


public int compare(String s1,String s2){
    return s1.hashCode() - s2.hashCode();
}


}