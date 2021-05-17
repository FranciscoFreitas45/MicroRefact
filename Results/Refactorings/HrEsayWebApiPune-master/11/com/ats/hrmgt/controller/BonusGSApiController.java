import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.NumberFormatting;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.bonus;
import com.ats.hrmgt.repo.bonus;
import com.ats.hrmgt.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@RestController
public class BonusGSApiController {

@Autowired
 private BonusMasterRepo bonusMasterRepo;

@Autowired
 private BonusApplicableRepo bonusApplicableRepo;

@Autowired
 private BonusCalcRepo bonusCalcRepo;

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

@Autowired
 private AllowancesRepo AalowancesRepo;

@Autowired
 private EmpSalAllowanceRepo empAllowanceSalCalRepo;

@Autowired
 private SalaryCalcRepo salaryCalcRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private BonusDatesRepo bonusDatesRepo;

@Autowired
 private BonusParamRepo bonusParamRepo;


@RequestMapping(value = { "/empExgratiaUpdateToBonusSaveGS" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusAppSaveOrUpdate(List<Integer> empIdList,int bonusId,int companyId,int userId){
    System.err.println("empExgratiaUpdateToBonusSaveGS");
    Info info = new Info();
    int flag = 0;
    Date date = new Date();
    SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        // insert
        double ded_exgretia_amt_percentage = 0.0;
        double exgretia_percentage = 0.0;
        String bonus_formula = null;
        List<Setting> settingList = new ArrayList<Setting>();
        settingList = settingRepo.findByGroup("BONUS");
        for (int k = 0; k < settingList.size(); k++) {
            if (settingList.get(k).getKey().equalsIgnoreCase("bonus_formula")) {
                bonus_formula = settingList.get(k).getValue();
            } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_exgretia_amt_percentage")) {
                ded_exgretia_amt_percentage = Float.parseFloat(settingList.get(k).getValue());
            }
        }
        int insertVal = 0;
        try {
            Setting setting = settingRepo.findByKey("ammount_format_Insert");
            insertVal = Integer.parseInt(setting.getValue());
        } catch (Exception e) {
            insertVal = 0;
        }
        BonusMaster bous = new BonusMaster();
        try {
            bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
            exgretia_percentage = bous.getExgratiaPercentage();
        } catch (Exception e) {
            exgretia_percentage = 0.0;
        }
        // temp.setLoginTimeExgretia("0000-00-00 00:00:00");
        for (int i = 0; i < empIdList.size(); i++) {
            int empId = empIdList.get(i);
            double exgratiaAmt = 0;
            double grossExgratiaAmt = 0;
            double dedExgratiaAmt = 0;
            double payableDays = 0;
            String isApp = null;
            double formTot = 0;
            double bonusAmt = 0;
            double netExgratiaAmt = 0;
            BonusCalc bonusCalc = bonusCalcRepo.findByEmpIdAndBonusIdAndDelStatus(empId, bonusId, 1);
            if (bonusCalc != null) {
                isApp = bonusCalc.getBonusApplicable();
                if (isApp.equals("Yes")) {
                    payableDays = bonusCalc.getTotalBonusDays();
                    payableDays = NumberFormatting.castNumber(payableDays, insertVal);
                    formTot = bonusCalc.getTotalBonusWages();
                    bonusAmt = bonusCalc.getGrossBonusAmt();
                    formTot = NumberFormatting.castNumber(formTot, insertVal);
                    // (formTot * exgretia_percentage) / 100;
                    exgratiaAmt = formTot - bonusAmt;
                    exgratiaAmt = NumberFormatting.castNumber(exgratiaAmt, insertVal);
                    // + formTot;
                    grossExgratiaAmt = exgratiaAmt;
                    grossExgratiaAmt = NumberFormatting.castNumber(grossExgratiaAmt, insertVal);
                    netExgratiaAmt = grossExgratiaAmt - exgratiaAmt;
                    netExgratiaAmt = NumberFormatting.castNumber(netExgratiaAmt, insertVal);
                    exgretia_percentage = (exgratiaAmt / formTot) * 100;
                    exgretia_percentage = NumberFormatting.castNumber(exgretia_percentage, insertVal);
                // dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
                // dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
                // dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;
                // dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
                } else {
                    formTot = 0;
                    exgratiaAmt = 0;
                    grossExgratiaAmt = 0;
                    dedExgratiaAmt = 0;
                    payableDays = bonusCalc.getTotalBonusDays();
                    isApp = "No";
                }
                int n = bonusCalcRepo.updateExgratiaAmts(formTot, grossExgratiaAmt, exgratiaAmt, dedExgratiaAmt, payableDays, yyDtTm.format(date), userId, isApp, bonusCalc.getBonusCalcId(), exgretia_percentage);
                if (n > 0) {
                    BonusCalc bonusCalc1 = bonusCalcRepo.findByEmpIdAndBonusIdAndDelStatus(empId, bonusId, 1);
                    ObjectMapper mapper = new ObjectMapper();
                    BonusCalc organisation = mapper.readValue(bonusCalc1.getBonusDetails(), BonusCalc.class);
                    organisation.setExgratiaPrcnt(exgretia_percentage);
                    organisation.setExgretiaApplicable("Yes");
                    organisation.setTotalExgretiaDays((int) (payableDays));
                    organisation.setTotalExgretiaWages(formTot);
                    organisation.setIsExgretiaFinalized("0");
                    organisation.setLoginIdExgretia(userId);
                    organisation.setLoginTimeExgretia(yyDtTm.format(date));
                    organisation.setPaidExgretiaAmt(0);
                    organisation.setGrossExgretiaAmt(grossExgratiaAmt);
                    organisation.setNetExgretiaAmt(netExgratiaAmt);
                    organisation.setDedExgretiaAmt(dedExgratiaAmt);
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(organisation);
                    int p = bonusCalcRepo.updateExgratiaDetails(json, bonusCalc1.getBonusCalcId());
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/deleteBonusCalcGS" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonusCalcGS(int bonusCalcId){
    System.err.println("deleteBonusCalcGS");
    Info info = new Info();
    try {
        int delete = bonusCalcRepo.deleteBonus(bonusCalcId);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("deleted");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getAllBonusCalcListGS" }, method = RequestMethod.GET)
@ResponseBody
public List<BonusCalc> getAllBonusCalcList(){
    System.err.println("getAllBonusCalcListGS");
    List<BonusCalc> list = new ArrayList<BonusCalc>();
    try {
        list = bonusCalcRepo.findByDelStatus(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getBonusCalcListGS" }, method = RequestMethod.POST)
public List<BonusCalc> getBonusCalcListGS(int bonusId,int flag){
    System.err.println("getBonusCalcListGS");
    List<BonusCalc> list = new ArrayList<BonusCalc>();
    try {
        if (flag == 0) {
            list = bonusCalcRepo.getEmpDetailListForBonus(bonusId);
        } else {
            list = bonusCalcRepo.getEmpDetailListForBonusEx(bonusId);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/empBonusAppUpdateForExgratiaGS" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusAppUpdateForExgratiaGS(int bonusAppId,String startDate,int isFinal,int bonusId,String remark,int companyId,String dateTime,int userId){
    System.err.println("empBonusAppUpdateForExgratiaGS");
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


@RequestMapping(value = { "/empBonusCalcUpdateForExgratiaGS" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusCalcUpdateForExgratiaGS(int bonusId,int bonusCalcId,double paidExgratiaAmt,int companyId,String dateTime,int userId){
    System.err.println("empBonusCalcUpdateForExgratiaGS");
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
        double totalExgretiaWages = 0;
        double dedExgratiaAmt = 0;
        double payableDays = 0;
        double exPrcnt = 0;
        double netExgretiaAmt = 0;
        String isApp = null;
        BonusCalc bonusCalc = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
        double exgratiaAmt1 = 0;
        if (bonusCalc != null) {
            isApp = bonusCalc.getBonusApplicable();
            if (isApp.equals("Yes")) {
                payableDays = bonusCalc.getTotalBonusDays();
                payableDays = NumberFormatting.castNumber(payableDays, insertVal);
                totalExgretiaWages = bonusCalc.getTotalExgretiaWages();
                netExgretiaAmt = paidExgratiaAmt;
                grossExgratiaAmt = paidExgratiaAmt;
                exPrcnt = (paidExgratiaAmt / totalExgretiaWages) * 100;
                exPrcnt = NumberFormatting.castNumber(exPrcnt, insertVal);
            // grossExgratiaAmt = exgratiaAmt1 + exgratiaAmt;
            // grossExgratiaAmt = NumberFormatting.castNumber(grossExgratiaAmt, insertVal);
            // dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
            // dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
            // dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;
            // dedExgratiaAmt = NumberFormatting.castNumber(dedExgratiaAmt, insertVal);
            } else {
                exgratiaAmt = 0;
                exgratiaAmt = 0;
                grossExgratiaAmt = 0;
                dedExgratiaAmt = 0;
                payableDays = bonusCalc.getTotalBonusDays();
                isApp = "No";
            }
            int n = bonusCalcRepo.updateExgratiaAmtsGS(paidExgratiaAmt, grossExgratiaAmt, netExgretiaAmt, exPrcnt, yyDtTm.format(date), userId, bonusCalc.getBonusCalcId());
            if (n > 0) {
                BonusCalc bonusCalc1 = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
                ObjectMapper mapper = new ObjectMapper();
                BonusCalc organisation = mapper.readValue(bonusCalc1.getBonusDetails(), BonusCalc.class);
                organisation.setExgratiaPrcnt(exPrcnt);
                organisation.setExgretiaApplicable("Yes");
                organisation.setTotalExgretiaDays(payableDays);
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


@RequestMapping(value = { "/getBonusByIdGS" }, method = RequestMethod.POST)
@ResponseBody
public BonusMaster getBonusById(int bonusId){
    System.err.println("getBonusByIdGS");
    BonusMaster bous = new BonusMaster();
    try {
        bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bous;
}


@RequestMapping(value = { "/deleteBonusGS" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonus(int bonusId){
    System.err.println("deleteBonusGS");
    Info info = new Info();
    try {
        int delete = bonusMasterRepo.deleteBonus(bonusId);
        if (delete > 0) {
            info.setError(false);
            info.setMsg("deleted");
        } else {
            info.setError(true);
            info.setMsg("failed");
        }
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/deleteBonusCalcExratiaGS" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonusCalcExratia(int bonusCalcId){
    System.err.println("deleteBonusCalcExratiaGS");
    Info info = new Info();
    try {
        int n1 = bonusCalcRepo.updateExgratiaAmtsDelete(bonusCalcId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/chkIsBonusFinalizedGS" }, method = RequestMethod.POST)
@ResponseBody
public BonusApplicable chkIsBonusFinalized(int bonusId){
    System.err.println("chkIsBonusFinalizedGS");
    BonusApplicable temp = new BonusApplicable();
    try {
        temp = bonusApplicableRepo.findByBonusId(bonusId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return temp;
}


@RequestMapping(value = { "/getBonusCalcByCalcIdGS" }, method = RequestMethod.POST)
@ResponseBody
public BonusCalc getBonusCalcByCalcIdGS(int bonusCalcId){
    System.err.println("getBonusCalcByCalcIdGS");
    BonusCalc list = new BonusCalc();
    try {
        list = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveBonusGS" }, method = RequestMethod.POST)
@ResponseBody
public BonusMaster saveBonus(BonusMaster bonusMaster){
    System.err.println("saveBonusGS");
    BonusMaster save = new BonusMaster();
    try {
        save = bonusMasterRepo.saveAndFlush(bonusMaster);
        if (save == null) {
            save = new BonusMaster();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getAllEmployeeDetailForBonusGS" }, method = RequestMethod.POST)
public List<GetEmployeeDetails> getAllEmployeeDetailForBonusGS(int bonusId,int flag,List<Integer> locId){
    System.err.println("getAllEmployeeDetailForBonusGS");
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


@RequestMapping(value = { "/empBonusSaveGS" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusSaveGS(List<Integer> empIdList,int bonusId,int companyId,int userId){
    System.err.println("empBonusSaveGS");
    // Note : bonus_sealing_limit_amount_per_month & bonus_app_below_amount are
    // remaning
    Info info = new Info();
    Date date = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // String retString = new String();
    // To get bonus details
    BonusMaster bonus = new BonusMaster();
    bonus = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
    double bonusPrcnt = bonus.getBonusPercentage();
    BonusDates datesDet = bonusDatesRepo.getDates(bonusId);
    // To get bonus formula
    List<Setting> settingList = new ArrayList<Setting>();
    String bonusFormula = "";
    double advPrcnt = 0.0;
    double pujaPrcnt = 0.0;
    double lossPrcnt = 0.0;
    settingList = settingRepo.findByGroup("BONUS");
    // try { settingList = settingRepo.findByGroup("BONUS"); if (settingList !=
    // null) { for (int i = 0; i < settingList.size(); i++) {
    // 
    // if (settingList.get(i).getKey().equals("bonus_formula")) { bonusFormula =
    // settingList.get(i).getValue(); // System.err.println("bonus formula**" +
    // bonusFormula); break; } else { bonusFormula = ""; }
    // 
    // } }
    // 
    // } catch (Exception e) {
    // 
    // info.setError(true); info.setMsg("failed");
    // 
    // }
    for (int k = 0; k < settingList.size(); k++) {
        if (settingList.get(k).getKey().equalsIgnoreCase("bonus_formula")) {
            bonusFormula = settingList.get(k).getValue();
        } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_adv_amt_percentage")) {
            advPrcnt = Float.parseFloat(settingList.get(k).getValue());
        } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_puja_amt_percentage")) {
            pujaPrcnt = Float.parseFloat(settingList.get(k).getValue());
        } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_loss_amt_percentage")) {
            lossPrcnt = Float.parseFloat(settingList.get(k).getValue());
        }
    }
    int insertVal = 0;
    try {
        Setting setting = settingRepo.findByKey("ammount_format_Insert");
        insertVal = Integer.parseInt(setting.getValue());
    } catch (Exception e) {
        insertVal = 0;
    }
    bonusPrcnt = NumberFormatting.castNumber(bonusPrcnt, insertVal);
    // System.err.println("bonusFormula **" + bonusFormula);
    String[] forList = bonusFormula.split("\\+");
    List<String> formulaList = new ArrayList<String>(Arrays.asList(forList));
    // System.err.println("formulaList before**" + formulaList.toString());
    for (int j = 0; j < formulaList.size(); j++) {
        if ((formulaList.get(j).trim()).equals("basic_cal")) {
            formulaList.remove(j);
            break;
        }
    }
    // System.err.println("formulaList after**" + formulaList.toString());
    // System.err.println("empIdList **" + empIdList.toString());
    try {
        for (int i = 0; i < empIdList.size(); i++) {
            int empId = empIdList.get(i);
            int isAdd = 0;
            try {
                BonusCalc ispresen = bonusCalcRepo.findByEmpIdAndBonusIdAndDelStatus(empId, bonusId, 1);
                if (ispresen == null) {
                    isAdd = 1;
                } else {
                    isAdd = 2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.err.println("is Add" + isAdd);
            double payableDay = 0.0;
            try {
                BonusParam salDays = bonusParamRepo.getDays(empId, datesDet.getMonthFrom(), datesDet.getMonthTo(), datesDet.getYearFrom(), datesDet.getYearTo());
                payableDay = Double.parseDouble(salDays.getTotalBasicCal());
                payableDay = NumberFormatting.castNumber(payableDay, insertVal);
            } catch (Exception e) {
                payableDay = 0;
            }
            double bonusAmt = 0;
            double grossBonus = 0;
            String isApplicable = null;
            double advPrcntAmt = 0.0;
            double pujaPrcntAmt = 0.0;
            double lossPrcntAmt = 0.0;
            double formTot = 0.0;
            if (isAdd == 1) {
                // System.err.println("isert process");
                if (payableDay >= bonus.getMinDays()) {
                    isApplicable = "Yes";
                    // System.err.println("Applicable");
                    double basic_calc = 0;
                    // to get total from formula
                    // List<Integer> allIdList = new ArrayList<Integer>(); for (int j = 0; j <
                    // formulaList.size(); j++) {
                    // 
                    // // System.err.println("formulaList for **" + formulaList.get(j)); Allowances
                    // ac = new Allowances(); try { ac =
                    // AalowancesRepo.findByShortNameAndDelStatus(formulaList.get(j).trim(), 1);
                    // allIdList.add(ac.getAllowanceId()); } catch (Exception e) {
                    // 
                    // } }
                    try {
                        // BonusParam salCal = bonusParamRepo.getBonusParameters(empId, datesDet.getMonthFrom(),
                        // datesDet.getMonthTo(), datesDet.getYearFrom(), datesDet.getYearTo());
                        // System.err.println("BonusParam**" + salCal.toString());
                        /*if (salCal.getTotalAllowance() == null) {
								formTot = Double.parseDouble(salCal.getTotalBasicCal());

							} else if (salCal.getTotalBasicCal() == null) {
								formTot = 0;
							} else {
								formTot = ((Double.parseDouble(salCal.getTotalBasicCal())
										+ Double.parseDouble(salCal.getTotalAllowance())) / 365) * payableDay;
							}*/
                        // mahendra 04-04-2020
                        SalaryCalc salCal = salaryCalcRepo.getBonusParametersGS(empId, datesDet.getMonthFrom(), datesDet.getMonthTo(), datesDet.getYearFrom(), datesDet.getYearTo());
                        if (salCal.getGrossSalDefault() != 0) {
                            formTot = salCal.getGrossSalDefault();
                        } else {
                            formTot = 0;
                        }
                        formTot = NumberFormatting.castNumber(formTot, insertVal);
                    } catch (Exception e) {
                        formTot = 0;
                    }
                    try {
                        bonusAmt = (formTot * bonusPrcnt) / 100;
                        bonusAmt = NumberFormatting.castNumber(bonusAmt, insertVal);
                        // + bonusAmt;
                        grossBonus = formTot;
                        grossBonus = NumberFormatting.castNumber(grossBonus, insertVal);
                    } catch (Exception e) {
                        grossBonus = formTot;
                        bonusAmt = (0 * bonusPrcnt) / 100;
                    }
                    // System.err.println("grossBonus"+grossBonus);
                    // System.err.println("formTot"+formTot);
                    /*advPrcntAmt = (grossBonus * advPrcnt) / 100;
						advPrcntAmt = advPrcntAmt + grossBonus;
						advPrcntAmt = NumberFormatting.castNumber(advPrcntAmt, insertVal);
						pujaPrcntAmt = (grossBonus * pujaPrcnt) * 100;
						pujaPrcntAmt = pujaPrcntAmt + grossBonus;
						pujaPrcntAmt = NumberFormatting.castNumber(pujaPrcntAmt, insertVal);

						lossPrcntAmt = (grossBonus * lossPrcnt) / 100;
						lossPrcntAmt = lossPrcntAmt + grossBonus;
						lossPrcntAmt = NumberFormatting.castNumber(lossPrcntAmt, insertVal);*/
                    // 04-04-2020
                    System.err.println("lossPrcntAmt" + lossPrcntAmt);
                } else {
                    isApplicable = "No";
                }
                GetEmployeeDetails list = getEmployeeDetailsRepo.getEmpDetailList(empId);
                BonusCalc calcSave = new BonusCalc();
                calcSave.setBonusId(bonusId);
                calcSave.setCompanyEmpCode(list.getEmpCode());
                calcSave.setCompanyId(companyId);
                calcSave.setLocation(list.getLocName());
                calcSave.setCurrAge(0);
                calcSave.setCurrDesignation(list.getEmpDesgn());
                calcSave.setEmpId(empId);
                calcSave.setEmpName(list.getFirstName().concat(" ").concat(list.getMiddleName().concat(" ").concat(list.getSurname())));
                calcSave.setNetBonusAmt(bonusAmt);
                calcSave.setDelStatus(1);
                calcSave.setExVar2("NA");
                calcSave.setExInt1(0);
                calcSave.setExInt2(0);
                calcSave.setExVar1("NA");
                calcSave.setLoginIdBonus(userId);
                calcSave.setLoginTimeBonus(sf.format(date));
                calcSave.setBonusApplicable(isApplicable);
                calcSave.setGrossBonusAmt(bonusAmt);
                calcSave.setExgratiaPrcnt(0);
                calcSave.setDedBonusAdvAmt(advPrcntAmt);
                calcSave.setDedBonusLossAmt(lossPrcntAmt);
                calcSave.setDedBonusPujaAmt(pujaPrcntAmt);
                calcSave.setDedExgretiaAmt(0);
                calcSave.setDedReason("");
                calcSave.setExgretiaApplicable("No");
                calcSave.setExgretiaDetails("");
                // ****
                calcSave.setIsBonussheetFinalized("0");
                calcSave.setIsExgretiaFinalized("0");
                calcSave.setLoginIdExgretia(0);
                calcSave.setLoginTimeExgretia("0000-00-00 00:00:00");
                calcSave.setNetExgretiaAmt(0);
                calcSave.setPaidBonusAmt(bonusAmt);
                // calcSave.setPaidBonusDate(0000-00-00);
                calcSave.setPaidExgretiaAmt(0);
                // calcSave.setPaidExgretiaDate(paidExgretiaDate);
                // ***
                calcSave.setRecStatus(0);
                // Gross Salary Default
                calcSave.setTotalBonusWages(formTot);
                calcSave.setTotalExgretiaDays(0);
                calcSave.setTotalExgretiaWages(0);
                // ***
                calcSave.setTotalBonusDays(payableDay);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(calcSave);
                calcSave.setBonusDetails(json);
                BonusCalc calcSave1 = bonusCalcRepo.save(calcSave);
                if (calcSave1 != null) {
                    // if (retString.length() == 0) { retString =
                    // (String.valueOf(calcSave1.getBonusCalcId()));
                    // 
                    // } else { retString =
                    // retString.concat(",").concat(String.valueOf(calcSave1.getBonusCalcId()));
                    // 
                    // }
                    info.setError(false);
                    info.setMsg("success");
                } else {
                    info.setError(true);
                    info.setMsg("failed");
                }
            // System.err.println("retString**" + retString);
            }
        }
    } catch (Exception e) {
        System.err.println("Exce in empBonusSaveGS  " + e.getMessage());
        e.printStackTrace();
        info.setError(true);
        info.setMsg("excep");
    }
    return info;
}


@RequestMapping(value = { "/getAllBonusListGS" }, method = RequestMethod.GET)
@ResponseBody
public List<BonusMaster> getAllBonusList(){
    System.err.println("getAllBonusListGS");
    List<BonusMaster> list = new ArrayList<BonusMaster>();
    try {
        list = bonusMasterRepo.findByDelStatus();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}