package demo;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

import demo.address.AddressRepository;
import demo.catalog.CatalogRepository;
import demo.inventory.InventoryRepository;
import demo.product.ProductRepository;
import demo.shipment.ShipmentRepository;
import demo.warehouse.WarehouseRepository;

@Configuration

@ComponentScan(basePackages = "demo")
//@EnableNeo4jRepositories(basePackages = "demo")
//@EntityScan("demo")
public class GraphConfiguration { //extends Neo4jConfiguration {

    @Autowired
    private Neo4jProperties properties;

    private Session session = null;

    
    // @Bean
    // public Neo4jServer neo4jServer() {
    //     String uri = this.properties.getUri();
    //     String pw = this.properties.getPassword();
    //     String user = this.properties.getUsername();
    //     if (StringUtils.hasText(pw) && StringUtils.hasText(user)) {
    //         return new RemoteServer(uri, user, pw);
    //     }
    //     return new RemoteServer(uri);
    // }
    

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        String uri = this.properties.getUri();
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
            .driverConfiguration()
            .setDriverClassName
                ("org.neo4j.ogm.drivers.http.driver.HttpDriver")
            .setURI(uri);
        return config;
        // return new org.neo4j.ogm.config.Configuration.Builder()
        //     .uri(uri)  //.uri("bolt://localhost")
        //     .build();
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // we need to specify which packages Neo4j should scan
        // we'll use classes in each package to avoid brittleness
        Class<?>[] packageClasses = {
                ProductRepository.class,
                ShipmentRepository.class,
                WarehouseRepository.class,
                AddressRepository.class,
                CatalogRepository.class,
                InventoryRepository.class
        };
        String[] packageNames =
                Arrays.asList(packageClasses)
                        .stream()
                        .map( c -> getClass().getPackage().getName())
                        .collect(Collectors.toList())
                        .toArray(new String[packageClasses.length]);
        return new SessionFactory(configuration(), packageNames);
    }


    
    //@Bean
    public Session getSession() throws Exception {
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        //return super.getSession();
        return session;
    }
    
}
