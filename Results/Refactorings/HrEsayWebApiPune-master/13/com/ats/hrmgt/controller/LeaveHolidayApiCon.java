import com.ats.hrmgt.model;
import com.ats.hrmgt.repo.HolidayListCatWiseRepo;
import com.ats.hrmgt.repo.HolidayMasterRepo;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@RestController
public class LeaveHolidayApiCon {

@Autowired
 private HolidayRepo holidayRepo;

@Autowired
 private GetHolidayRepo getHolidayRepo;

@Autowired
 private LeaveAuthorityRepository leaveAuthorityRepository;

@Autowired
 private CalculateYearRepository calculateYearRepository;

@Autowired
 private LocationRepository locationRepository;

@Autowired
 private HolidayMasterRepo holidayMasterRepo;

@Autowired
 private HolidayListCatWiseRepo holidayListCatWiseRepo;


@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.GET)
@ResponseBody
public List<LeaveAuthority> getLeaveAuthorityList(){
    List<LeaveAuthority> list = new ArrayList<LeaveAuthority>();
    try {
        list = leaveAuthorityRepository.findByDelStatus(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getHolidayByYearIdAndCateId" }, method = RequestMethod.POST)
@ResponseBody
public List<Holiday> getHolidayByYearIdAndCateId(int yearId,int catId){
    List<Holiday> holiday = new ArrayList<>();
    try {
        holiday = holidayRepo.getHolidayByYearIdAndCateId(yearId, catId);
    /*
             * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
             * )));
             * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    return holiday;
}


@RequestMapping(value = { "/deleteHolidayByGroup" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteHolidayByGroup(int catid,int yearId){
    Info info = new Info();
    try {
        int delete = holidayRepo.deleteHolidayByGroup(yearId, catid);
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


@RequestMapping(value = { "/deleteHolidayMaster" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteHolidayMaster(int holidayId){
    Info info = new Info();
    try {
        int delete = holidayMasterRepo.deleteHoliday(holidayId);
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


@RequestMapping(value = { "/saveHoliday" }, method = RequestMethod.POST)
@ResponseBody
public Holiday saveHoliday(Holiday holiday){
    Holiday save = new Holiday();
    try {
        save = holidayRepo.saveAndFlush(holiday);
        if (save != null) {
            save.setError(false);
        } else {
            save = new Holiday();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new Holiday();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getcountofholidaybyyear" }, method = RequestMethod.POST)
@ResponseBody
public Info getcountofholidaybyyear(int yearId,int catId){
    Info info = new Info();
    try {
        String count = holidayMasterRepo.getcountofholidaybyyear(catId, yearId);
        info.setMsg(count);
        info.setError(!count.equals("0"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/saveHolidayList" }, method = RequestMethod.POST)
@ResponseBody
public Info saveHolidayList(List<Holiday> holiday){
    Info info = new Info();
    try {
        List<Holiday> save = holidayRepo.saveAll(holiday);
        info.setError(save == null);
    } catch (Exception e) {
        info.setError(true);
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getHolidayMasterById" }, method = RequestMethod.POST)
@ResponseBody
public HolidayMaster getHolidayMasterById(int holidayId){
    HolidayMaster holiday = new HolidayMaster();
    try {
        holiday = holidayMasterRepo.findByHolidayIdAndDelStatus(holidayId, 1);
    /*
             * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
             * )));
             * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    return holiday;
}


@RequestMapping(value = { "/getHolidayCategoryListGroupBy" }, method = RequestMethod.GET)
@ResponseBody
public List<HolidayListCatWise> getHolidayCategoryListGroupBy(){
    List<HolidayListCatWise> list = new ArrayList<HolidayListCatWise>();
    try {
        List<GetHoliday> getHolidaylist = getHolidayRepo.getHolidayListByCompany(1);
        list = holidayListCatWiseRepo.getHolidayCategoryListGroupBy();
        for (int i = 0; i < list.size(); i++) {
            List<GetHoliday> hollist = new ArrayList<>();
            for (int j = 0; j < getHolidaylist.size(); j++) {
                if (getHolidaylist.get(j).getCalYrId() == list.get(i).getCalYrId() && list.get(i).getCatId() == getHolidaylist.get(j).getHoCatId()) {
                    hollist.add(getHolidaylist.get(j));
                }
            }
            list.get(i).setHolidaylist(hollist);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "getLeaveAuthorityById" }, method = RequestMethod.POST)
@ResponseBody
public LeaveAuthority getLeaveAuthorityById(int laPkey){
    LeaveAuthority leaveAuthority = new LeaveAuthority();
    try {
        leaveAuthority = leaveAuthorityRepository.findByLaPkeyAndDelStatus(laPkey, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveAuthority;
}


@RequestMapping(value = { "/getHolidayMaster" }, method = RequestMethod.GET)
@ResponseBody
public List<HolidayMaster> getHolidayMaster(){
    List<HolidayMaster> list = new ArrayList<>();
    try {
        list = holidayMasterRepo.findByDelStatusOrder(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLeaveAuthorityListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public LeaveAuthority getLeaveAuthorityListByEmpId(int empId){
    LeaveAuthority list = new LeaveAuthority();
    try {
        list = leaveAuthorityRepository.findByDelStatusAndEmpId(1, empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/deleteHoliday" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteHoliday(int holidayId){
    Info info = new Info();
    try {
        int delete = holidayRepo.deleteHoliday(holidayId);
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


@RequestMapping(value = { "/getHolidayList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetHoliday> getHolidayList(int companyId){
    List<GetHoliday> list = new ArrayList<GetHoliday>();
    try {
        list = getHolidayRepo.getHolidayListByCompany(companyId);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> locIds = Stream.of(list.get(i).getLocId().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<Location> locList = new ArrayList<>();
            locList = locationRepository.findByDelStatusAndIsActiveAndLocIdIn(1, 1, locIds);
            String locName = "";
            int x = 0;
            for (int j = 0; j < locList.size(); j++) {
                locName = locList.get(j).getLocName() + "," + locName;
                if (locList.size() > 1)
                    x = 1;
            }
            if (x == 1)
                list.get(i).setLocName(locName.substring(0, locName.length() - 1));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getHolidayListByDates" }, method = RequestMethod.POST)
@ResponseBody
public List<Holiday> getHolidayListByDates(List<String> dates,int holcatId){
    List<Holiday> list = new ArrayList<Holiday>();
    try {
        list = holidayRepo.getHolidayListByDates(dates, holcatId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveHolidayMaster" }, method = RequestMethod.POST)
@ResponseBody
public HolidayMaster saveHolidayMaster(HolidayMaster holiday){
    HolidayMaster save = new HolidayMaster();
    try {
        save = holidayMasterRepo.saveAndFlush(holiday);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/deleteLeaveAuthority" }, method = RequestMethod.POST)
@ResponseBody
public Info deleteLeaveAuthority(int laPkey){
    Info info = new Info();
    try {
        int delete = leaveAuthorityRepository.deleteLeaveAuthority(laPkey);
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


@RequestMapping(value = { "/getHolidayCountsByDate" }, method = RequestMethod.POST)
@ResponseBody
public Integer getHolidayCountsByDate(int holidayId,String holidaytDate){
    int holidayCount = 0;
    try {
        if (holidayId < 1) {
            holidayCount = holidayMasterRepo.getCountOfHolidayByDate(holidaytDate);
        } else {
            holidayCount = holidayMasterRepo.getCountOfHolidayByDateForEdit(holidaytDate, holidayId);
        }
    } catch (Exception e) {
        e.printStackTrace();
        holidayCount = 1;
    }
    System.err.println("holidayCount " + holidayCount);
    return holidayCount;
}


@RequestMapping(value = { "/getHolidayById" }, method = RequestMethod.POST)
@ResponseBody
public Holiday getHolidayById(int holidayId){
    Holiday holiday = new Holiday();
    try {
        holiday = holidayRepo.findByHolidayIdAndDelStatus(holidayId, 1);
    /*
             * holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt(
             * )));
             * holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));
             */
    } catch (Exception e) {
        e.printStackTrace();
    }
    return holiday;
}


@RequestMapping(value = { "/saveLeaveAuthority" }, method = RequestMethod.POST)
@ResponseBody
public LeaveAuthority saveLeaveAuthority(LeaveAuthority leavesAllotment){
    LeaveAuthority save = new LeaveAuthority();
    try {
        save = leaveAuthorityRepository.saveAndFlush(leavesAllotment);
        if (save != null) {
            save.setError(false);
        } else {
            save = new LeaveAuthority();
            save.setError(true);
        }
    } catch (Exception e) {
        save = new LeaveAuthority();
        save.setError(true);
        e.printStackTrace();
    }
    return save;
}


}