package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MdnLiveItemController {

 private MdnLiveItem mdnliveitem;

 private MdnLiveItem mdnliveitem;


@PutMapping
("/setLiveProtocol")
public void setLiveProtocol(@RequestParam(name = "liveProtocol") String liveProtocol){
mdnliveitem.setLiveProtocol(liveProtocol);
}


@PutMapping
("/setStreamOutMode")
public void setStreamOutMode(@RequestParam(name = "streamOutMode") Integer streamOutMode){
mdnliveitem.setStreamOutMode(streamOutMode);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
mdnliveitem.setName(name);
}


@PutMapping
("/setGuid")
public void setGuid(@RequestParam(name = "guid") String guid){
mdnliveitem.setGuid(guid);
}


@PutMapping
("/setBandwidth")
public void setBandwidth(@RequestParam(name = "bandwidth") String bandwidth){
mdnliveitem.setBandwidth(bandwidth);
}


@PutMapping
("/setEncoderMode")
public void setEncoderMode(@RequestParam(name = "encoderMode") Integer encoderMode){
mdnliveitem.setEncoderMode(encoderMode);
}


@PutMapping
("/setHttpUrl")
public void setHttpUrl(@RequestParam(name = "httpUrl") String httpUrl){
mdnliveitem.setHttpUrl(httpUrl);
}


@PutMapping
("/setHttpBitrate")
public void setHttpBitrate(@RequestParam(name = "httpBitrate") String httpBitrate){
mdnliveitem.setHttpBitrate(httpBitrate);
}


@PutMapping
("/setHlsUrl")
public void setHlsUrl(@RequestParam(name = "hlsUrl") String hlsUrl){
mdnliveitem.setHlsUrl(hlsUrl);
}


@PutMapping
("/setHlsBitrate")
public void setHlsBitrate(@RequestParam(name = "hlsBitrate") String hlsBitrate){
mdnliveitem.setHlsBitrate(hlsBitrate);
}


}