package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CircleController {

 private Circle circle;

 private Circle circle;


@PutMapping
("/setCreateUserId")
public void setCreateUserId(@RequestParam(name = "createUserId") int createUserId){
circle.setCreateUserId(createUserId);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
circle.setStatus(status);
}


@PutMapping
("/setHeadPath")
public void setHeadPath(@RequestParam(name = "headPath") String headPath){
circle.setHeadPath(headPath);
}


@PutMapping
("/setImagePath")
public void setImagePath(@RequestParam(name = "imagePath") String imagePath){
circle.setImagePath(imagePath);
}


}