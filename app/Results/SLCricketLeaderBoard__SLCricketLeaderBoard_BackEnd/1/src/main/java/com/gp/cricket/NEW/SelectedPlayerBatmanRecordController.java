package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.SelectedPlayer;
@RestController
@CrossOrigin
public class SelectedPlayerBatmanRecordController {

@Autowired
 private SelectedPlayerBatmanRecordService selectedplayerbatmanrecordservice;


@PutMapping
("/BatmanRecord/{id}/SelectedPlayer/setSelectedPlayerId")
public void setSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2,@RequestBody SelectedPlayer selectedPlayerId){
selectedplayerbatmanrecordservice.setSelectedPlayerId(selectedPlayerIdv2,selectedPlayerId);
}


@GetMapping
("/BatmanRecord/{id}/SelectedPlayer/getSelectedPlayerId")
public SelectedPlayer getSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2){
return selectedplayerbatmanrecordservice.getSelectedPlayerId(selectedPlayerIdv2);
}


}