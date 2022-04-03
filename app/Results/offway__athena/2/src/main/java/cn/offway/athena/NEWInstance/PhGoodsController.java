package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhGoodsController {

 private PhGoods phgoods;

 private PhGoods phgoods;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
phgoods.setStatus(status);
}


}