package com.zis.requirement.biz;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.bookinfo.repository.BookInfoDao;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.dto.AddBookToDepartmentResult;
import com.zis.requirement.dto.BookAmountAddApiRequestDTO;
import com.zis.requirement.dto.RequirementCollectScheduleDTO;
import com.zis.requirement.repository.BookAmountDao;
import com.zis.requirement.repository.DepartmentInfoDao;
import com.zis.Interface.BookInfoDao;
@Service
public class BookAmountService {

 private  Logger logger;

@Autowired
 private  BookAmountDao bookAmountDao;

@Autowired
 private  BookInfoDao bookinfoDao;

@Autowired
 private  DepartmentInfoDao departmentInfoDao;


public List<RequirementCollectScheduleDTO> findRequirementCollectSchedule(boolean groupByOperator){
    // 查询所有采集过数据的 学校
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookamount.class);
    // dc.setProjection(Projections.distinct(Projections.property("college")));
    List<String> collegeList = this.bookAmountDao.distinctCollege();
    // 列出数据涉及到的专业
    // DetachedCriteria dcDepartmentInfo = DetachedCriteria
    // .forClass(Departmentinfo.class);
    // dcDepartmentInfo.add(Restrictions.in("college", collegeList));
    // dcDepartmentInfo.addOrder(Order.asc("college"))
    // .addOrder(Order.asc("institute"))
    // .addOrder(Order.asc("partName"));
    List<Departmentinfo> departmentInfos = this.departmentInfoDao.findByCollegeListOrderByCollegeInstitutePartNameAsc(collegeList);
    // 将所有专业、学年、学期记录下来，供采集信息查漏用
    List<String> departNotDeal = new ArrayList<String>();
    for (Departmentinfo depart : departmentInfos) {
        for (int grade = 1; grade <= depart.getYears(); grade++) {
            departNotDeal.add(getRequirementCollectKey(depart.getDid(), grade, 1));
            departNotDeal.add(getRequirementCollectKey(depart.getDid(), grade, 2));
        }
    }
    // 对已采集的数据归类汇总
    // ProjectionList projectionList = Projections.projectionList();
    // projectionList.add(Projections.rowCount());
    // projectionList.add(Projections.groupProperty("college"));
    // projectionList.add(Projections.groupProperty("institute"));
    // projectionList.add(Projections.groupProperty("partName"));
    // projectionList.add(Projections.groupProperty("partId"));
    // projectionList.add(Projections.groupProperty("grade"));
    // projectionList.add(Projections.groupProperty("term"));
    // if (groupByOperator) {
    // projectionList.add(Projections.groupProperty("operator"));
    // }
    // // select c1, c2, c3, count(*) from XX group by c1, c2, c3;
    // DetachedCriteria dcGetGroupCount = DetachedCriteria
    // .forClass(Bookamount.class);
    // dcGetGroupCount.setProjection(projectionList);
    List<RequirementCollectScheduleDTO> countList;
    if (groupByOperator) {
        countList = this.bookAmountDao.countGroupByCollegeInseitutePartNamePartIdGradeTermOperator();
    } else {
        countList = this.bookAmountDao.countGroupByCollegeInseitutePartNamePartIdGradeTerm();
    }
    // 遍历统计出的数据
    List<RequirementCollectScheduleDTO> resultList = new ArrayList<RequirementCollectScheduleDTO>();
    for (RequirementCollectScheduleDTO req : countList) {
        RequirementCollectScheduleDTO collectDTO = new RequirementCollectScheduleDTO();
        collectDTO.setCount(req.getCount());
        collectDTO.setCollege(req.getCollege());
        collectDTO.setInstitute(req.getInstitute());
        collectDTO.setPartName(req.getPartName());
        collectDTO.setPartId(req.getPartId());
        collectDTO.setGrade(req.getGrade());
        collectDTO.setTerm(req.getTerm());
        if (groupByOperator) {
            collectDTO.setOperator(req.getOperator());
        }
        // 数据添加到结果集中
        resultList.add(collectDTO);
        // 把这个专业和学期的记录从departNotDeal中移除
        String key = getRequirementCollectKey(collectDTO.getPartId(), collectDTO.getGrade(), collectDTO.getTerm());
        if (departNotDeal.contains(key)) {
            departNotDeal.remove(key);
        }
    }
    // for (Object record : countList) {
    // Object[] dataArr = (Object[]) record;
    // RequirementCollectScheduleDTO collectDTO = new
    // RequirementCollectScheduleDTO();
    // collectDTO.setCount(Integer.valueOf(dataArr[0].toString()));
    // collectDTO.setCollege(dataArr[1].toString());
    // collectDTO.setInstitute(dataArr[2].toString());
    // collectDTO.setPartName(dataArr[3].toString());
    // collectDTO.setPartId(Integer.valueOf(dataArr[4].toString()));
    // collectDTO.setGrade(Integer.valueOf(dataArr[5].toString()));
    // collectDTO.setTerm(Integer.valueOf(dataArr[6].toString()));
    // if (groupByOperator) {
    // collectDTO.setOperator(dataArr[7].toString());
    // }
    // // 数据添加到结果集中
    // resultList.add(collectDTO);
    // // 把这个专业和学期的记录从departNotDeal中移除
    // String key = getRequirementCollectKey(collectDTO.getPartId(),
    // collectDTO.getGrade(), collectDTO.getTerm());
    // if (departNotDeal.contains(key)) {
    // departNotDeal.remove(key);
    // }
    // }
    // 经过上一轮处理，departNotDeal中的记录都是未处理的，这些记录追加到结果集最后
    for (String notDeal : departNotDeal) {
        String[] elem = notDeal.split("_");
        Departmentinfo di = this.departmentInfoDao.findOne(Integer.valueOf(elem[0]));
        RequirementCollectScheduleDTO collectDTO = new RequirementCollectScheduleDTO();
        BeanUtils.copyProperties(di, collectDTO);
        collectDTO.setPartId(di.getDid());
        collectDTO.setGrade(Integer.valueOf(elem[1]));
        collectDTO.setTerm(Integer.valueOf(elem[2]));
        collectDTO.setOperator("未操作");
        collectDTO.setCount(0);
        resultList.add(collectDTO);
    }
    return resultList;
}


public String getRequirementCollectKey(Integer departId,Integer grade,Integer term){
    return departId + "_" + grade + "_" + term;
}


public void saveBookAmountList(List<BookAmount> list){
    for (BookAmount bookamount : list) {
        this.saveBookAmount(bookamount);
    }
}


public AddBookToDepartmentResult addSelectedBookToDwrSession(Integer bookId,Integer departId,Integer grade,Integer term,HttpSession session){
    if (bookId == null) {
        return new AddBookToDepartmentResult(false, "图书ID不能为空");
    }
    if (departId == null || departId <= 0) {
        return new AddBookToDepartmentResult(false, "专业必须选择");
    }
    if (grade == null || grade <= 0) {
        return new AddBookToDepartmentResult(false, "年级不能为空");
    }
    if (term == null || term <= 0) {
        return new AddBookToDepartmentResult(false, "学期不能为空");
    }
    // 从session中取得bookMap，如果没有，则创建
    @SuppressWarnings("unchecked")
    Map<Integer, Bookinfo> bookMap = (Map<Integer, Bookinfo>) session.getAttribute("BookMap");
    if (bookMap == null) {
        bookMap = new HashMap<Integer, Bookinfo>();
        session.setAttribute("BookMap", bookMap);
    }
    // 检查图书状态
    Bookinfo bi = this.bookinfoDao.findOne(bookId);
    if (bi == null || BookinfoStatus.DISCARD.equals(bi.getBookStatus())) {
        return new AddBookToDepartmentResult(false, "图书不存在或者已删除");
    }
    // 检查该图书是否已添加
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Bookamount.class);
    // criteria.add(Restrictions.eq("bookId", bookId));
    // criteria.add(Restrictions.eq("partId", departId));
    // criteria.add(Restrictions.eq("grade", grade));
    // criteria.add(Restrictions.eq("term", term));
    List<BookAmount> baList = this.bookAmountDao.findByBookIdPartIdGradeAndTerm(bookId, departId, grade, term);
    if (baList != null && !baList.isEmpty()) {
        return new AddBookToDepartmentResult(false, "该专业已使用该图书，无需再次录入");
    }
    // 将书添加到bookMap中
    bookMap.put(bookId, bi);
    // 组装结果
    return buildSuccessAddBookToDepartmentResult(bookMap);
}


public String updateForImmigrateBookRequirement(Integer bookIdFrom,Integer bookIdTo){
    if (bookIdFrom.equals(bookIdTo)) {
        return "迁移后的对象与原对象相同";
    }
    List<BookAmount> list = this.bookAmountDao.findByBookId(bookIdFrom);
    if (list == null || list.isEmpty()) {
        return "没有查到" + bookIdFrom + "对应的记录";
    }
    Bookinfo bookFrom = this.bookinfoDao.findOne(bookIdFrom);
    Bookinfo bookTo = this.bookinfoDao.findOne(bookIdTo);
    if (bookFrom == null || bookTo == null) {
        return "图书对象不存在，请检查输入";
    }
    if (!bookFrom.getIsbn().equals(bookTo.getIsbn())) {
        return "图书不相符，迁移失败";
    }
    for (BookAmount record : list) {
        record.setBookId(bookIdTo);
        record.setGmtModify(ZisUtils.getTS());
        this.bookAmountDao.save(record);
    }
    return null;
}


public void saveBookAmount(BookAmount ba){
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Bookamount.class);
    // criteria.add(Restrictions.eq("bookId", ba.getBookId()));
    // criteria.add(Restrictions.eq("partId", ba.getPartId()));
    // criteria.add(Restrictions.eq("grade", ba.getGrade()));
    // criteria.add(Restrictions.eq("term", ba.getTerm()));
    List<BookAmount> list = this.bookAmountDao.findByBookIdPartIdGradeAndTerm(ba.getBookId(), ba.getPartId(), ba.getGrade(), ba.getTerm());
    // Bookamount ex = new Bookamount();
    // ex.setBookId(ba.getBookId());
    // ex.setPartId(ba.getPartId());
    // ex.setGrade(ba.getGrade());
    // ex.setTerm(ba.getTerm());
    // List<Bookamount> list = bookAmountDao.findByExample(ex);
    if (list != null && !list.isEmpty()) {
        Bookinfo book = this.bookinfoDao.findOne(ba.getBookId());
        throw new RuntimeException("专业" + ba.getPartId() + "已经登记过图书:" + book.getBookName() + ", bookId=" + ba.getBookId());
    }
    bookAmountDao.save(ba);
}


public void addBookAmount(BookAmountAddApiRequestDTO requestDTO){
    Departmentinfo di = departmentInfoDao.findOne(requestDTO.getDepartId());
    for (Integer bookId : requestDTO.getBookIdList()) {
        BookAmount ba = new BookAmount();
        BeanUtils.copyProperties(requestDTO, ba);
        ba.setBookId(bookId);
        ba.setCollege(di.getCollege());
        ba.setInstitute(di.getInstitute());
        ba.setPartName(di.getPartName());
        ba.setPartId(di.getDid());
        Bookinfo bi = bookinfoDao.findOne(bookId);
        if (bi == null) {
            throw new RuntimeException("bookInfo is null, for id=" + bookId);
        }
        BeanUtils.copyProperties(bi, ba);
        // copyProperties会把ID复制过来
        ba.setId(null);
        ba.setGmtCreate(ZisUtils.getTS());
        ba.setGmtModify(ZisUtils.getTS());
        this.saveBookAmount(ba);
    }
}


public Page<BookAmount> findBySpecPage(Specification<BookAmount> spec,Pageable page){
    return this.bookAmountDao.findAll(spec, page);
}


public AddBookToDepartmentResult buildSuccessAddBookToDepartmentResult(Map<Integer,Bookinfo> bookMap){
    AddBookToDepartmentResult result = new AddBookToDepartmentResult(true, "");
    for (Bookinfo book : bookMap.values()) {
        result.add(book);
    }
    return result;
}


public List<BookAmount> findBookAmountByBookId(Integer bookId){
    return this.bookAmountDao.findByBookId(bookId);
}


public Page<BookAmount> findAll(Pageable page){
    return this.bookAmountDao.findAll(page);
}


public AddBookToDepartmentResult removeSelectedBookToDwrSession(Integer bookId,HttpSession session){
    // 从session中取得bookMap，如果没有，直接返回
    @SuppressWarnings("unchecked")
    Map<Integer, Bookinfo> bookMap = (Map<Integer, Bookinfo>) session.getAttribute("BookMap");
    if (bookMap == null) {
        return new AddBookToDepartmentResult(false, "系统错误：session中没有已保存的图书记录");
    }
    // 从bookMap中移除记录
    if (bookMap.containsKey(bookId)) {
        bookMap.remove(bookId);
    }
    // 组装结果
    return buildSuccessAddBookToDepartmentResult(bookMap);
}


}