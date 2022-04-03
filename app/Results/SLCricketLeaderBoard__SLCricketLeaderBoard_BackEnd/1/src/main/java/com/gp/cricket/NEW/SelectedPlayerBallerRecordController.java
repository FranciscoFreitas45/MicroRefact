package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.SelectedPlayer;
@RestController
@CrossOrigin
public class SelectedPlayerBallerRecordController {

@Autowired
 private SelectedPlayerBallerRecordService selectedplayerballerrecordservice;


@PutMapping
("/BallerRecord/{id}/SelectedPlayer/setSelectedPlayerId")
public void setSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2,@RequestBody SelectedPlayer selectedPlayerId){
selectedplayerballerrecordservice.setSelectedPlayerId(selectedPlayerIdv2,selectedPlayerId);
}


@GetMapping
("/BallerRecord/{id}/SelectedPlayer/getSelectedPlayerId")
public SelectedPlayer getSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2){
return selectedplayerballerrecordservice.getSelectedPlayerId(selectedPlayerIdv2);
}


}