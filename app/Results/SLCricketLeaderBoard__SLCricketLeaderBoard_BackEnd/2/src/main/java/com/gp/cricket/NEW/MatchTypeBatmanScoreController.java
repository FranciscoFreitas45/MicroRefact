package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.MatchType;
@RestController
@CrossOrigin
public class MatchTypeBatmanScoreController {

@Autowired
 private MatchTypeBatmanScoreService matchtypebatmanscoreservice;


@PutMapping
("/BatmanScore/{id}/MatchType/setMatchTypeId")
public void setMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2,@RequestBody MatchType matchTypeId){
matchtypebatmanscoreservice.setMatchTypeId(matchTypeIdv2,matchTypeId);
}


@GetMapping
("/BatmanScore/{id}/MatchType/getMatchTypeId")
public MatchType getMatchTypeId(@PathVariable(name="id") Integer matchTypeIdv2){
return matchtypebatmanscoreservice.getMatchTypeId(matchTypeIdv2);
}


}