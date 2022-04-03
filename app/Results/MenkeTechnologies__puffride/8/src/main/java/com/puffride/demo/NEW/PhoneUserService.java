package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.PhoneRepository;
import com.puffride.demo.entity.Phone;
@Service
public class PhoneUserService {

@Autowired
 private PhoneRepository phonerepository;


}