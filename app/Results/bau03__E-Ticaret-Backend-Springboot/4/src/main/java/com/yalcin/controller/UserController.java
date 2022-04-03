package com.yalcin.controller;
 import com.yalcin.dto.request;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.entity;
import com.yalcin.enums.ErrorCodes;
import com.yalcin.event.OnRegistrationSuccessEvent;
import com.yalcin.exception.BadRequestException;
import com.yalcin.exception.ErrorWhileSendingEmailException;
import com.yalcin.exception.UnauthorizedException;
import com.yalcin.repository;
import com.yalcin.security.jwt.JwtProvider;
import com.yalcin.security.services.AdressService;
import com.yalcin.security.services.UserDetailImpl;
import com.yalcin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserServiceImpl;
import com.yalcin.Interface.ActiveSessionsRepository;
import com.yalcin.Interface.AdressService;
import com.yalcin.Interface.SellerBeginService;
import com.yalcin.Interface.ProductService;
import com.yalcin.Interface.OrderRepository;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.DTO.ActiveSessions;
import com.yalcin.DTO.SuccessResponse;
@RestController
@RequestMapping("ecommerce/user")
public class UserController {

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private UserRepository userRepository;

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private ActiveSessionsRepository activeSessionsRepository;

@Autowired
 private  ApplicationEventPublisher eventPublisher;

@Autowired
 private AdressService adressService;

@Autowired
 private SellerBeginService sellerBeginService;

@Autowired
 private ProductService productService;

@Autowired
 private PurseRepository purseRepository;

@Autowired
 private PurseService purseService;

@Autowired
 private OrderRepository orderRepository;

@Autowired
 private ShowcaseService showcaseService;

@Autowired
 private ShowcaseRepository showcaseRepository;

@Autowired
 private ProductRepository productRepository;

@Autowired
 private JwtProvider jwtProvider;


@GetMapping("/showcase/detail")
public ResponseEntity<?> getShowcaseDetails(){
    List<Showcase> showcaseList = showcaseRepository.findAll();
    for (int i = showcaseList.size() - 1; i >= 0; i--) {
        if (showcaseList.get(i).getEndTime().before(new Date())) {
            Product product = productRepository.findAllById(showcaseList.get(i).getProduct().getId());
            product.setShowcaseEnabled(false);
            productRepository.save(product);
            showcaseRepository.delete(showcaseList.get(i));
        }
    }
    return ResponseEntity.ok().body(showcaseRepository.findAll());
}


@GetMapping("/detail")
public ResponseEntity<?> getUser(){
    UserDetailImpl userDetails = userServiceImpl.getUserDetails(SecurityContextHolder.getContext().getAuthentication());
    return ResponseEntity.ok().body(userDetails);
}


@GetMapping("/adress/{userId}")
public ResponseEntity<?> getContent(String userId){
    return ResponseEntity.ok().body(adressService.getAdress(userId));
}


@GetMapping("/order")
public ResponseEntity<?> getAdminOrder(){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    return ResponseEntity.ok().body(orderRepository.findAllByUsername(user.getId()));
}


@PutMapping("/edit")
@Transactional
public ResponseEntity<?> editUser(ChangeForm changeForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    if (changeForm.getEmail() != null && !changeForm.getEmail().equals(user.getEmail())) {
        if (userServiceImpl.existsByEmail(changeForm.getEmail())) {
            return new ResponseEntity<String>("Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        Set<ActiveSessions> activeSessionsForUserWithCurrentEmail = user.getActiveSessions();
        user.setActiveSessions(null);
        userRepository.save(user);
        for (ActiveSessions activeSession : activeSessionsForUserWithCurrentEmail) {
            if (activeSessionsRepository.existsById(activeSession.getRefreshToken())) {
                activeSessionsRepository.delete(activeSession);
            }
        }
        userServiceImpl.changeEmail(user, changeForm.getEmail());
        System.out.println("I cannot reach here!");
        try {
            eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, "/api/auth"));
        } catch (Exception re) {
            throw new ErrorWhileSendingEmailException(re.getMessage());
        }
    }
    userServiceImpl.editUser(user, changeForm.getName(), changeForm.getLastname(), changeForm.getAge(), changeForm.getPhoneNumber());
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "User details successfuly changed.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@PostMapping("/purse")
public ResponseEntity<?> registerUser(PurseForm purseForm){
    purseService.purseAdd(purseForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Bakiye Yüklendi");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@PostMapping("/showcase")
public ResponseEntity<?> createShowcase(ShowcaseForm showcaseForm){
    showcaseService.showcaseSave(showcaseForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Ürün vitrine eklendi.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@GetMapping("/purse/all")
public ResponseEntity<?> getUserPurse(){
    return ResponseEntity.ok().body(purseService.getPurse());
}


@GetMapping("/product/{userId}")
public ResponseEntity<?> getUserProduct(String userId){
    return ResponseEntity.ok().body(productService.getUserProduct(userId));
}


@PostMapping("/adress")
public ResponseEntity<?> editUserRole(AdressFrom adressFrom){
    adressService.adressSave(adressFrom);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Successfully adress add.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@PostMapping("/create-new-password")
public ResponseEntity<?> createNewPassword(ForgotAndChangePasswordForm forgotAndChangePasswordForm,HttpServletRequest request){
    if (!forgotAndChangePasswordForm.checkAllFieldsAreGiven(forgotAndChangePasswordForm)) {
        throw new BadRequestException("All fields should be given", ErrorCodes.REQUIRE_ALL_FIELDS);
    } else {
        String token = forgotAndChangePasswordForm.getToken();
        if (token != null && jwtProvider.validateJwtToken(token, "password", request)) {
            User user = userServiceImpl.getUserByToken(token, "password");
            if (!forgotAndChangePasswordForm.getNewPassword().equals(forgotAndChangePasswordForm.getNewPasswordConfirmation())) {
                throw new BadRequestException("Password fields does not match", ErrorCodes.NEW_PASSWORD_DOES_NOT_MATCH);
            } else if (forgotAndChangePasswordForm.getNewPassword().equals(forgotAndChangePasswordForm.getNewPasswordConfirmation())) {
                userServiceImpl.setNewPassword(user, forgotAndChangePasswordForm.getNewPassword());
                SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Password successfully changed");
                return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
            }
        } else {
            throw new UnauthorizedException("Something is wrong with token", ErrorCodes.INVALID_ACCESS_TOKEN);
        }
    }
    throw new BadRequestException("Something is wrong", ErrorCodes.SOMETHING_IS_WRONG);
}


@PostMapping("/sellerBegin")
public ResponseEntity<?> createNewWrite(SellerBeginForm sellerBeginForm){
    sellerBeginService.save(sellerBeginForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Satıcı olma başvurusu alındı");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}