import com.ats.hrmgt.model;
import com.ats.hrmgt.repository.WeeklyOffShitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class CommonFunctionServiceImpl implements CommonFunctionService,com.ats.hrmgt.service.CommonFunctionService{

@Autowired
 private WeeklyOffShitRepository weeklyOffShitRepository;


@Override
public int findDateInOptionalHoliday(String fromDate,List<EmpListForHolidayApprove> optionalHolidayList,int empId){
    int sts = 4;
    try {
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        Date frmdt = yydate.parse(fromDate);
        for (int i = 0; i < optionalHolidayList.size(); i++) {
            Date holdte = optionalHolidayList.get(i).getHolidate();
            if (frmdt.compareTo(holdte) == 0 && empId == optionalHolidayList.get(i).getEmpId()) {
                sts = 3;
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sts;
}


@Override
public LeaveStsAndLeaveId findDateInLeave(String fromDate,List<LeaveApply> leavetList,int empId){
    int sts = 6;
    LeaveStsAndLeaveId stsInfo = new LeaveStsAndLeaveId();
    try {
        stsInfo.setSts(sts);
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        Date frmdt = yydate.parse(fromDate);
        for (int i = 0; i < leavetList.size(); i++) {
            if (empId == leavetList.get(i).getEmpId() && frmdt.compareTo(yydate.parse(leavetList.get(i).getLeaveFromdt())) >= 0 && frmdt.compareTo(yydate.parse(leavetList.get(i).getLeaveTodt())) <= 0) {
                sts = 5;
                stsInfo.setSts(sts);
                stsInfo.setDuration(Integer.parseInt(leavetList.get(i).getLeaveDuration()));
                stsInfo.setLeaveId(leavetList.get(i).getLeaveId());
                stsInfo.setLeaveTyId(leavetList.get(i).getLvTypeId());
                stsInfo.setNoOfLeave(leavetList.get(i).getLeaveNumDays());
                stsInfo.setLvTypeId(Integer.parseInt(leavetList.get(i).getExVar2()));
                stsInfo.setStsshortname(leavetList.get(i).getExVar1());
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return stsInfo;
}


@Override
public Integer findDateInWeekEnd(String fromDate,String toDate,List<WeeklyOff> weeklyList,List<WeeklyOffShit> weeklyOffShitList,int locationId,int weekendCatId,int empId,List<WeeklyOffShit> weeklyOffShitFromList){
    int sts = 2;
    try {
        sts = findShiftedWeekEnd(fromDate, weeklyOffShitList, locationId, empId);
        if (sts == 2) {
            SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dddate = new SimpleDateFormat("dd-MM-yyyy");
            for (int i = 0; i < weeklyList.size(); i++) {
                if (weekendCatId == weeklyList.get(i).getExInt1()) {
                    if (Integer.parseInt(weeklyList.get(i).getWoType()) == 0) {
                        for (Date j = yydate.parse(fromDate); j.compareTo(yydate.parse(toDate)) <= 0; ) {
                            Calendar c = Calendar.getInstance();
                            c.setTime(j);
                            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                            if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay())) {
                                sts = 1;
                                break;
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
                        Calendar temp = Calendar.getInstance();
                        temp.setTime(yydate.parse(fromDate));
                        int k = temp.get(Calendar.MONTH) + 1;
                        int year = fc.get(Calendar.YEAR);
                        // System.out.println("year " + year);
                        for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                            String fd = year + "-" + k + "-01";
                            String ld = year + "-" + k + "-07";
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
                                            sts = 1;
                                            break;
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
                        int year = fc.get(Calendar.YEAR);
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
                                            sts = 1;
                                            break;
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
                        int year = fc.get(Calendar.YEAR);
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
                                            sts = 1;
                                            break;
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
                        int year = fc.get(Calendar.YEAR);
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
                                            sts = 1;
                                            break;
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
                        int year = fc.get(Calendar.YEAR);
                        // System.out.println("year " + year);
                        for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                            String fd = year + "-" + k + "-08";
                            String ld = year + "-" + k + "-14";
                            Date wkfstdt = yydate.parse(fd);
                            Date wklstdt = yydate.parse(ld);
                            frmdt = yydate.parse(fromDate);
                            todt = yydate.parse(toDate);
                            int cnt1 = diffrence(wkfstdt, wklstdt, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()));
                            if (cnt1 == 2) {
                                String fd1 = year + "-" + k + "-22";
                                String ld1 = year + "-" + k + "-28";
                                Date wkfstdt1 = yydate.parse(fd1);
                                Date wklstdt1 = yydate.parse(ld1);
                                frmdt = yydate.parse(fromDate);
                                todt = yydate.parse(toDate);
                                int cnt2 = diffrence(wkfstdt1, wklstdt1, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()));
                                if (cnt2 == 1) {
                                    sts = 1;
                                    break;
                                }
                                // System.out.println("cnt1 " + cnt1 + "cnt2 " + cnt2 + " wkfstdt1 " + wkfstdt1
                                // + " wklstdt1 " + wklstdt1 + " " + weeklyList.get(i).getWoType());
                                String dt = year + "-" + (k + 1) + "-0";
                                e = yydate.parse(dt);
                                e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                                Calendar a = Calendar.getInstance();
                                a.setTime(e);
                                year = a.get(Calendar.YEAR);
                                k = a.get(Calendar.MONTH) + 1;
                            } else {
                                sts = 1;
                                break;
                            }
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
                        int year = fc.get(Calendar.YEAR);
                        // System.out.println("year " + year);
                        for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                            String fd = year + "-" + k + "-01";
                            String ld = year + "-" + k + "-07";
                            Date wkfstdt = yydate.parse(fd);
                            Date wklstdt = yydate.parse(ld);
                            frmdt = yydate.parse(fromDate);
                            todt = yydate.parse(toDate);
                            int cnt1 = diffrence(wkfstdt, wklstdt, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()));
                            if (cnt1 == 2) {
                                String fd1 = year + "-" + k + "-15";
                                String ld1 = year + "-" + k + "-21";
                                Date wkfstdt1 = yydate.parse(fd1);
                                Date wklstdt1 = yydate.parse(ld1);
                                frmdt = yydate.parse(fromDate);
                                todt = yydate.parse(toDate);
                                int cnt2 = diffrence(wkfstdt1, wklstdt1, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()));
                                if (cnt2 == 2) {
                                    String fd3 = year + "-" + k + "-29";
                                    String ld3 = year + "-" + (k + 1) + "-0";
                                    Date wkfstdt3 = yydate.parse(fd3);
                                    Date wklstdt3 = yydate.parse(ld3);
                                    frmdt = yydate.parse(fromDate);
                                    todt = yydate.parse(toDate);
                                    int cnt3 = diffrence(wkfstdt3, wklstdt3, frmdt, todt, Integer.parseInt(weeklyList.get(i).getWoDay()));
                                    if (cnt3 == 1) {
                                        sts = 1;
                                        break;
                                    }
                                    String dt = year + "-" + (k + 1) + "-0";
                                    e = yydate.parse(dt);
                                    e.setTime(e.getTime() + 1000 * 60 * 60 * 24);
                                    Calendar a = Calendar.getInstance();
                                    a.setTime(e);
                                    year = a.get(Calendar.YEAR);
                                    k = a.get(Calendar.MONTH) + 1;
                                } else {
                                    sts = 1;
                                    break;
                                }
                            } else {
                                sts = 1;
                                break;
                            }
                        }
                    }
                }
            }
            if (sts == 1) {
                /*
                     * List<WeeklyOffShit> weeklyOffShitFromList =
                     * weeklyOffShitRepository.getWeeklyOffShitListbetweenweekofffromdatebyempId(
                     * fromDate, fromDate,empId);
                     *
                     * if (weeklyOffShitFromList.size()>0) { sts = 2; }
                     */
                Date frmdt = yydate.parse(fromDate);
                for (int i = 0; i < weeklyOffShitFromList.size(); i++) {
                    if (empId == weeklyOffShitFromList.get(i).getEmpId() && frmdt.compareTo(yydate.parse(weeklyOffShitFromList.get(i).getWeekofffromdate())) == 0) {
                        sts = 2;
                        break;
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sts;
}


@Override
public Integer findDateInHoliday(String fromDate,String toDate,List<Holiday> holidayList,int locationId,int holidayCatId){
    int sts = 4;
    try {
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        Date frmdt = yydate.parse(fromDate);
        for (int i = 0; i < holidayList.size(); i++) {
            /*
                 * String[] locIds = holidayList.get(i).getLocId().split(","); for (int j = 0; j
                 * < locIds.length; j++) {
                 */
            if (frmdt.compareTo(yydate.parse(holidayList.get(i).getHolidayFromdt())) >= 0 && frmdt.compareTo(yydate.parse(holidayList.get(i).getHolidayTodt())) <= 0 && holidayCatId == holidayList.get(i).getExInt1()) {
                sts = 3;
                break;
            }
        /*
                 * }
                 *
                 * if (sts == 3) { break; }
                 */
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sts;
}


public Integer findShiftedWeekEnd(String fromDate,List<WeeklyOffShit> weeklyOffShitList,int locationId,int empId){
    int sts = 2;
    try {
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        Date frmdt = yydate.parse(fromDate);
        for (int i = 0; i < weeklyOffShitList.size(); i++) {
            if (empId == weeklyOffShitList.get(i).getEmpId() && frmdt.compareTo(yydate.parse(weeklyOffShitList.get(i).getWeekoffshiftdate())) == 0) {
                sts = 1;
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sts;
}


public int diffrence(Date date1,Date date2,Date holfrstdt,Date holseconddt,int day){
    int sts = 2;
    for (Date m = holfrstdt; m.compareTo(holseconddt) <= 0; ) {
        if (m.compareTo(date1) >= 0 && m.compareTo(date2) <= 0) {
            for (Date j = m; j.compareTo(date2) <= 0; ) {
                Calendar fc = Calendar.getInstance();
                fc.setTime(j);
                int dayOfWeek = fc.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == day && m.compareTo(holfrstdt) >= 0 && m.compareTo(holseconddt) <= 0) {
                    sts = 1;
                    break;
                }
                j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
            }
        }
        m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
    }
    return sts;
}


@Override
public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate,String toDate,List<WeeklyOff> weeklyList,int locationId,int holidayCatId){
    List<String> dates = new ArrayList<>();
    try {
        SimpleDateFormat yydate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dddate = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < weeklyList.size(); i++) {
            if (holidayCatId == weeklyList.get(i).getExInt1()) {
                if (Integer.parseInt(weeklyList.get(i).getWoType()) == 0) {
                    for (Date j = yydate.parse(fromDate); j.compareTo(yydate.parse(toDate)) <= 0; ) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(j);
                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                        if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay())) {
                            String dt = dddate.format(j);
                            dates.add(dt);
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
                    Calendar temp = Calendar.getInstance();
                    temp.setTime(yydate.parse(fromDate));
                    int k = temp.get(Calendar.MONTH) + 1;
                    int year = fc.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-01";
                        String ld = year + "-" + k + "-07";
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
                                        String dt = dddate.format(j);
                                        dates.add(dt);
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
                    int year = fc.get(Calendar.YEAR);
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
                                        String dt = dddate.format(j);
                                        dates.add(dt);
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
                    int year = fc.get(Calendar.YEAR);
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
                                        String dt = dddate.format(j);
                                        dates.add(dt);
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
                    int year = fc.get(Calendar.YEAR);
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
                                        String dt = dddate.format(j);
                                        dates.add(dt);
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
                    int year = fc.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-08";
                        String ld = year + "-" + k + "-14";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        for (Date m = frmdt; m.compareTo(todt) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar fz = Calendar.getInstance();
                                    fz.setTime(j);
                                    int dayOfWeek = fz.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                        String dt = dddate.format(j);
                                        dates.add(dt);
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String fd1 = year + "-" + k + "-22";
                        String ld1 = year + "-" + k + "-28";
                        Date wkfstdt1 = yydate.parse(fd1);
                        Date wklstdt1 = yydate.parse(ld1);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        for (Date n = frmdt; n.compareTo(todt) <= 0; ) {
                            if (n.compareTo(wkfstdt1) >= 0 && n.compareTo(wklstdt1) <= 0) {
                                for (Date j = n; j.compareTo(wklstdt1) <= 0; ) {
                                    Calendar fz = Calendar.getInstance();
                                    fz.setTime(j);
                                    int dayOfWeek = fz.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && n.compareTo(wkfstdt1) >= 0 && n.compareTo(wklstdt1) <= 0) {
                                        String dt = dddate.format(j);
                                        dates.add(dt);
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            n.setTime(n.getTime() + 1000 * 60 * 60 * 24);
                        }
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
                    int year = fc.get(Calendar.YEAR);
                    // System.out.println("year " + year);
                    for (Date e = yydate.parse(fromDate); e.compareTo(yydate.parse(toDate)) <= 0; ) {
                        String fd = year + "-" + k + "-01";
                        String ld = year + "-" + k + "-07";
                        Date wkfstdt = yydate.parse(fd);
                        Date wklstdt = yydate.parse(ld);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        for (Date m = frmdt; m.compareTo(todt) <= 0; ) {
                            if (m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                for (Date j = m; j.compareTo(wklstdt) <= 0; ) {
                                    Calendar fz = Calendar.getInstance();
                                    fz.setTime(j);
                                    int dayOfWeek = fz.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && m.compareTo(wkfstdt) >= 0 && m.compareTo(wklstdt) <= 0) {
                                        String dt = dddate.format(j);
                                        dates.add(dt);
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            m.setTime(m.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String fd1 = year + "-" + k + "-15";
                        String ld1 = year + "-" + k + "-21";
                        Date wkfstdt1 = yydate.parse(fd1);
                        Date wklstdt1 = yydate.parse(ld1);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        for (Date n = frmdt; n.compareTo(todt) <= 0; ) {
                            if (n.compareTo(wkfstdt1) >= 0 && n.compareTo(wklstdt1) <= 0) {
                                for (Date j = n; j.compareTo(wklstdt1) <= 0; ) {
                                    Calendar fz = Calendar.getInstance();
                                    fz.setTime(j);
                                    int dayOfWeek = fz.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && n.compareTo(wkfstdt1) >= 0 && n.compareTo(wklstdt1) <= 0) {
                                        String dt = dddate.format(j);
                                        dates.add(dt);
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            n.setTime(n.getTime() + 1000 * 60 * 60 * 24);
                        }
                        String fd3 = year + "-" + k + "-29";
                        String ld3 = year + "-" + (k + 1) + "-0";
                        Date wkfstdt3 = yydate.parse(fd3);
                        Date wklstdt3 = yydate.parse(ld3);
                        frmdt = yydate.parse(fromDate);
                        todt = yydate.parse(toDate);
                        for (Date o = frmdt; o.compareTo(todt) <= 0; ) {
                            if (o.compareTo(wkfstdt3) >= 0 && o.compareTo(wklstdt3) <= 0) {
                                for (Date j = o; j.compareTo(wklstdt3) <= 0; ) {
                                    Calendar fz = Calendar.getInstance();
                                    fz.setTime(j);
                                    int dayOfWeek = fz.get(Calendar.DAY_OF_WEEK) - 1;
                                    if (dayOfWeek == Integer.parseInt(weeklyList.get(i).getWoDay()) && o.compareTo(wkfstdt3) >= 0 && o.compareTo(wklstdt3) <= 0) {
                                        String dt = dddate.format(j);
                                        dates.add(dt);
                                    }
                                    j.setTime(j.getTime() + 1000 * 60 * 60 * 24);
                                }
                            }
                            o.setTime(o.getTime() + 1000 * 60 * 60 * 24);
                        }
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
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dates;
}


}