package com.zis.requirement.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.api.response.DepartmentQueryData;
import com.zis.requirement.bean.Departmentinfo;
public interface DepartmentInfoDao extends PagingAndSortingRepository<Departmentinfo, Integer>, JpaSpecificationExecutor<Departmentinfo>{


@Query(value = "from Departmentinfo where college = :college and institute = :institute and partName = :partName and years = :years")
public List<Departmentinfo> findByCollegeInstitutePartNameAndYears(String college,String institute,String partName,Integer years)
;

@Query(value = "SELECT new com.zis.api.response.DepartmentQueryData(did, college) FROM Departmentinfo WHERE college IN (:collegeList) GROUP BY college ORDER BY college ASC")
public List<DepartmentQueryData> findByCollegeListGroupByCollegeOrderByCollege(List<String> collegeList)
;

@Query(value = "from Departmentinfo where college = :college and institute = :institute and partName = :partName")
public List<Departmentinfo> findByCollegeInstituteAndPartName(String college,String institute,String partName)
;

@Query(value = "SELECT new com.zis.api.response.DepartmentQueryData(did, partName) FROM Departmentinfo WHERE college = :college and institute = :institute GROUP BY partName ORDER BY partName ASC")
public List<DepartmentQueryData> findByCollegeAndInstituteGroupByPartNameOrderByPartName(String college,String institute)
;

@Query(value = "from Departmentinfo order by college, institute, partName asc")
public List<Departmentinfo> findOrderByCollegeInstitutePartNameAsc()
;

@Query(value = "from Departmentinfo where college IN(:collegeList) order by college, institute, partName asc")
public List<Departmentinfo> findByCollegeListOrderByCollegeInstitutePartNameAsc(List<String> collegeList)
;

@Query(value = "SELECT new com.zis.api.response.DepartmentQueryData(did, institute) FROM Departmentinfo WHERE college = :college GROUP BY institute ORDER BY institute ASC")
public List<DepartmentQueryData> findByCollegeGroupByInstituteOrderByInstitute(String college)
;

}