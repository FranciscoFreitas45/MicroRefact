package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.ClubRanking;
import com.gp.cricket.mapobject.PlayerMatchBallerRecordExport;
import com.gp.cricket.mapobject.PlayerMatchBatmenRecordExport;
import com.gp.cricket.mapobject.PlayersData;
import com.gp.cricket.service.DataExportService;
import com.gp.cricket.service.PlayerService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class DataExportController {

@Autowired
 private DataExportService dataExportService;


@GetMapping("get/players")
public ResponseEntity<List<PlayersData>> getPlayers(){
    return ResponseEntity.ok(dataExportService.getPlayers());
}


@GetMapping("get/player/batmen/{playerId}")
public List<PlayerMatchBatmenRecordExport> getBatmenRecord(Integer playerId){
    return dataExportService.getBatmenRecord(playerId);
}


@GetMapping("get/player/baller/{playerId}")
public List<PlayerMatchBallerRecordExport> getBallerRecord(Integer playerId){
    return dataExportService.getBallerRecord(playerId);
}


}