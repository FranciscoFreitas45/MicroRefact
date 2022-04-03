package com.gp.cricket.controller;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.FieldingRecord;
import com.gp.cricket.entity.Player;
import com.gp.cricket.mapobject.PlayerRecord;
import com.gp.cricket.service.BallerRecordService;
import com.gp.cricket.service.BatmanRecordService;
import com.gp.cricket.service.FieldingRecordService;
import com.gp.cricket.service.PlayerRecordService;
import com.gp.cricket.service.SelectedPlayerService;
import com.gp.cricket.Interface.SelectedPlayerService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class PlayerRecordController {

@Autowired
 private BatmanRecordService batmanRecordService;

@Autowired
 private BallerRecordService ballerRecordService;

@Autowired
 private FieldingRecordService fieldingRecordService;

@Autowired
 private SelectedPlayerService selectedPlayerService;

@Autowired
 private PlayerRecordService playerRecordService;


@GetMapping("getPlayerRecord/{playerSelectedId}")
public PlayerRecord getPlayerRecord(Integer playerSelectedId){
    PlayerRecord result = playerRecordService.getPlayerRecord(playerSelectedId);
    if (result != null) {
        return result;
    }
    Byte b = 0;
    return new PlayerRecord(new BatmanRecord(null, 0, 0, 0, 0, b, null, b, b, b, b, b, b, 0.0, 0.0), new BallerRecord(null, 0.0, 0, 0, 0, null, 0.0, 0, 0, 0.0), new FieldingRecord(null, 0, null, 0.0));
}


@PostMapping("recordPlayerRecord")
public Integer recordBallerRecord(PlayerRecord playerRecord){
    BallerRecord result = ballerRecordService.saveRecord(playerRecord.getBallerRecord());
    BatmanRecord result2 = batmanRecordService.saveRecord(playerRecord.getBatmanRecord());
    FieldingRecord result3 = fieldingRecordService.saveRecord(playerRecord.getFieldingRecord());
    if (result != null && result2 != null && result3 != null) {
        selectedPlayerService.saveSelectedPlayer(playerRecord.getBallerRecord().getSelectedPlayerId());
        return 1;
    }
    return 0;
}


}