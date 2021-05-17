import javax.persistence;
import java.util.List;
@Entity
public class HolidayListCatWise {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "cal_yr_id")
 private  int calYrId;

@Column(name = "cat_id")
 private  int catId;

@Column(name = "ho_cat_name")
 private  String hoCatName;

@Column(name = "optional_holiday")
 private  int optionalHoliday;

@Column(name = "na_count")
 private  int naCount;

@Column(name = "fixed_count")
 private  int fixedCount;

@Column(name = "optional_count")
 private  int optionalCount;

@Column(name = "year_date")
 private  String yearDate;

@Transient
 private List<GetHoliday> holidaylist;


public List<GetHoliday> getHolidaylist(){
    return holidaylist;
}


public void setCatId(int catId){
    this.catId = catId;
}


public int getNaCount(){
    return naCount;
}


public void setOptionalHoliday(int optionalHoliday){
    this.optionalHoliday = optionalHoliday;
}


public int getCatId(){
    return catId;
}


public void setHoCatName(String hoCatName){
    this.hoCatName = hoCatName;
}


public String getHoCatName(){
    return hoCatName;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public String getId(){
    return id;
}


public String getYearDate(){
    return yearDate;
}


public void setNaCount(int naCount){
    this.naCount = naCount;
}


public void setYearDate(String yearDate){
    this.yearDate = yearDate;
}


public int getFixedCount(){
    return fixedCount;
}


public void setHolidaylist(List<GetHoliday> holidaylist){
    this.holidaylist = holidaylist;
}


public int getCalYrId(){
    return calYrId;
}


public void setOptionalCount(int optionalCount){
    this.optionalCount = optionalCount;
}


public void setFixedCount(int fixedCount){
    this.fixedCount = fixedCount;
}


public int getOptionalCount(){
    return optionalCount;
}


public void setId(String id){
    this.id = id;
}


public int getOptionalHoliday(){
    return optionalHoliday;
}


@Override
public String toString(){
    return "HolidayListCatWise [id=" + id + ", calYrId=" + calYrId + ", catId=" + catId + ", hoCatName=" + hoCatName + ", optionalHoliday=" + optionalHoliday + ", naCount=" + naCount + ", fixedCount=" + fixedCount + ", optionalCount=" + optionalCount + ", yearDate=" + yearDate + ", holidaylist=" + holidaylist + "]";
}


}