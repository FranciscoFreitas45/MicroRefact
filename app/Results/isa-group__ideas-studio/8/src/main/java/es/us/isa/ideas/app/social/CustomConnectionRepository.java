package es.us.isa.ideas.app.social;
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
@Repository
@Transactional
public class CustomConnectionRepository implements ConnectionRepository{

 private  JpaTemplate jpaTemplate;

 private  String userId;

 private  ConnectionFactoryLocator connectionFactoryLocator;

 private  TextEncryptor textEncryptor;

 private  ServiceProviderConnectionMapper connectionMapper;


public ConnectionFactoryLocator getConnectionFactoryLocator(){
    return connectionFactoryLocator;
}


public void updateConnection(Connection<?> connection){
    ConnectionData data = connection.createData();
    RemoteUser su = getJpaTemplate().get(getUserId(), data.getProviderId(), data.getProviderUserId());
    if (su != null) {
        su.setDisplayName(data.getDisplayName());
        su.setProfileUrl(data.getProfileUrl());
        su.setImageUrl(data.getImageUrl());
        su.setAccessToken(encrypt(data.getAccessToken()));
        su.setSecret(encrypt(data.getSecret()));
        su.setRefreshToken(encrypt(data.getRefreshToken()));
        su.setExpireTime(data.getExpireTime());
        su = getJpaTemplate().save(su);
    }
}


public void removeConnections(String providerId){
    getJpaTemplate().remove(getUserId(), providerId);
}


public MultiValueMap<String,Connection<?>> findConnectionsToUsers(MultiValueMap<String,String> providerUsers){
    if (providerUsers.isEmpty()) {
        throw new IllegalArgumentException("Unable to execute find: no providerUsers provided");
    }
    List<Connection<?>> resultList = getConnectionMapper().mapEntities(getJpaTemplate().getAll(getUserId(), providerUsers));
    MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<String, Connection<?>>();
    for (Connection<?> connection : resultList) {
        String providerId = connection.getKey().getProviderId();
        List<String> userIds = providerUsers.get(providerId);
        List<Connection<?>> connections = connectionsForUsers.get(providerId);
        if (connections == null) {
            connections = new ArrayList<Connection<?>>(userIds.size());
            for (int i = 0; i < userIds.size(); i++) {
                connections.add(null);
            }
            connectionsForUsers.put(providerId, connections);
        }
        String providerUserId = connection.getKey().getProviderUserId();
        int connectionIndex = userIds.indexOf(providerUserId);
        connections.set(connectionIndex, connection);
    }
    return connectionsForUsers;
}


public List<Connection<?>> mapEntities(List<RemoteUser> socialUsers){
    List<Connection<?>> result = new ArrayList<Connection<?>>();
    for (RemoteUser su : socialUsers) {
        result.add(mapEntity(su));
    }
    return result;
}


@SuppressWarnings("unchecked")
public Connection<A> getConnection(Class<A> apiType,String providerUserId){
    String providerId = getProviderId(apiType);
    return (Connection<A>) getConnection(new ConnectionKey(providerId, providerUserId));
}


public void setTextEncryptor(TextEncryptor textEncryptor){
    this.textEncryptor = textEncryptor;
}


public JpaTemplate getJpaTemplate(){
    return jpaTemplate;
}


@SuppressWarnings("unchecked")
public List<Connection<A>> findConnections(Class<A> apiType){
    List<?> connections = findConnections(getProviderId(apiType));
    return (List<Connection<A>>) connections;
}


public String encrypt(String text){
    return text != null ? getTextEncryptor().encrypt(text) : text;
}


public TextEncryptor getTextEncryptor(){
    return textEncryptor;
}


public String getProviderId(Class<A> apiType){
    return getConnectionFactoryLocator().getConnectionFactory(apiType).getProviderId();
}


public void setConnectionMapper(ServiceProviderConnectionMapper connectionMapper){
    this.connectionMapper = connectionMapper;
}


@SuppressWarnings("unchecked")
public Connection<A> getPrimaryConnection(Class<A> apiType){
    String providerId = getProviderId(apiType);
    Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
    if (connection == null) {
        throw new NotConnectedException(providerId);
    }
    return connection;
}


public void setConnectionFactoryLocator(ConnectionFactoryLocator connectionFactoryLocator){
    this.connectionFactoryLocator = connectionFactoryLocator;
}


public MultiValueMap<String,Connection<?>> findAllConnections(String customUserId){
    List<Connection<?>> resultList = getConnectionMapper().mapEntities(getJpaTemplate().getAll(customUserId));
    MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
    Set<String> registeredProviderIds = getConnectionFactoryLocator().registeredProviderIds();
    for (String registeredProviderId : registeredProviderIds) {
        connections.put(registeredProviderId, Collections.<Connection<?>>emptyList());
    }
    for (Connection<?> connection : resultList) {
        String providerId = connection.getKey().getProviderId();
        if (connections.get(providerId).size() == 0) {
            connections.put(providerId, new LinkedList<Connection<?>>());
        }
        connections.add(providerId, connection);
    }
    return connections;
}


public void setJpaTemplate(JpaTemplate jpaTemplate){
    this.jpaTemplate = jpaTemplate;
}


public ConnectionData mapConnectionData(RemoteUser socialUser){
    return new ConnectionData(socialUser.getProviderId(), socialUser.getProviderUserId(), socialUser.getDisplayName(), socialUser.getProfileUrl(), socialUser.getImageUrl(), decrypt(socialUser.getAccessToken()), decrypt(socialUser.getSecret()), decrypt(socialUser.getRefreshToken()), expireTime(socialUser.getExpireTime()));
}


@Transactional
public void addConnection(Connection<?> connection){
    try {
        ConnectionData data = connection.createData();
        int rank = getJpaTemplate().getRank(getUserId(), data.getProviderId());
        getJpaTemplate().createRemoteUser(getUserId(), data.getProviderId(), data.getProviderUserId(), rank, data.getDisplayName(), data.getProfileUrl(), data.getImageUrl(), encrypt(data.getAccessToken()), encrypt(data.getSecret()), encrypt(data.getRefreshToken()), data.getExpireTime());
    } catch (DuplicateKeyException e) {
        throw new DuplicateConnectionException(connection.getKey());
    }
}


public Connection<?> findPrimaryConnection(String providerId){
    List<Connection<?>> connections = getConnectionMapper().mapEntities(getJpaTemplate().getPrimary(getUserId(), providerId));
    if (connections.size() > 0) {
        return connections.get(0);
    } else {
        return null;
    }
}


public ServiceProviderConnectionMapper getConnectionMapper(){
    return connectionMapper;
}


public Connection<?> mapEntity(RemoteUser socialUser){
    ConnectionData connectionData = mapConnectionData(socialUser);
    ConnectionFactory<?> connectionFactory = getConnectionFactoryLocator().getConnectionFactory(connectionData.getProviderId());
    return connectionFactory.createConnection(connectionData);
}


public Long expireTime(Long expireTime){
    return expireTime == null || expireTime == 0 ? null : expireTime;
}


public void removeConnection(ConnectionKey connectionKey){
    getJpaTemplate().remove(getUserId(), connectionKey.getProviderId(), connectionKey.getProviderUserId());
}


public String decrypt(String encryptedText){
    return encryptedText != null ? getTextEncryptor().decrypt(encryptedText) : encryptedText;
}


public String getUserId(){
    return userId;
}


public void setUserId(String value){
    userId = value;
}


}