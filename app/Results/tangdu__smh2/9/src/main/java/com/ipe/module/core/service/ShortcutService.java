package com.ipe.module.core.service;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.common.service.BaseService;
import com.ipe.module.core.entity.Shortcut;
import com.ipe.module.core.repository.ShortcutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
public class ShortcutService extends BaseService<Shortcut, String>{

@Autowired
 private  ShortcutRepository shortcutRepository;


@Override
public CustomerRepository getRepository(){
    return shortcutRepository;
}


}