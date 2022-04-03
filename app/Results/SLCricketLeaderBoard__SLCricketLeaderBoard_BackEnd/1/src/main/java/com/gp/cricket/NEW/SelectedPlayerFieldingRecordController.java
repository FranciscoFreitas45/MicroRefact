package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.SelectedPlayer;
@RestController
@CrossOrigin
public class SelectedPlayerFieldingRecordController {

@Autowired
 private SelectedPlayerFieldingRecordService selectedplayerfieldingrecordservice;


@GetMapping
("/FieldingRecord/{id}/SelectedPlayer/getSelectedPlayerId")
public SelectedPlayer getSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2){
return selectedplayerfieldingrecordservice.getSelectedPlayerId(selectedPlayerIdv2);
}


@PutMapping
("/FieldingRecord/{id}/SelectedPlayer/setSelectedPlayerId")
public void setSelectedPlayerId(@PathVariable(name="id") Integer selectedPlayerIdv2,@RequestBody SelectedPlayer selectedPlayerId){
selectedplayerfieldingrecordservice.setSelectedPlayerId(selectedPlayerIdv2,selectedPlayerId);
}


}