package com.zis.bookinfo.bo;
 import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.common.util.SequenceCreator;
import com.zis.common.util.ZisUtils;
@Component
public class SameBookAnalysisBO extends BookInfoAnalysisBO{


public void relateToSameBooks(List<Bookinfo> samebookList){
    // 记录不存在，直接返回
    if (samebookList == null || samebookList.isEmpty()) {
        return;
    }
    // 出版社，检查用，所有待处理记录出版社必须相同
    String bookPublisher = samebookList.get(0).getBookPublisher();
    // 记录所有记录的ID，打印日志用
    StringBuilder idsDealt = new StringBuilder();
    // 1. 针对samebookList中的每一条记录，查出groupId相同的所有记录，加入booksToBeDeal统一处理
    Map<Integer, Bookinfo> booksToBeDeal = new HashMap<Integer, Bookinfo>();
    for (Bookinfo book : samebookList) {
        if (BookinfoStatus.DISCARD.equals(book.getBookStatus())) {
            // 已作废的记录不处理
            continue;
        }
        // 强制要求所有图书必须在同一出版社
        if (!bookPublisher.equals(book.getBookPublisher())) {
            throw new RuntimeException("出版社不同的图书不能合并到一组");
        }
        logger.debug("process bookinfo, id=" + book.getId() + "bookName=" + book.getBookName());
        // 如果没有相同图书，该书ID直接加入到集合中等待处理
        if (book.getGroupId() == null) {
            booksToBeDeal.put(book.getId(), book);
            idsDealt.append(book.getId()).append(",");
        } else {
            // 如果有相同图书，所有图书ID加入到集合中等待处理
            // DetachedCriteria criteria =
            // DetachedCriteria.forClass(Bookinfo.class);
            // criteria.add(Restrictions.eq("groupId", book.getGroupId()));
            // criteria.add(Restrictions.eq("bookPublisher",
            // book.getBookPublisher()));
            // criteria.add(Restrictions.ne("bookStatus",
            // BookinfoStatus.DISCARD));
            // List<Bookinfo> list = bookinfoDao.findByCriteria(criteria);
            List<Bookinfo> list = bookinfoDao.findByGroupIdAndPublisher(book.getGroupId(), book.getBookPublisher());
            for (Bookinfo bookinfo : list) {
                logger.debug("add bookinfo by groupId, id=" + bookinfo.getId());
                booksToBeDeal.put(bookinfo.getId(), bookinfo);
                idsDealt.append(book.getId()).append(",");
            }
        }
    }
    // 2. 生成新的groupId
    String groupId = "s" + getUniqueStr();
    // 3. 遍历booksToBeDeal，出版日期最大的记录isNewEdition=true，其他的均为false
    batchUpdateBooksIsNewEdition(booksToBeDeal.values(), groupId);
    logger.info("successfully combined same books, bookId=" + idsDealt.toString());
}


public String getUniqueStr(){
    return ZisUtils.getDateString("yyyyMMddHHmmss") + SequenceCreator.getSequence(SequenceCreator.SEQ_BOOK_GROUP_ID);
}


@Override
public void processOne(Bookinfo book){
    logger.info("process book, id=" + book.getId());
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    // dc.add(Restrictions.eq("bookName", book.getBookName()));
    // dc.add(Restrictions.eq("bookAuthor", book.getBookAuthor()));
    // dc.add(Restrictions.eq("bookPublisher", book.getBookPublisher()));
    // dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    List<Bookinfo> samebookList = bookinfoDao.findByBookNameAuthorPublisher(book.getBookName(), book.getBookAuthor(), book.getBookPublisher());
    if (samebookList != null && samebookList.size() > 1) {
        relateToSameBooks(samebookList);
    }
}


public void batchUpdateBooksIsNewEdition(Collection<Bookinfo> bookList,String groupId){
    if (bookList == null || bookList.isEmpty()) {
        return;
    }
    // 最新版图书
    Bookinfo newBook = null;
    Date maxEditionDate = ZisUtils.stringToDate("1901-01");
    for (Bookinfo book : bookList) {
        // 遍历list，选择出版日期最大的记录
        if (book.getPublishDate().after(maxEditionDate)) {
            maxEditionDate = book.getPublishDate();
            newBook = book;
        }
        // 更新这些记录的值
        book.setIsNewEdition(false);
        book.setGroupId(groupId);
    }
    // 设置最新版标记
    if (newBook != null) {
        newBook.setIsNewEdition(true);
    }
    // 批量更新
    for (Bookinfo bookinfo : bookList) {
        bookinfo.setGmtModify(ZisUtils.getTS());
        bookinfoDao.save(bookinfo);
    }
}


}