package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MdnVodItemController {

 private MdnVodItem mdnvoditem;

 private MdnVodItem mdnvoditem;


@PutMapping
("/setVodProtocol")
public void setVodProtocol(@RequestParam(name = "vodProtocol") String vodProtocol){
mdnvoditem.setVodProtocol(vodProtocol);
}


@PutMapping
("/setSourceStreamerUrl")
public void setSourceStreamerUrl(@RequestParam(name = "sourceStreamerUrl") String sourceStreamerUrl){
mdnvoditem.setSourceStreamerUrl(sourceStreamerUrl);
}


@PutMapping
("/setSourceOutBandwidth")
public void setSourceOutBandwidth(@RequestParam(name = "sourceOutBandwidth") String sourceOutBandwidth){
mdnvoditem.setSourceOutBandwidth(sourceOutBandwidth);
}


}