import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model;
import com.ats.hrmgt.repository;
import com.ats.hrmgt.service.CommonFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@RestController
public class WeeklyOffApiController {

@Autowired
 private WeeklyOffRepo weeklyOffRepo;

@Autowired
 private GetWeeklyOffRepo getWeeklyOffRepo;

@Autowired
 private HolidayRepo holidayRepo;

 private List<Date> arryadate;

 private String datearry;

@Autowired
 private WeeklyOffShitRepository weeklyOffShitRepository;

@Autowired
 private WeeklyOffRepo weeklyOffRepo1;

@Autowired
 private CommonFunctionService commonFunctionService;

@Autowired
 private EmployeeMasterRepository empRepo;


@RequestMapping(value = { "/getWeeklyOffList" }, method = RequestMethod.GET)
@ResponseBody
public List<WeeklyOff> getWeeklyOffList(){
    List<WeeklyOff> list = new ArrayList<WeeklyOff>();
    try {
        list = weeklyOffRepo.findByDelStatusAndIsActive(1, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getWeeklyOffById" }, method = RequestMethod.POST)
@ResponseBody
public WeeklyOff getWeeklyOffById(int woId){
    WeeklyOff woo = new WeeklyOff();
    try {
        woo = weeklyOffRepo.findBywoIdAndDelStatus(woId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return woo;
}


public int diffrence(Date date1,Date date2,Date holfrstdt,Date holseconddt,int day,List<WeeklyOffShit> weeklyOffShitFromList){
    int totalcount = 0;
    SimpleDateFormat dddate = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
    // System.out.println("date1 " + date1 + "date2 " + date2 + " holfrstdt " +
    // holfrstdt + " holseconddt " + holseconddt + " day " + day);
    for (Date m = holfrstdt; m.compareTo(holseconddt) <= 0; ) {
        if (m.compareTo(date1) >= 0 && m.compareTo(date2) <= 0) {
            for (Date j = m; j.compareTo(date2) <= 0; ) {
                Calendar fc = Calendar.getInstance();
                fc.setTime(j);
                int dayOfWeek = fc.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == day && m.compareTo(holfrstdt) >= 0 && m.compareTo(holseconddt) <= 0) {
                    /*
                         * arryadate.add(m); datearry = datearry + "," + dddate.format(m);
                         * System.out.println("add in odd even" + dddate.format(m)); totalcount++;
                         */
                    int find = 0;
                    try {
                        for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                            Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                            if (shiftWkDate.compareTo(j) == 0) {
                                find = 1;
                                break;
                            }
                        }
                    } catch (Exception e) {
                    }
                    if (find == 0) {
                        arryadate.add(m);
                        datearry = datearry + "," + dddate.format(m);
                        totalcount++;
                    }
                }
                j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
            }
        }
        m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
    }
    return totalcount;
}


@RequestMapping(value = { "/saveWeeklyOff" }, method = RequestMethod.POST)
@ResponseBody
public WeeklyOff saveWeeklyOff(WeeklyOff weeklyOff){
    WeeklyOff save = new WeeklyOff();
    try {
        save = weeklyOffRepo.saveAndFlush(weeklyOff);
        if (save != null) {
            save.setError(false);
        } else {
            save = new WeeklyOff();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new WeeklyOff();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getWeeklyOffListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<WeeklyOff> getWeeklyOffListByEmpId(int empId){
    List<WeeklyOff> list = new ArrayList<WeeklyOff>();
    try {
        list = weeklyOffRepo.getWeeklyOffListByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/calculateHolidayBetweenDate" }, method = RequestMethod.POST)
@ResponseBody
public LeaveCount calculateHolidayBetweenDate(int empId,String fromDate,String toDate){
    List<Holiday> holidayList = new ArrayList<Holiday>();
    List<WeeklyOff> weeklyList = new ArrayList<WeeklyOff>();
    int totalcount = 0;
    int diff = difffun(fromDate, toDate);
    LeaveCount leaveCount = new LeaveCount();
    try {
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dddate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat yearfrmt = new SimpleDateFormat("yyyy");
        SimpleDateFormat ddfrmt = new SimpleDateFormat("dd");
        arryadate.clear();
        datearry = "";
        int weekendCatId = weeklyOffRepo.getweekendCatId(empId);
        int holidayCatId = weeklyOffRepo.getholidayCatId(empId);
        /*
             * System.out.println(weekendCatId); System.out.println(holidayCatId);
             */
        List<WeeklyOffShit> weeklyOffShitFromList = weeklyOffShitRepository.getWeeklyOffShitListbetweenweekofffromdatebyempId(fromDate, toDate, empId);
        List<WeeklyOffShit> weeklyOffShitonList = weeklyOffShitRepository.getWeeklyOffShitListbetweenweekoffondatebyempId(fromDate, toDate, empId);
        System.out.println(weeklyOffShitFromList);
        System.out.println(weeklyOffShitonList);
        weeklyList = weeklyOffRepo.getWeeklyOffListByEmpId(empId);
        holidayList = holidayRepo.getHolidayByEmpIdAndFromDateTodate(empId, fromDate, toDate);
        arryadate = new ArrayList<>();
        for (int i = 0; i < weeklyList.size(); i++) {
            // System.out.println("in for ");
            if (weekendCatId == weeklyList.get(i).getExInt1()) {
                if (Integer.parseInt(weeklyList.get(i).getWoType()) == 0) {
                    for (Date j = yydate.parse(fromDate); j.compareTo(yydate.parse(toDate)) <= 0; ) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(j);
                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                        if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay())) {
                            int find = 0;
                            for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                                Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                                if (shiftWkDate.compareTo(j) == 0) {
                                    find = 1;
                                    break;
                                }
                            }
                            if (find == 0) {
                                arryadate.add(j);
                                datearry = datearry + "," + dddate.format(j);
                                // System.out.println("add in all" + dddate.format(j));
                                totalcount++;
                            }
                        }
                        j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 3) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    // System.out.println("year " + year);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-01";
                        String ld = year + "-" + k + "-07";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        // System.out.println(wkfstdt + " " + wklstdt);
                        // System.out.println(wkfstdt + " " + fd + " " + wklstdt + " " + ld);
                        for (Date m = yydate.parse(fromDate); m.compareTo(yydate.parse(toDate)) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar tempc = Calendar.getInstance();
                                    tempc.setTime(j);
                                    int dayOfWeek = tempc.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(yydate.parse(fromDate)) >= 0 && m.compareTo(yydate.parse(toDate)) <= 0) {
                                        /*
                                             * arryadate.add(m); datearry = datearry + "," + dddate.format(m);
                                             * totalcount++;
                                             */
                                        int find = 0;
                                        for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                                            Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                                            if (shiftWkDate.compareTo(j) == 0) {
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if (find == 0) {
                                            arryadate.add(m);
                                            datearry = datearry + "," + dddate.format(m);
                                            totalcount++;
                                        }
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 4) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-08";
                        String ld = year + "-" + k + "-14";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        // System.out.println(wkfstdt + " " + fd + " " + wklstdt + " " + ld);
                        for (Date m = yydate.parse(fromDate); m.compareTo(yydate.parse(toDate)) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar tempc = Calendar.getInstance();
                                    tempc.setTime(j);
                                    int dayOfWeek = tempc.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(yydate.parse(fromDate)) >= 0 && m.compareTo(yydate.parse(toDate)) <= 0) {
                                        /*
                                             * arryadate.add(m); datearry = datearry + "," + dddate.format(m);
                                             * totalcount++;
                                             */
                                        int find = 0;
                                        for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                                            Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                                            if (shiftWkDate.compareTo(j) == 0) {
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if (find == 0) {
                                            arryadate.add(m);
                                            datearry = datearry + "," + dddate.format(m);
                                            totalcount++;
                                        }
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 5) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-15";
                        String ld = year + "-" + k + "-21";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        // System.out.println(wkfstdt + " " + fd + " " + wklstdt + " " + ld);
                        for (Date m = yydate.parse(fromDate); m.compareTo(yydate.parse(toDate)) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar tempc = Calendar.getInstance();
                                    tempc.setTime(j);
                                    int dayOfWeek = tempc.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(yydate.parse(fromDate)) >= 0 && m.compareTo(yydate.parse(toDate)) <= 0) {
                                        /*
                                             * arryadate.add(m); datearry = datearry + "," + dddate.format(m);
                                             * totalcount++;
                                             */
                                        int find = 0;
                                        for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                                            Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                                            if (shiftWkDate.compareTo(j) == 0) {
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if (find == 0) {
                                            arryadate.add(m);
                                            datearry = datearry + "," + dddate.format(m);
                                            totalcount++;
                                        }
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 6) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-22";
                        String ld = year + "-" + k + "-28";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        // System.out.println(wkfstdt + " " + fd + " " + wklstdt + " " + ld);
                        for (Date m = yydate.parse(fromDate); m.compareTo(yydate.parse(toDate)) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar tempc = Calendar.getInstance();
                                    tempc.setTime(j);
                                    int dayOfWeek = tempc.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(yydate.parse(fromDate)) >= 0 && m.compareTo(yydate.parse(toDate)) <= 0) {
                                        /*
                                             * arryadate.add(m); datearry = datearry + "," + dddate.format(m);
                                             * totalcount++;
                                             */
                                        int find = 0;
                                        for (int a = 0; a < weeklyOffShitFromList.size(); a++) {
                                            Date shiftWkDate = yydate.parse(weeklyOffShitFromList.get(a).getWeekofffromdate());
                                            if (shiftWkDate.compareTo(j) == 0) {
                                                find = 1;
                                                break;
                                            }
                                        }
                                        if (find == 0) {
                                            arryadate.add(m);
                                            datearry = datearry + "," + dddate.format(m);
                                            totalcount++;
                                        }
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 1) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-08";
                        String ld = year + "-" + k + "-14";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        int cnt1 = diffrence(wkfstdt, wklstdt, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()), weeklyOffShitFromList);
                        String fd1 = year + "-" + k + "-22";
                        String ld1 = year + "-" + k + "-28";
                        Date wkfstdt1 = yydate.parse(fd1);
                        Date wklstdt1 = yydate.parse(ld1);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        int cnt2 = diffrence(wkfstdt1, wklstdt1, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()), weeklyOffShitFromList);
                        totalcount = totalcount + cnt1 + cnt2;
                        // System.out.println("cnt1 " + cnt1 + "cnt2 " + cnt2 + " wkfstdt1 " + wkfstdt1
                        // + " wklstdt1 " + wklstdt1 + " " + weeklyList.get(i).getWoType());
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                } else if (Integer.parseInt(weeklyList.get(i).getWoType()) == 2) {
                    Date frmdt = yydate.parse(fromDate);
                    Date todt = yydate.parse(toDate);
                    Calendar fc = Calendar.getInstance();
                    fc.setTime(frmdt);
                    Calendar tc = Calendar.getInstance();
                    tc.setTime(todt);
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-01";
                        String ld = year + "-" + k + "-07";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        int cnt1 = diffrence(wkfstdt, wklstdt, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()), weeklyOffShitFromList);
                        String fd1 = year + "-" + k + "-15";
                        String ld1 = year + "-" + k + "-21";
                        Date wkfstdt1 = yydate.parse(fd1);
                        Date wklstdt1 = yydate.parse(ld1);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        int cnt2 = diffrence(wkfstdt1, wklstdt1, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()), weeklyOffShitFromList);
                        String fd3 = year + "-" + k + "-29";
                        String ld3 = year + "-" + (k + 1) + "-0";
                        Date wkfstdt3 = yydate.parse(fd3);
                        Date wklstdt3 = yydate.parse(ld3);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        int cnt3 = diffrence(wkfstdt3, wklstdt3, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()), weeklyOffShitFromList);
                        totalcount = totalcount + cnt1 + cnt2 + cnt3;
                        String dt = year + "-" + (k + 1) + "-0";
                        e = yydate.parse(dt);
                        e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                        Calendar a = Calendar.getInstance();
                        a.setTime(e);
                        year = a.get(Calendar.YEAR);
                        k = a.get(Calendar.MONTH) + 1;
                    }
                }
            }
        }
        for (int i = 0; i < weeklyOffShitonList.size(); i++) {
            Date shiftWkDate = yydate.parse(weeklyOffShitonList.get(i).getWeekofffromdate());
            arryadate.add(shiftWkDate);
            datearry = datearry + "," + dddate.format(shiftWkDate);
            totalcount++;
        }
        for (int i = 0; i < holidayList.size(); i++) {
            // alert("Data " +JSON.stringify(data.holidayList[i]));
            if (holidayCatId == holidayList.get(i).getExInt1()) {
                Date frmdt = yydate.parse(fromDate);
                Date todt = yydate.parse(toDate);
                int tempdiff = difffun(holidayList.get(i).getHolidayFromdt(), holidayList.get(i).getHolidayTodt());
                String[] a = {};
                // datearry.substring(1 ,datearry.length());
                // System.out.println(datearry);
                try {
                    a = datearry.split(",");
                } catch (Exception e) {
                }
                for (int j = 1; j < a.length; j++) {
                    /*
                         * System.out.println(dddate.parse(a[j]) + " arryadate.get(j) " +
                         * yydate.parse(holidayList.get(i).getHolidayFromdt()) + " " +
                         * yydate.parse(holidayList.get(i).getHolidayTodt()));
                         */
                    if (dddate.parse(a[j]).compareTo(yydate.parse(holidayList.get(i).getHolidayFromdt())) >= 0 && dddate.parse(a[j]).compareTo(yydate.parse(holidayList.get(i).getHolidayTodt())) <= 0) {
                        tempdiff--;
                    }
                }
                totalcount = totalcount + tempdiff;
            }
        }
        // diff = diff - totalcount;
        leaveCount.setHolidaycount(0);
        leaveCount.setLeavecount(diff);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveCount;
}


@RequestMapping(value = { "/getWeeklyOffListByCatId" }, method = RequestMethod.POST)
@ResponseBody
public List<GetWeeklyOff> getWeeklyOffListByCatId(int catId){
    List<GetWeeklyOff> list = new ArrayList<GetWeeklyOff>();
    try {
        list = getWeeklyOffRepo.getWeeklyOffListByCatId(catId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getHolidayByEmpIdAndFromDateTodate" }, method = RequestMethod.POST)
@ResponseBody
public List<Holiday> getHolidayByEmpIdAndFromDateTodate(int empId,String fromDate,String toDate){
    List<Holiday> list = new ArrayList<Holiday>();
    try {
        list = holidayRepo.getHolidayByEmpIdAndFromDateTodate(empId, fromDate, toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
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


@RequestMapping(value = { "/getWeeklyOffDatesToChange" }, method = RequestMethod.POST)
@ResponseBody
public List<String> getWeeklyOffDatesToChange(int month,int year,int empId){
    List<WeeklyOffShit> shiftedList = new ArrayList<WeeklyOffShit>();
    List<WeeklyOffShit> shiftedToList = new ArrayList<WeeklyOffShit>();
    List<String> datesList = new ArrayList<>();
    try {
        EmployeeMaster emp = new EmployeeMaster();
        emp = empRepo.findByEmpIdAndDelStatus(empId, 1);
        int locId = emp.getLocationId();
        int weekoffCatId = emp.getWeekendCategory();
        String monthNew = null;
        if (String.valueOf(month).length() == 1) {
            monthNew = "0".concat(String.valueOf(month));
        } else {
            monthNew = String.valueOf(month);
        }
        int monthN = Integer.parseInt(monthNew);
        /*
             * Calendar calendar = Calendar.getInstance(); int date = 1; calendar.set(year,
             * monthN, date); int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
             */
        YearMonth yearMonthObject = YearMonth.of(year, monthN);
        // 28
        int days = yearMonthObject.lengthOfMonth();
        // System.out.println("Number of Days: " + days);
        String fromDate = String.valueOf(year).concat("-").concat(String.valueOf(monthN)).concat("-").concat("01");
        String toDate = String.valueOf(year).concat("-").concat(String.valueOf(monthN)).concat("-").concat(String.valueOf(days));
        // System.out.println("sht: " +
        // DateConvertor.convertToDMY(sht.getWeekofffromdate()));
        // System.out.println("fromDate: " + fromDate);
        // System.out.println("toDate: " + toDate);
        shiftedList = weeklyOffShitRepository.getWeeklyOffShitListbetweenweekofffromdatebyempId(fromDate, toDate, empId);
        shiftedToList = weeklyOffShitRepository.getWeeklyOffShitListbetweenweekoffondatebyempId(fromDate, toDate, empId);
        try {
            List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
            datesList = commonFunctionService.getDatesOfWeeklyOfForShiftingDate(fromDate, toDate, weeklyOfflist, locId, weekoffCatId);
            // System.out.println("datesList before: " + datesList.toString());
            if (shiftedList != null) {
                for (int j = 0; j < shiftedList.size(); j++) {
                    for (int i = 0; i < datesList.size(); i++) {
                        if (datesList.get(i).equals(DateConvertor.convertToDMY(shiftedList.get(j).getWeekofffromdate()))) {
                            // System.err.println("matched" + datesList.get(i));
                            datesList.remove(i);
                            break;
                        }
                    }
                }
            }
            if (shiftedToList != null) {
                for (int j = 0; j < shiftedToList.size(); j++) {
                    datesList.add(DateConvertor.convertToDMY(shiftedList.get(j).getWeekoffshiftdate()));
                }
            }
        // System.out.println("datesList after ******: " + datesList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return datesList;
}


@RequestMapping(value = { "/saveWeeklyOffShit" }, method = RequestMethod.POST)
@ResponseBody
public WeeklyOffShit saveWeeklyOffShit(WeeklyOffShit weeklyOff){
    WeeklyOffShit save = new WeeklyOffShit();
    try {
        save = weeklyOffShitRepository.saveAndFlush(weeklyOff);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getWeeklyOffListByEmpIdDashboard" }, method = RequestMethod.POST)
@ResponseBody
public List<GetWeeklyOff> getWeeklyOffListByEmpIdDashboard(int empId){
    List<GetWeeklyOff> list = new ArrayList<GetWeeklyOff>();
    try {
        list = getWeeklyOffRepo.getWeeklyOffListByEmpIdDashboard(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteWeeklyOff" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteWeeklyOff(int woId){
    Info info = new Info();
    try {
        int delete = weeklyOffRepo.deleteWeeklyOff(woId);
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