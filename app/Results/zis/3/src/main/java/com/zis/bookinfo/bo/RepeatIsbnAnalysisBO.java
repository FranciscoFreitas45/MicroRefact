package com.zis.bookinfo.bo;
 import java.util.List;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.common.util.ZisUtils;
@Component
public class RepeatIsbnAnalysisBO extends BookInfoAnalysisBO{


@Override
public void processOne(Bookinfo book){
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    // dc.add(Restrictions.eq("isbn", book.getIsbn()));
    // dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    List<Bookinfo> list = bookinfoDao.findByIsbn(book.getIsbn());
    if (list == null || list.isEmpty()) {
        return;
    }
    // 如果只有一条记录并且标记为一码多书，则取消该标记
    if (list.size() == 1) {
        Bookinfo bi = list.get(0);
        if (bi.getRepeatIsbn() != null && bi.getRepeatIsbn()) {
            bi.setRepeatIsbn(false);
            bi.setGmtModify(ZisUtils.getTS());
            bookinfoDao.save(bi);
        }
    } else // 多条记录，一码多书标记一下
    {
        for (Bookinfo bi : list) {
            if (bi.getRepeatIsbn() != null && !bi.getRepeatIsbn()) {
                bi.setRepeatIsbn(true);
                bi.setGmtModify(ZisUtils.getTS());
                bookinfoDao.save(bi);
            }
        }
        logger.info("successfuly process one ISBN reflect to multiple books, isbn=" + book.getIsbn());
    }
}


}