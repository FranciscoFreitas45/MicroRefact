package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.HotLabel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface LabelService {


public Page<HotLabel> queryHotLable(Pageable pageable)
;

public HotLabel save(HotLabel hotLabel)
;

public List<HotLabel> findAllLabel()
;

}