package com.zis.bookinfo.controller;
 import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.dto.AddYouLuDataDTO;
import com.zis.bookinfo.service.BookService;
@Controller
@RequestMapping(value = "/bookInfo")
public class AddYouLuDataController {

@Autowired
 private  BookService bookService;


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
@RequestMapping(value = "/addYouLuData")
public String addYouLuData(AddYouLuDataDTO addYouLuDataDTO,BindingResult br,ModelMap context){
    if (br.hasErrors()) {
        return "bookinfo/addYouLuData";
    }
    if (addYouLuDataDTO.getStartId() != null && addYouLuDataDTO.getFinalId() != null) {
        if (addYouLuDataDTO.getStartId() <= addYouLuDataDTO.getFinalId()) {
            Integer activeTaskCount = bookService.asynchronousCaptureBookInfoFromYouLuNet(addYouLuDataDTO.getStartId(), addYouLuDataDTO.getFinalId(), addYouLuDataDTO.getOperateType());
            if (activeTaskCount <= 1)
                activeTaskCount = 1;
            context.put("activeTaskCount", activeTaskCount + "");
        }
        return "bookinfo/addYouLuData";
    } else {
        return "error";
    }
}


}