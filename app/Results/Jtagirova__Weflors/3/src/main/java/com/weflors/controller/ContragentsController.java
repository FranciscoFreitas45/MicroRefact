package com.weflors.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.weflors.entity.ContragentsEntity;
import com.weflors.service.ContragentsServiceImpl;
@Controller
@RequestMapping("/contragents")
public class ContragentsController {

 private  ContragentsServiceImpl contragentsServiceImpl;

@Autowired
public ContragentsController(ContragentsServiceImpl contragentsServiceImpl) {
    this.contragentsServiceImpl = contragentsServiceImpl;
}
@GetMapping("/list")
@ResponseBody
public List<ContragentsEntity> getListOfContragents(){
    return contragentsServiceImpl.findAllContragents();
}


@DeleteMapping("/delete")
@ResponseBody
public String deleteContragent(ContragentsEntity contragentsEntity){
    contragentsServiceImpl.deleteContragent(contragentsEntity);
    return "Поставщик был удален из вашей базы данных";
}


@GetMapping
public String addContragentPage(Model model){
    model.addAttribute("formName", "Справочник Поставщики");
    return "contragents";
}


@PostMapping("/update")
@ResponseBody
public String updateContragent(ContragentsEntity contragentsEntity){
    contragentsServiceImpl.updateContragent(contragentsEntity);
    if (contragentsServiceImpl.findContragentByName(contragentsEntity.getContragentName()).isPresent()) {
        return "Данные о поставщике обновлены в вашей базе данных";
    } else {
        return "Ошибка обновления данных о поставщике";
    }
}


@PostMapping("/add")
@ResponseBody
public String addContragent(ContragentsEntity contragentsEntity){
    if (contragentsServiceImpl.findContragentByName(contragentsEntity.getContragentName()).isPresent()) {
        return "Поставщик с таким именем уже существует в вашей базе данных";
    }
    contragentsServiceImpl.saveContragent(contragentsEntity);
    return "Новый поставщик добавлен в вашу базу данных";
}


}