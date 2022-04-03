package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Rule;
@RestController
@CrossOrigin
public class RuleRiverController {

@Autowired
 private RuleRiverService ruleriverservice;


@GetMapping
("/River/{id}/Rule/getRules")
public List<Rule> getRules(@PathVariable(name="id") Long id){
return ruleriverservice.getRules(id);
}


@PutMapping
("/River/{id}/Rule/setRules")
public void setRules(@PathVariable(name="id") Long id,@RequestBody List<Rule> rules){
ruleriverservice.setRules(id,rules);
}


}