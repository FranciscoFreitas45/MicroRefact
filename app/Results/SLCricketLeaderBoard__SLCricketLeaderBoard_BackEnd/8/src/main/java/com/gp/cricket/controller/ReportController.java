package com.gp.cricket.controller;
 import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.service.ReportService;
import net.sf.jasperreports.engine.JRException;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ReportController {

@Autowired
 private ReportService reportService;


@GetMapping("report/tournament/{type}")
public ResponseEntity<Map<String,String>> getTournamentReport(Integer type){
    String path = reportService.getTournamentReport("pdf", type);
    Map<String, String> response = new HashMap<String, String>();
    response.put("fileName", path);
    return ResponseEntity.ok(response);
}


@GetMapping("report/club")
public ResponseEntity<Map<String,String>> getClubReport(){
    String path = reportService.getClubReport("pdf");
    Map<String, String> response = new HashMap<String, String>();
    response.put("fileName", path);
    return ResponseEntity.ok(response);
}


@GetMapping("report/tournament/match/{tournamentId}/{type}")
public ResponseEntity<Map<String,String>> getTournamentMatchFuture(Integer tournamentId,Integer type){
    String path = reportService.getTournamentMatchFuture("pdf", tournamentId, type);
    Map<String, String> response = new HashMap<String, String>();
    response.put("fileName", path);
    return ResponseEntity.ok(response);
}


@GetMapping("report/tournament/match/{tournamentId}")
public ResponseEntity<Map<String,String>> getTournamentMatchPast(Integer tournamentId){
    String path = reportService.getTournamentMatchPast("pdf", tournamentId);
    Map<String, String> response = new HashMap<String, String>();
    response.put("fileName", path);
    return ResponseEntity.ok(response);
}


@GetMapping("report/club/payment/{year}")
public ResponseEntity<Map<String,String>> getClubPaymentReport(Integer year){
    String path = reportService.getClubPaymentReport("pdf", year);
    Map<String, String> response = new HashMap<String, String>();
    response.put("fileName", path);
    return ResponseEntity.ok(response);
}


}