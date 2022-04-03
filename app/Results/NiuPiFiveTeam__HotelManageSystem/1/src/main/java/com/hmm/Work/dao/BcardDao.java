package com.hmm.Work.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.Work.entity.Bcard;
@Repository
public interface BcardDao extends JpaSpecificationExecutor<Bcard>, PagingAndSortingRepository<Bcard, Long>{


public Set<Bcard> getBcards(Integer emp_id);

public void setBcards(Integer emp_id,Set<Bcard> bcards);

public Set<Bcard> getBcards(Integer emp_id);

public void setBcards(Integer emp_id,Set<Bcard> bcards);

}