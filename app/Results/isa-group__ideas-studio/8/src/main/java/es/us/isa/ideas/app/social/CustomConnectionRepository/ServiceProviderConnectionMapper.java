package es.us.isa.ideas.app.social.CustomConnectionRepository;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.NoSuchConnectionException;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.jpa.JpaTemplate;
import org.springframework.social.connect.jpa.RemoteUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
public class ServiceProviderConnectionMapper {


public Long expireTime(Long expireTime){
    return expireTime == null || expireTime == 0 ? null : expireTime;
}


public ConnectionData mapConnectionData(RemoteUser socialUser){
    return new ConnectionData(socialUser.getProviderId(), socialUser.getProviderUserId(), socialUser.getDisplayName(), socialUser.getProfileUrl(), socialUser.getImageUrl(), decrypt(socialUser.getAccessToken()), decrypt(socialUser.getSecret()), decrypt(socialUser.getRefreshToken()), expireTime(socialUser.getExpireTime()));
}


public String decrypt(String encryptedText){
    return encryptedText != null ? getTextEncryptor().decrypt(encryptedText) : encryptedText;
}


public Connection<?> mapEntity(RemoteUser socialUser){
    ConnectionData connectionData = mapConnectionData(socialUser);
    ConnectionFactory<?> connectionFactory = getConnectionFactoryLocator().getConnectionFactory(connectionData.getProviderId());
    return connectionFactory.createConnection(connectionData);
}


public List<Connection<?>> mapEntities(List<RemoteUser> socialUsers){
    List<Connection<?>> result = new ArrayList<Connection<?>>();
    for (RemoteUser su : socialUsers) {
        result.add(mapEntity(su));
    }
    return result;
}


}