import org.apache.commons.lang3.StringUtils.isBlank;
import org.apache.commons.lang3.StringUtils.isNotBlank;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AppUtils {

 private  String SYSPROP_HOST_NAME;

 private  String SYSPROP_PORT_NUMBER;

 private  String SYSPROP_IP_ADDRESS;

 private  String UNKNOWN;

 private  Logger log;


public String getHostName(){
    String hostName = System.getProperty(SYSPROP_HOST_NAME, UNKNOWN);
    if (UNKNOWN.equals(hostName)) {
        try {
            hostName = System.getenv("HOSTNAME");
            if (isBlank(hostName)) {
                hostName = System.getenv("COMPUTERNAME");
            }
            if (isBlank(hostName)) {
                try {
                    hostName = IOUtils.toString(Runtime.getRuntime().exec("hostname").getInputStream());
                } catch (Exception ex) {
                }
            }
            if (isBlank(hostName)) {
                hostName = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress()).getHostName();
            }
            if (isNotBlank(hostName)) {
                hostName = hostName.trim();
            }
        } catch (Exception ex) {
            log.info("Unable to lookup hostname: " + ex.getMessage());
        }
    }
    return hostName;
}


public String getPortNumber(){
    String portNumber = System.getProperty(SYSPROP_PORT_NUMBER, System.getProperty("http.port", System.getProperty("https.port", UNKNOWN)));
    if (UNKNOWN.equals(portNumber)) {
        try {
            portNumber = "31415";
        } catch (Exception ex) {
            log.warn("", ex);
        }
    }
    return portNumber;
}


public String getIpAddress(){
    String ipAddress = System.getProperty(SYSPROP_IP_ADDRESS, UNKNOWN);
    if (UNKNOWN.equals(ipAddress)) {
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            log.warn("", ex);
        } finally {
        }
    }
    if (UNKNOWN.equals(ipAddress)) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("", ex);
            ipAddress = "127.0.0.1";
        }
    }
    return ipAddress;
}


}