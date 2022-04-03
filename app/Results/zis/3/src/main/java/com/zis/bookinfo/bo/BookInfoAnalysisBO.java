package com.zis.bookinfo.bo;
 import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.repository.BookInfoDao;
import com.zis.bookinfo.util.ConstantString;
public class BookInfoAnalysisBO {

@Autowired
 protected  BookInfoDao bookinfoDao;

 protected  Logger logger;


public void analysis(){
    Integer maxId = this.bookinfoDao.findMaxBookId();
    // 按照ID顺序遍历整个bookinfo表
    for (int i = 1; i <= maxId; i++) {
        Bookinfo book = this.bookinfoDao.findOne(i);
        // 只处理状态不为“废弃”的记录
        if (book != null && !book.getBookStatus().equals(ConstantString.ABANDON)) {
            try {
                processOne(book);
            } catch (Exception e) {
                logger.error("处理图书信息过程出错，" + e.getMessage(), e);
            }
        }
    }
}


public void processOne(Bookinfo book)


}