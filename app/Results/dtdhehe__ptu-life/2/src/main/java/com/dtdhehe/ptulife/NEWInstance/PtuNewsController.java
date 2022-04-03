package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PtuNewsController {

 private PtuNewsRepository ptunewsrepository;


@PutMapping
("/setNewsDate/{id}")
public void setNewsDate(@PathVariable(name = "id") String id,@RequestParam(name = "newsDate") String newsDate){
 ptunewsrepository.setNewsDate(id,newsDate);
}


}