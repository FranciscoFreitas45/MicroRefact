package com.hmm.logistics.roomClean.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.logistics.roomClean.entity.RoomCleanRecord;
import com.hmm.logistics.roomClean.service.IRoomCleanRecordService;
@RestController
@RequestMapping("roomCleanRecord")
public class RoomCleanRecordController {

@Autowired
 private  IRoomCleanRecordService roomCleanRecordService;


@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ExtAjaxResponse save(RoomCleanRecord roomCleanRecord){
    try {
        roomCleanRecordService.save(roomCleanRecord);
        return new ExtAjaxResponse(true, "保存成功！");
    } catch (Exception e) {
        return new ExtAjaxResponse(true, "保存失败！");
    }
}


}