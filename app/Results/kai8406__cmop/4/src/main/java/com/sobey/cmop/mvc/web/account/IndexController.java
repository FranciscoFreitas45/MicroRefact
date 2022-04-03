package com.sobey.cmop.mvc.web.account;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sobey.cmop.mvc.comm.BaseController;
@Controller
public class IndexController extends BaseController{


@RequestMapping(value = { "/", "index/" })
public String index(){
    return "index";
}


}