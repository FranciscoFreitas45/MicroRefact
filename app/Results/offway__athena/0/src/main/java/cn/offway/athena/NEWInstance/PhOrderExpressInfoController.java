package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderExpressInfoController {

 private PhOrderExpressInfo phorderexpressinfo;

 private PhOrderExpressInfo phorderexpressinfo;


@PutMapping
("/setExPhone")
public void setExPhone(@RequestParam(name = "exPhone") String exPhone){
phorderexpressinfo.setExPhone(exPhone);
}


@PutMapping
("/setLastTime")
public void setLastTime(@RequestParam(name = "lastTime") String lastTime){
phorderexpressinfo.setLastTime(lastTime);
}


}