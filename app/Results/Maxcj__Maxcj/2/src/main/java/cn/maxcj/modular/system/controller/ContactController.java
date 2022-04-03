package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
@Controller
@RequestMapping("/contact")
public class ContactController {

@Autowired
 private  IDeptService deptService;


@RequestMapping(value = "/sltree")
@ResponseBody
public List<ZTreeNode> tree(){
    List<ZTreeNode> tree = this.deptService.sheliantree();
    // tree.add(ZTreeNode.createParent());
    return tree;
}


}