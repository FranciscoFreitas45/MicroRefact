package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComboBox4EasyUIController {

 private ComboBox4EasyUI combobox4easyui;

 private ComboBox4EasyUI combobox4easyui;


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
combobox4easyui.setText(text);
}


@PutMapping
("/setSelected")
public void setSelected(@RequestParam(name = "selected") boolean selected){
combobox4easyui.setSelected(selected);
}


}