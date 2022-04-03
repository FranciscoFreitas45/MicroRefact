package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.vo.AnswerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PtuAnswerRepository extends JpaRepository<PtuAnswer, String>{


@Query(value = "select t from PtuAnswer t where t.userId=?1 and t.answerTitle like %?2%")
public Page<PtuAnswer> findByUserId(String userId,String answerTitle,Pageable pageable)
;

@Query(value = "select new com.dtdhehe.ptulife.vo.AnswerDto(t,u.headImg) from PtuAnswer t left join PtuUser u on t.userId=u.userId")
public Page<AnswerDto> queryAllAnswerWithHead(Pageable pageable)
;

public void setAnswerDate(String id,String answerDate);

}