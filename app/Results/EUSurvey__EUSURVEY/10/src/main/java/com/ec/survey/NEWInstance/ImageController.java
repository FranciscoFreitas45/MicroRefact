package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ImageController {

 private Image image;

 private Image image;


@PutMapping
("/setUrl")
public void setUrl(@RequestParam(name = "url") String url){
image.setUrl(url);
}


@PutMapping
("/setFilename")
public void setFilename(@RequestParam(name = "filename") String filename){
image.setFilename(filename);
}


}