package com.yalcin.service;
 import com.yalcin.dto.request.EditSellerForm;
import com.yalcin.dto.request.SellerBeginForm;
import com.yalcin.entity;
import com.yalcin.enums.ErrorCodes;
import com.yalcin.exception.BadRequestException;
import com.yalcin.repository.ProductRepository;
import com.yalcin.repository.RoleRepository;
import com.yalcin.repository.SellerBeginRepository;
import com.yalcin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.yalcin.Interface.UserServiceImpl;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.RoleRepository;
import com.yalcin.Interface.ProductRepository;
@Service
public class SellerBeginService {

@Autowired
 private SellerBeginRepository sellerBeginRepository;

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private UserRepository userRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private ProductRepository productRepository;


public List<SellerBegin> getSeller(){
    return sellerBeginRepository.findAll();
}


public void save(SellerBeginForm sellerBeginForm){
    SellerBegin sellerBegin = new SellerBegin(sellerBeginForm.getContent());
    sellerBegin.setTimestap(new Date());
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    sellerBegin.setUser(user);
    sellerBeginRepository.save(sellerBegin);
}


public void editUserRole(EditSellerForm editSellerForm){
    if (editSellerForm.getUserid() != null) {
        User user = userRepository.findById(Integer.parseInt(editSellerForm.getUserid())).orElseThrow(() -> new BadRequestException("No such user", ErrorCodes.NO_SUCH_USER));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(Roles.ROLE_SELLER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        if (userRepository.existsByUsername(user.getUsername())) {
            sellerBeginRepository.deleteById(Integer.parseInt(editSellerForm.getId()));
        }
    } else if (editSellerForm.getUserid() == null && editSellerForm.getId() != null) {
        sellerBeginRepository.deleteById(Integer.parseInt(editSellerForm.getId()));
    }
}


}