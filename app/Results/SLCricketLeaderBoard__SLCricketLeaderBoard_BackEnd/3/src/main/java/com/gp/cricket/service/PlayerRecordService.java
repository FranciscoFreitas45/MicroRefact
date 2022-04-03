package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.FieldingRecord;
import com.gp.cricket.entity.SelectedPlayer;
import com.gp.cricket.mapobject.PlayerRecord;
import com.gp.cricket.repository.BallerRecordRepository;
import com.gp.cricket.repository.BatmanRecordRepository;
import com.gp.cricket.repository.FieldingRecordRepository;
import com.gp.cricket.repository.SelectedPlayerRepository;
import com.gp.cricket.Interface.SelectedPlayerRepository;
@Service
public class PlayerRecordService {

@Autowired
 private BatmanRecordService batmanRecordService;

@Autowired
 private BallerRecordService ballerRecordService;

@Autowired
 private FieldingRecordService fieldingRecordService;

@Autowired
 private SelectedPlayerRepository selectedPlayerRepository;


public PlayerRecord getPlayerRecord(Integer selectedPlayerId){
    SelectedPlayer player = selectedPlayerRepository.findById(selectedPlayerId).get();
    BatmanRecord bat = batmanRecordService.getbatmanRecordBtSelectedPlayerId(selectedPlayerId);
    if (bat == null) {
        Byte b = 0;
        bat = new BatmanRecord(null, 0, 0, 0, 0, b, player, b, b, b, b, b, b, 0.0, 0.0);
    }
    BallerRecord bal = ballerRecordService.getballerRecordBtSelectedPlayerId(selectedPlayerId);
    if (bal == null) {
        bal = new BallerRecord(null, 0.0, 0, 0, 0, player, 0.0, 0, 0, 0.0);
    }
    FieldingRecord fied = fieldingRecordService.getFieldingRecordBtSelectedPlayerId(selectedPlayerId);
    if (fied == null) {
        fied = new FieldingRecord(null, 0, player, 0.0);
    }
    // 
    PlayerRecord playerRecord = new PlayerRecord(bat, bal, fied);
    return playerRecord;
}


}