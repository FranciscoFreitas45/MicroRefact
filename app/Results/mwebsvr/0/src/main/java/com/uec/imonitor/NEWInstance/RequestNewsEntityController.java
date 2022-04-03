package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RequestNewsEntityController {

 private RequestNewsEntity requestnewsentity;

 private RequestNewsEntity requestnewsentity;


@PutMapping
("/setNewsSection")
public void setNewsSection(@RequestParam(name = "newsSection") String newsSection){
requestnewsentity.setNewsSection(newsSection);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
requestnewsentity.setTitle(title);
}


@PutMapping
("/setSubtitle")
public void setSubtitle(@RequestParam(name = "subtitle") String subtitle){
requestnewsentity.setSubtitle(subtitle);
}


@PutMapping
("/setNewsAuthor")
public void setNewsAuthor(@RequestParam(name = "newsAuthor") String newsAuthor){
requestnewsentity.setNewsAuthor(newsAuthor);
}


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") String content){
requestnewsentity.setContent(content);
}


@PutMapping
("/setIsDeleted")
public void setIsDeleted(@RequestParam(name = "isDeleted") Integer isDeleted){
requestnewsentity.setIsDeleted(isDeleted);
}


@PutMapping
("/setRequestId")
public void setRequestId(@RequestParam(name = "requestId") Integer requestId){
requestnewsentity.setRequestId(requestId);
}


@PutMapping
("/setNewsSource")
public void setNewsSource(@RequestParam(name = "newsSource") String newsSource){
requestnewsentity.setNewsSource(newsSource);
}


@PutMapping
("/setOriginalCode")
public void setOriginalCode(@RequestParam(name = "originalCode") String originalCode){
requestnewsentity.setOriginalCode(originalCode);
}


@PutMapping
("/setWebpageCode")
public void setWebpageCode(@RequestParam(name = "webpageCode") String webpageCode){
requestnewsentity.setWebpageCode(webpageCode);
}


@PutMapping
("/setReportDatetime")
public void setReportDatetime(@RequestParam(name = "reportDatetime") Date reportDatetime){
requestnewsentity.setReportDatetime(reportDatetime);
}


@PutMapping
("/setCreateDatetime")
public void setCreateDatetime(@RequestParam(name = "createDatetime") Date createDatetime){
requestnewsentity.setCreateDatetime(createDatetime);
}


@PutMapping
("/setStartDatetime")
public void setStartDatetime(@RequestParam(name = "startDatetime") Date startDatetime){
requestnewsentity.setStartDatetime(startDatetime);
}


@PutMapping
("/setEndDatetime")
public void setEndDatetime(@RequestParam(name = "endDatetime") Date endDatetime){
requestnewsentity.setEndDatetime(endDatetime);
}


}