package cn.offway.athena.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhOrderRemarkController {

 private PhOrderRemark phorderremark;

 private PhOrderRemark phorderremark;


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") String content){
phorderremark.setContent(content);
}


@PutMapping
("/setCreateTime")
public void setCreateTime(@RequestParam(name = "createTime") Date createTime){
phorderremark.setCreateTime(createTime);
}


@PutMapping
("/setOrdersId")
public void setOrdersId(@RequestParam(name = "ordersId") String ordersId){
phorderremark.setOrdersId(ordersId);
}


}