package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.MatchType;
@RestController
@CrossOrigin
public class MatchTypeBallerScoreController {

@Autowired
 private MatchTypeBallerScoreService matchtypeballerscoreservice;


@PutMapping
("/BallerScore/{id}/MatchType/setMatchTypeId")
public void setMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2,@RequestBody MatchType matchTypeId){
matchtypeballerscoreservice.setMatchTypeId(matchTypeIdv2,matchTypeId);
}


@GetMapping
("/BallerScore/{id}/MatchType/getMatchTypeId")
public MatchType getMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2){
return matchtypeballerscoreservice.getMatchTypeId(matchTypeIdv2);
}


}