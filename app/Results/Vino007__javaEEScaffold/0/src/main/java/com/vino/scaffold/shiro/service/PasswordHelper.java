package com.vino.scaffold.shiro.service;
 import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import com.vino.scaffold.shiro.entity.User;
@Service("passwordHelper")
public class PasswordHelper {

 private  RandomNumberGenerator randomNumberGenerator;

 private  String algorithmName;

 private  int hashIterations;


public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator){
    this.randomNumberGenerator = randomNumberGenerator;
}


public void setAlgorithmName(String algorithmName){
    this.algorithmName = algorithmName;
}


public void encryptPassword(User user){
    // 
    user.setSalt(randomNumberGenerator.nextBytes().toHex());
    String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
    user.setPassword(newPassword);
}


public void setHashIterations(int hashIterations){
    this.hashIterations = hashIterations;
}


}