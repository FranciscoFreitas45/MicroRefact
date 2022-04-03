package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.UserRepository;
import com.puffride.demo.entity.User;
@Service
public class UserDriverService {

@Autowired
 private UserRepository userrepository;


}