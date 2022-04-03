package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TableDTOController {

 private TableDTO tabledto;

 private TableDTO tabledto;


@PutMapping
("/add")
public void add(@RequestParam(name = "row") T row){
tabledto.add(row);
}


}