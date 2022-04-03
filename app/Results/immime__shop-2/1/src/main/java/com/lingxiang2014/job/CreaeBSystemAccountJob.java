package com.lingxiang2014.job;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.BSystemAccount;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.BSystemAccountService;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.InComeService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.InComeService;
import com.lingxiang2014.Interface.BSystemAccountService;
import com.lingxiang2014.DTO.Setting;
import com.lingxiang2014.DTO.BSystemAccount;
@Component("creaeBAccountJob")
@Lazy(false)
public class CreaeBSystemAccountJob {

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "inComeServiceImpl")
 private  InComeService inComeService;

@Resource(name = "bSystemAccountServiceImpl")
 private  BSystemAccountService bSystemAccountService;


@Scheduled(cron = "${job.clear.cron}")
public void clearSystem(){
    Setting setting = SettingUtils.get();
    List<Member> list = memberService.findMemberByDefaultMemberRank(true);
    for (Member member : list) {
        delete(member.getId());
    }
    // 创建新会员
    list = memberService.findAll();
    for (Member member : list) {
        if (member.getBalance1().compareTo(setting.getbSystemLeave()) >= 0) {
            // 生成一个新的账号，推荐人是该会员
            BSystemAccount child = new BSystemAccount();
            child.init();
            child.setNumber(memberService.getMemberNumber(null));
            child.setMember(member);
            BSystemAccount top = bSystemAccountService.findLeaf(1);
            child.setTop(top);
            bSystemAccountService.save(child);
            if (top.getLeftChildren() == null) {
                top.setLeftChildren(child);
            } else {
                top.setRightChildren(child);
                top.setIsLeaf(false);
                bSystemAccountService.update(top);
            }
        }
    }
}


public void delete(Long id){
    Member member = memberService.find(id);
    if (member == null || (member != null && !member.getMemberRank().getIsDefault())) {
        return;
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
}


}