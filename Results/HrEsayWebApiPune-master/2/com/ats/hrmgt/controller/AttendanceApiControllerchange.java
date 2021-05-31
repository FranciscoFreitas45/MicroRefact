import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.EmpJsonDataRepository;
import com.ats.hrmgt.repo.ShiftAssignDailyRepository;
import com.ats.hrmgt.repository;
import com.ats.hrmgt.service.CommonFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util;
import java.util.concurrent.TimeUnit;
@RestController
@Component
public class AttendanceApiControllerchange {

@Autowired
 private AccessRightModuleRepository accessRightModuleRepository;

@Autowired
 private EmployeeMasterRepository employeeMasterRepository;

@Autowired
 private DailyAttendanceRepository dailyAttendanceRepository;

@Autowired
 private SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

@Autowired
 private InfoForUploadAttendanceRepository infoForUploadAttendanceRepository;

@Autowired
 private EmpInfoRepository empInfoRepository;

@Autowired
 private HolidayRepo holidayRepo;

@Autowired
 private ShiftMasterRepository shiftMasterRepository;

@Autowired
 private WeeklyOffShitRepository weeklyOffShitRepository;

@Autowired
 private LvTypeRepository lvTypeRepository;

@Autowired
 private LvmSumUpRepository lvmSumUpRepository;

@Autowired
 private MstEmpTypeRepository mstEmpTypeRepository;

@Autowired
 private CommonFunctionService commonFunctionService;

@Autowired
 private WeeklyOffRepo weeklyOffRepo;

@Autowired
 private LeaveApplyRepository leaveApplyRepository;

@Autowired
 private LeaveTrailRepository leaveTrailRepository;

@Autowired
 private DailyDailyInformationRepository dailyDailyInformationRepository;

@Autowired
 private SummaryAttendanceRepository summaryAttendanceRepository;

@Autowired
 private GetDailyDailyRecordRepository getDailyDailyRecordRepository;

 private int PL_CL_HD_leave_insert_automatic;

@Autowired
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private EmpJsonDataRepository empJsonDataRepository;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private EmpSalaryInfoForPayrollRepository empSalaryInfoForPayrollRepository;

@Autowired
 private ShiftAssignDailyRepository shiftAssignDailyRepository;

@Autowired
 private EmpSalTypeRepository empSalTypeRepository;

@Autowired
 private EmpListForHolidayApproveRepo empListForHolidayApproveRepo;

@Autowired
 private AttendaceLiveCountRepository attendaceLiveCountRepository;

@Autowired
 private DailyAttendaceReportRepository dailyAttendaceReportRepository;

@Autowired
 private LiveThumbDataRepository liveThumbDataRepository;

@Autowired
 private InoutTableRepo inoutTableRepo;


@RequestMapping(value = { "/getDailyDailyRecordForOtApproval" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecordForOtApproval(String date,int empId,List<Integer> locId){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.getDailyDailyRecordForOtApproval(date, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/getDailyDailyRecordForFinalOtApproval" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecordForFinalOtApproval(String date,int empId,List<Integer> locId){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.getDailyDailyRecordForFinalOtApproval(date, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/getAttendanceSheet" }, method = RequestMethod.POST)
@ResponseBody
public AttendanceSheetData getAttendanceSheet(String fromDate,String toDate,int userType,int userId,List<Integer> locId){
    AttendanceSheetData info = new AttendanceSheetData();
    try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fmdt = df.parse(fromDate);
        Date todt = df.parse(toDate);
        /*
             * System.out.println(fmdt + " " + todt);
             *
             * Calendar temp = Calendar.getInstance(); temp.setTime(fmdt); int year =
             * temp.get(Calendar.YEAR); int month = temp.get(Calendar.MONTH) + 1;
             */
        List<EmpInfo> empList = new ArrayList<>();
        List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
        if (userType == 1) {
            empList = empInfoRepository.getEmpListForHod(fromDate, userId);
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListForHod(fromDate, toDate, userId);
        } else {
            empList = empInfoRepository.getEmpListAlllocId(fromDate, locId);
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAlllocId(fromDate, toDate, locId);
        }
        System.out.println("OK");
        List<String> dates = new ArrayList<>();
        List<DateAndDay> dateAndDayList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        for (Date j = fmdt; j.compareTo(todt) <= 0; ) {
            dates.add(sf.format(j));
            DateAndDay dateAndDay = new DateAndDay();
            String stringDate = sdf.format(j);
            dateAndDay.setDate(sf.format(j));
            dateAndDay.setDay(stringDate);
            dateAndDayList.add(dateAndDay);
            /* System.out.println(sf.parse(sf.format(j))); */
            j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
        }
        List<EmpInfoWithDateInfoList> infomationList = new ArrayList<>();
        for (int i = 0; i < empList.size(); i++) {
            EmpInfoWithDateInfoList infomation = new EmpInfoWithDateInfoList();
            infomation.setEmpCode(empList.get(i).getEmpCode());
            infomation.setEmpId(empList.get(i).getEmpId());
            infomation.setEmpName(empList.get(i).getFirstName() + " " + empList.get(i).getSurname());
            List<DailyDailyInfomationForChart> dateInfo = new ArrayList<>();
            for (int j = 0; j < dates.size(); j++) {
                Date dt = sf.parse(dates.get(j));
                int find = 0;
                for (int k = 0; k < dailyAttendanceList.size(); k++) {
                    Date attsdt = df.parse(dailyAttendanceList.get(k).getAttDate());
                    if (dailyAttendanceList.get(k).getEmpId() == empList.get(i).getEmpId() && attsdt.compareTo(dt) == 0) {
                        DailyDailyInfomationForChart dilydly = new DailyDailyInfomationForChart();
                        dilydly.setStatus(dailyAttendanceList.get(k).getAttStatus());
                        dilydly.setStatusShwo(dailyAttendanceList.get(k).getAttsSdShow());
                        dilydly.setDate(dates.get(j));
                        dilydly.setInTime(dailyAttendanceList.get(k).getInTime());
                        dilydly.setOutTime(dailyAttendanceList.get(k).getOutTime());
                        dilydly.setOtMin(dailyAttendanceList.get(k).getOtHr());
                        dilydly.setLateMin(String.valueOf(dailyAttendanceList.get(k).getLateMin()));
                        int hrs = (int) (dailyAttendanceList.get(k).getWorkingHrs() / 60);
                        int min = (int) (dailyAttendanceList.get(k).getWorkingHrs() % 60);
                        dilydly.setWorkingMin(hrs + "." + min);
                        dateInfo.add(dilydly);
                        dailyAttendanceList.remove(k);
                        find = 1;
                        break;
                    }
                }
                if (find == 0) {
                    DailyDailyInfomationForChart dilydly = new DailyDailyInfomationForChart();
                    dilydly.setStatus("NA");
                    dateInfo.add(dilydly);
                }
            }
            infomation.setSttsList(dateInfo);
            infomationList.add(infomation);
        }
        info.setDates(dates);
        info.setInfomationList(infomationList);
        info.setDateAndDayList(dateAndDayList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getLvTypeListAll" }, method = RequestMethod.GET)
@ResponseBody
public List<LvType> getLvTypeListAll(){
    List<LvType> lvTypeList = new ArrayList<>();
    try {
        lvTypeList = lvTypeRepository.findAll();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lvTypeList;
}


@RequestMapping(value = { "/getDailyDailyRecordBetweenDateAndByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getDailyDailyRecordBetweenDateAndByEmpId(String fromDate,String toDate,int empId){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/importAttendanceByFileAndUpdateForPresentStatus" }, method = RequestMethod.POST)
@ResponseBody
public AttendaceReturnInfo importAttendanceByFileAndUpdateForPresentStatus(DataForUpdateAttendance dataForUpdateAttendance){
    AttendaceReturnInfo info = new AttendaceReturnInfo();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
    List<LeaveCancelEmployee> list = new ArrayList<>();
    try {
        String fromDate = dataForUpdateAttendance.getFromDate();
        String toDate = dataForUpdateAttendance.getToDate();
        List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
        List<EmpJsonData> jsonDataList = new ArrayList<>();
        dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate);
        List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
        List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
        List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
        List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
        List<LvType> lvTypeList = lvTypeRepository.findAll();
        List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate, toDate);
        List<LeaveApply> leavetList = leaveApplyRepository.getleavetListForAttndace(fromDate, toDate);
        jsonDataList = empJsonDataRepository.jsonDataList();
        List<FileUploadedData> fileUploadedDataList = dataForUpdateAttendance.getFileUploadedDataList();
        StringBuilder querysb = new StringBuilder();
        MstEmpType mstEmpType = new MstEmpType();
        for (int i = 0; i < dailyAttendanceList.size(); i++) {
            Date defaultDate = sf.parse(dailyAttendanceList.get(i).getAttDate());
            EmpJsonData employee = new EmpJsonData();
            for (int j = 0; j < jsonDataList.size(); j++) {
                if (jsonDataList.get(j).getEmpId() == dailyAttendanceList.get(i).getEmpId()) {
                    employee = jsonDataList.get(j);
                    break;
                }
            }
            dailyAttendanceList.get(i).setEmpType(employee.getEmpType());
            for (int j = 0; j < mstEmpTypeList.size(); j++) {
                if (mstEmpTypeList.get(j).getEmpTypeId() == employee.getEmpType()) {
                    mstEmpType = mstEmpTypeList.get(j);
                    break;
                }
            }
            // assign in time and out time from uploaded csv to record
            for (int j = 0; j < fileUploadedDataList.size(); j++) {
                try {
                    Date uploadedDate = dd.parse(fileUploadedDataList.get(j).getLogDate());
                    if (dailyAttendanceList.get(i).getEmpCode().equals(fileUploadedDataList.get(j).getEmpCode()) && defaultDate.compareTo(uploadedDate) == 0) {
                        if (fileUploadedDataList.get(j).getInTime().trim().equalsIgnoreCase("") || fileUploadedDataList.get(j).getInTime().equals("0:00") || fileUploadedDataList.get(j).getInTime().equals("0:00") || fileUploadedDataList.get(j).getInTime().equals("00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00:00")) {
                        // dailyAttendanceList.get(i).setInTime("00:00:00");
                        } else {
                            // weekEnnd : 1=Weekly off,2: no weekly off
                            // holiday : 3=holiday ,4: holiday NO
                            // leave : 5=leave ,6: no leave
                            // presentStatus : 7=present ,8: absent
                            dailyAttendanceList.get(i).setByFileUpdated(1);
                            dailyAttendanceList.get(i).setInTime(fileUploadedDataList.get(j).getInTime());
                            dailyAttendanceList.get(i).setOutTime("00:00:00");
                            dailyAttendanceList.get(i).setCommentsSupervisor("8");
                            int weekEndStatus = commonFunctionService.findDateInWeekEnd(sf.format(defaultDate), sf.format(defaultDate), weeklyOfflist, weeklyOffShitList, dailyAttendanceList.get(i).getLocationId(), employee.getWeekEndCatId(), dailyAttendanceList.get(i).getEmpId(), weeklyOffShitFromList);
                            int holidayStatus = commonFunctionService.findDateInHoliday(sf.format(defaultDate), sf.format(defaultDate), holidayList, dailyAttendanceList.get(i).getLocationId(), employee.getHolidayCatId());
                            int presentStatus = 7;
                            LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(sf.format(defaultDate), leavetList, dailyAttendanceList.get(i).getEmpId());
                            int leaveStatus = stsInfo.getSts();
                            String atteanceCase = weekEndStatus + "" + holidayStatus + "" + leaveStatus + "" + presentStatus;
                            dailyAttendanceList.get(i).setCasetype(atteanceCase);
                            System.out.println("case" + atteanceCase + " " + dailyAttendanceList.get(i).getEmpName());
                            if (atteanceCase.equals("1357") || atteanceCase.equals("1457") || atteanceCase.equals("2357") || atteanceCase.equals("2457")) {
                                LeaveCancelEmployee leaveCancelEmployee = new LeaveCancelEmployee();
                                leaveCancelEmployee.setEmpCode(dailyAttendanceList.get(i).getEmpCode());
                                leaveCancelEmployee.setEmpId(dailyAttendanceList.get(i).getEmpId());
                                leaveCancelEmployee.setEmpName(dailyAttendanceList.get(i).getEmpName());
                                leaveCancelEmployee.setLeaveType(dailyAttendanceList.get(i).getAttStatus());
                                list.add(leaveCancelEmployee);
                            // System.out.println("Plzzzz cancel Leave" +
                            // dailyAttendanceList.get(i).getEmpName());
                            } else if (atteanceCase.equals("1358") || atteanceCase.equals("1458") || atteanceCase.equals("2358") || atteanceCase.equals("2458")) {
                                dailyAttendanceList.get(i).setAttStatus(stsInfo.getStsshortname());
                                dailyAttendanceList.get(i).setLvSumupId(stsInfo.getLvTypeId());
                                dailyAttendanceList.get(i).setAttsSdShow(stsInfo.getStsshortname());
                            } else if (atteanceCase.equals("1367") || atteanceCase.equals("1368") || atteanceCase.equals("1467") || atteanceCase.equals("1468") || atteanceCase.equals("2367") || atteanceCase.equals("2368")) {
                                // apply wo,ho,wo-ot,ho-ot
                                if (!dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("WO-CO") && !dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("PH-CO")) {
                                    String newStts = AttendanceStatusOnlyPresent(employee, dailyAttendanceList.get(i), dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(), atteanceCase, mstEmpType);
                                    dailyAttendanceList.get(i).setAttStatus(newStts);
                                    for (int k = 0; k < lvTypeList.size(); k++) {
                                        if (lvTypeList.get(k).getNameSd().equals(newStts)) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(k).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(k).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                            } else if (atteanceCase.equals("2467") || atteanceCase.equals("2468")) {
                                // as thumb or direct AB
                                if (atteanceCase.equals("2467")) {
                                    dailyAttendanceList.get(i).setAttStatus("P");
                                    for (int k = 0; k < lvTypeList.size(); k++) {
                                        if (lvTypeList.get(k).getNameSd().equals("P")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(k).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(k).getNameSdShow());
                                            break;
                                        }
                                    }
                                } else if (atteanceCase.equals("2468")) {
                                    try {
                                        if (defaultDate.compareTo(sf.parse(employee.getCmpJoiningDate())) >= 0) {
                                            dailyAttendanceList.get(i).setAttStatus("AB");
                                            for (int k = 0; k < lvTypeList.size(); k++) {
                                                if (lvTypeList.get(k).getNameSd().equals("AB")) {
                                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(k).getLvSumupId());
                                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(k).getNameSdShow());
                                                    break;
                                                }
                                            }
                                        } else {
                                            dailyAttendanceList.get(i).setAttStatus("NA");
                                            for (int k = 0; k < lvTypeList.size(); k++) {
                                                if (lvTypeList.get(k).getNameSd().equals("NA")) {
                                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(k).getLvSumupId());
                                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(k).getNameSdShow());
                                                    break;
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        dailyAttendanceList.get(i).setAttStatus("NA");
                                        for (int k = 0; k < lvTypeList.size(); k++) {
                                            if (lvTypeList.get(k).getNameSd().equals("NA")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(k).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(k).getNameSdShow());
                                                break;
                                            }
                                        }
                                    }
                                // System.out.println(dailyAttendanceList.get(i).getAttStatus());
                                }
                            }
                            System.out.println("STS" + dailyAttendanceList.get(i).getAttsSdShow());
                        }
                        break;
                    }
                } catch (Exception e) {
                // e.printStackTrace();
                }
            }
            querysb = new StringBuilder();
            dailyAttendanceList.get(i).setLoginName(dataForUpdateAttendance.getUserId() + ": import Exel Aurangabd");
            // System.out.println("case type
            // -----------------"+dailyAttendanceList.get(i).getCasetype());
            if (dailyAttendanceList.get(i).getByFileUpdated() == 1) {
                querysb.append("update\n" + "        tbl_attt_daily_daily \n" + "    set\n" + "        atsumm_uid='" + dailyAttendanceList.get(i).getAtsummUid() + "'," + "        att_date='" + dailyAttendanceList.get(i).getAttDate() + "'," + "        att_status='" + dailyAttendanceList.get(i).getAttStatus() + "'," + "        by_file_updated='" + dailyAttendanceList.get(i).getByFileUpdated() + "'," + "        casetype='" + dailyAttendanceList.get(i).getCasetype() + "'," + "        comments_supervisor='" + dailyAttendanceList.get(i).getCommentsSupervisor() + "'," + "        company_id='" + dailyAttendanceList.get(i).getCompanyId() + "'," + "        current_shiftid='" + dailyAttendanceList.get(i).getCurrentShiftid() + "'," + "        current_shiftname='FLEXIBLE SHIFT'," + "        early_going_mark='" + dailyAttendanceList.get(i).getEarlyGoingMark() + "'," + "        early_going_min='" + dailyAttendanceList.get(i).getEarlyGoingMin() + "'," + "        emp_code='" + dailyAttendanceList.get(i).getEmpCode() + "'," + "        emp_id='" + dailyAttendanceList.get(i).getEmpId() + "'," + "        emp_json='" + dailyAttendanceList.get(i).getEmpJson() + "'," + "        emp_name='" + dailyAttendanceList.get(i).getEmpName() + "'," + "        emp_type='" + dailyAttendanceList.get(i).getEmpType() + "'," + "        file_name=NULL," + "        freeze_by_supervisor='" + dailyAttendanceList.get(i).getFreezeBySupervisor() + "'," + "        full_night='" + dailyAttendanceList.get(i).getFullNight() + "'," + "        get_pass_used_count='" + dailyAttendanceList.get(i).getGetPassUsedCount() + "'," + "        get_pass_used_hour='" + dailyAttendanceList.get(i).getGetPassUsedHour() + "'," + "        get_pass_used_hour_reason=NULL," + "        half_night='" + dailyAttendanceList.get(i).getHalfNight() + "'," + "        import_date=NULL," + "        in_time='" + dailyAttendanceList.get(i).getInTime() + "'," + "        is_fixed='" + dailyAttendanceList.get(i).getIsFixed() + "'," + "        late_mark='" + dailyAttendanceList.get(i).getLateMark() + "'," + "        late_min='" + dailyAttendanceList.get(i).getLateMin() + "'," + "        location_id='" + dailyAttendanceList.get(i).getLocationId() + "'," + "        login_name='" + dailyAttendanceList.get(i).getLoginName() + "'," + "        login_time=NULL," + "        lv_sumup_id='" + dailyAttendanceList.get(i).getLvSumupId() + "'," + "        manual_ot_hr='" + dailyAttendanceList.get(i).getManualOtHr() + "'," + "        multiple_entries='" + dailyAttendanceList.get(i).getMultipleEntries() + "', ot_hr='" + dailyAttendanceList.get(i).getOtHr() + "'," + "        out_time='" + dailyAttendanceList.get(i).getOutTime() + "'," + "        raw_data_inout=NULL," + "        reason=NULL," + "        rec_status='" + dailyAttendanceList.get(i).getRecStatus() + "'," + "        row_id='" + dailyAttendanceList.get(i).getRowId() + "'," + "        working_hrs='" + dailyAttendanceList.get(i).getWorkingHrs() + "'" + ",atts_sd_show='" + dailyAttendanceList.get(i).getAttsSdShow() + "'    where\n" + "        id=" + dailyAttendanceList.get(i).getId() + ";");
                try {
                    String quiry = querysb.toString();
                    jdbcTemplate.batchUpdate(quiry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String[] dts = dataForUpdateAttendance.getFromDate().split("-");
        Date firstDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]) - 1, 1).getTime();
        Date lastDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]), 0).getTime();
        int month = Integer.parseInt(dts[1]);
        int year = Integer.parseInt(dts[0]);
        Info info1 = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), dataForUpdateAttendance.getUserId(), month, year, 0);
        // System.out.println("dailyAttendanceList " + quiry);
        // List<DailyAttendance> dailyAttendanceSaveRes =
        // dailyAttendanceRepository.saveAll(dailyAttendanceList);
        info.setError(false);
        info.setMsg("success");
        info.setList(list);
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        info.setList(list);
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getDailyDailyRecordForRoaster" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecordForRoaster(String date,List<Integer> empIds){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.getDailyDailyRecordForRoaster(date, empIds);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/updateAttendaceFinalRecordByempId" }, method = RequestMethod.POST)
@ResponseBody
public Info updateAttendaceFinalRecordByempId(String fromDate,String toDate,int userId,int month,int year,List<Integer> empIds){
    Info info = new Info();
    try {
        for (int i = 0; i < empIds.size(); i++) {
            info = finalUpdateDailySumaryRecord(fromDate, toDate, userId, month, year, empIds.get(i));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/presentAttendaceLiveCount" }, method = RequestMethod.POST)
@ResponseBody
public List<AttendaceLiveCount> presentAttendaceLiveCount(String fromDate,List<Integer> locId){
    List<AttendaceLiveCount> list = new ArrayList<>();
    try {
        list = attendaceLiveCountRepository.presentAttendaceLiveCount(fromDate, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getDailyDailyRecordByDailyId" }, method = RequestMethod.POST)
@ResponseBody
public GetDailyDailyRecord getDailyDailyRecordByDailyId(int dailyId){
    GetDailyDailyRecord getDailyDailyRecord = new GetDailyDailyRecord();
    try {
        getDailyDailyRecord = getDailyDailyRecordRepository.getDailyDailyRecordByDailyId(dailyId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return getDailyDailyRecord;
}


@RequestMapping(value = { "/initiallyInsertDailyRecord" }, method = RequestMethod.POST)
@ResponseBody
public Info initiallyInsertDailyRecord(String fromDate,String toDate,int userId,List<Integer> locId){
    Info info = new Info();
    try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fmdt = df.parse(fromDate);
        Date todt = df.parse(toDate);
        int totalDaysInmonth = difffun(fromDate, toDate);
        // System.out.println(fmdt + " " + todt);
        Calendar temp = Calendar.getInstance();
        temp.setTime(fmdt);
        int year = temp.get(Calendar.YEAR);
        int month = temp.get(Calendar.MONTH) + 1;
        List<EmpInfo> empList = empInfoRepository.getEmpListLocId(fromDate, toDate, locId);
        // List<SummaryDailyAttendance> summaryDailyAttendanceList = new ArrayList<>();
        // SummaryDailyAttendance summaryDailyAttendance = new SummaryDailyAttendance();
        List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
        /*
             * String dailyDailyQuery =
             * "INSERT INTO tbl_attt_daily_daily (id, company_id, emp_code, emp_name, att_date, att_status,  lv_sumup_id, working_hrs, in_time, rec_status, login_name, login_time, import_date, cmp_code, emp_id, ot_hr,  current_shiftid, late_mark, late_min, reason, current_shiftname, freeze_by_supervisor, comments_supervisor, get_pass_used_count, get_pass_used_hour, get_pass_used_hour_reason, raw_data_inout, manual_ot_hr, full_night, half_night, out_time, early_going_mark, early_going_min, multiple_entries, casetype, is_fixed, by_file_updated, location_id, emp_type, emp_json, atsumm_uid, file_name, row_id,atts_sd_show) VALUES  "
             * ; String dailyDailySummryQuery =
             * "INSERT INTO tbl_attt_summary_daily (id, company_id, emp_id, emp_code, emp_name, month, year, working_days, present_days, weekly_off, paid_holiday, paid_leave, legal_strike, lay_off, unpaid_holiday, unpaid_leave, absent_days, payable_days, ncp_days, totlate_mins, totlate_days, totout_mins, totworking_hrs, totot_hrs, tot_othr, tot_late, rec_status, login_name, login_time, status, import_date, cmp_code, rec_status_paid, total_days_inmonth, late_ded_leave_paid, holiday_present, weekly_off_present, full_night, half_night, holiday_present_half, weekly_off_present_half, weekly_off_holiday_off, weekly_off_holiday_off_present, weekly_off_holiday_off_present_halfday, hdpresent_hdleave, tot_early_going, atsumm_uid, calculation_done) VALUES "
             * ;
             */
        for (int i = 0; i < empList.size(); i++) {
            String dailyDailyQuery = "INSERT INTO tbl_attt_daily_daily (id, company_id, emp_code, emp_name, att_date, att_status,  lv_sumup_id, working_hrs, in_time, rec_status, login_name, login_time, import_date, cmp_code, emp_id, ot_hr,  current_shiftid, late_mark, late_min, reason, current_shiftname, freeze_by_supervisor, comments_supervisor, get_pass_used_count, get_pass_used_hour, get_pass_used_hour_reason, raw_data_inout, manual_ot_hr, full_night, half_night, out_time, early_going_mark, early_going_min, multiple_entries, casetype, is_fixed, by_file_updated, location_id, emp_type, emp_json, atsumm_uid, file_name, row_id,atts_sd_show) VALUES  ";
            String dailyDailySummryQuery = "INSERT INTO tbl_attt_summary_daily (id, company_id, emp_id, emp_code, emp_name, month, year, working_days, present_days, weekly_off, paid_holiday, paid_leave, legal_strike, lay_off, unpaid_holiday, unpaid_leave, absent_days, payable_days, ncp_days, totlate_mins, totlate_days, totout_mins, totworking_hrs, totot_hrs, tot_othr, tot_late, rec_status, login_name, login_time, status, import_date, cmp_code, rec_status_paid, total_days_inmonth, late_ded_leave_paid, holiday_present, weekly_off_present, full_night, half_night, holiday_present_half, weekly_off_present_half, weekly_off_holiday_off, weekly_off_holiday_off_present, weekly_off_holiday_off_present_halfday, hdpresent_hdleave, tot_early_going, atsumm_uid, calculation_done) VALUES ";
            String empName = empList.get(i).getFirstName() + " " + empList.get(i).getSurname();
            /*
                 * summaryDailyAttendance = new SummaryDailyAttendance();
                 * summaryDailyAttendance.setEmpCode(empList.get(i).getEmpCode());
                 * summaryDailyAttendance.setEmpId(empList.get(i).getEmpId());
                 * summaryDailyAttendance.setEmpName(empList.get(i).getFirstName() + " " +
                 * empList.get(i).getSurname()); summaryDailyAttendance.setMonth(month);
                 * summaryDailyAttendance.setYear(year); summaryDailyAttendance.setCompanyId(1);
                 * summaryDailyAttendance.setLoginName(String.valueOf(userId));
                 * summaryDailyAttendance.setRecStatus("O");
                 * summaryDailyAttendanceList.add(summaryDailyAttendance);
                 */
            dailyDailySummryQuery = dailyDailySummryQuery + "('0', '1', '" + empList.get(i).getEmpId() + "', '" + empList.get(i).getEmpCode() + "', '" + empName + "', '" + month + "', '" + year + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'O', '" + userId + "', NULL, '0', NULL, NULL, '0', '" + totalDaysInmonth + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),";
            EmpJsonData empJsonData = new EmpJsonData();
            empJsonData.setCmpCode(empList.get(i).getCmpCode());
            empJsonData.setCmpJoiningDate(empList.get(i).getCmpJoiningDate());
            empJsonData.setCurrentShiftid(empList.get(i).getCurrentShiftid());
            empJsonData.setDepartId(empList.get(i).getDepartId());
            empJsonData.setDesignationId(empList.get(i).getDepartId());
            empJsonData.setEmpCategory(empList.get(i).getEmpCategory());
            empJsonData.setEmpCode(empList.get(i).getEmpCode());
            empJsonData.setEmpId(empList.get(i).getEmpId());
            empJsonData.setEmpType(empList.get(i).getEmpType());
            empJsonData.setFirstName(empList.get(i).getFirstName());
            empJsonData.setIsEmp(empList.get(i).getIsEmp());
            empJsonData.setLocationId(empList.get(i).getLocationId());
            empJsonData.setMiddleName(empList.get(i).getMiddleName());
            empJsonData.setSalaryTypeId(empList.get(i).getSalaryTypeId());
            empJsonData.setSalBasis(empList.get(i).getSalBasis());
            empJsonData.setSurname(empList.get(i).getSurname());
            empJsonData.setHolidayCatId(empList.get(i).getHolidayCategory());
            empJsonData.setWeekEndCatId(empList.get(i).getWeekendCategory());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(empJsonData);
            fmdt = df.parse(fromDate);
            // System.out.println(empList.get(i).getEmpId() + " - " + fmdt);
            for (Date j = fmdt; j.compareTo(todt) <= 0; ) {
                // System.out.println(j);
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                temp = Calendar.getInstance();
                temp.setTime(j);
                String attdate = sf.format(j);
                /*
                     * DailyAttendance dailyAttendance = new DailyAttendance();
                     * dailyAttendance.setEmpCode(summaryDailyAttendance.getEmpCode());
                     * dailyAttendance.setEmpId(summaryDailyAttendance.getEmpId());
                     * dailyAttendance.setCompanyId(1);
                     * dailyAttendance.setLocationId(empList.get(i).getLocationId());
                     * dailyAttendance.setAttDate(attdate);
                     * dailyAttendance.setEmpName(empList.get(i).getFirstName() + " " +
                     * empList.get(i).getSurname());
                     * dailyAttendance.setLoginName(String.valueOf(userId));
                     * dailyAttendance.setEmpJson(json); dailyAttendance.setRecStatus("O");
                     * dailyAttendance.setAttStatus("NA"); dailyAttendance.setLateMark("0");
                     * dailyAttendanceList.add(dailyAttendance);
                     */
                dailyDailyQuery = dailyDailyQuery + "('0', '1','" + empList.get(i).getEmpCode() + "','" + empName + "','" + attdate + "', 'NA', '0', '0', NULL, 'O', \n '" + userId + "', NULL, NULL, NULL, '" + empList.get(i).getEmpId() + "', '0', '0', '0', '0', NULL, NULL, '0', '0', '0', '0' \n " + ", NULL, NULL, '0', '0', '0', NULL, '0', '0.00', '0', NULL, '0', '0', '" + empList.get(i).getLocationId() + "', '0', \n '" + json + "', '0', NULL, '0','NA'),";
                j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
            }
            dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);
            dailyDailySummryQuery = dailyDailySummryQuery.substring(0, dailyDailySummryQuery.length() - 1);
            jdbcTemplate.batchUpdate(dailyDailyQuery);
            jdbcTemplate.batchUpdate(dailyDailySummryQuery);
        }
        /*
             * dailyDailyQuery = dailyDailyQuery.substring(0, dailyDailyQuery.length() - 1);
             * dailyDailySummryQuery = dailyDailySummryQuery.substring(0,
             * dailyDailySummryQuery.length() - 1);
             */
        /*
             * Calendar cal1 = Calendar.getInstance(); SimpleDateFormat date_format = new
             * SimpleDateFormat("HH:mm:ss");
             * System.out.println(date_format.format(cal1.getTime()));
             */
        /*
             * jdbcTemplate.batchUpdate(dailyDailyQuery);
             * jdbcTemplate.batchUpdate(dailyDailySummryQuery);
             */
        // query=query.substring(0, query.length()-1);
        // dailyAttendanceRepository.insert(query);
        // System.out.println(query);
        // List<DailyAttendance> dailyAttendanceSaveRes =
        // dailyAttendanceRepository.saveAll(dailyAttendanceList);
        /*
             * List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes =
             * summaryDailyAttendanceRepository .saveAll(summaryDailyAttendanceList);
             */
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getMonthlySummryAttendaceByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public SummaryAttendance getMonthlySummryAttendaceByEmpId(int month,int year,int empId){
    SummaryAttendance summaryAttendance = new SummaryAttendance();
    try {
        summaryAttendance = summaryAttendanceRepository.summaryDailyAttendanceListAll(month, year, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryAttendance;
}


@RequestMapping(value = { "/importAttendanceByFileAndUpdate" }, method = RequestMethod.POST)
@ResponseBody
public Info getVariousListForUploadAttendace(DataForUpdateAttendance dataForUpdateAttendance){
    Info info = new Info();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        Setting leave_working_hr = settingRepo.findByKey("leave_working_hr");
        Setting auto_approve_ot_hr = settingRepo.findByKey("auto_approve_ot_hr");
        Setting setting = settingRepo.findByKey("fix_shift");
        int fixShiftValue = Integer.parseInt(setting.getValue());
        String fromDate = dataForUpdateAttendance.getFromDate();
        String toDate = dataForUpdateAttendance.getToDate();
        int month = dataForUpdateAttendance.getMonth();
        int year = dataForUpdateAttendance.getYear();
        List<LeaveApply> leaveListAddeBySystem = new ArrayList<>();
        List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
        List<LeaveApply> leavetList = new ArrayList<>();
        List<EmpJsonData> jsonDataList = new ArrayList<>();
        List<EmpListForHolidayApprove> optionalHolidayList = new ArrayList<>();
        if (dataForUpdateAttendance.getEmpId() != 0) {
            leaveListAddeBySystem = leaveApplyRepository.leaveListAddeBySystem(fromDate, toDate, dataForUpdateAttendance.getEmpId());
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate, dataForUpdateAttendance.getEmpId());
            leavetList = leaveApplyRepository.getleavetListForAttndace(fromDate, toDate, dataForUpdateAttendance.getEmpId());
            jsonDataList = empJsonDataRepository.jsonDataList(dataForUpdateAttendance.getEmpId());
            optionalHolidayList = empListForHolidayApproveRepo.getOptionalHolidayApprovalList(dataForUpdateAttendance.getEmpId(), 1);
        } else {
            leaveListAddeBySystem = leaveApplyRepository.leaveListAddeBySystem(fromDate, toDate);
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate);
            leavetList = leaveApplyRepository.getleavetListForAttndace(fromDate, toDate);
            jsonDataList = empJsonDataRepository.jsonDataList();
            optionalHolidayList = empListForHolidayApproveRepo.getOptionalHolidayApprovalList(1);
        }
        List<ShiftAssignDaily> shiftAssignDailyList = new ArrayList<>();
        if (fixShiftValue == 1) {
            if (dataForUpdateAttendance.getEmpId() != 0) {
                shiftAssignDailyList = shiftAssignDailyRepository.shiftAssignDailyListById(fromDate, toDate, dataForUpdateAttendance.getEmpId());
            } else {
                shiftAssignDailyList = shiftAssignDailyRepository.shiftAssignDailyList(fromDate, toDate);
            }
        }
        for (int j = 0; j < leaveListAddeBySystem.size(); j++) {
            float updateNoOfDays = 0;
            if (Integer.parseInt(leaveListAddeBySystem.get(j).getLeaveDuration()) != 1) {
                // cancel halfDay leave
                updateNoOfDays = (float) 0.5;
            } else {
                // cancel full leave
                updateNoOfDays = 1;
            }
            int updateNoOfDaysInLeave = leaveApplyRepository.reverseupdateNoOfDaysInLeave(leaveListAddeBySystem.get(j).getLvtApplicationIdParent(), updateNoOfDays, "");
            int deleteRocord = leaveApplyRepository.deleteByLeaveId(leaveListAddeBySystem.get(j).getLeaveId());
            int deleteRocordTrail = leaveTrailRepository.deleteByLeaveId(leaveListAddeBySystem.get(j).getLeaveId());
        }
        List<FileUploadedData> fileUploadedDataList = dataForUpdateAttendance.getFileUploadedDataList();
        List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
        List<ShiftMaster> shiftList = shiftMasterRepository.findByStatus(1);
        List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
        List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
        List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
        List<LvType> lvTypeList = lvTypeRepository.findAll();
        List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.weeklyOffShitFromList(fromDate, toDate);
        // System.out.println(fileUploadedDataList);
        /*
             * List<SummaryDailyAttendance> summaryDailyAttendanceList =
             * summaryDailyAttendanceRepository .summaryDailyAttendanceList(month, year);
             */
        // 
        // List<MstWeeklyOff> mstWeeklyOffList = mstWeeklyOffRepository.findAll();
        /*
             * List<LvType> lvTypeList = lvTypeRepository.findAll(); List<Holiday>
             *
             * List<LvmSumUp> lvmSumUpList = lvmSumUpRepository.findAll();
             *
             */
        MstEmpType mstEmpType = new MstEmpType();
        ShiftMaster shiftMaster = new ShiftMaster();
        List<ShiftMaster> possibleShiftList = new ArrayList<>();
        String inDttime = "";
        String outDttime = "";
        String shiftTime = "";
        StringBuilder querysb = new StringBuilder();
        for (int i = 0; i < dailyAttendanceList.size(); i++) {
            int update = 0;
            int groupType = 0;
            int flagLatemarkHd = 0;
            possibleShiftList = new ArrayList<>();
            Date defaultDate = sf.parse(dailyAttendanceList.get(i).getAttDate());
            // ObjectMapper mapper = new ObjectMapper();
            // EmpJsonData employee =
            // mapper.readValue(dailyAttendanceList.get(i).getEmpJson(), EmpJsonData.class);
            EmpJsonData employee = new EmpJsonData();
            for (int j = 0; j < jsonDataList.size(); j++) {
                if (jsonDataList.get(j).getEmpId() == dailyAttendanceList.get(i).getEmpId()) {
                    employee = jsonDataList.get(j);
                    break;
                }
            }
            dailyAttendanceList.get(i).setEmpType(employee.getEmpType());
            // get emptype record and break;
            for (int j = 0; j < mstEmpTypeList.size(); j++) {
                if (mstEmpTypeList.get(j).getEmpTypeId() == employee.getEmpType()) {
                    mstEmpType = mstEmpTypeList.get(j);
                    break;
                }
            }
            // get timeShifting record by shiftid
            if (fixShiftValue == 1) {
                int shiftId = dailyAttendanceList.get(i).getCurrentShiftid();
                int findshiftId = 0;
                for (int j = 0; j < shiftAssignDailyList.size(); j++) {
                    Date date = sf.parse(shiftAssignDailyList.get(j).getShiftDate());
                    if (date.compareTo(defaultDate) == 0 && employee.getEmpId() == shiftAssignDailyList.get(j).getEmpId()) {
                        shiftId = shiftAssignDailyList.get(j).getShiftId();
                        groupType = shiftAssignDailyList.get(j).getExtra1();
                        findshiftId = 1;
                        break;
                    }
                }
                if (findshiftId == 0) {
                    shiftId = 3;
                    groupType = 2;
                }
                for (int j = 0; j < shiftList.size(); j++) {
                    if (shiftList.get(j).getId() == shiftId) {
                        shiftMaster = shiftList.get(j);
                        dailyAttendanceList.get(i).setCurrentShiftid(shiftList.get(j).getId());
                        possibleShiftList.add(shiftMaster);
                        break;
                    }
                }
            // System.out.println(employee.getEmpCode() + " " + defaultDate + " " + shiftId
            // );
            } else {
                for (int j = 0; j < shiftList.size(); j++) {
                    if (shiftList.get(j).getId() == employee.getCurrentShiftid() && employee.getLocationId() == shiftList.get(j).getLocationId()) {
                        shiftMaster = shiftList.get(j);
                        dailyAttendanceList.get(i).setCurrentShiftid(shiftList.get(j).getId());
                        break;
                    }
                }
                // get possible timeShifting records List by same deptId of employee
                for (int j = 0; j < shiftList.size(); j++) {
                    if (shiftList.get(j).getSelfGroupId() == shiftMaster.getSelfGroupId()) {
                        possibleShiftList.add(shiftList.get(j));
                    }
                }
            }
            // assign in time and out time from uploaded csv to record
            for (int j = 0; j < fileUploadedDataList.size(); j++) {
                try {
                    Date uploadedDate = dd.parse(fileUploadedDataList.get(j).getLogDate());
                    if (dailyAttendanceList.get(i).getEmpCode().equals(fileUploadedDataList.get(j).getEmpCode()) && defaultDate.compareTo(uploadedDate) == 0) {
                        dailyAttendanceList.get(i).setInTime(fileUploadedDataList.get(j).getInTime());
                        if (fileUploadedDataList.get(j).getInTime().trim().equalsIgnoreCase("") || fileUploadedDataList.get(j).getInTime().equals("0:00") || fileUploadedDataList.get(j).getInTime().equals("0:00") || fileUploadedDataList.get(j).getInTime().equals("00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00:00") || fileUploadedDataList.get(j).getInTime().equals("00:00:00")) {
                            dailyAttendanceList.get(i).setOutTime("00:00:00");
                        } else if (fileUploadedDataList.get(j).getOutTime().trim().equalsIgnoreCase("") || fileUploadedDataList.get(j).getOutTime().equals("0:00") || fileUploadedDataList.get(j).getOutTime().equals("0:00") || fileUploadedDataList.get(j).getOutTime().equals("00:00") || fileUploadedDataList.get(j).getOutTime().equals("00:00") || fileUploadedDataList.get(j).getOutTime().equals("00:00:00") || fileUploadedDataList.get(j).getOutTime().equals("00:00:00")) {
                        // dailyAttendanceList.get(i).setOutTime(shiftMaster.getTotime());
                        } else {
                            dailyAttendanceList.get(i).setOutTime(fileUploadedDataList.get(j).getOutTime());
                        }
                        // System.out.println("DSfsdfdsf" + fileUploadedDataList.get(j).getOutTime());
                        dailyAttendanceList.get(i).setByFileUpdated(1);
                        update = 1;
                        if (dataForUpdateAttendance.getEmpId() == 0) {
                            dailyAttendanceList.get(i).setRowId(j + 1);
                        }
                        break;
                    }
                } catch (Exception e) {
                // e.printStackTrace();
                }
            }
            // create default date and time
            inDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getInTime() + ":00";
            outDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getOutTime() + ":00";
            long minimumMin = 0;
            // find current shift by thumb
            for (int j = 0; j < possibleShiftList.size(); j++) {
                try {
                    long x = 0;
                    shiftTime = dailyAttendanceList.get(i).getAttDate() + " " + possibleShiftList.get(j).getFromtime();
                    Date startDate = yyDtTm.parse(inDttime);
                    // Set end date
                    Date endDate = yyDtTm.parse(shiftTime);
                    if (startDate.compareTo(endDate) > 0) {
                        long duration = startDate.getTime() - endDate.getTime();
                        long diffHours = duration / (60 * 60 * 1000);
                        long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);
                        x = diffMinutes;
                    } else {
                        long duration = endDate.getTime() - startDate.getTime();
                        long diffHours = duration / (60 * 60 * 1000);
                        long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);
                        x = diffMinutes;
                    }
                    if (j == 0) {
                        minimumMin = x;
                        shiftMaster = possibleShiftList.get(j);
                        dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
                        dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
                    }
                    if (minimumMin > x) {
                        minimumMin = x;
                        shiftMaster = possibleShiftList.get(j);
                        dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
                        dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
                    }
                } catch (Exception e) {
                // e.printStackTrace();
                }
            }
            // calculate working hours
            try {
                // Set start date
                Date inDt = yyDtTm.parse(inDttime);
                Date outDt = yyDtTm.parse(outDttime);
                String nighttime = dailyAttendanceList.get(i).getAttDate() + " 01:00:00";
                if (inDt.compareTo(outDt) > 0) {
                    outDt.setTime(outDt.getTime() + 1000 * 60 * 60 * 24);
                    nighttime = sf.format(outDt) + " 01:00:00";
                }
                Date nighttimedt = yyDtTm.parse(nighttime);
                long durationBetweenInOut = outDt.getTime() - inDt.getTime();
                long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
                long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60) + (diffHoursBetweenInOut * 60);
                dailyAttendanceList.get(i).setWorkingHrs(diffMinutesBetweenInOut);
                /*
                     * if (diffMinutesBetweenInOut > shiftMaster.getShiftOtHour()) {
                     * dailyAttendanceList.get(i) .setOtHr(String.valueOf(diffMinutesBetweenInOut -
                     * shiftMaster.getShiftOtHour())); } else {
                     * dailyAttendanceList.get(i).setOtHr("0"); }
                     */
                // identify night work
                if (shiftMaster.getDepartmentId() == 1) {
                    dailyAttendanceList.get(i).setFullNight(1);
                } else if (inDt.compareTo(nighttimedt) <= 0 && outDt.compareTo(nighttimedt) >= 0) {
                    dailyAttendanceList.get(i).setFullNight(1);
                } else {
                    dailyAttendanceList.get(i).setFullNight(0);
                }
                if (groupType == 2) {
                    dailyAttendanceList.get(i).setOtHr("0");
                } else {
                    if (diffMinutesBetweenInOut > shiftMaster.getShiftOtHour()) {
                        int otHr = (int) (diffMinutesBetweenInOut - shiftMaster.getShiftOtHour());
                        int otintHrs = otHr / 60;
                        int otintmin = otHr % 60;
                        if (otintHrs >= 1) {
                            int actualotmin = otintHrs * 60;
                            if (otintmin > 30 && otintmin < 60) {
                                actualotmin = actualotmin + 30;
                            }
                            dailyAttendanceList.get(i).setOtHr(String.valueOf(actualotmin));
                        } else {
                            dailyAttendanceList.get(i).setOtHr("0");
                        // dailyAttendanceList.get(i).setFreezeBySupervisor(2);
                        }
                    } else {
                        dailyAttendanceList.get(i).setOtHr("0");
                    // dailyAttendanceList.get(i).setFreezeBySupervisor(2);
                    }
                }
                if (Integer.parseInt(auto_approve_ot_hr.getValue()) == 1) {
                    dailyAttendanceList.get(i).setFreezeBySupervisor(2);
                }
            } catch (Exception e) {
            // e.printStackTrace();
            }
            // calculate late time
            try {
                if (groupType == 2) {
                    dailyAttendanceList.get(i).setLateMin(0);
                    dailyAttendanceList.get(i).setLateMark("0");
                } else {
                    int allowdLateTime = 0;
                    shiftTime = dailyAttendanceList.get(i).getAttDate() + " " + shiftMaster.getFromtime();
                    if (mstEmpType.getMaxLateTimeAllowed() > shiftMaster.getMaxLateTimeAllowed()) {
                        allowdLateTime = mstEmpType.getMaxLateTimeAllowed();
                    } else {
                        allowdLateTime = shiftMaster.getMaxLateTimeAllowed();
                    }
                    Date inDateTime = yyDtTm.parse(inDttime);
                    // Set end date
                    Date shiftDatetime = yyDtTm.parse(shiftTime);
                    if (inDateTime.compareTo(shiftDatetime) > 0) {
                        long durationBetweenInOut = inDateTime.getTime() - shiftDatetime.getTime();
                        long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
                        long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60) + (diffHoursBetweenInOut * 60);
                        dailyAttendanceList.get(i).setLateMin((int) diffMinutesBetweenInOut);
                        if (diffMinutesBetweenInOut > allowdLateTime) {
                            dailyAttendanceList.get(i).setLateMark("1");
                        } else {
                            dailyAttendanceList.get(i).setLateMark("0");
                        }
                    } else {
                        dailyAttendanceList.get(i).setLateMin(0);
                        dailyAttendanceList.get(i).setLateMark("0");
                    }
                    if (Float.parseFloat(shiftMaster.getShiftHr()) > dailyAttendanceList.get(i).getWorkingHrs()) {
                    // dailyAttendanceList.get(i).setLateMark("0");
                    }
                    if (dailyAttendanceList.get(i).getLateMin() > 0) {
                        dailyAttendanceList.get(i).setLateMark("1");
                    }
                    if (dailyAttendanceList.get(i).getLateMin() > allowdLateTime) {
                        dailyAttendanceList.get(i).setLateMark("0");
                        flagLatemarkHd = 1;
                    }
                }
            } catch (Exception e) {
            // e.printStackTrace();
            }
            try {
                // weekEnnd : 1=Weekly off,2: no weekly off
                // holiday : 3=holiday ,4: holiday NO
                // leave : 5=leave ,6: no leave
                // presentStatus : 7=present ,8: absent
                int weekEndStatus = commonFunctionService.findDateInWeekEnd(sf.format(defaultDate), sf.format(defaultDate), weeklyOfflist, weeklyOffShitList, dailyAttendanceList.get(i).getLocationId(), employee.getWeekEndCatId(), dailyAttendanceList.get(i).getEmpId(), weeklyOffShitFromList);
                int holidayStatus = commonFunctionService.findDateInHoliday(sf.format(defaultDate), sf.format(defaultDate), holidayList, dailyAttendanceList.get(i).getLocationId(), employee.getHolidayCatId());
                LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(sf.format(defaultDate), leavetList, dailyAttendanceList.get(i).getEmpId());
                int leaveStatus = stsInfo.getSts();
                int presentStatus = 7;
                if (dailyAttendanceList.get(i).getInTime().equals("0:00") || dailyAttendanceList.get(i).getOutTime().equals("0:00") || dailyAttendanceList.get(i).getInTime().equals("00:00") || dailyAttendanceList.get(i).getOutTime().equals("00:00") || dailyAttendanceList.get(i).getInTime().equals("00:00:00") || dailyAttendanceList.get(i).getOutTime().equals("00:00:00")) {
                    presentStatus = 8;
                    dailyAttendanceList.get(i).setFullNight(0);
                }
                if (holidayStatus == 4 && presentStatus == 8) {
                    holidayStatus = commonFunctionService.findDateInOptionalHoliday(sf.format(defaultDate), optionalHolidayList, dailyAttendanceList.get(i).getEmpId());
                }
                /*
                     * if ((presentStatus == 7 && weekEndStatus == 1) || (presentStatus == 7 &&
                     * holidayStatus == 1)) {
                     * dailyAttendanceList.get(i).setOtHr(String.valueOf(dailyAttendanceList.get(i).
                     * getWorkingHrs())); }
                     */
                String atteanceCase = weekEndStatus + "" + holidayStatus + "" + leaveStatus + "" + presentStatus;
                System.out.println(atteanceCase);
                dailyAttendanceList.get(i).setCasetype(atteanceCase);
                try {
                    // / start cases implementation
                    if (atteanceCase.equals("1357") || atteanceCase.equals("1457") || atteanceCase.equals("2357") || atteanceCase.equals("2457")) {
                        // first cancel leave and apply As thumb
                        // start Cancle leave
                        try {
                            float updateNoOfDays = 0;
                            if (stsInfo.getDuration() != 1) {
                                // cancel halfDay leave
                                updateNoOfDays = (float) 0.5;
                            } else {
                                // cancel full leave
                                updateNoOfDays = 1;
                            }
                            Date date = new Date();
                            SimpleDateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            int updateNoOfDaysInLeave = leaveApplyRepository.updateNoOfDaysInLeave(stsInfo.getLeaveId(), updateNoOfDays, "Cancel By System");
                            LeaveApply leaveSummary = new LeaveApply();
                            leaveSummary.setCalYrId(2);
                            leaveSummary.setEmpId(dailyAttendanceList.get(i).getEmpId());
                            leaveSummary.setFinalStatus(1);
                            leaveSummary.setLeaveDuration(String.valueOf(stsInfo.getDuration()));
                            leaveSummary.setCirculatedTo("1");
                            leaveSummary.setLeaveEmpReason("Leave Add And Cancel By System");
                            leaveSummary.setLeaveCancleRemark("Leave Add And Cancel By System");
                            leaveSummary.setLvtApplicationIdParent(stsInfo.getLeaveId());
                            leaveSummary.setLvTypeId(stsInfo.getLeaveTyId());
                            leaveSummary.setLeaveFromdt(sf.format(defaultDate));
                            leaveSummary.setLeaveTodt(sf.format(defaultDate));
                            leaveSummary.setExInt1(7);
                            leaveSummary.setExInt2(1);
                            leaveSummary.setExInt3(1);
                            leaveSummary.setExVar1("NA");
                            leaveSummary.setExVar2("NA");
                            leaveSummary.setExVar3("NA");
                            leaveSummary.setIsActive(1);
                            leaveSummary.setDelStatus(1);
                            leaveSummary.setMakerUserId(dataForUpdateAttendance.getUserId());
                            leaveSummary.setMakerEnterDatetime(dtformat.format(date));
                            if (stsInfo.getDuration() != 1) {
                                // cancel halfDay leave
                                leaveSummary.setLeaveNumDays((float) 0.5);
                            } else {
                                // cancel full leave
                                leaveSummary.setLeaveNumDays(1);
                            }
                            LeaveApply saveLeave = leaveApplyRepository.saveAndFlush(leaveSummary);
                            LeaveTrail lt = new LeaveTrail();
                            lt.setEmpRemarks(leaveSummary.getLeaveEmpReason());
                            lt.setLeaveId(saveLeave.getLeaveId());
                            lt.setLeaveStatus(7);
                            lt.setEmpId(dailyAttendanceList.get(i).getEmpId());
                            lt.setExInt1(1);
                            lt.setExInt2(1);
                            lt.setExInt3(1);
                            lt.setExVar1("NA");
                            lt.setExVar2("NA");
                            lt.setExVar3("NA");
                            lt.setMakerUserId(dataForUpdateAttendance.getUserId());
                            lt.setMakerEnterDatetime(dtformat.format(date));
                            LeaveTrail saveTr = leaveTrailRepository.saveAndFlush(lt);
                            int updateTrailId = leaveApplyRepository.updateLeaveApply(saveLeave.getLeaveId(), saveTr.getTrailPkey());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // end Cancle leave
                        if (atteanceCase.equals("1357") || atteanceCase.equals("1457") || atteanceCase.equals("2357")) {
                            if (!dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("WO-CO") && !dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("PH-CO")) {
                                dailyAttendanceList.get(i).setAtsummUid("0");
                                String newStts = AttendanceStatus(employee, dailyAttendanceList.get(i), dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(), atteanceCase, dailyAttendanceList.get(i).getWorkingHrs(), shiftMaster, mstEmpType);
                                dailyAttendanceList.get(i).setAttStatus(newStts);
                                for (int j = 0; j < lvTypeList.size(); j++) {
                                    if (lvTypeList.get(j).getNameSd().equals(newStts)) {
                                        dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                        dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (dailyAttendanceList.get(i).getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
                                if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {
                                    if (mstEmpType.getOtApplicable().contains("Yes")) {
                                        dailyAttendanceList.get(i).setAttStatus("OT");
                                        for (int j = 0; j < lvTypeList.size(); j++) {
                                            if (lvTypeList.get(j).getNameSd().equals("OT")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                break;
                                            }
                                        }
                                    } else {
                                        dailyAttendanceList.get(i).setAttStatus("P");
                                        for (int j = 0; j < lvTypeList.size(); j++) {
                                            if (lvTypeList.get(j).getNameSd().equals("P")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                break;
                                            }
                                        }
                                    }
                                } else // $working_hrs > $rs_shift_timming->shift_hr
                                {
                                    dailyAttendanceList.get(i).setAttStatus("P");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("P")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                                if (flagLatemarkHd == 1) {
                                    dailyAttendanceList.get(i).setAttStatus("HD");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("HD")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                    dailyAttendanceList.get(i).setLateMark("0");
                                }
                            } else // $working_hrs >= $resultp->working_hrs
                            if (dailyAttendanceList.get(i).getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr()) && dailyAttendanceList.get(i).getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr()) && stsInfo.getDuration() != 1) {
                                // HD
                                if (mstEmpType.getHalfDay().equals("Yes")) {
                                    dailyAttendanceList.get(i).setAttStatus("HDPHDL");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("HDPHDL")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                } else // $rs_emp_type->half_day == 'Yes'
                                {
                                    dailyAttendanceList.get(i).setAttStatus("P");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("P")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                            } else {
                                /*
                                     * if (stsInfo.getLeaveTyId() == 2) {
                                     * dailyAttendanceList.get(i).setAttStatus("LWP"); for (int j = 0; j <
                                     * lvTypeList.size(); j++) { if (lvTypeList.get(j).getNameSd().equals("LWP")) {
                                     * dailyAttendanceList.get(i) .setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                     * break; } } } else { dailyAttendanceList.get(i).setAttStatus("PL"); for (int j
                                     * = 0; j < lvTypeList.size(); j++) { if
                                     * (lvTypeList.get(j).getNameSd().equals("PL")) { dailyAttendanceList.get(i)
                                     * .setLvSumupId(lvTypeList.get(j).getLvSumupId()); break; } } }
                                     */
                                dailyAttendanceList.get(i).setAttStatus(stsInfo.getStsshortname());
                                dailyAttendanceList.get(i).setLvSumupId(stsInfo.getLvTypeId());
                                dailyAttendanceList.get(i).setAttsSdShow(stsInfo.getStsshortname());
                            }
                        }
                    } else if (atteanceCase.equals("1358") || atteanceCase.equals("1458") || atteanceCase.equals("2358") || atteanceCase.equals("2458")) {
                        // Apply leave
                        /*
                             * if (stsInfo.getLeaveTyId() == 2) {
                             *
                             * dailyAttendanceList.get(i).setAttStatus("LWP"); for (int j = 0; j <
                             * lvTypeList.size(); j++) { if (lvTypeList.get(j).getNameSd().equals("LWP")) {
                             * dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                             * break; } } } else { dailyAttendanceList.get(i).setAttStatus("PL"); for (int j
                             * = 0; j < lvTypeList.size(); j++) { if
                             * (lvTypeList.get(j).getNameSd().equals("PL")) {
                             * dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                             * break; } } }
                             */
                        if (leave_working_hr.getValue().equals("1")) {
                            if (employee.getSalBasis().equalsIgnoreCase("hour")) {
                                String[] otarr = employee.getDailyHr().split("\\.");
                                // System.out.println(Arrays.asList(otarr) + " " + otarr.length);
                                int monthlyOtMin = 0;
                                if (otarr.length > 1) {
                                    monthlyOtMin = (Integer.parseInt(otarr[0]) * 60) + Integer.parseInt(otarr[1]);
                                } else {
                                    monthlyOtMin = (Integer.parseInt(otarr[0]) * 60);
                                }
                                dailyAttendanceList.get(i).setWorkingHrs(monthlyOtMin);
                            } else {
                                dailyAttendanceList.get(i).setWorkingHrs(Float.parseFloat(shiftMaster.getShiftHr()));
                            /*
                                     * if (stsInfo.getNoOfLeave() == 0.5) { dailyAttendanceList.get(i)
                                     * .setWorkingHrs(Float.parseFloat(shiftMaster.getShiftHalfdayHr()));
                                     *
                                     * } else { dailyAttendanceList.get(i)
                                     * .setWorkingHrs(Float.parseFloat(shiftMaster.getShiftHr())); }
                                     */
                            }
                        }
                        if (stsInfo.getNoOfLeave() == 0.5) {
                            dailyAttendanceList.get(i).setAttStatus("HDPHDL");
                            for (int j = 0; j < lvTypeList.size(); j++) {
                                if (lvTypeList.get(j).getNameSd().equals("HDPHDL")) {
                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                    break;
                                }
                            }
                        } else {
                            dailyAttendanceList.get(i).setAttStatus(stsInfo.getStsshortname());
                            dailyAttendanceList.get(i).setLvSumupId(stsInfo.getLvTypeId());
                            dailyAttendanceList.get(i).setAttsSdShow(stsInfo.getStsshortname());
                        }
                    } else if (atteanceCase.equals("1367") || atteanceCase.equals("1368") || atteanceCase.equals("1467") || atteanceCase.equals("1468") || atteanceCase.equals("2367") || atteanceCase.equals("2368")) {
                        // apply wo,ho,wo-ot,ho-ot
                        if (!dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("WO-CO") && !dailyAttendanceList.get(i).getAttStatus().equalsIgnoreCase("PH-CO")) {
                            dailyAttendanceList.get(i).setAtsummUid("0");
                            String newStts = AttendanceStatus(employee, dailyAttendanceList.get(i), dailyAttendanceList.get(i).getEmpId(), dailyAttendanceList.get(i).getAttDate(), atteanceCase, dailyAttendanceList.get(i).getWorkingHrs(), shiftMaster, mstEmpType);
                            dailyAttendanceList.get(i).setAttStatus(newStts);
                            for (int j = 0; j < lvTypeList.size(); j++) {
                                if (lvTypeList.get(j).getNameSd().equals(newStts)) {
                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                    break;
                                }
                            }
                            if (newStts.equalsIgnoreCase("PH")) {
                                if (leave_working_hr.getValue().equals("1")) {
                                    if (employee.getSalBasis().equalsIgnoreCase("hour")) {
                                        String[] otarr = employee.getDailyHr().split("\\.");
                                        // System.out.println(Arrays.asList(otarr) + " " + otarr.length);
                                        int monthlyOtMin = 0;
                                        if (otarr.length > 1) {
                                            monthlyOtMin = (Integer.parseInt(otarr[0]) * 60) + Integer.parseInt(otarr[1]);
                                        } else {
                                            monthlyOtMin = (Integer.parseInt(otarr[0]) * 60);
                                        }
                                        dailyAttendanceList.get(i).setWorkingHrs(monthlyOtMin);
                                    } else {
                                        dailyAttendanceList.get(i).setWorkingHrs(Float.parseFloat(shiftMaster.getShiftHr()));
                                    }
                                }
                            }
                        }
                    } else if (atteanceCase.equals("2467") || atteanceCase.equals("2468")) {
                        // as thumb or direct AB
                        if (atteanceCase.equals("2467")) {
                            if (mstEmpType.getMinworkApplicable().equals("Yes")) {
                                if (dailyAttendanceList.get(i).getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
                                    if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {
                                        if (mstEmpType.getOtApplicable().contains("Yes")) {
                                            dailyAttendanceList.get(i).setAttStatus("OT");
                                            for (int j = 0; j < lvTypeList.size(); j++) {
                                                if (lvTypeList.get(j).getNameSd().equals("OT")) {
                                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                    break;
                                                }
                                            }
                                        } else {
                                            dailyAttendanceList.get(i).setAttStatus("P");
                                            for (int j = 0; j < lvTypeList.size(); j++) {
                                                if (lvTypeList.get(j).getNameSd().equals("P")) {
                                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        dailyAttendanceList.get(i).setAttStatus("P");
                                        for (int j = 0; j < lvTypeList.size(); j++) {
                                            if (lvTypeList.get(j).getNameSd().equals("P")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                break;
                                            }
                                        }
                                    }
                                    if (flagLatemarkHd == 1) {
                                        dailyAttendanceList.get(i).setAttStatus("HD");
                                        for (int j = 0; j < lvTypeList.size(); j++) {
                                            if (lvTypeList.get(j).getNameSd().equals("HD")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                break;
                                            }
                                        }
                                        dailyAttendanceList.get(i).setLateMark("0");
                                    }
                                } else if (dailyAttendanceList.get(i).getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr()) && dailyAttendanceList.get(i).getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
                                    if (mstEmpType.getHalfDay().equals("Yes")) {
                                        if (PL_CL_HD_leave_insert_automatic == 1) {
                                        } else {
                                            dailyAttendanceList.get(i).setAttStatus("HD");
                                            for (int j = 0; j < lvTypeList.size(); j++) {
                                                if (lvTypeList.get(j).getNameSd().equals("HD")) {
                                                    dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                    dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                    break;
                                                }
                                            }
                                            dailyAttendanceList.get(i).setLateMark("0");
                                        }
                                    } else {
                                        dailyAttendanceList.get(i).setAttStatus("P");
                                        for (int j = 0; j < lvTypeList.size(); j++) {
                                            if (lvTypeList.get(j).getNameSd().equals("P")) {
                                                dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                                dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                                break;
                                            }
                                        }
                                    }
                                } else if (dailyAttendanceList.get(i).getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
                                    dailyAttendanceList.get(i).setEarlyGoingMark(0);
                                    dailyAttendanceList.get(i).setLateMark("0");
                                    dailyAttendanceList.get(i).setAttStatus("AB");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("AB")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (dailyAttendanceList.get(i).getWorkingHrs() > shiftMaster.getShiftOtHour()) {
                                    dailyAttendanceList.get(i).setAttStatus("OT");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("OT")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                } else {
                                    dailyAttendanceList.get(i).setAttStatus("P");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("P")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                            }
                        } else if (atteanceCase.equals("2468")) {
                            try {
                                if (defaultDate.compareTo(sf.parse(employee.getCmpJoiningDate())) >= 0) {
                                    dailyAttendanceList.get(i).setAttStatus("AB");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("AB")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                } else {
                                    dailyAttendanceList.get(i).setAttStatus("NA");
                                    for (int j = 0; j < lvTypeList.size(); j++) {
                                        if (lvTypeList.get(j).getNameSd().equals("NA")) {
                                            dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                            dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                dailyAttendanceList.get(i).setAttStatus("NA");
                                for (int j = 0; j < lvTypeList.size(); j++) {
                                    if (lvTypeList.get(j).getNameSd().equals("NA")) {
                                        dailyAttendanceList.get(i).setLvSumupId(lvTypeList.get(j).getLvSumupId());
                                        dailyAttendanceList.get(i).setAttsSdShow(lvTypeList.get(j).getNameSdShow());
                                        break;
                                    }
                                }
                            }
                        // System.out.println(dailyAttendanceList.get(i).getAttStatus());
                        }
                    }
                // end cases implementation
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
            // e.printStackTrace();
            }
            querysb = new StringBuilder();
            dailyAttendanceList.get(i).setLoginName(dataForUpdateAttendance.getUserId() + ": import Exel");
            // System.out.println("case type
            // -----------------"+dailyAttendanceList.get(i).getCasetype());
            if (dailyAttendanceList.get(i).getByFileUpdated() == 1 && update == 1) {
                // dailyAttendanceList.get(i).setCommentsSupervisor("8");
                querysb.append("update\n" + "        tbl_attt_daily_daily \n" + "    set\n" + "        atsumm_uid='" + dailyAttendanceList.get(i).getAtsummUid() + "'," + "        att_date='" + dailyAttendanceList.get(i).getAttDate() + "'," + "        att_status='" + dailyAttendanceList.get(i).getAttStatus() + "'," + "        by_file_updated='" + dailyAttendanceList.get(i).getByFileUpdated() + "'," + "        casetype='" + dailyAttendanceList.get(i).getCasetype() + "'," + "        comments_supervisor='" + dailyAttendanceList.get(i).getCommentsSupervisor() + "'," + "        company_id='" + dailyAttendanceList.get(i).getCompanyId() + "'," + "        current_shiftid='" + dailyAttendanceList.get(i).getCurrentShiftid() + "'," + "        current_shiftname='" + dailyAttendanceList.get(i).getCurrentShiftname() + "'," + "        early_going_mark='" + dailyAttendanceList.get(i).getEarlyGoingMark() + "'," + "        early_going_min='" + dailyAttendanceList.get(i).getEarlyGoingMin() + "'," + "        emp_code='" + dailyAttendanceList.get(i).getEmpCode() + "'," + "        emp_id='" + dailyAttendanceList.get(i).getEmpId() + "'," + "        emp_json='" + dailyAttendanceList.get(i).getEmpJson() + "'," + "        emp_name='" + dailyAttendanceList.get(i).getEmpName() + "'," + "        emp_type='" + dailyAttendanceList.get(i).getEmpType() + "'," + "        file_name=NULL," + "        freeze_by_supervisor='" + dailyAttendanceList.get(i).getFreezeBySupervisor() + "'," + "        full_night='" + dailyAttendanceList.get(i).getFullNight() + "'," + "        get_pass_used_count='" + dailyAttendanceList.get(i).getGetPassUsedCount() + "'," + "        get_pass_used_hour='" + dailyAttendanceList.get(i).getGetPassUsedHour() + "'," + "        get_pass_used_hour_reason=NULL," + "        half_night='" + dailyAttendanceList.get(i).getHalfNight() + "'," + "        import_date=NULL," + "        in_time='" + dailyAttendanceList.get(i).getInTime() + "'," + "        is_fixed='" + dailyAttendanceList.get(i).getIsFixed() + "'," + "        late_mark='" + dailyAttendanceList.get(i).getLateMark() + "'," + "        late_min='" + dailyAttendanceList.get(i).getLateMin() + "'," + "        location_id='" + dailyAttendanceList.get(i).getLocationId() + "'," + "        login_name='" + dailyAttendanceList.get(i).getLoginName() + "'," + "        login_time=NULL," + "        lv_sumup_id='" + dailyAttendanceList.get(i).getLvSumupId() + "'," + "        manual_ot_hr='" + dailyAttendanceList.get(i).getManualOtHr() + "'," + "        multiple_entries='" + dailyAttendanceList.get(i).getMultipleEntries() + "', ot_hr='" + dailyAttendanceList.get(i).getOtHr() + "'," + "        out_time='" + dailyAttendanceList.get(i).getOutTime() + "'," + "        raw_data_inout=NULL," + "        reason=NULL," + "        rec_status='" + dailyAttendanceList.get(i).getRecStatus() + "'," + "        row_id='" + dailyAttendanceList.get(i).getRowId() + "'," + "        working_hrs='" + dailyAttendanceList.get(i).getWorkingHrs() + "'" + ",atts_sd_show='" + dailyAttendanceList.get(i).getAttsSdShow() + "'    where\n" + "        id=" + dailyAttendanceList.get(i).getId() + ";");
                try {
                    String quiry = querysb.toString();
                    jdbcTemplate.batchUpdate(quiry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // System.out.println("dailyAttendanceList " + quiry);
        // List<DailyAttendance> dailyAttendanceSaveRes =
        // dailyAttendanceRepository.saveAll(dailyAttendanceList);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getLvTypeList" }, method = RequestMethod.GET)
@ResponseBody
public List<LvType> getLvTypeList(){
    List<LvType> lvTypeList = new ArrayList<>();
    try {
        lvTypeList = lvTypeRepository.getLvTypeList();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lvTypeList;
}


@RequestMapping(value = { "/getDailyDailyRecordForHrByDate" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecordForHrByDate(String date){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.getDailyDailyRecordForHrByDate(date);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/updateAttendaceRecordSingleByHod" }, method = RequestMethod.POST)
@ResponseBody
public Info updateAttendaceRecordSingleByHod(int dailyId,int selectStatus,String lateMark,String selectStatusText,int userId,int flag,String otHours,int lateMin,String nameSd){
    Info info = new Info();
    try {
        DailyAttendance dailyRecordById = dailyAttendanceRepository.getdailyRecordById(dailyId);
        String[] dts = dailyRecordById.getAttDate().split("-");
        Date firstDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]) - 1, 1).getTime();
        Date lastDay = new GregorianCalendar(Integer.parseInt(dts[0]), Integer.parseInt(dts[1]), 0).getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        int month = Integer.parseInt(dts[1]);
        int year = Integer.parseInt(dts[0]);
        if (dailyRecordById.getIsFixed() == 0) {
            if (flag == 0) {
                if (selectStatus != 0) {
                    dailyRecordById.setLvSumupId(selectStatus);
                    dailyRecordById.setAttStatus(selectStatusText);
                    dailyRecordById.setAttsSdShow(nameSd);
                }
                dailyRecordById.setLateMark(lateMark);
                dailyRecordById.setLateMin(lateMin);
                dailyRecordById.setCommentsSupervisor("8");
            } else {
                // System.out.println("In else");
                if (selectStatus != 0) {
                    dailyRecordById.setLvSumupId(selectStatus);
                    dailyRecordById.setAttStatus(selectStatusText);
                    dailyRecordById.setAttsSdShow(nameSd);
                }
                dailyRecordById.setLateMark(lateMark);
                dailyRecordById.setLateMin(lateMin);
                String[] othrsarry = otHours.split(":");
                int othrs = (Integer.parseInt(othrsarry[0]) * 60) + Integer.parseInt(othrsarry[1]);
                dailyRecordById.setOtHr(String.valueOf(othrs));
            }
            DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
            info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), userId, month, year, dailyRecordById.getEmpId());
        // System.out.println(othrsarry[0] + " " + othrsarry[1]);
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/approveAttendanceStatusById" }, method = RequestMethod.POST)
@ResponseBody
public Info approveAttendanceStatusById(List<Integer> ids,int status){
    Info info = new Info();
    try {
        int updateStatusById = dailyAttendanceRepository.updateAttendaceApproveStatus(ids, status);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/updateAttendaceOfWeeklyOffInDailyDaily" }, method = RequestMethod.POST)
@ResponseBody
public Info updateAttendaceOfWeeklyOffInDailyDaily(int id,int userId){
    Info info = new Info();
    try {
        WeeklyOffShit shiftWeeklyofById = weeklyOffShitRepository.shiftWeeklyofById(id);
        if (shiftWeeklyofById != null) {
            List<DailyAttendance> dailyRecordByfromDate = dailyAttendanceRepository.dailyAttendanceList(shiftWeeklyofById.getWeekofffromdate(), shiftWeeklyofById.getWeekofffromdate(), shiftWeeklyofById.getEmpId());
            List<DailyAttendance> dailyRecordByonDate = dailyAttendanceRepository.dailyAttendanceList(shiftWeeklyofById.getWeekoffshiftdate(), shiftWeeklyofById.getWeekoffshiftdate(), shiftWeeklyofById.getEmpId());
            Info fistres = changeStatusOfDailyDailyDate(dailyRecordByfromDate, shiftWeeklyofById, userId);
            Info setcondres = changeStatusOfDailyDailyDate(dailyRecordByonDate, shiftWeeklyofById, userId);
            info.setError(false);
            info.setMsg("success");
        // System.out.println(dailyRecordByDate);
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/fixAttendanceByDateOfEmpLoyee" }, method = RequestMethod.POST)
@ResponseBody
public Info fixAttendanceByDateOfEmpLoyee(String fromDate,String toDate,List<Integer> empIds,int isFixed,String sts){
    Info info = new Info();
    try {
        int fixDailyDailyRecord = dailyAttendanceRepository.fixDailyDailyRecordBetweenDate(fromDate, toDate, empIds, isFixed, sts);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/updateMarkAsCompOff" }, method = RequestMethod.POST)
@ResponseBody
public Info updateMarkAsCompOff(int dailyId,String sts){
    Info info = new Info();
    try {
        DailyAttendance dailyRecordById = dailyAttendanceRepository.getdailyRecordById(dailyId);
        if (dailyRecordById.getIsFixed() == 0) {
            dailyRecordById.setAtsummUid("1");
            if (sts.equalsIgnoreCase("PH-OT")) {
                dailyRecordById.setAttStatus("PH-CO");
                dailyRecordById.setAttsSdShow("PH-CO");
                DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
            } else if (sts.equalsIgnoreCase("WO-OT")) {
                dailyRecordById.setAttStatus("WO-CO");
                dailyRecordById.setAttsSdShow("WO-CO");
                DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
            } else if (sts.equalsIgnoreCase("PH-WO-P")) {
                dailyRecordById.setLvSumupId(14);
                dailyRecordById.setAttStatus("WO-CO");
                dailyRecordById.setAttsSdShow("WO-CO");
                DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
            }
            System.out.println("Update Finalllllllllllll");
            String[] othrsarry = dailyRecordById.getAttDate().split("-");
            Date firstDay = new GregorianCalendar(Integer.parseInt(othrsarry[0]), Integer.parseInt(othrsarry[1]) - 1, 1).getTime();
            Date lastDay = new GregorianCalendar(Integer.parseInt(othrsarry[0]), Integer.parseInt(othrsarry[1]), 0).getTime();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), 1, Integer.parseInt(othrsarry[1]), Integer.parseInt(othrsarry[0]), dailyRecordById.getEmpId());
        }
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmployyeDailyDailyListByAuthorityLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getEmployyeDailyDailyListByAuthorityLocId(String date,int empId,List<Integer> locId){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        dailyAttendanceList = dailyAttendanceRepository.getEmployyeDailyDailyListByAuthorityLocId(date, empId, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/updateAttendaceRecordSingle" }, method = RequestMethod.POST)
@ResponseBody
public Info updateAttendaceRecordSingle(int dailyId,int selectStatus,int byStatus,String lateMark,String otHours,String outTime,String inTime,String selectStatusText,String fromDate,String toDate,int userId,int month,int year,int selectShift,int otApproval,String namesd,int lateMin){
    Info info = new Info();
    try {
        DailyAttendance dailyRecordById = dailyAttendanceRepository.getdailyRecordById(dailyId);
        if (dailyRecordById.getIsFixed() == 0) {
            List<Integer> empIdList = new ArrayList<>();
            empIdList.add(dailyRecordById.getEmpId());
            String dt = dailyRecordById.getAttDate();
            int update = shiftAssignDailyRepository.updateAssignShiftByDate(empIdList, dt, dt, selectShift);
            if (byStatus == 1) {
                if (selectStatus != 0) {
                    dailyRecordById.setLvSumupId(selectStatus);
                    dailyRecordById.setAttStatus(selectStatusText);
                    dailyRecordById.setAttsSdShow(namesd);
                /*
                         * if (selectStatusText.equalsIgnoreCase("PH-CO") ||
                         * selectStatusText.equalsIgnoreCase("WO-CO")) {
                         * dailyRecordById.setAtsummUid("1"); }
                         */
                }
                dailyRecordById.setLateMin(lateMin);
                dailyRecordById.setLateMark(lateMark);
                String[] othrsarry = otHours.split(":");
                int othrs = (Integer.parseInt(othrsarry[0]) * 60) + Integer.parseInt(othrsarry[1]);
                dailyRecordById.setOtHr(String.valueOf(othrs));
                dailyRecordById.setLoginName(userId + ":manualchange");
                dailyRecordById.setCurrentShiftid(selectShift);
                if (otApproval == 1) {
                    dailyRecordById.setFreezeBySupervisor(2);
                }
                DailyAttendance updateRes = dailyAttendanceRepository.save(dailyRecordById);
                info = finalUpdateDailySumaryRecord(fromDate, toDate, userId, month, year, dailyRecordById.getEmpId());
            // System.out.println(othrsarry[0] + " " + othrsarry[1]);
            } else {
                List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
                FileUploadedData fileUploadedData = new FileUploadedData();
                fileUploadedData.setEmpCode(dailyRecordById.getEmpCode());
                fileUploadedData.setEmpName(dailyRecordById.getEmpName());
                fileUploadedData.setLogDate(DateConvertor.convertToDMY(dailyRecordById.getAttDate()));
                fileUploadedData.setInTime(inTime);
                fileUploadedData.setOutTime(outTime);
                fileUploadedDataList.add(fileUploadedData);
                DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
                dataForUpdateAttendance.setFromDate(dailyRecordById.getAttDate());
                dataForUpdateAttendance.setToDate(dailyRecordById.getAttDate());
                dataForUpdateAttendance.setMonth(month);
                dataForUpdateAttendance.setYear(year);
                dataForUpdateAttendance.setUserId(userId);
                dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
                dataForUpdateAttendance.setEmpId(dailyRecordById.getEmpId());
                info = getVariousListForUploadAttendace(dataForUpdateAttendance);
                info = finalUpdateDailySumaryRecord(fromDate, toDate, userId, month, year, dailyRecordById.getEmpId());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmployyeDailyDailyListByDeptIds" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getEmployyeDailyDailyListByDeptIds(String date,int desgType,List<Integer> departIds){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        if (desgType == 1) {
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceByDeptId(date, departIds);
        } else if (desgType == 2) {
            dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListForSecurityApprove(date);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/getEmployyeDailyDailyListByAuthority" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getEmployyeDailyDailyListByAuthority(String date,int empId){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        dailyAttendanceList = dailyAttendanceRepository.getEmployyeDailyDailyListByAuthority(date, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/getDailyDailyRecord" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecord(String fromDate,String toDate,int empId){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.summaryDailyAttendanceListAll(fromDate, toDate, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


public Info changeStatusOfDailyDailyDate(List<DailyAttendance> dailyRecordByDate,WeeklyOffShit shiftWeeklyofById,int userId){
    Info info = new Info();
    try {
        System.out.println(dailyRecordByDate.size());
        if (dailyRecordByDate.size() > 0) {
            if (dailyRecordByDate.get(0).getByFileUpdated() == 1) {
                int month = shiftWeeklyofById.getMonth();
                int year = shiftWeeklyofById.getYear();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                Date firstDay = new GregorianCalendar(year, month - 1, 1).getTime();
                Date lastDay = new GregorianCalendar(year, month, 0).getTime();
                List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
                FileUploadedData fileUploadedData = new FileUploadedData();
                fileUploadedData.setEmpCode(dailyRecordByDate.get(0).getEmpCode());
                fileUploadedData.setEmpName(dailyRecordByDate.get(0).getEmpName());
                fileUploadedData.setLogDate(DateConvertor.convertToDMY(dailyRecordByDate.get(0).getAttDate()));
                fileUploadedData.setInTime(dailyRecordByDate.get(0).getInTime().substring(0, dailyRecordByDate.get(0).getInTime().length() - 3));
                fileUploadedData.setOutTime(dailyRecordByDate.get(0).getOutTime().substring(0, dailyRecordByDate.get(0).getOutTime().length() - 3));
                fileUploadedDataList.add(fileUploadedData);
                DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
                dataForUpdateAttendance.setFromDate(dailyRecordByDate.get(0).getAttDate());
                dataForUpdateAttendance.setToDate(dailyRecordByDate.get(0).getAttDate());
                dataForUpdateAttendance.setMonth(shiftWeeklyofById.getMonth());
                dataForUpdateAttendance.setYear(shiftWeeklyofById.getYear());
                dataForUpdateAttendance.setUserId(userId);
                dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
                dataForUpdateAttendance.setEmpId(shiftWeeklyofById.getEmpId());
                // System.out.println(fileUploadedData);
                info = getVariousListForUploadAttendace(dataForUpdateAttendance);
                info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), userId, month, year, shiftWeeklyofById.getEmpId());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/finalUpdateDailySumaryRecord" }, method = RequestMethod.POST)
@ResponseBody
public Info finalUpdateDailySumaryRecord(String fromDate,String toDate,int userId,int month,int year,int empId){
    Info info = new Info();
    try {
        Setting setting = settingRepo.findByKey("ab_deduction");
        Setting max_late_day_allowed = settingRepo.findByKey("max_late_day_allowed");
        List<DailyDailyInformation> dailyDailyInformationList = new ArrayList<>();
        List<SummaryDailyAttendance> summaryDailyAttendanceList = new ArrayList<>();
        List<EmpSalType> empSalTypeList = new ArrayList<>();
        if (empId != 0) {
            dailyDailyInformationList = dailyDailyInformationRepository.dailyDailyInformationList(fromDate, toDate, empId);
            summaryDailyAttendanceList = summaryDailyAttendanceRepository.summaryDailyAttendanceList(month, year, empId);
            empSalTypeList = empSalTypeRepository.empSalTypeList(month, year, empId);
        } else {
            dailyDailyInformationList = dailyDailyInformationRepository.dailyDailyInformationList(fromDate, toDate);
            summaryDailyAttendanceList = summaryDailyAttendanceRepository.summaryDailyAttendanceListAll(month, year);
            empSalTypeList = empSalTypeRepository.empSalTypeList(month, year);
        }
        for (int i = 0; i < summaryDailyAttendanceList.size(); i++) {
            try {
                EmpSalType empSalType = new EmpSalType();
                for (int j = 0; j < empSalTypeList.size(); j++) {
                    if (empSalTypeList.get(j).getEmpId() == summaryDailyAttendanceList.get(i).getEmpId()) {
                        empSalType = empSalTypeList.get(j);
                        break;
                    }
                }
                int totalDaysInmonth = difffun(fromDate, toDate);
                float workingDays = 0;
                float presentDays = 0;
                float holidayPresentHalf = 0;
                float paidLeave = 0;
                float unPaidLeave = 0;
                float weeklyOff = 0;
                float paidHoliday = 0;
                float layOff = 0;
                float legalStrike = 0;
                float wot = 0;
                float phot = 0;
                float wphot = 0;
                int absentLeave = 0;
                int lateMin = 0;
                int lateMark = 0;
                int totalWorkingHr = 0;
                int totalOtHr = 0;
                int nightcount = 0;
                int markascompoff = 0;
                float latededuct = 0;
                int ncpDays = 0;
                float ab_deduction = Float.parseFloat(setting.getValue());
                String isDaily = "daily";
                for (int j = 0; j < dailyDailyInformationList.size(); j++) {
                    if (dailyDailyInformationList.get(j).getEmpId() == summaryDailyAttendanceList.get(i).getEmpId()) {
                        isDaily = dailyDailyInformationList.get(j).getSalBasis();
                        lateMin = lateMin + dailyDailyInformationList.get(j).getLateMin();
                        lateMark = lateMark + dailyDailyInformationList.get(j).getLateMark();
                        totalWorkingHr = totalWorkingHr + dailyDailyInformationList.get(j).getWorkingMin();
                        totalOtHr = totalOtHr + dailyDailyInformationList.get(j).getOtMin();
                        nightcount = nightcount + dailyDailyInformationList.get(j).getFullNight();
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 5) {
                            // 5=p,ot
                            presentDays = presentDays + dailyDailyInformationList.get(j).getDaycount();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 20) {
                            // 20=HDPHDL,
                            presentDays = (float) (presentDays + (dailyDailyInformationList.get(j).getDaycount() * 0.5));
                            /*
                                 * holidayPresentHalf = (float) (holidayPresentHalf +
                                 * (dailyDailyInformationList.get(j).getDaycount() * 0.5));
                                 */
                            paidLeave = (float) (paidLeave + (dailyDailyInformationList.get(j).getDaycount() * 0.5));
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 21) {
                            // 21=HD
                            presentDays = (float) (presentDays + (dailyDailyInformationList.get(j).getDaycount() * 0.5));
                            unPaidLeave = (float) (unPaidLeave + (dailyDailyInformationList.get(j).getDaycount() * 0.5));
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 22) {
                            // 21=HD
                            absentLeave = absentLeave + (dailyDailyInformationList.get(j).getDaycount());
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 14) {
                            // 14=WO-OT
                            wot = wot + dailyDailyInformationList.get(j).getDaycount();
                            markascompoff = markascompoff + dailyDailyInformationList.get(j).getMarkCompoff();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 13) {
                            // 13=PH-OT
                            phot = phot + dailyDailyInformationList.get(j).getDaycount();
                            markascompoff = markascompoff + dailyDailyInformationList.get(j).getMarkCompoff();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 18) {
                            // 18=WO-PH-OT
                            wphot = wphot + dailyDailyInformationList.get(j).getDaycount();
                            markascompoff = markascompoff + dailyDailyInformationList.get(j).getMarkCompoff();
                        }
                        try {
                            if (dailyDailyInformationList.get(j).getSalBasis().equals("daily")) {
                                if (dailyDailyInformationList.get(j).getLvSumupId() == 12 || dailyDailyInformationList.get(j).getLvSumupId() == 14 || dailyDailyInformationList.get(j).getLvSumupId() == 16 || dailyDailyInformationList.get(j).getLvSumupId() == 18 || dailyDailyInformationList.get(j).getLvSumupId() == 19) {
                                    weeklyOff = weeklyOff + dailyDailyInformationList.get(j).getDaycount();
                                }
                                if (dailyDailyInformationList.get(j).getLvSumupId() == 6 || dailyDailyInformationList.get(j).getLvSumupId() == 13 || dailyDailyInformationList.get(j).getLvSumupId() == 15 || dailyDailyInformationList.get(j).getLvSumupId() == 17 || dailyDailyInformationList.get(j).getLvSumupId() == 18 || dailyDailyInformationList.get(j).getLvSumupId() == 19) {
                                    paidHoliday = paidHoliday + dailyDailyInformationList.get(j).getDaycount();
                                }
                            } else {
                                if (dailyDailyInformationList.get(j).getLvSumupId() == 12 || dailyDailyInformationList.get(j).getLvSumupId() == 14 || dailyDailyInformationList.get(j).getLvSumupId() == 16 || dailyDailyInformationList.get(j).getLvSumupId() == 17 || dailyDailyInformationList.get(j).getLvSumupId() == 18 || dailyDailyInformationList.get(j).getLvSumupId() == 19) {
                                    weeklyOff = weeklyOff + dailyDailyInformationList.get(j).getDaycount();
                                }
                                if (dailyDailyInformationList.get(j).getLvSumupId() == 6 || dailyDailyInformationList.get(j).getLvSumupId() == 13 || dailyDailyInformationList.get(j).getLvSumupId() == 15) {
                                    paidHoliday = paidHoliday + dailyDailyInformationList.get(j).getDaycount();
                                }
                            }
                        } catch (Exception e) {
                        // e.printStackTrace();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 7) {
                            paidLeave = paidLeave + dailyDailyInformationList.get(j).getDaycount();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 11) {
                            unPaidLeave = unPaidLeave + dailyDailyInformationList.get(j).getDaycount();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 9) {
                            layOff = layOff + dailyDailyInformationList.get(j).getDaycount();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 8) {
                            legalStrike = legalStrike + dailyDailyInformationList.get(j).getDaycount();
                        }
                        if (dailyDailyInformationList.get(j).getLvSumupId() == 11 || dailyDailyInformationList.get(j).getLvSumupId() == 22) {
                            ncpDays = ncpDays + dailyDailyInformationList.get(j).getDaycount();
                        }
                    }
                }
                try {
                    // System.out.println(empSalType.getSalBasis());
                    if (empSalType.getSalBasis().equalsIgnoreCase("hour")) {
                        summaryDailyAttendanceList.get(i).setTotlateMins(0);
                        summaryDailyAttendanceList.get(i).setTotLate(0);
                        summaryDailyAttendanceList.get(i).setTotworkingHrs(totalWorkingHr);
                        String[] otarr = empSalType.getMonthlyOtHr().split("\\.");
                        // System.out.println(Arrays.asList(otarr) + " " + otarr.length);
                        int monthlyOtMin = 0;
                        if (otarr.length > 1) {
                            monthlyOtMin = (Integer.parseInt(otarr[0]) * 60) + Integer.parseInt(otarr[1]);
                        } else {
                            monthlyOtMin = (Integer.parseInt(otarr[0]) * 60);
                        }
                        if (totalWorkingHr > monthlyOtMin) {
                            summaryDailyAttendanceList.get(i).setTotOthr(totalWorkingHr - monthlyOtMin);
                        } else {
                            summaryDailyAttendanceList.get(i).setTotOthr(0);
                        }
                    // System.out.println("************************ in if" + monthlyOtMin + " " +
                    // totalWorkingHr);
                    } else {
                        summaryDailyAttendanceList.get(i).setTotlateMins(lateMin);
                        summaryDailyAttendanceList.get(i).setTotLate(lateMark);
                        summaryDailyAttendanceList.get(i).setTotworkingHrs(totalWorkingHr);
                        summaryDailyAttendanceList.get(i).setTotOthr(totalOtHr);
                    }
                } catch (Exception e) {
                    summaryDailyAttendanceList.get(i).setTotlateMins(lateMin);
                    summaryDailyAttendanceList.get(i).setTotLate(lateMark);
                    summaryDailyAttendanceList.get(i).setTotworkingHrs(totalWorkingHr);
                    summaryDailyAttendanceList.get(i).setTotOthr(totalOtHr);
                }
                summaryDailyAttendanceList.get(i).setPresentDays(presentDays);
                summaryDailyAttendanceList.get(i).setHdpresentHdleave(holidayPresentHalf);
                summaryDailyAttendanceList.get(i).setPaidLeave(paidLeave);
                summaryDailyAttendanceList.get(i).setWeeklyOff(weeklyOff);
                summaryDailyAttendanceList.get(i).setPaidHoliday(paidHoliday);
                summaryDailyAttendanceList.get(i).setUnpaidLeave(unPaidLeave);
                summaryDailyAttendanceList.get(i).setLayOff(layOff);
                summaryDailyAttendanceList.get(i).setLegalStrike(legalStrike);
                summaryDailyAttendanceList.get(i).setNcpDays(layOff + legalStrike);
                summaryDailyAttendanceList.get(i).setAbsentDays(absentLeave);
                summaryDailyAttendanceList.get(i).setTotalDaysInmonth(totalDaysInmonth);
                summaryDailyAttendanceList.get(i).setWeeklyOffPresent(wot);
                summaryDailyAttendanceList.get(i).setHolidayPresent(phot);
                summaryDailyAttendanceList.get(i).setWeeklyOffHolidayOffPresent(wphot);
                summaryDailyAttendanceList.get(i).setFullNight(nightcount);
                summaryDailyAttendanceList.get(i).setAtsummUid(String.valueOf(markascompoff));
                summaryDailyAttendanceList.get(i).setNcpDays(ncpDays);
                if (lateMark > Integer.parseInt(max_late_day_allowed.getValue())) {
                    latededuct = (lateMark - Float.parseFloat(max_late_day_allowed.getValue())) / 2;
                }
                summaryDailyAttendanceList.get(i).setTotlateDays(latededuct);
                workingDays = totalDaysInmonth - summaryDailyAttendanceList.get(i).getWeeklyOff() - summaryDailyAttendanceList.get(i).getPaidHoliday();
                summaryDailyAttendanceList.get(i).setWorkingDays(workingDays);
                // dont deduct late deduct from payable days for pune;
                latededuct = 0;
                try {
                    if (isDaily.equals("daily")) {
                        summaryDailyAttendanceList.get(i).setPayableDays(summaryDailyAttendanceList.get(i).getPresentDays() + summaryDailyAttendanceList.get(i).getPaidHoliday() + summaryDailyAttendanceList.get(i).getPaidLeave() - latededuct);
                    } else {
                        summaryDailyAttendanceList.get(i).setPayableDays(summaryDailyAttendanceList.get(i).getPresentDays() + summaryDailyAttendanceList.get(i).getPaidHoliday() + summaryDailyAttendanceList.get(i).getPaidLeave() + summaryDailyAttendanceList.get(i).getWeeklyOff() - latededuct);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                summaryDailyAttendanceList.get(i).setPayableDays(summaryDailyAttendanceList.get(i).getPayableDays() - (absentLeave * ab_deduction));
                summaryDailyAttendanceList.get(i).setCalculationDone(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("summaryDailyAttendanceList " + summaryDailyAttendanceList);
        List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes = summaryDailyAttendanceRepository.saveAll(summaryDailyAttendanceList);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAttendanceSheetReport" }, method = RequestMethod.POST)
@ResponseBody
public List<DateAndDay> getAttendanceSheetReport(String fromDate,String toDate,int locId){
    List<DateAndDay> dateAndDayList = new ArrayList<>();
    try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fmdt = df.parse(fromDate);
        Date todt = df.parse(toDate);
        List<DailyAttendaceReport> dailyAttendanceList = dailyAttendaceReportRepository.dailyAttendanceListAlllocId(fromDate, toDate, locId);
        // System.out.println("OK");
        // List<String> dates = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        for (Date j = fmdt; j.compareTo(todt) <= 0; ) {
            // dates.add(sf.format(j));
            Date dt = j;
            DateAndDay dateAndDay = new DateAndDay();
            String stringDate = sdf.format(j);
            dateAndDay.setDate(sf.format(j));
            dateAndDay.setDay(stringDate);
            List<DailyAttendaceReport> finalDailyList = new ArrayList<>();
            for (int k = 0; k < dailyAttendanceList.size(); k++) {
                Date attsdt = dailyAttendanceList.get(k).getAttDate();
                if (attsdt.compareTo(dt) == 0) {
                    finalDailyList.add(dailyAttendanceList.get(k));
                }
            }
            dateAndDay.setFinalDailyList(finalDailyList);
            dateAndDayList.add(dateAndDay);
            /* System.out.println(sf.parse(sf.format(j))); */
            j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dateAndDayList;
}


public String AttendanceStatusOnlyPresent(EmpJsonData employee,DailyAttendance dailyAttendance,int empId,String date,String atteanceCase,MstEmpType mstEmpType){
    String ret = "";
    try {
        if (atteanceCase.equals("1357") || atteanceCase.equals("1367") || atteanceCase.equals("2357") || atteanceCase.equals("2367")) {
            if (mstEmpType.getWhWork().equals("OT") || mstEmpType.getWhWork().equals("Compoff")) {
                if (atteanceCase.equals("1357") || atteanceCase.equals("1367")) {
                    ret = "PH-WO-P";
                } else {
                    ret = "PH-OT";
                }
            } else {
                ret = "PH";
            }
        } else if (atteanceCase.equals("1358") || atteanceCase.equals("1368") || atteanceCase.equals("2358") || atteanceCase.equals("2368")) {
            if (atteanceCase.equals("1358") || atteanceCase.equals("1368")) {
                ret = "PH-WO";
            } else {
                ret = "PH";
            }
        } else if (atteanceCase.equals("1457") || atteanceCase.equals("1467")) {
            if (mstEmpType.getWhWork().equals("OT") || mstEmpType.getWhWork().equals("Compoff")) {
                ret = "WO-OT";
            } else {
                ret = "WO";
            }
        } else if (atteanceCase.equals("1458") || atteanceCase.equals("1468")) {
            ret = "WO";
        } else if (atteanceCase.equals("2468")) {
            ret = "AB";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ret;
}


@RequestMapping(value = { "/getEmployyeDailyDailyListforHr" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getEmployyeDailyDailyListforHr(String date,int desgType,List<Integer> departIds){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListForSecurityApprove(date);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@Scheduled(cron = "0 0 1 * * ? ")
public void callAttendancFuntion(){
    try {
        Date dt = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
        String dateyy = sf.format(dt);
        String datedd = dd.format(dt);
        /*
             * String dateyy = "2020-05-03"; String datedd = "03-05-2020";
             */
        List<DailyAttendance> dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListAll(dateyy, dateyy);
        Calendar a = Calendar.getInstance();
        a.setTime(dt);
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
        dataForUpdateAttendance.setFromDate(dateyy);
        dataForUpdateAttendance.setToDate(dateyy);
        dataForUpdateAttendance.setEmpId(0);
        dataForUpdateAttendance.setUserId(1);
        dataForUpdateAttendance.setMonth(month);
        dataForUpdateAttendance.setYear(year);
        List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
        for (int i = 0; i < dailyAttendanceList.size(); i++) {
            FileUploadedData fileUploadedData = new FileUploadedData();
            fileUploadedData.setEmpCode(dailyAttendanceList.get(i).getEmpCode());
            fileUploadedData.setEmpName(dailyAttendanceList.get(i).getEmpName());
            fileUploadedData.setLogDate(datedd);
            fileUploadedData.setInTime("00:00:00");
            fileUploadedData.setOutTime("00:00:00");
            fileUploadedDataList.add(fileUploadedData);
        }
        dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
        Info info = getVariousListForUploadAttendace(dataForUpdateAttendance);
        Date firstDay = new GregorianCalendar(year, month - 1, 1).getTime();
        Date lastDay = new GregorianCalendar(year, month, 0).getTime();
        info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), 1, month, year, 0);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public String AttendanceStatus(EmpJsonData employee,DailyAttendance dailyAttendance,int empId,String date,String atteanceCase,float hour,ShiftMaster shiftMaster,MstEmpType mstEmpType){
    String ret = "";
    try {
        if (atteanceCase.equals("1357") || atteanceCase.equals("1367") || atteanceCase.equals("2357") || atteanceCase.equals("2367")) {
            if (mstEmpType.getWhWork().equals("OT") || mstEmpType.getWhWork().equals("Compoff")) {
                if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
                    if (atteanceCase.equals("1357") || atteanceCase.equals("1367")) {
                        ret = "PH-WO-P";
                    } else {
                        ret = "PH-OT";
                    }
                } else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr()) && dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
                    if (atteanceCase.equals("1357") || atteanceCase.equals("1367")) {
                        ret = "PH-WO-HD";
                    } else {
                        ret = "PH-HD";
                    }
                } else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
                    ret = "PH";
                } else {
                    ret = "PH-OT";
                }
            } else /*
                 * else if (mstEmpType.getWhWork().equals("Compoff")) { if
                 * (dailyAttendance.getWorkingHrs() >=
                 * Float.parseFloat(shiftMaster.getShiftHr())) { ret = "PH-CO"; } else if
                 * (dailyAttendance.getWorkingHrs() >=
                 * Float.parseFloat(shiftMaster.getShiftHalfdayHr()) &&
                 * dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr()))
                 * { ret = "PH-COHD"; } else if (dailyAttendance.getWorkingHrs() <
                 * Float.parseFloat(shiftMaster.getShiftHalfdayHr())) { ret = "PH"; } else { ret
                 * = "PH-CO"; } }
                 */
            {
                ret = "PH";
            }
        } else if (atteanceCase.equals("1358") || atteanceCase.equals("1368") || atteanceCase.equals("2358") || atteanceCase.equals("2368")) {
            if (atteanceCase.equals("1358") || atteanceCase.equals("1368")) {
                ret = "PH-WO";
            } else {
                ret = "PH";
            }
        } else if (atteanceCase.equals("1457") || atteanceCase.equals("1467")) {
            if (mstEmpType.getWhWork().equals("OT") || mstEmpType.getWhWork().equals("Compoff")) {
                if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHr())) {
                    ret = "WO-OT";
                } else if (dailyAttendance.getWorkingHrs() >= Float.parseFloat(shiftMaster.getShiftHalfdayHr()) && dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr())) {
                    ret = "WO-HD";
                } else if (dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHalfdayHr())) {
                    ret = "WO";
                } else {
                    ret = "WO-OT";
                }
            } else /*
                 * else if (mstEmpType.getWhWork().equals("Compoff")) { if
                 * (dailyAttendance.getWorkingHrs() >=
                 * Float.parseFloat(shiftMaster.getShiftHr())) { ret = "WO-CO"; } else if
                 * (dailyAttendance.getWorkingHrs() >=
                 * Float.parseFloat(shiftMaster.getShiftHalfdayHr()) &&
                 * dailyAttendance.getWorkingHrs() < Float.parseFloat(shiftMaster.getShiftHr()))
                 * { ret = "WO-COHD"; } else if (dailyAttendance.getWorkingHrs() <
                 * Float.parseFloat(shiftMaster.getShiftHalfdayHr())) { ret = "WO"; } else { ret
                 * = "WO"; } }
                 */
            {
                ret = "WO";
            }
        } else if (atteanceCase.equals("1458") || atteanceCase.equals("1468")) {
            ret = "WO";
        } else if (atteanceCase.equals("2468")) {
            ret = "AB";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ret;
}


@RequestMapping(value = { "/updateOtApproveStatus" }, method = RequestMethod.POST)
@ResponseBody
public Info updateOtApproveStatus(List<Integer> ids,int status){
    Info info = new Info();
    try {
        int update = dailyAttendanceRepository.updateOtApproveStatus(ids, status);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


public int difffun(String date1,String date2){
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    int result = 0;
    try {
        Date date3 = myFormat.parse(date1);
        Date date4 = myFormat.parse(date2);
        long diff = date4.getTime() - date3.getTime();
        result = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
    }
    return result + 1;
}


@RequestMapping(value = { "/getMonthlySummryAttendace" }, method = RequestMethod.POST)
@ResponseBody
public List<SummaryAttendance> getMonthlySummryAttendace(int month,int year,int userType,int userId,List<Integer> locId){
    List<SummaryAttendance> summaryDailyAttendanceList = new ArrayList<>();
    try {
        if (userType == 1) {
            summaryDailyAttendanceList = summaryAttendanceRepository.summaryDailyAttendanceListForHod(month, year, userId);
        } else {
            summaryDailyAttendanceList = summaryAttendanceRepository.summaryDailyAttendanceListAlllocId(month, year, locId);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/getEmployyeDailyDailyListforHrLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyAttendance> getEmployyeDailyDailyListforHrLocId(String date,int desgType,List<Integer> locId){
    List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
    try {
        dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceListForSecurityApproveLocId(date, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/updateweeklyoffotStatutoused" }, method = RequestMethod.POST)
@ResponseBody
public Info updateweeklyoffotStatutoused(List<Integer> dailydaillyIds,String status){
    Info info = new Info();
    try {
        int updateweeklyoffotStatutoused = dailyAttendanceRepository.updateweeklyoffotStatutoused(dailydaillyIds, status);
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("failed");
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/changeInDailyDailyAfterLeaveTransactionByApp" }, method = RequestMethod.POST)
@ResponseBody
public Info changeInDailyDailyAfterLeaveTransactionByApp(String fromDate,String toDate,int empId,int userId){
    Info info = new Info();
    try {
        SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
        Date fmdt = dd.parse(fromDate);
        Date todt = dd.parse(toDate);
        List<DailyAttendance> dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate, empId);
        List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
        for (int i = 0; i < dailyAttendanceList.size(); i++) {
            FileUploadedData fileUploadedData = new FileUploadedData();
            fileUploadedData.setEmpCode(dailyAttendanceList.get(i).getEmpCode());
            fileUploadedData.setEmpName(dailyAttendanceList.get(i).getEmpName());
            fileUploadedData.setLogDate(DateConvertor.convertToDMY(dailyAttendanceList.get(i).getAttDate()));
            fileUploadedData.setInTime(dailyAttendanceList.get(i).getInTime().substring(0, 5));
            fileUploadedData.setOutTime(dailyAttendanceList.get(i).getOutTime().substring(0, 5));
            fileUploadedDataList.add(fileUploadedData);
        }
        DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
        dataForUpdateAttendance.setFromDate(DateConvertor.convertToYMD(fromDate));
        dataForUpdateAttendance.setToDate(DateConvertor.convertToYMD(toDate));
        dataForUpdateAttendance.setMonth(0);
        dataForUpdateAttendance.setYear(0);
        dataForUpdateAttendance.setUserId(userId);
        dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
        dataForUpdateAttendance.setEmpId(empId);
        Info dailydailyinfo = getVariousListForUploadAttendace(dataForUpdateAttendance);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if (dailydailyinfo.isError() == false) {
            for (Date m = fmdt; m.compareTo(todt) <= 0; ) {
                Calendar a = Calendar.getInstance();
                a.setTime(m);
                int year = a.get(Calendar.YEAR);
                int month = a.get(Calendar.MONTH) + 1;
                // System.out.println(m + " " + year + " " + k);
                Date firstDay = new GregorianCalendar(year, month - 1, 1).getTime();
                Date lastDay = new GregorianCalendar(year, month, 0).getTime();
                Info sumryinfo = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), userId, month, year, empId);
                String dt = "0" + "-" + (month + 1) + "-" + year;
                m = dd.parse(dt);
                m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
            }
        }
        info.setError(false);
        info.setMsg("success");
    } catch (Exception e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("failed");
    }
    return info;
}


@RequestMapping(value = { "/getCountofWeeklyOff" }, method = RequestMethod.POST)
@ResponseBody
public List<SummaryAttendance> getCountofWeeklyOff(List<Integer> deptId,int month,int year){
    List<SummaryAttendance> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = summaryAttendanceRepository.getCountofWeeklyOff(month, year, deptId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/autoThumbAttendance" }, method = RequestMethod.GET)
@ResponseBody
public List<LiveThumbData> autoThumbAttendance(){
    List<LiveThumbData> dailyAttendanceList = new ArrayList<>();
    try {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        // String dateyy = sf.format(dt);
        String dateyy = "2021-01-01";
        List<Integer> yesterdaysIds = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(sf.parse(dateyy));
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        int yesterdayYear = cal.get(Calendar.YEAR);
        int yesterdayMonth = cal.get(Calendar.MONTH) + 1;
        List<LiveThumbData> yesterdayAttendanceList = liveThumbDataRepository.getThumbDataYesterday(dateyy, sf.format(yesterday));
        DataForUpdateAttendance dataForUpdateAttendance = new DataForUpdateAttendance();
        dataForUpdateAttendance.setFromDate(sf.format(yesterday));
        dataForUpdateAttendance.setToDate(sf.format(yesterday));
        dataForUpdateAttendance.setEmpId(0);
        dataForUpdateAttendance.setUserId(1);
        dataForUpdateAttendance.setMonth(yesterdayYear);
        dataForUpdateAttendance.setYear(yesterdayMonth);
        List<FileUploadedData> fileUploadedDataList = new ArrayList<>();
        for (int i = 0; i < yesterdayAttendanceList.size(); i++) {
            FileUploadedData fileUploadedData = new FileUploadedData();
            yesterdaysIds.add(yesterdayAttendanceList.get(i).getInId());
            yesterdaysIds.add(yesterdayAttendanceList.get(i).getOutId());
            if (yesterdayAttendanceList.get(i).getInId() != 0 && yesterdayAttendanceList.get(i).getOutId() != 0) {
                fileUploadedData.setInTime(yesterdayAttendanceList.get(i).getInTime());
                fileUploadedData.setOutTime(yesterdayAttendanceList.get(i).getOutTime());
            } else {
                fileUploadedData.setInTime("00:00");
                fileUploadedData.setOutTime("00:00");
            }
            fileUploadedData.setEmpCode(yesterdayAttendanceList.get(i).getEmpCode());
            fileUploadedData.setEmpName(yesterdayAttendanceList.get(i).getEmpCode());
            fileUploadedData.setLogDate(dd.format(yesterday));
            fileUploadedDataList.add(fileUploadedData);
        }
        dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
        Info info = getVariousListForUploadAttendace(dataForUpdateAttendance);
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDay = new GregorianCalendar(yesterdayYear, yesterdayMonth - 1, 1).getTime();
        Date lastDay = new GregorianCalendar(yesterdayYear, yesterdayMonth, 0).getTime();
        info = finalUpdateDailySumaryRecord(sf.format(firstDay), sf.format(lastDay), 1, yesterdayMonth, yesterdayYear, 0);
        try {
            System.out.println(yesterdayAttendanceList);
            System.out.println(yesterdaysIds);
        // int updateThumbFlag = inoutTableRepo.updatethumbflag(yesterdaysIds);
        } catch (Exception e) {
        }
        // -------------current date attendace-------------------------------
        List<Integer> ids = new ArrayList<>();
        Calendar a = Calendar.getInstance();
        a.setTime(sf.parse(dateyy));
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        dailyAttendanceList = liveThumbDataRepository.getThumbData(dateyy);
        DataForUpdateAttendance dataForUpdateAttendance1 = new DataForUpdateAttendance();
        dataForUpdateAttendance1.setFromDate(dateyy);
        dataForUpdateAttendance1.setToDate(dateyy);
        dataForUpdateAttendance1.setEmpId(0);
        dataForUpdateAttendance1.setUserId(1);
        dataForUpdateAttendance1.setMonth(month);
        dataForUpdateAttendance1.setYear(year);
        fileUploadedDataList = new ArrayList<>();
        for (int i = 0; i < dailyAttendanceList.size(); i++) {
            FileUploadedData fileUploadedData = new FileUploadedData();
            ids.add(dailyAttendanceList.get(i).getInId());
            ids.add(dailyAttendanceList.get(i).getOutId());
            if (dailyAttendanceList.get(i).getInId() != 0 && dailyAttendanceList.get(i).getOutId() != 0) {
                fileUploadedData.setInTime(dailyAttendanceList.get(i).getInTime());
                fileUploadedData.setOutTime(dailyAttendanceList.get(i).getOutTime());
            } else {
                fileUploadedData.setInTime("00:00");
                fileUploadedData.setOutTime("00:00");
            }
            fileUploadedData.setEmpCode(dailyAttendanceList.get(i).getEmpCode());
            fileUploadedData.setEmpName(dailyAttendanceList.get(i).getEmpCode());
            fileUploadedData.setLogDate(DateConvertor.convertToDMY(dateyy));
            fileUploadedDataList.add(fileUploadedData);
        }
        dataForUpdateAttendance.setFileUploadedDataList(fileUploadedDataList);
        info = getVariousListForUploadAttendace(dataForUpdateAttendance);
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDayYeasterday = new GregorianCalendar(year, month - 1, 1).getTime();
        Date lastDayYeasterday = new GregorianCalendar(year, month, 0).getTime();
        info = finalUpdateDailySumaryRecord(sf.format(firstDayYeasterday), sf.format(lastDayYeasterday), 1, month, year, 0);
        try {
            System.out.println(dailyAttendanceList);
            System.out.println(ids);
        // int updateThumbFlag = inoutTableRepo.updatethumbflag(ids);
        } catch (Exception e) {
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyAttendanceList;
}


@RequestMapping(value = { "/getInformationOfUploadedAttendance" }, method = RequestMethod.POST)
@ResponseBody
public InfoForUploadAttendance getInformationOfUploadedAttendance(String fromDate,String toDate,List<Integer> locId){
    InfoForUploadAttendance infoForUploadAttendance = new InfoForUploadAttendance();
    try {
        infoForUploadAttendance = infoForUploadAttendanceRepository.getInformationOfUploadedAttendance(fromDate, toDate, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return infoForUploadAttendance;
}


@RequestMapping(value = { "/getDailyDailyRecordForHrByDateLocId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetDailyDailyRecord> getDailyDailyRecordForHrByDateLocId(String date,List<Integer> locId){
    List<GetDailyDailyRecord> summaryDailyAttendanceList = new ArrayList<>();
    try {
        summaryDailyAttendanceList = getDailyDailyRecordRepository.getDailyDailyRecordForHrByDateLocId(date, locId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return summaryDailyAttendanceList;
}


@RequestMapping(value = { "/getListForfixunfixAttendance" }, method = RequestMethod.POST)
@ResponseBody
public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendance(int month,int year,int isFixed,String sts,List<Integer> locId,int deptId,int typeId){
    List<EmpSalaryInfoForPayroll> list = new ArrayList<>();
    try {
        if (deptId != 0 && typeId != 0) {
            list = empSalaryInfoForPayrollRepository.getListForfixunfixAttendance(month, year, isFixed, sts, locId, typeId, deptId);
        } else if (deptId == 0 && typeId != 0) {
            list = empSalaryInfoForPayrollRepository.getListForfixunfixAttendanceTypeId(month, year, isFixed, sts, locId, typeId);
        } else if (deptId != 0 && typeId == 0) {
            list = empSalaryInfoForPayrollRepository.getListForfixunfixAttendanceDeptId(month, year, isFixed, sts, locId, deptId);
        } else {
            list = empSalaryInfoForPayrollRepository.getListForfixunfixAttendance(month, year, isFixed, sts, locId);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmpListForFixAttendace" }, method = RequestMethod.GET)
@ResponseBody
public List<EmpInfo> getEmpListForFixAttendace(){
    List<EmpInfo> empList = new ArrayList<>();
    try {
        empList = empInfoRepository.getEmpListAll();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return empList;
}


@RequestMapping(value = { "/getDatesOfWeeklyOfForShiftingDate" }, method = RequestMethod.POST)
@ResponseBody
public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate,String toDate,int locationId,int weekCatId){
    List<String> datesList = new ArrayList<>();
    try {
        List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
        datesList = commonFunctionService.getDatesOfWeeklyOfForShiftingDate(fromDate, toDate, weeklyOfflist, locationId, weekCatId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return datesList;
}


}