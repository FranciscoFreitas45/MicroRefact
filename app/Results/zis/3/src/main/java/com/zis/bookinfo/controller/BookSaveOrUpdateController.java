package com.zis.bookinfo.controller;
 import org.apache.commons.lang3.StringUtils.isBlank;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.bookinfo.dto.BookInfoDTO;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.BookMetadataSource;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.util.ZisUtils;
@Controller
@RequestMapping(value = "/bookInfo")
public class BookSaveOrUpdateController {

 private  Logger logger;

 private  String ALTER_BOOK;

 private  String ADD_BOOK;

@Autowired
 private  BookService bookService;


public Bookinfo buildBook(Bookinfo book,BookInfoDTO bookInfoDTO){
    book.setIsbn(bookInfoDTO.getIsbn());
    book.setBookName(bookInfoDTO.getBookName());
    book.setBookAuthor(bookInfoDTO.getBookAuthor());
    book.setBookPublisher(bookInfoDTO.getBookPublisher());
    book.setPublishDate(bookInfoDTO.getPublishDate());
    book.setBookPrice(bookInfoDTO.getBookPrice());
    book.setBookEdition(bookInfoDTO.getBookEdition());
    book.setIsNewEdition(bookInfoDTO.getIsNewEdition());
    book.setRepeatIsbn(bookInfoDTO.getRepeatIsbn());
    return book;
}


public String getUrl(String type){
    if (ADD_BOOK.equals(type)) {
        return "bookinfo/addBook";
    }
    if (ALTER_BOOK.equals(type)) {
        return "bookinfo/alterBook";
    }
    return "error";
}


public BookinfoDetail buildBookInfoDetail(BookInfoDTO bookInfoDTO){
    // ?????????????????????????????????????????????null?????????????????????detail??????????????????
    if (isBlank(bookInfoDTO.getImageUrl()) && isBlank(bookInfoDTO.getTaobaoTitle()) && isBlank(bookInfoDTO.getSummary()) && isBlank(bookInfoDTO.getCatalog()) && bookInfoDTO.getTaobaoCatagoryId() == null) {
        return null;
    }
    BookinfoDetail detail = null;
    if (bookInfoDTO.getId() != null) {
        detail = bookService.findBookInfoDetailByBookId(bookInfoDTO.getId());
    }
    // DB????????????????????????????????????????????????????????????null
    if (detail == null) {
        detail = new BookinfoDetail();
        detail.setBookid(bookInfoDTO.getId());
        detail.setImageUrl(bookInfoDTO.getImageUrl());
        detail.setCatalog(bookInfoDTO.getCatalog());
        detail.setSummary(bookInfoDTO.getSummary());
        detail.setTaobaoForbidden(false);
        detail.setTaobaoTitle(bookInfoDTO.getTaobaoTitle());
        detail.setTaobaoCatagoryId(bookInfoDTO.getTaobaoCatagoryId());
        detail.setSource(BookMetadataSource.USER);
        detail.setGmtCreate(ZisUtils.getTS());
        detail.setGmtModify(ZisUtils.getTS());
        return detail;
    } else {
        if (!isBlank(bookInfoDTO.getImageUrl())) {
            detail.setImageUrl(bookInfoDTO.getImageUrl());
        }
        if (!isBlank(bookInfoDTO.getTaobaoTitle())) {
            detail.setTaobaoTitle(bookInfoDTO.getTaobaoTitle());
        }
        if (!isBlank(bookInfoDTO.getSummary())) {
            detail.setSummary(bookInfoDTO.getSummary());
        }
        if (!isBlank(bookInfoDTO.getCatalog())) {
            detail.setCatalog(bookInfoDTO.getCatalog());
        }
        if (bookInfoDTO.getTaobaoCatagoryId() != null) {
            detail.setTaobaoCatagoryId(bookInfoDTO.getTaobaoCatagoryId());
        }
        return detail;
    }
}


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
@RequestMapping(value = "/saveOrUpdate")
public String saveOrUpdate(BookInfoDTO bookInfoDTO,BindingResult br,ModelMap map){
    map.put("book", bookInfoDTO);
    if (br.hasErrors()) {
        return getUrl(bookInfoDTO.getUrlType());
    }
    if (bookInfoDTO.getBookPrice() < 0) {
        map.put("actionError", "bookPrice ??????????????????0");
        return "error";
    }
    try {
        Bookinfo book;
        BookinfoDetail detail;
        if (bookInfoDTO.getId() != null) {
            Bookinfo bi = bookService.findBookById(bookInfoDTO.getId());
            book = buildBook(bi, bookInfoDTO);
            // ??????
            if (ConstantString.CHECKOk.equals(bookInfoDTO.getOperateType())) {
                this.bookService.updateBookForCheckOK(book);
            } else // ??????
            if (ConstantString.NOTUSE.equals(bookInfoDTO.getOperateType())) {
                this.bookService.updateBookForDisable(bookInfoDTO.getId());
            } else // ??????
            {
                detail = buildBookInfoDetail(bookInfoDTO);
                book.setBookStatus(bi.getBookStatus());
                bookService.updateBook(book, detail);
            }
        } else // ??????
        {
            book = buildBook(new Bookinfo(), bookInfoDTO);
            detail = buildBookInfoDetail(bookInfoDTO);
            book.setOutId(bookInfoDTO.getOutId());
            book.setBookStatus(ConstantString.USEFUL);
            // XXX ???????????????????????????
            bookService.addBook(book, detail);
            // ???????????????????????????????????????
            QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
            query.like("bookAuthor", "%" + book.getBookAuthor() + "%");
            query.eq("bookPublisher", book.getBookPublisher());
            query.ne("bookStatus", BookinfoStatus.DISCARD);
            Specification<Bookinfo> spec = query.getSpecification();
            List<Bookinfo> samebookList = bookService.findBySpecificationAll(spec);
            if (samebookList != null && samebookList.size() > 1) {
                // ?????????????????????????????????????????????????????????????????????
                map.put("actionMessage", "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
                return "forward:/bookInfo/getAllBooks";
            }
        }
        map.put("actionMessage", bookInfoDTO.getBookName() + "?????????");
        return getUrl(bookInfoDTO.getUrlType());
    } catch (Exception e) {
        map.put("actionError", "????????????:" + e.getMessage());
        e.printStackTrace();
        logger.error("????????????:" + e.getMessage(), e);
        return "error";
    }
}


}