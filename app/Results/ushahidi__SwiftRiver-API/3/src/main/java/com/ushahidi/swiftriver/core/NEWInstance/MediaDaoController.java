package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MediaDaoController {

 private MediaDao mediadao;


@PutMapping
("/getMedia")
public void getMedia(@RequestParam(name = "drops") List<Drop> drops){
mediadao.getMedia(drops);
}


}