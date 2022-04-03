package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.SelectedPlayerRepository;
import com.gp.cricket.entity.SelectedPlayer;
@Service
public class SelectedPlayerFieldingRecordService {

@Autowired
 private SelectedPlayerRepository selectedplayerrepository;


public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2){
return selectedplayerrepository.getSelectedPlayerId(selectedPlayerIdv2);
}


public void setSelectedPlayerId(Integer selectedPlayerIdv2,SelectedPlayer selectedPlayerId){
selectedplayerrepository.setSelectedPlayerId(selectedPlayerIdv2,selectedPlayerId);
}


}