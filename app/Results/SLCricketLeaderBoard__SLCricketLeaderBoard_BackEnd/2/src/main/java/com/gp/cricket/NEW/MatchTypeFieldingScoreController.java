package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.MatchType;
@RestController
@CrossOrigin
public class MatchTypeFieldingScoreController {

@Autowired
 private MatchTypeFieldingScoreService matchtypefieldingscoreservice;


@GetMapping
("/FieldingScore/{id}/MatchType/getMatchTypeId")
public MatchType getMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2){
return matchtypefieldingscoreservice.getMatchTypeId(matchTypeIdv2);
}


@PutMapping
("/FieldingScore/{id}/MatchType/setMatchTypeId")
public void setMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2,@RequestBody MatchType matchTypeId){
matchtypefieldingscoreservice.setMatchTypeId(matchTypeIdv2,matchTypeId);
}


}