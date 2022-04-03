package com.zis.toolkit.controller;
 import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.toolkit.service.BookInfoToolkit;
import com.zis.Interface.BookService;
@Controller
@RequestMapping(value = "/toolkit")
public class BookAuthorFixController extends BaseBookFixController{

@Autowired
 private  BookInfoToolkit bookInfoToolkit;

@Autowired
 private  BookService bookService;


@RequiresPermissions(value = { "toolkit:toolkit" })
@RequestMapping(value = "/fixBookAuthor")
public String fixBookAuthor(ModelMap context){
    Pageable page = new PageRequest(0, 500);
    List<String> list = new ArrayList<String>();
    Page<Bookinfo> pageList = null;
    do {
        pageList = this.bookService.findAll(page);
        List<Bookinfo> bookList = pageList.getContent();
        List<String> result = bookInfoToolkit.updateFixBookAuthor(bookList);
        list.addAll(result);
        page = pageList.nextPageable();
    } while (pageList.hasNext());
    showResult(list, context);
    return "toolkit/toolkit";
}


}