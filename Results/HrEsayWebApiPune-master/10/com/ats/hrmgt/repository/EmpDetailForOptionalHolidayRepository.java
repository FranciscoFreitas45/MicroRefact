import com.ats.hrmgt.model.EmpDetailForOptionalHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmpDetailForOptionalHolidayRepository extends JpaRepository<EmpDetailForOptionalHoliday, Integer> {


@Query(value = "SELECT\n" + "        emp.emp_id,\n" + "        emp.emp_code,\n" + "        concat(emp.first_name,' ',emp.surname) as emp_name,\n" + "        emp.holiday_category,\n" + "        emp.weekend_category,\n" + "        dep.name_sd AS dept_name,\n" + "        dg.name_sd AS emp_desgn,\n" + "        loc.loc_name_short as  loc_name, \n" + "        wocat.wo_cat_name as wo_cat_name,\n" + "        holidaycat.ho_cat_name as ho_cat_name,\n" + "        ifnull((holidaycat.ex_int1),0) as optional_holiday,ifnull((select\n" + "            count('') as count \n" + "        from\n" + "            t_optional_holiday \n" + "        where\n" + "            emp_id=emp.emp_id \n" + "            and year_id=:yearId and status=0),\n" + "        0) as apply_ho,\n" + "        ifnull((select count('') as count from t_optional_holiday where emp_id=emp.emp_id and year_id=:yearId and status=1),0) as used_ho\n" + "    FROM\n" + "        m_employees emp \n" + "     \n" + "    LEFT JOIN\n" + "        m_designation dg \n" + "            ON     emp.designation_id = dg.desig_id \n" + "    LEFT JOIN\n" + "        m_department dep \n" + "            ON     emp.depart_id = dep.depart_id \n" + "    LEFT JOIN\n" + "        m_location loc \n" + "            ON     emp.location_id = loc.loc_id  \n" + "    LEFT JOIN\n" + "        weekoff_category wocat \n" + "            ON     wocat.wo_cat_id = emp.weekend_category \n" + "    LEFT JOIN\n" + "        holiday_category holidaycat \n" + "            ON     holidaycat.ho_cat_id = emp.holiday_category \n" + "    WHERE\n" + "        emp.del_status = 1 and emp.emp_id=:empId", nativeQuery = true)
public EmpDetailForOptionalHoliday getEmpInfoForOptionalHoliday(int empId,int yearId)


}