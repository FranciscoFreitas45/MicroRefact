package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.HotLabel;
import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.entity.PtuNews;
import com.dtdhehe.ptulife.repository.HotLableRepository;
import com.dtdhehe.ptulife.service.LabelService;
import com.dtdhehe.ptulife.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LabelServiceImpl implements LabelService{

@Autowired
 private  HotLableRepository hotLableRepository;


@Override
public Page<HotLabel> queryHotLable(Pageable pageable){
    return hotLableRepository.findAll(pageable);
}


@Override
public HotLabel save(HotLabel hotLabel){
    return hotLableRepository.save(hotLabel);
}


@Override
public List<HotLabel> findAllLabel(){
    return hotLableRepository.findAll();
}


}