package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.repository.PtuAnswerRepository;
import com.dtdhehe.ptulife.service.AnswerService;
import com.dtdhehe.ptulife.vo.AnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private  PtuAnswerRepository ptuAnswerRepository;


@Override
public void delAnswerById(String answerId){
    ptuAnswerRepository.deleteById(answerId);
}


@Override
public PtuAnswer save(PtuAnswer ptuAnswer){
    return ptuAnswerRepository.save(ptuAnswer);
}


@Override
public Page<AnswerDto> queryAllAnswerWithHead(Pageable pageable){
    return ptuAnswerRepository.queryAllAnswerWithHead(pageable);
}


@Override
public List<PtuAnswer> queryAllAnswer(){
    return ptuAnswerRepository.findAll();
}


@Override
public PtuAnswer queryAnswerById(String answerId){
    return ptuAnswerRepository.findById(answerId).get();
}


@Override
public Page<PtuAnswer> queryAnswerByUserId(String userId,String answerTitle,Pageable pageable){
    return ptuAnswerRepository.findByUserId(userId, answerTitle, pageable);
}


}