package run.halo.app.utils;
 import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import run.halo.app.exception.AuthenticationException;
import run.halo.app.exception.BadRequestException;
public class TwoFactorAuthUtils {

 private  int VALID_TFA_WINDOW_MILLIS;

 public  int DEFAULT_TIME_STEP_SECONDS;

 private  String BLOCK_OF_ZEROS;

 private  int NUM_DIGITS_OUTPUT;


public void validateTFACode(String tfaKey,String tfaCode){
    try {
        int validCode = Integer.parseInt(tfaCode);
        boolean result = TimeBasedOneTimePasswordUtil.validateCurrentNumber(tfaKey, validCode, VALID_TFA_WINDOW_MILLIS);
        if (!result) {
            throw new BadRequestException("两步验证码验证错误，请确认时间是否同步");
        }
    } catch (NumberFormatException e) {
        throw new BadRequestException("两步验证码请输入数字");
    } catch (GeneralSecurityException e) {
        throw new BadRequestException("两步验证码验证异常");
    }
}


public boolean validateCurrentNumber(String base32Secret,int authNumber,int windowMillis,long timeMillis,int timeStepSeconds){
    long fromTimeMillis = timeMillis;
    long toTimeMillis = timeMillis;
    if (windowMillis > 0) {
        fromTimeMillis -= windowMillis;
        toTimeMillis += windowMillis;
    }
    long timeStepMillis = timeStepSeconds * 1000;
    for (long millis = fromTimeMillis; millis <= toTimeMillis; millis += timeStepMillis) {
        int generatedNumber = generateNumber(base32Secret, millis, timeStepSeconds);
        if (generatedNumber == authNumber) {
            return true;
        }
    }
    return false;
}


public String generateTFACode(String tfaKey){
    try {
        return TimeBasedOneTimePasswordUtil.generateCurrentNumberString(tfaKey);
    } catch (GeneralSecurityException e) {
        throw new AuthenticationException("两步验证码生成异常");
    }
}


public int generateNumber(String base32Secret,long timeMillis,int timeStepSeconds){
    byte[] key = decodeBase32(base32Secret);
    byte[] data = new byte[8];
    long value = timeMillis / 1000 / timeStepSeconds;
    for (int i = 7; value > 0; i--) {
        data[i] = (byte) (value & 0xFF);
        value >>= 8;
    }
    // encrypt the data with the key and return the SHA1 of it in hex
    SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
    // if this is expensive, could put in a thread-local
    Mac mac = Mac.getInstance("HmacSHA1");
    mac.init(signKey);
    byte[] hash = mac.doFinal(data);
    // take the 4 least significant bits from the encrypted string as an offset
    int offset = hash[hash.length - 1] & 0xF;
    // We're using a long because Java hasn't got unsigned int.
    long truncatedHash = 0;
    for (int i = offset; i < offset + 4; ++i) {
        truncatedHash <<= 8;
        // get the 4 bytes at the offset
        truncatedHash |= hash[i] & 0xFF;
    }
    // cut off the top bit
    truncatedHash &= 0x7FFFFFFF;
    // the token is then the last 6 digits in the number
    truncatedHash %= 1000000;
    // this is only 6 digits so we can safely case it
    return (int) truncatedHash;
}


public String generateOtpAuthUrl(String keyId,String secret){
    StringBuilder sb = new StringBuilder(64);
    addOtpAuthPart(keyId, secret, sb);
    return sb.toString();
}


public String generateTFAKey(){
    return TimeBasedOneTimePasswordUtil.generateBase32Secret(32);
}


public String generateNumberString(String base32Secret,long timeMillis,int timeStepSeconds){
    int number = generateNumber(base32Secret, timeMillis, timeStepSeconds);
    return zeroPrepend(number, NUM_DIGITS_OUTPUT);
}


public String generateBase32Secret(int length){
    StringBuilder sb = new StringBuilder(length);
    Random random = new SecureRandom();
    for (int i = 0; i < length; i++) {
        int val = random.nextInt(32);
        if (val < 26) {
            sb.append((char) ('A' + val));
        } else {
            sb.append((char) ('2' + (val - 26)));
        }
    }
    return sb.toString();
}


public String generateCurrentNumberString(String base32Secret){
    return generateNumberString(base32Secret, System.currentTimeMillis(), DEFAULT_TIME_STEP_SECONDS);
}


public int generateCurrentNumber(String base32Secret){
    return generateNumber(base32Secret, System.currentTimeMillis(), DEFAULT_TIME_STEP_SECONDS);
}


public String qrImageUrl(String keyId,String secret){
    StringBuilder sb = new StringBuilder(128);
    sb.append("https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=200x200&chld=M|0" + "&cht=qr&chl=");
    addOtpAuthPart(keyId, secret, sb);
    return sb.toString();
}


public String zeroPrepend(int num,int digits){
    String numStr = Integer.toString(num);
    if (numStr.length() >= digits) {
        return numStr;
    } else {
        StringBuilder sb = new StringBuilder(digits);
        int zeroCount = digits - numStr.length();
        sb.append(BLOCK_OF_ZEROS, 0, zeroCount);
        sb.append(numStr);
        return sb.toString();
    }
}


public byte[] decodeBase32(String str){
    // each base-32 character encodes 5 bits
    int numBytes = ((str.length() * 5) + 7) / 8;
    byte[] result = new byte[numBytes];
    int resultIndex = 0;
    int which = 0;
    int working = 0;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        int val;
        if (ch >= 'a' && ch <= 'z') {
            val = ch - 'a';
        } else if (ch >= 'A' && ch <= 'Z') {
            val = ch - 'A';
        } else if (ch >= '2' && ch <= '7') {
            val = 26 + (ch - '2');
        } else if (ch == '=') {
            // special case
            which = 0;
            break;
        } else {
            throw new IllegalArgumentException("Invalid base-32 character: " + ch);
        }
        /*
                 * There are probably better ways to do this but this seemed the most
                 * straightforward.
                 */
        switch(which) {
            case 0:
                // all 5 bits is top 5 bits
                working = (val & 0x1F) << 3;
                which = 1;
                break;
            case 1:
                // top 3 bits is lower 3 bits
                working |= (val & 0x1C) >> 2;
                result[resultIndex++] = (byte) working;
                // lower 2 bits is upper 2 bits
                working = (val & 0x03) << 6;
                which = 2;
                break;
            case 2:
                // all 5 bits is mid 5 bits
                working |= (val & 0x1F) << 1;
                which = 3;
                break;
            case 3:
                // top 1 bit is lowest 1 bit
                working |= (val & 0x10) >> 4;
                result[resultIndex++] = (byte) working;
                // lower 4 bits is top 4 bits
                working = (val & 0x0F) << 4;
                which = 4;
                break;
            case 4:
                // top 4 bits is lowest 4 bits
                working |= (val & 0x1E) >> 1;
                result[resultIndex++] = (byte) working;
                // lower 1 bit is top 1 bit
                working = (val & 0x01) << 7;
                which = 5;
                break;
            case 5:
                // all 5 bits is mid 5 bits
                working |= (val & 0x1F) << 2;
                which = 6;
                break;
            case 6:
                // top 2 bits is lowest 2 bits
                working |= (val & 0x18) >> 3;
                result[resultIndex++] = (byte) working;
                // lower 3 bits of byte 6 is top 3 bits
                working = (val & 0x07) << 5;
                which = 7;
                break;
            case 7:
                // all 5 bits is lower 5 bits
                working |= val & 0x1F;
                result[resultIndex++] = (byte) working;
                which = 0;
                break;
            default:
                // should never happen
                throw new IllegalArgumentException();
        }
    }
    if (which != 0) {
        result[resultIndex++] = (byte) working;
    }
    if (resultIndex != result.length) {
        result = Arrays.copyOf(result, resultIndex);
    }
    return result;
}


public void addOtpAuthPart(String keyId,String secret,StringBuilder sb){
    sb.append("otpauth://totp/").append(keyId).append("?secret=").append(secret);
}


}