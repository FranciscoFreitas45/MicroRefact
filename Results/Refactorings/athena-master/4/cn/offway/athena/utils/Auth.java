import java.net.URI;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.qiniu.http.Client;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;
public class Auth {

 private  String[] policyFields;

 private  String[] deprecatedPolicyFields;

 public  String accessKey;

 private  SecretKeySpec secretKey;


public String signRoomToken(String roomAccess){
    String encodedRoomAcc = UrlSafeBase64.encodeToString(roomAccess);
    byte[] sign = createMac().doFinal(encodedRoomAcc.getBytes());
    String encodedSign = UrlSafeBase64.encodeToString(sign);
    return this.accessKey + ":" + encodedSign + ":" + encodedRoomAcc;
}


public String signWithData(String data){
    return signWithData(StringUtils.utf8Bytes(data));
}


public String privateDownloadUrl(String baseUrl,long expires){
    long deadline = System.currentTimeMillis() / 1000 + expires;
    return privateDownloadUrlWithDeadline(baseUrl, deadline);
}


public String uploadToken(String bucket,String key,long expires,StringMap policy,boolean strict){
    long deadline = System.currentTimeMillis() / 1000 + expires;
    return uploadTokenWithDeadline(bucket, key, deadline, policy, strict);
}


public StringMap authorizationV2(String url){
    return authorizationV2(url, "GET", null, null);
}


public String sign(String data){
    return sign(StringUtils.utf8Bytes(data));
}


@Override
public void accept(String key,Object value){
    if (StringUtils.inStringArray(key, deprecatedPolicyFields)) {
        throw new IllegalArgumentException(key + " is deprecated!");
    }
    if (!strict || StringUtils.inStringArray(key, policyFields)) {
        policy.put(key, value);
    }
}


public StringMap authorization(String url){
    return authorization(url, null, null);
}


public String uploadTokenWithPolicy(Object obj){
    String s = Json.encode(obj);
    return signWithData(StringUtils.utf8Bytes(s));
}


public boolean isValidCallback(String originAuthorization,String url,byte[] body,String contentType){
    String authorization = "QBox " + signRequest(url, body, contentType);
    return authorization.equals(originAuthorization);
}


public String signRequestV2(String urlString,String method,byte[] body,String contentType){
    URI uri = URI.create(urlString);
    Mac mac = createMac();
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%s %s", method, uri.getPath()));
    if (uri.getQuery() != null) {
        sb.append(String.format("?%s", uri.getQuery()));
    }
    sb.append(String.format("\nHost: %s", uri.getHost()));
    if (uri.getPort() > 0) {
        sb.append(String.format(":%d", uri.getPort()));
    }
    if (contentType != null) {
        sb.append(String.format("\nContent-Type: %s", contentType));
    }
    // body
    sb.append("\n\n");
    if (body != null && body.length > 0 && !StringUtils.isNullOrEmpty(contentType)) {
        if (contentType.equals(Client.FormMime) || contentType.equals(Client.JsonMime)) {
            sb.append(new String(body));
        }
    }
    mac.update(StringUtils.utf8Bytes(sb.toString()));
    String digest = UrlSafeBase64.encodeToString(mac.doFinal());
    return this.accessKey + ":" + digest;
}


public Auth create(String accessKey,String secretKey){
    if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey)) {
        throw new IllegalArgumentException("empty key");
    }
    byte[] sk = StringUtils.utf8Bytes(secretKey);
    SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
    return new Auth(accessKey, secretKeySpec);
}


public Mac createMac(){
    Mac mac;
    try {
        mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
    } catch (GeneralSecurityException e) {
        e.printStackTrace();
        throw new IllegalArgumentException(e);
    }
    return mac;
}


public void copyPolicy(StringMap policy,StringMap originPolicy,boolean strict){
    if (originPolicy == null) {
        return;
    }
    originPolicy.forEach(new StringMap.Consumer() {

        @Override
        public void accept(String key, Object value) {
            if (StringUtils.inStringArray(key, deprecatedPolicyFields)) {
                throw new IllegalArgumentException(key + " is deprecated!");
            }
            if (!strict || StringUtils.inStringArray(key, policyFields)) {
                policy.put(key, value);
            }
        }
    });
}


public String uploadTokenWithDeadline(String bucket,String key,long deadline,StringMap policy,boolean strict){
    // TODO   UpHosts Global
    String scope = bucket;
    if (key != null) {
        scope = bucket + ":" + key;
    }
    StringMap x = new StringMap();
    copyPolicy(x, policy, strict);
    x.put("scope", scope);
    x.put("deadline", deadline);
    String s = Json.encode(x);
    return signWithData(StringUtils.utf8Bytes(s));
}


public String signRequest(String urlString,byte[] body,String contentType){
    URI uri = URI.create(urlString);
    String path = uri.getRawPath();
    String query = uri.getRawQuery();
    Mac mac = createMac();
    mac.update(StringUtils.utf8Bytes(path));
    if (query != null && query.length() != 0) {
        mac.update((byte) ('?'));
        mac.update(StringUtils.utf8Bytes(query));
    }
    mac.update((byte) '\n');
    if (body != null && Client.FormMime.equalsIgnoreCase(contentType)) {
        mac.update(body);
    }
    String digest = UrlSafeBase64.encodeToString(mac.doFinal());
    return this.accessKey + ":" + digest;
}


public String privateDownloadUrlWithDeadline(String baseUrl,long deadline){
    StringBuilder b = new StringBuilder();
    b.append(baseUrl);
    int pos = baseUrl.indexOf("?");
    if (pos > 0) {
        b.append("&e=");
    } else {
        b.append("?e=");
    }
    b.append(deadline);
    String token = sign(StringUtils.utf8Bytes(b.toString()));
    b.append("&token=");
    b.append(token);
    return b.toString();
}


}