package com.fzshuai.dao;
 import com.fzshuai.po.Picture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PictureRepository extends JpaRepository<Picture, Long>{


@Override
public List<Picture> findAll(Sort Sort)
;

}