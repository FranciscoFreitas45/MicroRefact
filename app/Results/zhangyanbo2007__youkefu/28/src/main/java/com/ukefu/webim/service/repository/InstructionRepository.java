package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Instruction;
public interface InstructionRepository extends JpaRepository<Instruction, String>{


public long countByNameAndSnsidAndOrgi(String name,String snsid,String orgi)
;

public List<Instruction> findByOrgi(String orgi)
;

public Page<Instruction> findBySnsidAndOrgi(String snsid,String orgi,Pageable paramPageable)
;

public Instruction findByNameAndOrgi(String name,String orgi)
;

public long countByNameAndSnsidAndOrgiAndIdNot(String name,String snsid,String orgi,String id)
;

public Instruction findByIdAndOrgi(String id,String orgi)
;

}