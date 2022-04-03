package com.zis.purchase.biz;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.TempImportDetail;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.bean.TempImportTaskStatus;
import com.zis.purchase.dto.TempImportDTO;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.purchase.repository.TempImportDetailDao;
import com.zis.purchase.repository.TempImportTaskDao;
import com.zis.Interface.BookService;
@Component
public class TempImportBO {

@Autowired
 private  TempImportTaskDao tempImportTaskDao;

@Autowired
 private  TempImportDetailDao tempImportDetailDao;

@Autowired
 private  BookService bookService;

 private  Logger logger;


public String updateAssociateTempImportDetailWithBookInfo(Integer tempImportDetailId,Integer bookId){
    TempImportDetail tmpRecord = this.tempImportDetailDao.findOne(tempImportDetailId);
    Bookinfo book = this.bookService.findBookById(bookId);
    return this.updateAssociatePurchaseTempImportWithBookInfo(tmpRecord, book);
}


public String updateAssociatePurchaseTempImportWithBookInfo(TempImportDetail detail,Bookinfo book){
    if (detail == null) {
        return "数据错误，PurchaseTempImport不能为空";
    }
    if (book == null) {
        return "数据错误，book不能为空";
    }
    if (!detail.getStatus().equals(TempImportDetailStatus.NOT_MATCHED)) {
        return "数据错误，进行关联的PurchaseTempImport状态必须是NOT_MATCHED, id=" + detail.getId();
    }
    if (!book.getBookStatus().equals(ConstantString.USEFUL)) {
        return "数据错误，图书状态必须是 正式, bookId=" + book.getId();
    }
    detail.setIsbn(book.getIsbn());
    detail.setBookId(book.getId());
    detail.setStatus(TempImportDetailStatus.MATCHED);
    detail.setGmtModify(ZisUtils.getTS());
    this.tempImportDetailDao.save(detail);
    logger.info("tempImportDetail associate with bookInfo, detailId=" + detail.getId());
    // 如果本次待处理的全部搞定，则修改TaskStatus
    updateTaskIfFullyMatched(detail.getTaskId());
    return null;
}


public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus){
    if (taskId == null || StringUtils.isBlank(tempImportDetailStatus)) {
        throw new RuntimeException("illegal arguments, for input null");
    }
    // 查找记录
    List<TempImportDetail> list = this.tempImportDetailDao.findByTaskIdAndStatus(taskId, tempImportDetailStatus);
    // 转换结果
    List<TempImportDetailView> resultList = new ArrayList<TempImportDetailView>();
    for (TempImportDetail detail : list) {
        TempImportDetailView view = new TempImportDetailView();
        BeanUtils.copyProperties(detail, view);
        // 未匹配成功的，查找出可能相关的记录
        if (detail.getStatus().equals(TempImportDetailStatus.NOT_MATCHED)) {
            List<Bookinfo> relatedBooks = this.bookService.findBookByISBN(detail.getOrigIsbn());
            view.setRelatedBooks(relatedBooks);
            view.setIsbn(detail.getOrigIsbn());
        } else // 匹配成功的，查找出匹配的记录
        if (detail.getStatus().equals(TempImportDetailStatus.MATCHED)) {
            Bookinfo book = this.bookService.findBookById(detail.getBookId());
            if (book == null) {
                throw new RuntimeException("图书记录不存在,bookId=" + detail.getBookId());
            }
            view.setAssociateBook(book);
        } else {
            throw new RuntimeException("临时导入记录状态不正确, id=" + detail.getId());
        }
        resultList.add(view);
    }
    return resultList;
}


public void updateTaskIfFullyMatched(Integer taskId){
    List<TempImportDetail> notMatched = tempImportDetailDao.findByTaskIdAndStatus(taskId, TempImportDetailStatus.NOT_MATCHED);
    if (notMatched == null || notMatched.isEmpty()) {
        TempImportTask task = this.tempImportTaskDao.findOne(taskId);
        if (task == null) {
            throw new RuntimeException("TempImportTask could not be null, taskId=" + taskId);
        }
        task.setStatus(TempImportTaskStatus.FULLY_MATCHED);
        task.setGmtModify(ZisUtils.getTS());
        this.tempImportTaskDao.save(task);
        logger.info("tempImportTask fully matched, taskId=" + taskId);
    }
}


public void addTempImportTask(List<TempImportDTO> list,TempImportTaskBizTypeEnum bizType,String memo){
    TempImportTask task = new TempImportTask();
    task.setBizType(bizType.getValue());
    task.setMemo(memo);
    task.setStatus(TempImportTaskStatus.IMPORT_COMPLETE);
    task.setTotalCount(list.size());
    task.setGmtCreate(ZisUtils.getTS());
    task.setGmtModify(ZisUtils.getTS());
    this.tempImportTaskDao.save(task);
    for (TempImportDTO tmp : list) {
        TempImportDetail detail = new TempImportDetail();
        detail.setData(tmp.getData());
        detail.setTaskId(task.getId());
        detail.setOrigIsbn(tmp.getIsbn());
        detail.setStatus(TempImportDetailStatus.NOT_MATCHED);
        detail.setAdditionalInfo(tmp.getAdditionalInfo());
        detail.setGmtCreate(ZisUtils.getTS());
        detail.setGmtModify(ZisUtils.getTS());
        this.tempImportDetailDao.save(detail);
    }
    logger.info("save tempImportTask, id=" + task.getId());
    // 批量关联到bookinfo
    this.updateTempImportDetail(task.getId());
}


public void updateTempImportTaskStatus(Integer taskId,Integer tempImportTaskStatus){
    if (taskId == null) {
        throw new RuntimeException("参数taskId不能为空");
    }
    TempImportTask task = this.tempImportTaskDao.findOne(taskId);
    if (task == null) {
        throw new RuntimeException("tempImportTask不能为空,taskId=" + taskId);
    }
    // 更新到操作成功
    if (TempImportTaskStatus.SUCCESS.equals(tempImportTaskStatus)) {
        // task当前状态必须是FULLY_MATCHED
        if (task.getStatus() != TempImportTaskStatus.FULLY_MATCHED) {
            throw new RuntimeException("更新TempImportTaskStatus失败，SUCCESS的前提必须是FULLY_MATCHED, taskId=" + taskId);
        }
    } else // 更新到取消操作
    if (TempImportTaskStatus.CANCEL == tempImportTaskStatus) {
        // task当前状态必须是FULLY_MATCHED或者IMPORT_COMPLETE
        if (task.getStatus() != TempImportTaskStatus.FULLY_MATCHED && task.getStatus() != TempImportTaskStatus.IMPORT_COMPLETE) {
            throw new RuntimeException("更新TempImportTaskStatus失败，CANCEL的前提必须是FULLY_MATCHED或者IMPORT_COMPLETE, taskId=" + taskId);
        }
    }
    task.setStatus(tempImportTaskStatus);
    task.setGmtModify(ZisUtils.getTS());
    this.tempImportTaskDao.save(task);
}


public TempImportTask findTempImportTaskByTaskId(Integer taskId){
    return this.tempImportTaskDao.findOne(taskId);
}


public Page<TempImportTask> findAllTempImportTask(Pageable page){
    // DetachedCriteria criteria = DetachedCriteria
    // .forClass(TempImportTask.class);
    // criteria.add(Restrictions.ne("status", TempImportTaskStatus.CANCEL));
    // criteria.addOrder(Order.asc("status").desc("gmtCreate"));
    // return this.tempImportTaskDao.findByCriteria(criteria);
    // Sort sort = new Sort(Direction.ASC, "status").and(new
    // Sort(Direction.DESC, "gmtCreate"));
    // Pageable page = new PageRequest(0, 20, sort);
    return tempImportTaskDao.findAllTempImportTask(page);
}


public void updateTempImportDetail(Integer taskId){
    // 1. 查找出未匹配图书的记录
    List<TempImportDetail> list = tempImportDetailDao.findByTaskIdAndStatus(taskId, TempImportDetailStatus.NOT_MATCHED);
    for (TempImportDetail record : list) {
        // 2. 如果isbn = null，则先填写isbn
        if (StringUtils.isBlank(record.getIsbn())) {
            // 2.1 isbn=null且origIsbn包含分隔符，则检查分隔符后面的bookId是否和系统匹配
            final String SPLIT = "-";
            if (record.getOrigIsbn().contains(SPLIT)) {
                String[] part = record.getOrigIsbn().split(SPLIT);
                String exactIsbn = part[0];
                Bookinfo book = this.bookService.findBookById(Integer.valueOf(part[1]));
                // 2.1.1 分隔符后面的bookId对应的图书存在、状态合法，且ISBN相等，匹配成功
                if (book != null && book.getBookStatus().equals(ConstantString.USEFUL) && book.getIsbn().equals(exactIsbn)) {
                    record.setBookId(book.getId());
                    record.setStatus(TempImportDetailStatus.MATCHED);
                    logger.info("tempImportDetail associate with bookInfo, detailId=" + record.getId());
                }
                // 2.1.2 匹配失败，仅更新isbn
                record.setIsbn(exactIsbn);
                record.setGmtModify(ZisUtils.getTS());
                this.tempImportDetailDao.save(record);
                // 处理完成，跳过后续流程
                continue;
            }
            // 2.2 如果origIsbn不包含分隔符，则isbn=origIsbn，处理流程同3
            record.setIsbn(record.getOrigIsbn());
        }
        // 3. 使用isbn进行匹配（如果isbn!=null或者isbn=origIsbn）
        List<Bookinfo> bookList = this.bookService.findBookByISBN(record.getIsbn());
        // 3.1 当且仅当isbn只有一条记录，匹配成功
        if (bookList != null && bookList.size() == 1) {
            record.setBookId(bookList.get(0).getId());
            record.setStatus(TempImportDetailStatus.MATCHED);
            record.setGmtModify(ZisUtils.getTS());
            this.tempImportDetailDao.save(record);
            logger.info("tempImportDetail associate with bookInfo, detailId=" + record.getId());
        }
    }
    // 如果本次待处理的全部搞定，则修改TaskStatus
    updateTaskIfFullyMatched(taskId);
}


}