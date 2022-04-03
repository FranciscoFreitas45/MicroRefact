package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataGridController {

 private DataGrid datagrid;


@GetMapping
("/getPage")
public int getPage(){
  return datagrid.getPage();
}


@GetMapping
("/getField")
public String getField(){
  return datagrid.getField();
}


@GetMapping
("/getRows")
public int getRows(){
  return datagrid.getRows();
}


@PutMapping
("/setTotal")
public void setTotal(@RequestParam(name = "total") int total){
datagrid.setTotal(total);
}


@PutMapping
("/setResults")
public void setResults(@RequestParam(name = "results") List results){
datagrid.setResults(results);
}


@PutMapping
("/clear")
public void clear(){
datagrid.clear();
}


@PutMapping
("/setFooter")
public void setFooter(@RequestParam(name = "footer") String footer){
datagrid.setFooter(footer);
}


}