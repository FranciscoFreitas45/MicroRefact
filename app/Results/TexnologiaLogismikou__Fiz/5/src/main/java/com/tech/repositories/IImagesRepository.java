package com.tech.repositories;
 import com.tech.models.entities.ImagesMod;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IImagesRepository extends JpaRepository<ImagesMod, Long>{


public List<ImagesMod> findByUserid(Long id)
;

public ImagesMod findByHashtag(Long hashtag)
;

}