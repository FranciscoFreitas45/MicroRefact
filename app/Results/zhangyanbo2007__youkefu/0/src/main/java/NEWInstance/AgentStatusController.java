package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AgentStatusController {

 private AgentStatus agentstatus;

 private AgentStatus agentstatus;


@PutMapping
("/setSkill")
public void setSkill(@RequestParam(name = "skill") String skill){
agentstatus.setSkill(skill);
}


@PutMapping
("/setSkillname")
public void setSkillname(@RequestParam(name = "skillname") String skillname){
agentstatus.setSkillname(skillname);
}


}