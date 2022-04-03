package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SequenceDaoController {

 private SequenceDao sequencedao;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return sequencedao.findById(Object);
}


@GetMapping
("/getIds")
public long getIds(@RequestParam(name = "seq") Sequence seq,@RequestParam(name = "num") int num){
  return sequencedao.getIds(seq,num);
}


}