package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MatrixController {

 private Matrix matrix;

 private Matrix matrix;


@PutMapping
("/setForeditor")
public void setForeditor(@RequestParam(name = "foreditor") boolean foreditor){
matrix.setForeditor(foreditor);
}


}