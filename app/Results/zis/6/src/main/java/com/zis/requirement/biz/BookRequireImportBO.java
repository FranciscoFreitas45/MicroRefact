package com.zis.requirement.biz;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.BookRequireImportDetail;
import com.zis.requirement.bean.BookRequireImportDetailStatus;
import com.zis.requirement.bean.BookRequireImportTask;
import com.zis.requirement.bean.BookRequireImportTaskStatus;
import com.zis.requirement.dto.BookRequireUploadDTO;
import com.zis.requirement.repository.BookRequireImportDetailDao;
import com.zis.requirement.repository.BookRequireImportTaskDao;
import com.zis.Interface.BookService;
@Component
public class BookRequireImportBO {

@Autowired
 private  BookRequireImportTaskDao bookRequireImportTaskDao;

@Autowired
 private  BookRequireImportDetailDao bookRequireImportDetailDao;

@Autowired
 private  BookService bookService;


public Page<BookRequireImportDetail> findAllBookRequireImportDetailBySpecPage(Specification<BookRequireImportDetail> spec,Pageable page){
    return this.bookRequireImportDetailDao.findAll(spec, page);
}


public void doMatchBook(BookRequireImportDetail detail,Integer bookId){
    detail.setBookid(bookId);
    detail.setStatus(BookRequireImportDetailStatus.DEPARTMENT_NOT_MATCHED);
    detail.setGmtModify(ZisUtils.getTS());
    this.bookRequireImportDetailDao.save(detail);
}


public Page<BookRequireImportDetail> findAllBookRequireImportDetail(Pageable page){
    return this.bookRequireImportDetailDao.findAll(page);
}


public void saveTempBookRequireImportDetails(List<BookRequireUploadDTO> list,String college,String operator,String memo){
    if (list == null || list.isEmpty()) {
        throw new RuntimeException("导入失败，list不能为空");
    }
    if (StringUtils.isBlank(college)) {
        throw new RuntimeException("导入失败，学校不能为空");
    }
    if (StringUtils.isBlank(operator)) {
        throw new RuntimeException("导入失败，操作员不能为空");
    }
    if (StringUtils.isBlank(memo)) {
        throw new RuntimeException("导入失败，操作备注不能为空");
    }
    // 新增TempBookRequireImport记录
    BookRequireImportTask record = saveTempBookRequireImport(operator, college, memo, list.size());
    // 批量保存明细
    List<BookRequireImportDetail> detailList = new ArrayList<BookRequireImportDetail>();
    for (BookRequireUploadDTO dto : list) {
        BookRequireImportDetail detail = new BookRequireImportDetail();
        BeanUtils.copyProperties(dto, detail);
        detail.setBatchId(record.getId());
        detail.setStatus(BookRequireImportDetailStatus.BOOK_NOT_MATCHED);
        detail.setGmtCreate(ZisUtils.getTS());
        detail.setGmtModify(ZisUtils.getTS());
        this.bookRequireImportDetailDao.save(detail);
        detailList.add(detail);
    }
    // 匹配图书
    doMatchBook(detailList);
// 匹配专业
// doMatchDepartment(record.getId());
}


public BookRequireImportTask saveTempBookRequireImport(String operator,String college,String memo,int totalCount){
    BookRequireImportTask record = new BookRequireImportTask();
    record.setCollege(college);
    record.setMemo(memo);
    record.setOperator(operator);
    record.setStatus(BookRequireImportTaskStatus.NOT_MATCHED);
    record.setTotalCount(totalCount);
    record.setGmtCreate(ZisUtils.getTS());
    record.setGmtModify(ZisUtils.getTS());
    this.bookRequireImportTaskDao.save(record);
    return record;
}


public Page<BookRequireImportTask> findAllBookRequireImportTask(Pageable page){
    return this.bookRequireImportTaskDao.findAll(page);
}


public Page<BookRequireImportTask> findAllBookRequireImportTaskBySpecPage(Specification<BookRequireImportTask> spec,Pageable page){
    return this.bookRequireImportTaskDao.findAll(spec, page);
}


}