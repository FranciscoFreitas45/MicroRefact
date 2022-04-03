package com.fzshuai.service;
 import com.fzshuai.po.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PictureService {


public Picture updatePicture(Long id,Picture picture)
;

public Picture getPicture(Long id)
;

public void deletePicture(Long id)
;

public Page<Picture> listPicture(Pageable pageable)
;

public Picture savePicture(Picture picture)
;

}