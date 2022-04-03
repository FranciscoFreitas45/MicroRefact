package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class Pager4EasyUIController {

 private Pager4EasyUI pager4easyui;

 private Pager4EasyUI pager4easyui;


@PutMapping
("/setRows")
public void setRows(@RequestParam(name = "rows") List<T> rows){
pager4easyui.setRows(rows);
}


@PutMapping
("/setTotal")
public void setTotal(@RequestParam(name = "total") int total){
pager4easyui.setTotal(total);
}


}