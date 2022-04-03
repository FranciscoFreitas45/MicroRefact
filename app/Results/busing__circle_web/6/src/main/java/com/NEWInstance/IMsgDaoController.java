package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IMsgDaoController {

 private IMsgDao imsgdao;


@GetMapping
("/queryMessageTypeBeans")
public List<MessageTypeBean> queryMessageTypeBeans(){
  return imsgdao.queryMessageTypeBeans();
}


}