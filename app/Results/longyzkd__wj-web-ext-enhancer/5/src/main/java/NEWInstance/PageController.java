package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PageController {

 private Page page;

 private Page page;


@PutMapping
("/setDataList")
public void setDataList(@RequestParam(name = "dataList") List<T> dataList){
page.setDataList(dataList);
}


}