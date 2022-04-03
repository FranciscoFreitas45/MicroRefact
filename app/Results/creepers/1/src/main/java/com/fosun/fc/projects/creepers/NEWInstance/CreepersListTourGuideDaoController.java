package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersListTourGuideDaoController {

 private CreepersListTourGuideDao creeperslisttourguidedao;


@GetMapping
("/findByGuideNoAndCertNoOrCardNoAndCertNo")
public List<TCreepersListTourGuide> findByGuideNoAndCertNoOrCardNoAndCertNo(@RequestParam(name = "guideNo") String guideNo,@RequestParam(name = "certNo1") String certNo1,@RequestParam(name = "cardNo") String cardNo,@RequestParam(name = "certNo2") String certNo2){
  return creeperslisttourguidedao.findByGuideNoAndCertNoOrCardNoAndCertNo(guideNo,certNo1,cardNo,certNo2);
}


@GetMapping
("/findByGuideNoOrCertNo")
public List<TCreepersListTourGuide> findByGuideNoOrCertNo(@RequestParam(name = "guideNo") String guideNo,@RequestParam(name = "cardNo") String cardNo){
  return creeperslisttourguidedao.findByGuideNoOrCertNo(guideNo,cardNo);
}


@GetMapping
("/findByGuideNoAndCardNo")
public List<TCreepersListTourGuide> findByGuideNoAndCardNo(@RequestParam(name = "guideNo") String guideNo,@RequestParam(name = "cardNo") String cardNo){
  return creeperslisttourguidedao.findByGuideNoAndCardNo(guideNo,cardNo);
}


@GetMapping
("/findByGuideNoAndCardNoAndCertNo")
public List<TCreepersListTourGuide> findByGuideNoAndCardNoAndCertNo(@RequestParam(name = "guideNo") String guideNo,@RequestParam(name = "cardNo") String cardNo,@RequestParam(name = "certNo") String certNo){
  return creeperslisttourguidedao.findByGuideNoAndCardNoAndCertNo(guideNo,cardNo,certNo);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return creeperslisttourguidedao.findAll(Object);
}


@GetMapping
("/findTop1ByGuideNoOrCardNo")
public TCreepersListTourGuide findTop1ByGuideNoOrCardNo(@RequestParam(name = "guideNo") String guideNo,@RequestParam(name = "cardNo") String cardNo){
  return creeperslisttourguidedao.findTop1ByGuideNoOrCardNo(guideNo,cardNo);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return creeperslisttourguidedao.saveAndFlush(Object);
}


}