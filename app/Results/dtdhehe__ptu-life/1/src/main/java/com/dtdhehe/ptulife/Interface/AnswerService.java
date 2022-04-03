package com.dtdhehe.ptulife.Interface;
public interface AnswerService {

   public Page<PtuAnswer> queryAnswerByUserId(String userId,String answerTitle,Pageable pageable);
   public void delAnswerById(String answerId);
}