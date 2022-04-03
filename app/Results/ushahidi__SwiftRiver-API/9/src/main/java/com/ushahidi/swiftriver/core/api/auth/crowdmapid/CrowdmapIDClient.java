package com.ushahidi.swiftriver.core.api.auth.crowdmapid;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
public class CrowdmapIDClient {

 private  String serverURL;

 private  String apiKey;

 private  String apiKeyParamName;

 private  HttpClient httpClient;

 private  Logger logger;

@Autowired
 private  ObjectMapper jacksonObjectMapper;

/**
 * Default constructor
 */
public CrowdmapIDClient() {
}/**
 * Initializes the <code>CrowdmapIDClient</code> with the url of the
 * <code>CrowdmapID</code> server, the api key for authenticating
 * requests and the name of the request parameter used to specify
 * the api key
 *
 * @param serverURL
 * @param apiKey
 * @param apiKeyParamName
 */
public CrowdmapIDClient(String serverURL, String apiKey, String apiKeyParamName) {
    setServerURL(serverURL);
    setApiKey(apiKey);
    setApiKeyParamName(apiKeyParamName);
    httpClient = new DefaultHttpClient();
}
public boolean setPassword(String token,String email,String password){
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("passsword", password));
    params.add(new BasicNameValuePair("token", token));
    return !executeApiRequest(CrowdmapIDRequestType.SETPASSWORD, params).isEmpty();
}


public void setHttpClient(HttpClient httpClient){
    this.httpClient = httpClient;
}


public String getApiKey(){
    return apiKey;
}


public Map<String,Object> executeApiRequest(CrowdmapIDRequestType apiRequest,List<NameValuePair> requestParams){
    // Base URI for the request
    String baseURIStr = getBaseRequestUrl(apiRequest);
    Map<String, Object> responseMap = new HashMap<String, Object>();
    // Build the request and set parameters
    HttpPost httpPost = new HttpPost(baseURIStr);
    requestParams.add(new BasicNameValuePair(this.apiKeyParamName, this.apiKey));
    try {
        httpPost.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
    } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
    }
    String apiResponse = null;
    // Execute the request
    try {
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        apiResponse = readApiResponse(httpResponse);
        if (statusCode != 200) {
            logger.error("The server returned status {} - {}", statusCode, apiResponse);
            return responseMap;
        }
    } catch (ClientProtocolException e) {
        logger.error("An error occurred when processing request: {} - {}", this.serverURL, e.getMessage());
    } catch (IOException e) {
        logger.error("An error occurred when reading the response from the server: {} - {}", this.serverURL, e.getMessage());
    }
    // Deserialize the response into a java.util.Map
    if (apiResponse != null) {
        try {
            responseMap = jacksonObjectMapper.readValue(apiResponse, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Check for the status
    if (!responseMap.isEmpty()) {
        Boolean status = (Boolean) responseMap.get("success");
        if (!status) {
            logger.error("The API returned the following error message: {}", responseMap.get("error"));
            // Clear all mappings and return an empty map
            responseMap.clear();
        }
    }
    return responseMap;
}


public void setApiKey(String apiKey){
    this.apiKey = apiKey;
}


public void setObjectMapper(ObjectMapper objectMapper){
    this.jacksonObjectMapper = objectMapper;
}


public boolean changePassword(String email,String oldPassword,String newPassword){
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("oldpassword", oldPassword));
    params.add(new BasicNameValuePair("newpassword", newPassword));
    Map<String, Object> response = executeApiRequest(CrowdmapIDRequestType.CHANGEPASSWORD, params);
    return !response.isEmpty();
}


public String getApiKeyParamName(){
    return apiKeyParamName;
}


public String readApiResponse(HttpResponse httpResponse) throws IOException{
    HttpEntity entity = httpResponse.getEntity();
    // Write the server to response to a buffer
    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
    StringBuffer buffer = new StringBuffer();
    String output = null;
    while ((output = reader.readLine()) != null) {
        buffer.append(output);
    }
    // Close the reader
    reader.close();
    return buffer.toString();
}


public void requestPassword(String email,String mailBody){
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("mailbody", mailBody));
    executeApiRequest(CrowdmapIDRequestType.REQUESTPASSWORD, params);
}


public String getBaseRequestUrl(CrowdmapIDRequestType apiRequest){
    switch(apiRequest) {
        case SIGNIN:
            return serverURL + "/signin";
        case CHANGEPASSWORD:
            return serverURL + "/changepassword";
        case REGISTER:
            return serverURL + "/register";
        case REQUESTPASSWORD:
            return serverURL + "/requestpassword";
        case SETPASSWORD:
            return serverURL + "/setpassword";
        case REGISTERED:
            return serverURL + "/registered";
    }
    return serverURL;
}


public void setApiKeyParamName(String apiKeyParamName){
    this.apiKeyParamName = apiKeyParamName;
}


public void setServerURL(String serverURL){
    this.serverURL = serverURL;
}


public boolean signIn(String email,String password){
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("password", password));
    Map<String, Object> apiResponse = executeApiRequest(CrowdmapIDRequestType.SIGNIN, params);
    return !apiResponse.isEmpty();
}


public boolean isRegistered(String email){
    logger.debug("Checking if {} is registered", email);
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    Map<String, Object> response = executeApiRequest(CrowdmapIDRequestType.REGISTERED, params);
    if (!response.isEmpty()) {
        return (Boolean) response.get("response");
    }
    return true;
}


public String getServerURL(){
    return serverURL;
}


public String register(String email,String password){
    // Check if the email is already registered
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("email", email));
    params.add(new BasicNameValuePair("password", password));
    Map<String, Object> response = executeApiRequest(CrowdmapIDRequestType.REGISTER, params);
    return response.isEmpty() ? null : (String) response.get("response");
}


}