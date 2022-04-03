package com.yalcin.service;
 import com.yalcin.dto.request.ShowcaseForm;
import com.yalcin.entity;
import com.yalcin.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.lang.Integer.parseInt;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserServiceImpl;
@Service
public class ShowcaseService {

@Autowired
 private ProductRepository productRepository;

@Autowired
 private ShowcaseRepository showcaseRepository;

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private UserRepository userRepository;

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private PurseRepository purseRepository;

@Autowired
 private PurseService purseService;


public void showcaseSave(ShowcaseForm showcaseForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Purse purse = purseRepository.findAllByUser_Id(user.getId());
    Product product = productRepository.findAllById(parseInt(showcaseForm.getProductId()));
    Showcase showcase = new Showcase(showcaseForm.getEndTime(), Float.parseFloat(showcaseForm.getPrice()));
    showcase.setProduct(product);
    showcase.setUser(user);
    showcase.setEnabled(true);
    showcase.setStartTime(new Date());
    purse.setBalance(purse.getBalance() - Float.parseFloat(showcaseForm.getPrice()));
    purseRepository.save(purse);
    showcaseRepository.save(showcase);
    product.setShowcaseEnabled(true);
    productRepository.save(product);
}


}