package com.webapp.repository;
 import com.webapp.models.User;
import com.webapp.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface WordRepository extends JpaRepository<Word, Long>{


public boolean existsByOriginalAndUserId(String original,Long userId)
;

public Optional<Word> findByOriginal(String original)
;

public List<Word> findByUser(User getRequest)
;

public Boolean existsByOriginal(String original)
;

}