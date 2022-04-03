package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DropFilterController {

 private DropFilter dropfilter;

 private DropFilter dropfilter;


@PutMapping
("/setDropIds")
public void setDropIds(@RequestParam(name = "dropIds") List<Long> dropIds){
dropfilter.setDropIds(dropIds);
}


@PutMapping
("/setMaxId")
public void setMaxId(@RequestParam(name = "maxId") Long maxId){
dropfilter.setMaxId(maxId);
}


@PutMapping
("/setSinceId")
public void setSinceId(@RequestParam(name = "sinceId") Long sinceId){
dropfilter.setSinceId(sinceId);
}


@PutMapping
("/setChannels")
public void setChannels(@RequestParam(name = "channels") List<String> channels){
dropfilter.setChannels(channels);
}


@PutMapping
("/setChannelIds")
public void setChannelIds(@RequestParam(name = "channelIds") List<Long> channelIds){
dropfilter.setChannelIds(channelIds);
}


@PutMapping
("/setDateFrom")
public void setDateFrom(@RequestParam(name = "dateFrom") Date dateFrom){
dropfilter.setDateFrom(dateFrom);
}


@PutMapping
("/setDateTo")
public void setDateTo(@RequestParam(name = "dateTo") Date dateTo){
dropfilter.setDateTo(dateTo);
}


@PutMapping
("/setRead")
public void setRead(@RequestParam(name = "read") Boolean read){
dropfilter.setRead(read);
}


@PutMapping
("/setPhotos")
public void setPhotos(@RequestParam(name = "photos") Boolean photos){
dropfilter.setPhotos(photos);
}


@PutMapping
("/setKeywords")
public void setKeywords(@RequestParam(name = "keywords") String keywords){
dropfilter.setKeywords(keywords);
}


@PutMapping
("/setBoundingBox")
public void setBoundingBox(@RequestParam(name = "boundingBoxStr") String boundingBoxStr){
dropfilter.setBoundingBox(boundingBoxStr);
}


}