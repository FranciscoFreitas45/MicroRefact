package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.ClubRanking;
import com.gp.cricket.service.ClubRankingService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ClubRankingController {

@Autowired
 private ClubRankingService clubRankingService;


@GetMapping("clubranking/{matchType}")
public ResponseEntity<List<ClubRanking>> getClubRanking(Integer matchType){
    return ResponseEntity.ok(clubRankingService.getClubRanking(matchType));
}


}