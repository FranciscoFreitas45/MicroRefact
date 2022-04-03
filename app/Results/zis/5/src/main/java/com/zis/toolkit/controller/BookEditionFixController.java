package com.zis.toolkit.controller;
 import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.toolkit.service.BookInfoToolkit;
@Controller
@RequestMapping(value = "/toolkit")
public class BookEditionFixController extends BaseBookFixController{

@Autowired
 private  BookInfoToolkit bookInfoToolkit;


@RequiresPermissions(value = { "toolkit:toolkit" })
@RequestMapping(value = "/batchReplaceEditionByBookName")
public String batchReplaceEditionByBookName(ModelMap context){
    List<String> list = bookInfoToolkit.updateReplaceEditionByBookName();
    showResult(list, context);
    return "toolkit/toolkit";
}


@RequiresPermissions(value = { "toolkit:toolkit" })
@RequestMapping(value = "/batchFixEditionByBookName")
public String batchFixEditionByBookName(ModelMap context){
    List<String> list = bookInfoToolkit.updateFixEditionByBookName("修订版");
    List<String> list2 = bookInfoToolkit.updateFixEditionByBookName("修订本");
    list.addAll(list2);
    showResult(list, context);
    return "toolkit/toolkit";
}


}