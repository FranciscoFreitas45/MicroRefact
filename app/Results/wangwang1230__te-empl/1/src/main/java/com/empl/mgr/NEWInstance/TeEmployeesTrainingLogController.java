package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeEmployeesTrainingLogController {

 private TeEmployeesTrainingLog teemployeestraininglog;

 private TeEmployeesTrainingLog teemployeestraininglog;


@PutMapping
("/setCreator")
public void setCreator(@RequestParam(name = "creator") String creator){
teemployeestraininglog.setCreator(creator);
}


@PutMapping
("/setEmplId")
public void setEmplId(@RequestParam(name = "emplId") long emplId){
teemployeestraininglog.setEmplId(emplId);
}


@PutMapping
("/setTrainingItemId")
public void setTrainingItemId(@RequestParam(name = "trainingItemId") long trainingItemId){
teemployeestraininglog.setTrainingItemId(trainingItemId);
}


@PutMapping
("/setApplyTime")
public void setApplyTime(@RequestParam(name = "applyTime") String applyTime){
teemployeestraininglog.setApplyTime(applyTime);
}


@PutMapping
("/setNote")
public void setNote(@RequestParam(name = "note") String note){
teemployeestraininglog.setNote(note);
}


@PutMapping
("/setState")
public void setState(@RequestParam(name = "state") Integer state){
teemployeestraininglog.setState(state);
}


}