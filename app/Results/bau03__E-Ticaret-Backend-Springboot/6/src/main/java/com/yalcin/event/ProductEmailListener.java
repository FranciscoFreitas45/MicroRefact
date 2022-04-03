package com.yalcin.event;
 import com.yalcin.entity.Product;
import com.yalcin.entity.User;
import com.yalcin.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.DTO.Product;
import com.yalcin.DTO.User;
@Component
public class ProductEmailListener implements ApplicationListener<ProductSuccessEvent>{

@Autowired
 private EmailSender emailSender;

@Autowired
 private JwtProvider jwtProvider;

@Autowired
 private  SpringTemplateEngine templateEngine;


public void confirmRegistration(ProductSuccessEvent event){
    User user = event.getUser();
    // String token = jwtProvider.generateJwtTokenForVerification(user);
    Product product = event.getProduct();
    String recipient = product.getUser().getEmail();
    String productName = product.getProductName();
    Map model = new HashMap();
    model.put("username", product.getUser().getUsername());
    model.put("productName", productName);
    model.put("name", user.getUsername());
    model.put("signature", "Ürün satış");
    Context context = new Context();
    context.setVariables(model);
    String content = templateEngine.process("db-product_email", context);
    emailSender.sendSimpleMessage(content, recipient, "Ürün Bilgisi");
}


@Override
public void onApplicationEvent(ProductSuccessEvent event){
    try {
        this.confirmRegistration(event);
    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
}


}