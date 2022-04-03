package utilities.internal;
 import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.spi.PersistenceProvider;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.dialect.Dialect;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.jdbc.Work;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utilities.DatabaseConfig;
import domain.DomainEntity;
public class DatabaseUtil {

 private  PersistenceProvider persistenceProvider;

 private  EntityManagerFactory entityManagerFactory;

 private  EntityManager entityManager;

 private  Map<String,Object> properties;

 private  String databaseUrl;

 private  String databaseName;

 private  String databaseDialectName;

 private  Dialect databaseDialect;

 private  Configuration configuration;

 private  EntityTransaction entityTransaction;

// Constructor ------------------------------------------------------------
public DatabaseUtil() {
}
public void setReadUncommittedIsolationLevel(){
    List<String> script;
    script = new ArrayList<String>();
    script.add("set transaction isolation level read uncommitted;");
    this.executeScript(script);
}


public void setReadCommittedIsolationLevel(){
    List<String> script;
    script = new ArrayList<String>();
    script.add("set transaction isolation level read committed;");
    this.executeScript(script);
}


public void startTransaction(){
    this.entityTransaction.begin();
}


public void recreateDatabase(){
    List<String> databaseScript;
    List<String> schemaScript;
    String[] statements;
    databaseScript = new ArrayList<String>();
    databaseScript.add(String.format("drop database if exists `%s`;", this.databaseName));
    databaseScript.add(String.format("create database `%s`;", this.databaseName));
    this.executeScript(databaseScript);
    schemaScript = new ArrayList<String>();
    schemaScript.add(String.format("use `%s`;", this.databaseName));
    statements = this.configuration.generateSchemaCreationScript(this.databaseDialect);
    for (final String statement : statements) schemaScript.add(String.format("%s;", statement));
    this.executeScript(schemaScript);
}


public void executeScript(List<String> script){
    Session session;
    session = this.entityManager.unwrap(Session.class);
    session.doWork(new Work() {

        @Override
        public void execute(final Connection connection) throws SQLException {
            Statement statement;
            // System.out.println();
            statement = connection.createStatement();
            for (final String line : script) // System.out.println(line);
            statement.execute(line);
            connection.commit();
        // System.out.println();
        }
    });
}


public void rollbackTransaction(){
    this.entityTransaction.rollback();
}


public String getDatabaseName(){
    return this.databaseName;
}


public String findProperty(String property){
    String result;
    Object value;
    value = this.properties.get(property);
    if (value == null)
        throw new RuntimeException(String.format("Property `%s' was not found", property));
    if (!(value instanceof String))
        throw new RuntimeException(String.format("Property `%s' is not a string", property));
    result = (String) value;
    if (StringUtils.isBlank(result))
        throw new RuntimeException(String.format("Property `%s' is blank", property));
    return result;
}


@Override
public void execute(Connection connection){
    Statement statement;
    // System.out.println();
    statement = connection.createStatement();
    for (final String line : script) // System.out.println(line);
    statement.execute(line);
    connection.commit();
// System.out.println();
}


public List<?> executeSelect(String line){
    List<?> result;
    Query query;
    this.entityManager.clear();
    query = this.entityManager.createQuery(line);
    result = query.getResultList();
    return result;
}


public void flush(){
    this.entityManager.flush();
}


public String getDatabaseDialectName(){
    return this.databaseDialectName;
}


public void commitTransaction(){
    this.entityTransaction.commit();
}


public void printProperties(Map<String,Object> properties){
    for (final Entry<String, Object> entry : properties.entrySet()) System.out.println(String.format("%s=`%s'", entry.getKey(), entry.getValue()));
}


public void persist(DomainEntity entity){
    this.entityManager.persist(entity);
}


public void initialise(){
    // this.resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();
    // this.providers = this.resolver.getPersistenceProviders();
    this.persistenceProvider = new HibernatePersistenceProvider();
    this.entityManagerFactory = this.persistenceProvider.createEntityManagerFactory(DatabaseConfig.PersistenceUnit, null);
    if (this.entityManagerFactory == null)
        throw new RuntimeException(String.format("Couldn't create an entity manager factory for persistence unit `%s'", DatabaseConfig.PersistenceUnit));
    this.entityManager = this.entityManagerFactory.createEntityManager();
    if (this.entityManager == null)
        throw new RuntimeException(String.format("Couldn't create an entity manager for persistence unit `%s'", DatabaseConfig.PersistenceUnit));
    this.entityManager.setFlushMode(FlushModeType.AUTO);
    this.properties = this.entityManagerFactory.getProperties();
    // printProperties(properties);
    this.databaseUrl = this.findProperty("javax.persistence.jdbc.url");
    this.databaseName = StringUtils.substringAfterLast(this.databaseUrl, "/");
    this.databaseDialectName = this.findProperty("hibernate.dialect");
    this.databaseDialect = (Dialect) ReflectHelper.classForName(this.databaseDialectName).newInstance();
    this.configuration = this.buildConfiguration();
    this.entityTransaction = this.entityManager.getTransaction();
}


public Configuration buildConfiguration(){
    Configuration result;
    final ApplicationContext context;
    final Properties properties;
    String namingStrategyClassName;
    Class<?> namingStrategyClass;
    NamingStrategy namingStrategy;
    Metamodel metamodel;
    Collection<EntityType<?>> entities;
    Collection<EmbeddableType<?>> embeddables;
    result = new Configuration();
    context = new ClassPathXmlApplicationContext("classpath:spring/datasource.xml");
    properties = (Properties) context.getBean("jpaProperties");
    namingStrategyClassName = properties.getProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    try {
        namingStrategyClass = Class.forName(namingStrategyClassName);
        namingStrategy = (NamingStrategy) namingStrategyClass.newInstance();
    } catch (final Throwable oops) {
        throw new RuntimeException(oops);
    }
    result.setNamingStrategy(namingStrategy);
    metamodel = this.entityManagerFactory.getMetamodel();
    entities = metamodel.getEntities();
    for (final EntityType<?> entity : entities) result.addAnnotatedClass(entity.getJavaType());
    embeddables = metamodel.getEmbeddables();
    for (final EmbeddableType<?> embeddable : embeddables) result.addAnnotatedClass(embeddable.getJavaType());
    return result;
}


public void shutdown(){
    if (this.entityTransaction != null && this.entityTransaction.isActive())
        this.entityTransaction.rollback();
    if (this.entityManager != null && this.entityManager.isOpen())
        this.entityManager.close();
    if (this.entityManagerFactory != null && this.entityManagerFactory.isOpen())
        this.entityManagerFactory.close();
}


public int executeUpdate(String line){
    int result;
    Query query;
    this.entityManager.clear();
    query = this.entityManager.createQuery(line);
    result = query.executeUpdate();
    return result;
}


}