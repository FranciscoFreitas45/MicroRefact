package com.zis.toolkit.service;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.YouLuNetTextUtil;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.Interface.BookService;
import com.zis.DTO.QueryUtil;
import com.zis.DTO.BookService;
@Service
public class BookInfoToolkit {

@Autowired
 private  BookService bookService;

 private  Logger logger;


public List<String> updateBatchReplaceBookName(String orig,String replace){
    if (StringUtils.isBlank(orig)) {
        String errMsg = String.format("illegal arguments, for input orig=%s", orig);
        throw new RuntimeException(errMsg);
    }
    if (StringUtils.isBlank(replace)) {
        replace = "";
    }
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.like("bookName", "%" + orig + "%");
    Specification<Bookinfo> spec = query.getSpecification();
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Bookinfo.class);
    // criteria.add(Restrictions.like("bookName", "%" + orig + "%"));
    List<Bookinfo> list = this.bookService.findBySpecificationAll(spec);
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : list) {
        String origName = book.getBookName();
        String newName = origName.replace(orig, replace);
        book.setBookName(newName);
        this.bookService.updateBook(book);
        String infoMsg = String.format("update bookName %s to %s where id=%s", origName, newName, book.getId());
        logger.info(infoMsg);
        result.add(infoMsg);
    }
    return result;
}


public List<String> updateFixBookName(String startLabel,String keyword){
    if (StringUtils.isBlank(startLabel) || StringUtils.isBlank(keyword)) {
        String errMsg = String.format("illegal arguments, for input startLabel=%s, keyword=%s", startLabel, keyword);
        throw new RuntimeException(errMsg);
    }
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.like("bookName", "%" + startLabel + "%" + keyword + "%");
    Specification<Bookinfo> spec = query.getSpecification();
    List<Bookinfo> list = this.bookService.findBySpecificationAll(spec);
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : list) {
        String origName = book.getBookName();
        String newName = origName.substring(0, origName.lastIndexOf(startLabel));
        book.setBookName(newName);
        this.bookService.updateBook(book);
        String infoMsg = String.format("update bookName %s to %s where id=%s", origName, newName, book.getId());
        logger.info(infoMsg);
        result.add(infoMsg);
    }
    return result;
}


public List<String> updateReplaceEditionByBookName(){
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.like("bookName", "%第%版%");
    Specification<Bookinfo> spec = query.getSpecification();
    // DetachedCriteria criteria = DetachedCriteria.forClass(Bookinfo.class);
    // criteria.add(Restrictions.like("bookName", "%第%版%"));
    List<Bookinfo> list = this.bookService.findBySpecificationAll(spec);
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : list) {
        String orig = book.getBookName();
        String origEdition = book.getBookEdition();
        String newEdition = null;
        try {
            newEdition = orig.substring(orig.lastIndexOf("第"), orig.lastIndexOf("版") + 1);
        } catch (Exception e) {
            logger.error("修复版次失败, bookId=" + book.getId(), e);
            continue;
        }
        String newName = orig.replace(newEdition, "");
        newName = newName.replace("()", "");
        newName = newName.replace("( )", "");
        newName = newName.replace("（）", "");
        newName = newName.replace("（ ）", "");
        book.setBookName(newName);
        book.setBookEdition(newEdition);
        this.bookService.updateBook(book);
        String infoMsg = String.format("update book %s,%s to %s,%s where id=%s", orig, origEdition, newName, newEdition, book.getId());
        logger.info(infoMsg);
        result.add(infoMsg);
    }
    return result;
}


public List<String> deleteUselessSuffixInBookAuthor(String suffix){
    if (StringUtils.isBlank(suffix)) {
        throw new RuntimeException("illegal arguments, for input suffix=" + suffix);
    }
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.like("bookAuthor", "%" + suffix);
    Specification<Bookinfo> spec = query.getSpecification();
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Bookinfo.class);
    // criteria.add(Restrictions.like("bookAuthor", "%" + suffix));
    List<Bookinfo> list = this.bookService.findBySpecificationAll(spec);
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : list) {
        String orig = book.getBookAuthor();
        String newStr = orig.substring(0, orig.lastIndexOf(suffix));
        book.setBookAuthor(newStr);
        this.bookService.updateBook(book);
        String infoMsg = String.format("update bookAuthor %s to %s where id=%s", orig, newStr, book.getId());
        logger.info(infoMsg);
        result.add(infoMsg);
    }
    return result;
}


public List<String> updateFixEditionByBookName(String keyword){
    if (StringUtils.isBlank(keyword)) {
        throw new RuntimeException("illegal arguments, for input keyword=" + keyword);
    }
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.like("bookName", "%" + keyword + "%");
    Specification<Bookinfo> spec = query.getSpecification();
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Bookinfo.class);
    // criteria.add(Restrictions.like("bookName", "%" + keyword + "%"));
    List<Bookinfo> list = this.bookService.findBySpecificationAll(spec);
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : list) {
        String orig = book.getBookName();
        String origEdition = book.getBookEdition();
        String newName = orig.replace(keyword, "");
        newName = newName.replace("()", "");
        newName = newName.replace("( )", "");
        newName = newName.replace("（）", "");
        newName = newName.replace("（ ）", "");
        String newEdition = origEdition + keyword;
        book.setBookName(newName);
        book.setBookEdition(newEdition);
        this.bookService.updateBook(book);
        String infoMsg = String.format("update book %s,%s to %s,%s where id=%s", orig, origEdition, newName, newEdition, book.getId());
        logger.info(infoMsg);
        result.add(infoMsg);
    }
    return result;
}


public List<String> updateFixBookAuthor(List<Bookinfo> bookList){
    if (bookList == null || bookList.isEmpty()) {
        return new ArrayList<String>();
    }
    List<String> result = new ArrayList<String>();
    for (Bookinfo book : bookList) {
        String origAuthor = book.getBookAuthor();
        try {
            String destAuthor = YouLuNetTextUtil.clearBookAuthor(origAuthor);
            if (origAuthor.equals(destAuthor)) {
                // 如果处理后的作者名称没有变化，则跳过
                continue;
            }
            book.setBookAuthor(destAuthor);
            this.bookService.updateBook(book);
            String infoMsg = String.format("update bookAuthor %s to %s where id=%s", origAuthor, destAuthor, book.getId());
            logger.info(infoMsg);
            result.add(infoMsg);
        } catch (Exception e) {
            logger.error("更新数据错误：" + e.getMessage(), e);
        }
    }
    return result;
}


}