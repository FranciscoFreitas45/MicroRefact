package run.halo.app.utils;
 import cn.hutool.core.lang.Tuple;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
public class HttpClientUtils {

 private  int TIMEOUT;

 private  String filename;

private HttpClientUtils() {
}
@NonNull
public CloseableHttpClient createHttpsClient(int timeout){
    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
    return resolveProxySetting(HttpClients.custom()).setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).setDefaultRequestConfig(getRequestConfig(timeout)).build();
}


@Override
public String getFilename(){
    return this.filename;
}


public HttpClientBuilder resolveProxySetting(HttpClientBuilder httpClientBuilder){
    final String httpProxyEnv = System.getenv("http_proxy");
    if (StringUtils.isNotBlank(httpProxyEnv)) {
        final Tuple httpProxy = resolveHttpProxy(httpProxyEnv);
        final HttpHost httpHost = HttpHost.create(httpProxy.get(0));
        httpClientBuilder.setProxy(httpHost);
        if (httpProxy.getMembers().length == 3) {
            // set proxy credentials
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()), new UsernamePasswordCredentials(httpProxy.get(1), httpProxy.get(2)));
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }
    }
    return httpClientBuilder;
}


public Tuple resolveHttpProxy(String httpProxy){
    final URI proxyUri = URI.create(httpProxy);
    int port = proxyUri.getPort();
    if (port == -1) {
        if (Objects.equals("http", proxyUri.getScheme())) {
            port = 80;
        }
        if (Objects.equals("https", proxyUri.getScheme())) {
            port = 443;
        }
    }
    final String hostUrl = proxyUri.getScheme() + "://" + proxyUri.getHost() + ":" + port;
    final String usernamePassword = proxyUri.getUserInfo();
    if (StringUtils.isNotBlank(usernamePassword)) {
        final String username;
        final String password;
        final int atColon = usernamePassword.indexOf(':');
        if (atColon >= 0) {
            username = usernamePassword.substring(0, atColon);
            password = usernamePassword.substring(atColon + 1);
        } else {
            username = usernamePassword;
            password = null;
        }
        return new Tuple(hostUrl, username, password);
    } else {
        return new Tuple(hostUrl);
    }
}


public RequestConfig getRequestConfig(int timeout){
    return RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
}


}