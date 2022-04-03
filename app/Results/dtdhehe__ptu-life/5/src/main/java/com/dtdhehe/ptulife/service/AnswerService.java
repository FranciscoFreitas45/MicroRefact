package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.vo.AnswerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface AnswerService {


public void delAnswerById(String answerId)
;

public PtuAnswer save(PtuAnswer ptuAnswer)
;

public Page<AnswerDto> queryAllAnswerWithHead(Pageable pageable)
;

public List<PtuAnswer> queryAllAnswer()
;

public PtuAnswer queryAnswerById(String answerId)
;

public Page<PtuAnswer> queryAnswerByUserId(String userId,String answerTitle,Pageable pageable)
;

}