package com.zis.toolkit.controller;
 import java.util.List;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.toolkit.dto.FixBookNameDTO;
import com.zis.toolkit.service.BookInfoToolkit;
@Controller
@RequestMapping(value = "/toolkit")
public class BookNameFixController extends BaseBookFixController{

@Autowired
 private  BookInfoToolkit bookInfoToolkit;


@RequiresPermissions(value = { "toolkit:toolkit" })
@RequestMapping(value = "/batchFixBookName")
public String fixBookName(FixBookNameDTO fixBookNameDTO,BindingResult br,ModelMap context){
    if (br.hasErrors()) {
        return "toolkit/toolkit";
    }
    List<String> list = bookInfoToolkit.updateFixBookName(fixBookNameDTO.getStartLabel(), fixBookNameDTO.getKeyword());
    showResult(list, context);
    return "toolkit/toolkit";
}


}