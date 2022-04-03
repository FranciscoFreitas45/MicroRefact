package es.us.isa.ideas.app.controllers;
 import com.google.common.base.Strings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import es.us.isa.ideas.app.configuration.StudioConfiguration;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Controller
@RequestMapping("/js")
public class AceProxy extends AbstractController{

 private  Logger LOGGER;

 private  String PARENT_PATH;

 private  String ACE_LIB;

 private  String JS_EXT;

 private  String MODE_PREFIX;

 private  String THEME_PREFIX;

 private  String DEPRECATED_MANIFEST_ENDPOINT;

 private  String DEPRECATED_FORMAT_ENDPOINT;

 private  String SYNTAX_ENDPOINT;

@Autowired
 private  ServletContext servletContext;

@Autowired
 private  StudioConfiguration studioConfiguration;

 private  Map<String,String> modeUriCache;


@Override
public void checkClientTrusted(X509Certificate[] certs,String authType){
}


@RequestMapping(value = "/ace-reset", method = RequestMethod.GET)
@ResponseBody
public String resetCache(){
    modeUriCache.clear();
    return "Mode cache was cleared successfully";
}


public String getAceFile(){
    String content = "";
    try {
        InputStream input = servletContext.getResourceAsStream(PARENT_PATH + ACE_LIB + JS_EXT);
        StringWriter writer = new StringWriter();
        IOUtils.copy(input, writer);
        content = writer.toString();
    } catch (Exception e) {
        LOGGER.severe(e.getMessage());
    }
    return content;
}


@Override
public void checkServerTrusted(X509Certificate[] certs,String authType){
}


public String setFormats(JSONObject language,String fileName,String languageModuleUri,Double version){
    String result = "";
    String languageId = language.getString(("id"));
    JSONArray syntaxes;
    if (version >= 2.0) {
        syntaxes = language.getJSONArray("syntaxes");
    } else {
        syntaxes = language.getJSONArray("formats");
    }
    for (int i = 0; i < syntaxes.length(); i++) {
        JSONObject syntax = syntaxes.getJSONObject(i);
        String syntaxId;
        if (version >= 2.0) {
            syntaxId = syntax.getString("id");
        } else {
            syntaxId = syntax.getString("format");
        }
        if (version >= 2.0) {
            if (!syntax.isNull("editorModeURI")) {
                String editorModeURI = syntax.getString("editorModeURI");
                if (fileName.startsWith(MODE_PREFIX) && editorModeURI != null && editorModeURI.equals(fileName + JS_EXT)) {
                    String uri = "";
                    uri = languageModuleUri + "/models/" + languageId + SYNTAX_ENDPOINT + "/" + syntaxId + "/mode";
                    LOGGER.log(Level.INFO, "Loading mode from: {0}", uri);
                    result = requestContent(uri);
                    modeUriCache.put(fileName, uri);
                    return result;
                }
            }
        } else if (!syntax.isNull("_editorModeURI")) {
            String editorModeURI = syntax.getString("_editorModeURI");
            if (fileName.startsWith(MODE_PREFIX) && editorModeURI != null && editorModeURI.equals(fileName + JS_EXT)) {
                String uri = "";
                uri = languageModuleUri + DEPRECATED_MANIFEST_ENDPOINT + "/" + DEPRECATED_FORMAT_ENDPOINT + "/" + syntaxId + "/mode";
                LOGGER.log(Level.INFO, "Loading mode from: {0}", uri);
                result = requestContent(uri);
                modeUriCache.put(fileName, uri);
                return result;
            }
        }
        if (version >= 2.0) {
            if (!syntax.isNull("editorThemeURI")) {
                String editorThemeURI = syntax.getString("editorThemeURI");
                if (fileName.startsWith(THEME_PREFIX)) {
                    LOGGER.log(Level.INFO, "Is {0} equal to {1}" + JS_EXT + "?", new Object[] { editorThemeURI, fileName });
                    if (editorThemeURI != null && editorThemeURI.equals(fileName + JS_EXT)) {
                        String uri = "";
                        uri = languageModuleUri + "/models/" + languageId + SYNTAX_ENDPOINT + "/" + syntaxId + "/theme";
                        LOGGER.log(Level.INFO, "Loading theme from: {0}", uri);
                        result = requestContent(uri);
                        modeUriCache.put(fileName, uri);
                        return result;
                    }
                }
            }
        } else if (!syntax.isNull("_editorThemeURI")) {
            String editorThemeURI = syntax.getString("_editorThemeURI");
            if (fileName.startsWith(THEME_PREFIX)) {
                LOGGER.log(Level.INFO, "Is {0} equal to {1}" + JS_EXT + "?", new Object[] { editorThemeURI, fileName });
                if (editorThemeURI != null && editorThemeURI.equals(fileName + JS_EXT)) {
                    String uri = "";
                    uri = languageModuleUri + DEPRECATED_MANIFEST_ENDPOINT + "/" + DEPRECATED_FORMAT_ENDPOINT + "/" + syntaxId + "/theme";
                    LOGGER.log(Level.INFO, "Loading theme from: {0}", uri);
                    result = requestContent(uri);
                    modeUriCache.put(fileName, uri);
                    return result;
                }
            }
        }
    }
    return result;
}


@Override
public boolean verify(String hostname,SSLSession session){
    return true;
}


@Override
public X509Certificate[] getAcceptedIssuers(){
    return null;
}


public String getRemoteAceContent(String fileName){
    String result = "";
    if (modeUriCache.containsKey(fileName)) {
        LOGGER.log(Level.INFO, "Getting from cache for '" + fileName + "' (uri=" + modeUriCache.get(fileName) + ")");
        return requestContent(modeUriCache.get(fileName));
    } else {
        for (String moduleEndpoint : studioConfiguration.getModules().values()) {
            String languageModuleUri = moduleEndpoint;
            String languageString = requestContent(languageModuleUri + DEPRECATED_MANIFEST_ENDPOINT);
            if (languageString.isEmpty()) {
                languageString = requestContent(languageModuleUri);
            }
            if (!Strings.isNullOrEmpty(languageString)) {
                JSONObject json;
                try {
                    json = new JSONObject(languageString);
                    Double version = 1.0;
                    try {
                        version = json.getDouble("apiVersion");
                        if (version >= 2.0) {
                            JSONArray languages = json.getJSONArray("models");
                            for (int i = 0; i < languages.length(); i++) {
                                JSONObject language = languages.getJSONObject(i);
                                result = setFormats(language, fileName, languageModuleUri, version);
                                if (!result.isEmpty()) {
                                    return result;
                                }
                            }
                        } else {
                            result = setFormats(json, fileName, languageModuleUri, version);
                        }
                    } catch (JSONException ex) {
                        result = setFormats(json, fileName, languageModuleUri, version);
                    }
                } catch (JSONException ex) {
                    LOGGER.log(Level.SEVERE, "Error retrieving remote ace content: {0}", ex.getMessage());
                }
            } else {
                Logger.getLogger(AceProxy.class.getName()).severe("Failed loading language manifest from " + languageModuleUri);
            }
        }
    }
    return result;
}


@RequestMapping(value = "/ace/{file}", method = RequestMethod.GET)
@ResponseBody
public String getAceproxyContent(String file,HttpServletRequest request,HttpServletResponse response){
    response.setContentType("text/javascript");
    response.setCharacterEncoding("UTF-8");
    if (file.equals(ACE_LIB)) {
        return getAceFile();
    } else if (file.startsWith(MODE_PREFIX)) {
        return getRemoteAceContent(file);
    } else if (file.startsWith(THEME_PREFIX)) {
        return getRemoteAceContent(file);
    } else {
        return "Unexpected file: " + file;
    }
}


public String requestContent(String stringUrl){
    String result = "";
    try {
        URL url = new URL(stringUrl);
        // Workaround for SSL problem doing the module servlets requests
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        LOGGER.log(Level.INFO, "Getting content from: " + url);
        conn.connect();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result += inputLine + System.getProperty("line.separator");
            }
        }
    } catch (MalformedURLException e) {
        LOGGER.severe(e.getMessage());
    } catch (IOException e) {
        LOGGER.severe(e.getMessage());
    } catch (KeyManagementException e) {
        LOGGER.severe(e.getMessage());
    } catch (NoSuchAlgorithmException e) {
        LOGGER.severe(e.getMessage());
    } catch (Exception e) {
        LOGGER.severe(e.getMessage());
    }
    return result;
}


}