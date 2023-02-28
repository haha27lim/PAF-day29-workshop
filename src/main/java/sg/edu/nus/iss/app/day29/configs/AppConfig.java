package sg.edu.nus.iss.app.day29.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static sg.edu.nus.iss.app.day29.Constants.*;

@Configuration
public class AppConfig {
    
    // Inject the mongo connection string
    @Value("${mongo.url}")
    private String mongoUrl;

    // Create and initialize MongoTemplate
    @Bean
    public MongoTemplate createMongoTemplate() {
        // Create a MongoClient with the mongo connection string
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, ORDERS);
    }
}
