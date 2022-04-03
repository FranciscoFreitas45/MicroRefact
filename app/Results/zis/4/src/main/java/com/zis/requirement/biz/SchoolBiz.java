package com.zis.requirement.biz;
 import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.zis.api.response.DepartmentQueryData;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.repository.DepartmentInfoDao;
@Service
public class SchoolBiz {

 private  Logger logger;

@Autowired
 private  DepartmentInfoDao departmentInfoDao;


public List<DepartmentQueryData> findByCollegeListGroupByCollegeOrderByCollege(List<String> collegeList){
    return departmentInfoDao.findByCollegeListGroupByCollegeOrderByCollege(collegeList);
}


public List<Departmentinfo> findByCollegeInstituteAndPartName(String college,String institute,String partName){
    return departmentInfoDao.findByCollegeInstituteAndPartName(college, institute, partName);
}


public List<DepartmentQueryData> findByCollegeAndInstituteGroupByPartNameOrderByPartName(String college,String institute){
    return departmentInfoDao.findByCollegeAndInstituteGroupByPartNameOrderByPartName(college, institute);
}


public int ifDepartmentInfoExist(Departmentinfo dmi){
    // Departmentinfo di = new Departmentinfo();
    // di.setCollege(dmi.getCollege());
    // di.setInstitute(dmi.getInstitute());
    // di.setPartName(dmi.getPartName());
    List<Departmentinfo> list = departmentInfoDao.findByCollegeInstituteAndPartName(dmi.getCollege(), dmi.getInstitute(), dmi.getPartName());
    logger.info("requirement.biz.AddSchoolBiz.existSchool--院校信息已存在");
    if (list.size() > 0) {
        return list.get(0).getDid();
    } else {
        return 0;
    }
}


public List<Departmentinfo> getAllDepartmentInfo(){
    // DetachedCriteria dc =
    // DetachedCriteria.forClass(Departmentinfo.class);
    // dc.addOrder(Order.asc("college")).addOrder(Order.asc("institute"))
    // .addOrder(Order.asc("partName"));
    List<Departmentinfo> list = departmentInfoDao.findOrderByCollegeInstitutePartNameAsc();
    logger.info("requirement.biz.GetInfoBiz.getAllInfo--查找院校信息成功");
    return list;
}


public Page<Departmentinfo> findAll(Pageable page){
    return this.departmentInfoDao.findAll(page);
}


public Page<Departmentinfo> findAllBySpecPage(Specification<Departmentinfo> spec,Pageable page){
    return this.departmentInfoDao.findAll(spec, page);
}


public void addDepartmentInfo(Departmentinfo dmi){
    // Departmentinfo ex = new Departmentinfo();
    // BeanUtils.copyProperties(dmi, ex);
    // ex.setGmtCreate(null);
    // ex.setGmtModify(null);
    List<Departmentinfo> list = this.departmentInfoDao.findByCollegeInstitutePartNameAndYears(dmi.getCollege(), dmi.getInstitute(), dmi.getPartName(), dmi.getYears());
    if (!list.isEmpty()) {
        String fmt = "重复添加院校信息, %s, %s, %s";
        throw new RuntimeException(String.format(fmt, dmi.getCollege(), dmi.getInstitute(), dmi.getPartName()));
    }
    departmentInfoDao.save(dmi);
}


public Departmentinfo findDepartmentInfoById(Integer id){
    Departmentinfo di = departmentInfoDao.findOne(id);
    return di;
}


public List<DepartmentQueryData> findByCollegeGroupByInstituteOrderByInstitute(String college){
    return departmentInfoDao.findByCollegeGroupByInstituteOrderByInstitute(college);
}


public void updateDepartmentInfo(Departmentinfo dmi){
    departmentInfoDao.save(dmi);
}


}