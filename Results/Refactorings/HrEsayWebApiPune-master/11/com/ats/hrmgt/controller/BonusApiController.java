import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.NumberFormatting;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
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
public class BonusApiController {

@Autowired
 private BonusMasterRepo bonusMasterRepo;

@Autowired
 private BonusApplicableRepo bonusApplicableRepo;

@Autowired
 private BonusCalcRepo bonusCalcRepo;

@Autowired
 private SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

@Autowired
 private AllowancesRepo AalowancesRepo;

@Autowired
 private EmpSalAllowanceRepo empAllowanceSalCalRepo;

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private SalaryCalcRepo salaryCalcRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private BonusDatesRepo bonusDatesRepo;

@Autowired
 private BonusParamRepo bonusParamRepo;

@Autowired
 private PayBonusRepo payBonusRepo;

@Autowired
 private PayBonusDetailsRepo payBonusDetailsRepo;


@RequestMapping(value = { "/empBonusAppSaveOrUpdate" }, method = RequestMethod.POST)
@ResponseBody
public Info empBonusAppSaveOrUpdate(int bonusAppId,String startDate,int isFinal,int bonusId,String remark,int companyId,String dateTime,int userId){
    Info info = new Info();
    int flag = 0;
    String paidDate = DateConvertor.convertToYMD(startDate);
    String[] a = paidDate.split("-");
    try {
        if (isFinal == 3) {
            // insert
            double advPrcnt = 0.0;
            double pujaPrcnt = 0.0;
            double lossPrcnt = 0.0;
            String bonus_formula = null;
            String bonus_app_below_applicable = null;
            double bonus_app_below_amount = 0;
            String bonus_sealing_limit_applicable = null;
            double bonus_sealing_limit_amount = 0;
            double bonus_percentage = 0;
            /*
                 * Setting setting = new Setting();
                 *
                 * try { setting = settingRepo.findByKey("bonus_app_below_amount");
                 * bonus_app_below_amount = Double.parseDouble(setting.getValue()); } catch
                 * (Exception e) {
                 *
                 * bonus_app_below_amount = 0.0;
                 *
                 * }
                 *
                 * try { setting = settingRepo.findByKey("bonus_sealing_limit_amount");
                 * bonus_sealing_limit_amount = Double.parseDouble(setting.getValue()); } catch
                 * (Exception e) {
                 *
                 * bonus_sealing_limit_amount = 0.0;
                 *
                 * }
                 *
                 * try { setting = settingRepo.findByKey("bonus_app_below_applicable");
                 * bonus_app_below_applicable = setting.getValue(); } catch (Exception e) {
                 *
                 * bonus_app_below_applicable = "";
                 *
                 * }
                 *
                 * try { setting = settingRepo.findByKey("bonus_sealing_limit_applicable");
                 * bonus_sealing_limit_applicable = setting.getValue(); } catch (Exception e) {
                 *
                 * bonus_sealing_limit_applicable = "";
                 *
                 * }
                 *
                 * try { setting = settingRepo.findByKey("ded_bonus_adv_amt_percentage");
                 * advPrcnt = Double.parseDouble(setting.getValue()); } catch (Exception e) {
                 *
                 * advPrcnt = 0;
                 *
                 * } try {
                 *
                 * setting = settingRepo.findByKey("ded_bonus_puja_amt_percentage"); pujaPrcnt =
                 * Double.parseDouble(setting.getValue()); } catch (Exception e) {
                 *
                 * pujaPrcnt = 0;
                 *
                 * } try { setting = settingRepo.findByKey("ded_bonus_loss_amt_percentage");
                 * lossPrcnt = Double.parseDouble(setting.getValue()); } catch (Exception e) {
                 *
                 * lossPrcnt = 0;
                 *
                 * } try {
                 *
                 * setting = settingRepo.findByKey("bonus_formula"); bonus_formula =
                 * setting.getValue(); } catch (Exception e) {
                 *
                 * bonus_formula = "";
                 *
                 * }
                 */
            List<Setting> settingList = new ArrayList<Setting>();
            settingList = settingRepo.findByGroup("BONUS");
            String bonusFormula = "";
            for (int k = 0; k < settingList.size(); k++) {
                if (settingList.get(k).getKey().equalsIgnoreCase("bonus_app_below_amount")) {
                    bonus_app_below_amount = Double.parseDouble(settingList.get(k).getValue());
                } else if (settingList.get(k).getKey().equalsIgnoreCase("bonus_sealing_limit_amount")) {
                    bonus_sealing_limit_amount = Float.parseFloat(settingList.get(k).getValue());
                } else if (settingList.get(k).getKey().equalsIgnoreCase("bonus_app_below_applicable")) {
                    bonus_app_below_applicable = settingList.get(k).getValue();
                } else if (settingList.get(k).getKey().equalsIgnoreCase("bonus_sealing_limit_applicable")) {
                    bonus_sealing_limit_applicable = settingList.get(k).getValue();
                } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_adv_amt_percentage")) {
                    advPrcnt = Float.parseFloat(settingList.get(k).getValue());
                } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_puja_amt_percentage")) {
                    pujaPrcnt = Float.parseFloat(settingList.get(k).getValue());
                } else if (settingList.get(k).getKey().equalsIgnoreCase("ded_bonus_loss_amt_percentage")) {
                    lossPrcnt = Float.parseFloat(settingList.get(k).getValue());
                } else if (settingList.get(k).getKey().equalsIgnoreCase("bonus_formula")) {
                    bonus_formula = settingList.get(k).getValue();
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
                bonus_percentage = bous.getBonusPercentage();
            } catch (Exception e) {
                bonus_percentage = 0.0;
            }
            BonusApplicable temp = new BonusApplicable();
            temp.setBonusAppBelowAmount(NumberFormatting.castNumber(bonus_app_below_amount, insertVal));
            temp.setBonusFormula(bonus_formula);
            temp.setBonusId(bonusId);
            temp.setBonusItSub(0);
            temp.setBonusPercentage(NumberFormatting.castNumber(bonus_percentage, insertVal));
            temp.setBonusRemark(remark);
            temp.setBonusSealingLimitAmount(NumberFormatting.castNumber(bonus_sealing_limit_amount, insertVal));
            temp.setBonusSealingLimitApplicable(bonus_sealing_limit_applicable);
            temp.setCompanyId(companyId);
            temp.setExgretiaFormula("");
            temp.setExgretiaItSub(0);
            temp.setExgretiaPercentage(0);
            temp.setExgretiaRemark("");
            temp.setIsBonussheetFinalized(String.valueOf(1));
            temp.setIsExgretiaFinalized("");
            temp.setIsPayrollFinalized("");
            temp.setLoginIdBonus(userId);
            temp.setLoginTimeBonus(dateTime);
            temp.setLoginIdExgretia(0);
            temp.setLoginTimeExgretia("0000-00-00 00:00:00");
            temp.setPayrollMonth(0);
            temp.setPayrollYear(0);
            // 27-04-2020
            temp.setBonusPaidDate(DateConvertor.convertToYMD(startDate));
            temp.setExgratiaPaidDate("0000-00-00");
            temp.setExVar1("");
            temp.setExInt1(0);
            BonusApplicable temp1 = bonusApplicableRepo.save(temp);
            if (temp1 != null) {
                flag = 1;
            } else {
                flag = 0;
            }
        } else {
            int n = bonusApplicableRepo.updateBonus(bonusAppId, a[0], a[1]);
            // update
            flag = 1;
        }
        if (flag == 1) {
            int n = bonusCalcRepo.updateCalcFinalize(bonusId, paidDate);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllPayBonus" }, method = RequestMethod.GET)
@ResponseBody
public List<PayBonus> getAllPayBonus(){
    List<PayBonus> payBonusList = new ArrayList<PayBonus>();
    try {
        payBonusList = payBonusRepo.findByDelStatus(1);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return payBonusList;
}


@RequestMapping(value = { "/getAllPayPedingDetails" }, method = RequestMethod.GET)
@ResponseBody
public List<PayBonusDetails> getAllPayPedingDetails(){
    List<PayBonusDetails> detList = new ArrayList<PayBonusDetails>();
    try {
        detList = payBonusDetailsRepo.getAllUnpaid();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return detList;
}


@RequestMapping(value = { "/getPayBonusById" }, method = RequestMethod.POST)
@ResponseBody
public PayBonus getPayBonusById(int payBonusTypeId){
    PayBonus pay = new PayBonus();
    try {
        pay = payBonusRepo.findByPayTypeIdAndDelStatus(payBonusTypeId, 1);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return pay;
}


@RequestMapping(value = { "/getAllBonusCalcList" }, method = RequestMethod.GET)
@ResponseBody
public List<BonusCalc> getAllBonusCalcList(){
    List<BonusCalc> list = new ArrayList<BonusCalc>();
    try {
        list = bonusCalcRepo.findByDelStatus(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getBonusCalcList" }, method = RequestMethod.POST)
public List<BonusCalc> getAllEmployeeDetailForBonus(int bonusId,int flag){
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


@RequestMapping(value = { "/getBonusById" }, method = RequestMethod.POST)
@ResponseBody
public BonusMaster getBonusById(int bonusId){
    BonusMaster bous = new BonusMaster();
    try {
        bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bous;
}


@RequestMapping(value = { "/deletePayBonus" }, method = RequestMethod.POST)
@ResponseBody
public Info deletePayBonus(int payBonusTypeId){
    Info info = new Info();
    try {
        int delete = payBonusRepo.deleteBonusPayType(payBonusTypeId);
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


@RequestMapping(value = { "/getPayDetById" }, method = RequestMethod.POST)
@ResponseBody
public PayBonusDetails getPayDetById(int payDetId){
    PayBonusDetails payDet = new PayBonusDetails();
    try {
        payDet = payBonusDetailsRepo.findByPayIdAndDelStatus(payDetId, 1);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return payDet;
}


@RequestMapping(value = { "/checkBonusTitle" }, method = RequestMethod.POST)
@ResponseBody
public Info checkBonusTitle(String bonusTitle){
    Info info = new Info();
    List<BonusMaster> emp = new ArrayList<BonusMaster>();
    try {
        emp = bonusMasterRepo.findByFyTitleAndDelStatus(bonusTitle, 1);
        info.setError(emp.size() > 0);
    } catch (Exception e) {
        System.err.println("Exce in checkEmployeeEmail  " + e.getMessage());
    }
    return info;
}


@RequestMapping(value = { "/deleteBonus" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonus(int bonusId){
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


@RequestMapping(value = { "/deleteBonusCalc" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteBonusCalc(int bonusCalcId){
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


@RequestMapping(value = { "/getAllPayDetails" }, method = RequestMethod.GET)
@ResponseBody
public List<PayBonusDetails> getAllPayDetails(){
    List<PayBonusDetails> detList = new ArrayList<PayBonusDetails>();
    try {
        detList = payBonusDetailsRepo.findByDelStatus(1);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}


@RequestMapping(value = { "/getAllPayPedingDetailsByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<PayBonusDetails> getAllPayPedingDetailsByEmpId(int empId){
    List<PayBonusDetails> detList = new ArrayList<PayBonusDetails>();
    try {
        detList = payBonusDetailsRepo.getAllPayPedingDetailsByEmpId(empId);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return detList;
}


@RequestMapping(value = { "/chkIsBonusFinalized" }, method = RequestMethod.POST)
@ResponseBody
public BonusApplicable chkIsBonusFinalized(int bonusId){
    BonusApplicable temp = new BonusApplicable();
    try {
        temp = bonusApplicableRepo.findByBonusId(bonusId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return temp;
}


@RequestMapping(value = { "/savePayBonusDetails" }, method = RequestMethod.POST)
@ResponseBody
public PayBonusDetails savePayBonusDetails(PayBonusDetails payBonusDetails){
    PayBonusDetails save = new PayBonusDetails();
    try {
        save = payBonusDetailsRepo.saveAndFlush(payBonusDetails);
        if (save == null) {
            save = new PayBonusDetails();
            save.setError(true);
        } else {
            save.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/empBonusSave" }, method = RequestMethod.POST)
@ResponseBody
public Info taskAssignmentUpdate(List<Integer> empIdList,int bonusId,int companyId,int userId){
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
    /*
         * try { settingList = settingRepo.findByGroup("BONUS"); if (settingList !=
         * null) { for (int i = 0; i < settingList.size(); i++) {
         *
         * if (settingList.get(i).getKey().equals("bonus_formula")) { bonusFormula =
         * settingList.get(i).getValue(); // System.err.println("bonus formula**" +
         * bonusFormula); break; } else { bonusFormula = ""; }
         *
         * } }
         *
         * } catch (Exception e) {
         *
         * info.setError(true); info.setMsg("failed");
         *
         * }
         */
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
                if (payableDay <= bonus.getMinDays()) {
                    isApplicable = "Yes";
                    // System.err.println("Applicable");
                    double basic_calc = 0;
                    // to get total from formula
                    /*
                         * List<Integer> allIdList = new ArrayList<Integer>(); for (int j = 0; j <
                         * formulaList.size(); j++) {
                         *
                         * // System.err.println("formulaList for **" + formulaList.get(j)); Allowances
                         * ac = new Allowances(); try { ac =
                         * AalowancesRepo.findByShortNameAndDelStatus(formulaList.get(j).trim(), 1);
                         * allIdList.add(ac.getAllowanceId()); } catch (Exception e) {
                         *
                         * } }
                         */
                    try {
                        BonusParam salCal = bonusParamRepo.getBonusParameters(empId, datesDet.getMonthFrom(), datesDet.getMonthTo(), datesDet.getYearFrom(), datesDet.getYearTo());
                        // System.err.println("BonusParam**" + salCal.toString());
                        if (salCal.getTotalAllowance() == null) {
                            formTot = Double.parseDouble(salCal.getTotalBasicCal());
                        } else if (salCal.getTotalBasicCal() == null) {
                            formTot = 0;
                        } else {
                            formTot = ((Double.parseDouble(salCal.getTotalBasicCal()) + Double.parseDouble(salCal.getTotalAllowance())) / 365) * payableDay;
                        }
                        formTot = NumberFormatting.castNumber(formTot, insertVal);
                    } catch (Exception e) {
                        formTot = 0;
                    }
                    try {
                        bonusAmt = (formTot * bonusPrcnt) / 100;
                        bonusAmt = NumberFormatting.castNumber(bonusAmt, insertVal);
                        grossBonus = formTot + bonusAmt;
                        grossBonus = NumberFormatting.castNumber(grossBonus, insertVal);
                    } catch (Exception e) {
                        grossBonus = formTot;
                        bonusAmt = (0 * bonusPrcnt) / 100;
                    }
                    // System.err.println("grossBonus"+grossBonus);
                    // System.err.println("formTot"+formTot);
                    advPrcntAmt = (grossBonus * advPrcnt) / 100;
                    advPrcntAmt = advPrcntAmt + grossBonus;
                    advPrcntAmt = NumberFormatting.castNumber(advPrcntAmt, insertVal);
                    pujaPrcntAmt = (grossBonus * pujaPrcnt) * 100;
                    pujaPrcntAmt = pujaPrcntAmt + grossBonus;
                    pujaPrcntAmt = NumberFormatting.castNumber(pujaPrcntAmt, insertVal);
                    lossPrcntAmt = (grossBonus * lossPrcnt) / 100;
                    lossPrcntAmt = lossPrcntAmt + grossBonus;
                    lossPrcntAmt = NumberFormatting.castNumber(lossPrcntAmt, insertVal);
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
                calcSave.setGrossBonusAmt(grossBonus);
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
                calcSave.setPaidBonusAmt(0);
                // calcSave.setPaidBonusDate(0000-00-00);
                calcSave.setPaidExgretiaAmt(0);
                // calcSave.setPaidExgretiaDate(paidExgretiaDate);
                // ***
                calcSave.setRecStatus(0);
                // ******
                calcSave.setTotalBonusWages((int) formTot);
                calcSave.setTotalExgretiaDays(0);
                calcSave.setTotalExgretiaWages(0);
                // ***
                calcSave.setTotalBonusDays(payableDay);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(calcSave);
                calcSave.setBonusDetails(json);
                BonusCalc calcSave1 = bonusCalcRepo.save(calcSave);
                if (calcSave1 != null) {
                    /*
                         * if (retString.length() == 0) { retString =
                         * (String.valueOf(calcSave1.getBonusCalcId()));
                         *
                         * } else { retString =
                         * retString.concat(",").concat(String.valueOf(calcSave1.getBonusCalcId()));
                         *
                         * }
                         */
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
        System.err.println("Exce in deleteService  " + e.getMessage());
        e.printStackTrace();
        info.setError(true);
        info.setMsg("excep");
    }
    return info;
}


@RequestMapping(value = { "/saveBonus" }, method = RequestMethod.POST)
@ResponseBody
public BonusMaster saveBonus(BonusMaster bonusMaster){
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


@RequestMapping(value = { "/savePayBonusType" }, method = RequestMethod.POST)
@ResponseBody
public PayBonus savePayBonusType(PayBonus paybonus){
    PayBonus pay = new PayBonus();
    try {
        pay = payBonusRepo.save(paybonus);
        if (pay == null) {
            pay = new PayBonus();
            pay.setError(true);
        } else {
            pay.setError(false);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return paybonus;
}


@RequestMapping(value = { "/getAllBonusList" }, method = RequestMethod.GET)
@ResponseBody
public List<BonusMaster> getAllBonusList(){
    List<BonusMaster> list = new ArrayList<BonusMaster>();
    try {
        list = bonusMasterRepo.findByDelStatus();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deletePayBonusDet" }, method = RequestMethod.POST)
@ResponseBody
public Info deletePayBonusDet(int payId){
    Info info = new Info();
    try {
        int delete = payBonusDetailsRepo.deleteBonusPay(payId);
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


}