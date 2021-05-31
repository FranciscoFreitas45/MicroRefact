import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.NumberFormatting;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.repo.bonus.BonusApplicableRepo;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.bonus.BonusMasterRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class ExgratiaApiController {

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private BonusMasterRepo bonusMasterRepo;

@Autowired
 private BonusApplicableRepo bonusApplicableRepo;

@Autowired
 private BonusCalcRepo bonusCalcRepo;


@RequestMapping(value = { "/empBonusAppUpdateForExgratia" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusAppSaveOrUpdate(int bonusAppId,String startDate,int isFinal,int bonusId,String remark,int companyId,String dateTime,int userId){
    Info info = new Info();
    String paidDate = DateConvertor.convertToYMD(startDate);
    String[] a = paidDate.split("-");
    String exgratiaDate = DateConvertor.convertToYMD(startDate);
    double ded_exgretia_amt_percentage = 0.0;
    double exgretia_percentage = 0.0;
    String bonus_formula = null;
    Setting setting = new Setting();
    BonusMaster bous = new BonusMaster();
    try {
        bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
        exgretia_percentage = bous.getExgratiaPercentage();
    } catch (Exception e) {
        exgretia_percentage = 0.0;
    }
    try {
        setting = settingRepo.findByKey("ded_exgretia_amt_percentage");
        ded_exgretia_amt_percentage = Double.parseDouble(setting.getValue());
    } catch (Exception e) {
        ded_exgretia_amt_percentage = 0;
    }
    try {
        setting = settingRepo.findByKey("bonus_formula");
        bonus_formula = setting.getValue();
    } catch (Exception e) {
        bonus_formula = "";
    }
    int insertVal = 0;
    try {
        setting = settingRepo.findByKey("ammount_format_Insert");
        insertVal = Integer.parseInt(setting.getValue());
    } catch (Exception e) {
        insertVal = 0;
    }
    try {
        int n = bonusApplicableRepo.updateBonusExgratia(bonusAppId, bonus_formula, NumberFormatting.castNumber(exgretia_percentage, insertVal), NumberFormatting.castNumber(ded_exgretia_amt_percentage, insertVal), userId, dateTime, remark, exgratiaDate);
        int n1 = bonusCalcRepo.updateCalcFinalizeExgratia(bonusId, paidDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/empBonusCalcUpdateForExgratia" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusCalcUpdateForExgratia(int bonusId,int bonusCalcId,double exPrcnt,int companyId,String dateTime,int userId){
    Info info = new Info();
    int flag = 0;
    Date date = new Date();
    SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        // edit
        BonusMaster bous = new BonusMaster();
        // temp.setLoginTimeExgretia("0000-00-00 00:00:00");
        double ded_exgretia_amt_percentage = 0.0;
        Setting setting = new Setting();
        try {
            setting = settingRepo.findByKey("ded_exgretia_amt_percentage");
            ded_exgretia_amt_percentage = Double.parseDouble(setting.getValue());
        } catch (Exception e) {
            ded_exgretia_amt_percentage = 0;
        }
        int insertVal = 0;
        try {
            setting = settingRepo.findByKey("ammount_format_Insert");
            insertVal = Integer.parseInt(setting.getValue());
        } catch (Exception e) {
            insertVal = 0;
        }
        double exgratiaAmt = 0;
        double grossExgratiaAmt = 0;
        double dedExgratiaAmt = 0;
        double payableDays = 0;
        String isApp = null;
        BonusCalc bonusCalc = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
        double exgratiaAmt1 = 0;
        if (bonusCalc != null) {
            isApp = bonusCalc.getBonusApplicable();
            if (isApp.equals("Yes")) {
                payableDays = bonusCalc.getTotalBonusDays();
                payableDays = NumberFormatting.castNumber(payableDays, insertVal);
                exgratiaAmt1 = bonusCalc.getTotalExgretiaWages();
                exgratiaAmt1 = NumberFormatting.castNumber(exgratiaAmt1, insertVal);
                exgratiaAmt = (exgratiaAmt1 * exPrcnt) / 100;
                exgratiaAmt = NumberFormatting.castNumber(exgratiaAmt, insertVal);
                grossExgratiaAmt = exgratiaAmt1 + exgratiaAmt;
                grossExgratiaAmt = NumberFormatting.castNumber(grossExgratiaAmt, insertVal);
                dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
                dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
                dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;
                dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
            } else {
                exgratiaAmt1 = 0;
                exgratiaAmt = 0;
                grossExgratiaAmt = 0;
                dedExgratiaAmt = 0;
                payableDays = bonusCalc.getTotalBonusDays();
                isApp = "No";
            }
            int n = bonusCalcRepo.updateExgratiaAmts(exgratiaAmt1, grossExgratiaAmt, exgratiaAmt, dedExgratiaAmt, payableDays, yyDtTm.format(date), userId, isApp, bonusCalc.getBonusCalcId(), exPrcnt);
            if (n > 0) {
                BonusCalc bonusCalc1 = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
                ObjectMapper mapper = new ObjectMapper();
                BonusCalc organisation = mapper.readValue(bonusCalc1.getBonusDetails(), BonusCalc.class);
                organisation.setExgratiaPrcnt(exPrcnt);
                organisation.setExgretiaApplicable("Yes");
                organisation.setTotalExgretiaDays((int) (payableDays));
                organisation.setTotalExgretiaWages(exgratiaAmt1);
                organisation.setIsExgretiaFinalized("0");
                organisation.setLoginIdExgretia(userId);
                organisation.setLoginTimeExgretia(yyDtTm.format(date));
                organisation.setPaidExgretiaAmt(0);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(organisation);
                int p = bonusCalcRepo.updateExgratiaDetails(json, bonusCalc1.getBonusCalcId());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getBonusCalcByCalcId" }, method = RequestMethod.POST)
@ResponseBody
public BonusCalc getBonusCalcByCalcId(int bonusCalcId){
    BonusCalc list = new BonusCalc();
    try {
        list = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteBonusCalcExratia" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonusCalcExratia(int bonusCalcId){
    Info info = new Info();
    try {
        int n1 = bonusCalcRepo.updateExgratiaAmtsDelete(bonusCalcId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllEmployeeDetailForBonus" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailForBonusUpdate(int bonusId,int flag,List<Integer> locId){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    try {
        if (flag == 1) {
            list = getEmployeeDetailsRepo.getEmpDetailListByBonusId(bonusId, locId);
        } else {
            list = getEmployeeDetailsRepo.getEmpDetailListByBonusIdAssignedBonus(bonusId, locId);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


}