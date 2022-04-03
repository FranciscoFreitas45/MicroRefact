package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaobaoCsvDataGenerateBOController {

 private TaobaoCsvDataGenerateBO taobaocsvdatageneratebo;


@PutMapping
("/generateV2")
public void generateV2(@RequestParam(name = "bookList") List<BookInfoAndDetailV2DTO> bookList,@RequestParam(name = "emails") String[] emails){
taobaocsvdatageneratebo.generateV2(bookList,emails);
}


}