package com.lingxiang2014.job;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.StaticBonudsRank;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.InComeService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.ChangeDate;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.InComeService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.DTO.Setting;
import com.lingxiang2014.DTO.Member;
import com.lingxiang2014.DTO.StaticBonudsRank;
@Component("bonudsJob")
@Lazy(false)
public class BonudsJob {

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "inComeServiceImpl")
 private  InComeService inComeService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;


@Scheduled(cron = "${job.bonuds.cron}")
public void bonudsDelivery(){
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
}


}