package com.lingxiang2014.controller.admin;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.lingxiang2014.Message;
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.InCome;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Transfer;
import com.lingxiang2014.entity.Withdraw;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.DepositService;
import com.lingxiang2014.service.InComeService;
import com.lingxiang2014.service.MailService;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.service.TemplateService;
import com.lingxiang2014.service.TransferService;
import com.lingxiang2014.service.WithdrawService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.TransferService;
import com.lingxiang2014.Interface.InComeService;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.DTO.Message;
import com.lingxiang2014.DTO.Member;
@Controller("adminBackupController")
@RequestMapping("/admin/backup")
public class BackupController extends BaseController{

@Resource(name = "freeMarkerConfigurer")
 private  FreeMarkerConfigurer freeMarkerConfigurer;

@Resource(name = "mailServiceImpl")
 private  MailService mailService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;

@Resource(name = "templateServiceImpl")
 private  TemplateService templateService;

@Resource(name = "transferServiceImpl")
 private  TransferService transferService;

@Resource(name = "inComeServiceImpl")
 private  InComeService inComeService;

@Resource(name = "depositServiceImpl")
 private  DepositService depositService;

@Resource(name = "withdrawServiceImpl")
 private  WithdrawService withdrawService;

 private  Map<String,String> map;


public Map<String,String> readfile(String filepath){
    Map<String, String> map = new HashMap<String, String>();
    File file = new File(filepath);
    File[] tempList = file.listFiles();
    if (tempList != null && tempList.length > 0) {
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文     件：" + tempList[i]);
                map.put(tempList[i].getName(), tempList[i].getAbsolutePath());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }
    }
    return map;
}


@RequestMapping(value = "/backup", method = RequestMethod.POST)
@ResponseBody
public Message backup(){
    String filePath = "D:\\backup\\" + System.currentTimeMillis() + ".sql";
    Boolean flag = false;
    String path = "D:";
    String str = "cmd /c " + path + "\\mysqldump -hlocalhost -uroot -proot --opt blackboy>" + filePath;
    try {
        Process process = Runtime.getRuntime().exec(str);
        if (process.waitFor() == 0) {
            // 0 表示线程正常终止。
            flag = true;
        }
    } catch (IOException e) {
        flag = false;
        e.printStackTrace();
    } catch (InterruptedException e) {
        flag = false;
        e.printStackTrace();
    }
    if (flag) {
        List<String> attachments = new ArrayList<String>();
        File file = new File(filePath);
        filePath = file.getAbsolutePath().replace("\\", "\\\\");
        attachments.add(filePath);
        Setting setting = SettingUtils.get();
        mailService.sendBackupMail(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), "431820031@qq.com", attachments);
        return Message.success("备份成功！");
    } else {
        return Message.error("备份失败！");
    }
}


@RequestMapping(value = "/restore", method = RequestMethod.POST)
@ResponseBody
public Message restore(String fileName){
    String filePath = "D:\\backup\\" + fileName;
    Boolean flag = false;
    String path = "D:";
    String str = "cmd /c " + path + "\\mysql -hlocalhost -uroot -proot blackboy<" + filePath;
    try {
        Process process = Runtime.getRuntime().exec(str);
        if (process.waitFor() == 0) {
            // 0 表示线程正常终止。
            flag = true;
        }
    } catch (IOException e) {
        flag = false;
        e.printStackTrace();
    } catch (InterruptedException e) {
        flag = false;
        e.printStackTrace();
    }
    if (flag) {
        return Message.success("还原成功！");
    } else {
        return Message.error("还原失败！");
    }
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(String id,ModelMap model){
    if (StringUtils.isEmpty(id)) {
        return ERROR_VIEW;
    }
    model.addAttribute("template", templateService.get(id));
    model.addAttribute("content", templateService.read(id));
    return "/admin/template/edit";
}


@RequestMapping(value = "/clear", method = RequestMethod.POST)
@ResponseBody
public Message clear(){
    Boolean flag = false;
    try {
        List<Bonuds> bonuds = bonudsService.findAll();
        for (Bonuds bonud : bonuds) {
            bonudsService.delete(bonud);
        }
        List<Transfer> transfers = transferService.findAll();
        for (Transfer transfer : transfers) {
            transferService.delete(transfer);
        }
        List<InCome> inComes = inComeService.findAll();
        for (InCome inCome : inComes) {
            inComeService.delete(inCome);
        }
        List<Deposit> deposits = depositService.findAll();
        for (Deposit deposit : deposits) {
            depositService.delete(deposit);
        }
        List<Withdraw> withdraws = withdrawService.findAll();
        for (Withdraw withdraw : withdraws) {
            withdrawService.delete(withdraw);
        }
        List<Member> list = memberService.findAll();
        for (Member member : list) {
            member.setTop(null);
            member.setLeftChildren(null);
            member.setRightChildren(null);
            member.setMidChildren(null);
            member.setParent(null);
            memberService.update(member);
        }
        for (Member member : list) {
            memberService.delete(member);
        }
        flag = true;
    } catch (Exception e) {
        flag = false;
    }
    if (flag) {
        return Message.success("删除成功！");
    } else {
        return Message.error("删除失败！");
    }
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String id,String content,RedirectAttributes redirectAttributes){
    if (StringUtils.isEmpty(id) || content == null) {
        return ERROR_VIEW;
    }
    templateService.write(id, content);
    freeMarkerConfigurer.getConfiguration().clearTemplateCache();
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(ModelMap model){
    String filePath = "D:\\backup\\";
    map = readfile(filePath);
    model.addAttribute("map", map);
    return "/admin/backup/list";
}


}