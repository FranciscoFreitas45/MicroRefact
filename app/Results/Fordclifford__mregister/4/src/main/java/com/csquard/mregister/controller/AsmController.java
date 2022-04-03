package com.csquard.mregister.controller;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.Asm;
import com.csquard.mregister.repository.AsmRepository;
@RestController
@RequestMapping("/api")
public class AsmController {

@Autowired
 private AsmRepository asmRepository;


@GetMapping("/asms/{id}")
@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
public Asm getAsmById(Long asmId){
    return asmRepository.findById(asmId).orElseThrow(() -> new ResourceNotFoundException("Asm", "id", asmId));
}


@PutMapping("/asms/{id}")
@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
public Asm updateAsm(Long asmId,Asm asmDetails){
    Asm asm = asmRepository.findById(asmId).orElseThrow(() -> new ResourceNotFoundException("Asm", "id", asmId));
    asm.setSales_region_id(asmDetails.getSales_region_id());
    asm.setUser_id(asmDetails.getUser_id());
    asm.setFirst_name(asmDetails.getFirst_name());
    asm.setLast_name(asmDetails.getLast_name());
    asm.setId_no(asmDetails.getId_no());
    Asm updatedAsm = asmRepository.save(asm);
    return updatedAsm;
}


@GetMapping("/asms")
@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
public List<Asm> getAllAsms(){
    return asmRepository.findAllAsmsHere();
}


@DeleteMapping("/asms/{id}")
@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
public ResponseEntity<?> deleteAsm(Long asmId){
    Asm asm = asmRepository.findById(asmId).orElseThrow(() -> new ResourceNotFoundException("Asm", "id", asmId));
    asmRepository.delete(asm);
    return ResponseEntity.ok().build();
}


}