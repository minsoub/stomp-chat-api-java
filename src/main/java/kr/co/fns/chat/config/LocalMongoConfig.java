package kr.co.fns.chat.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import kr.co.fns.chat.config.properties.MongoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("local|dev")
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "kr.co.fns.chat.api.chat.repository")
public class LocalMongoConfig {
    private final MongoProperties properties;


    @Bean
    public MongoClient mongoClient() {

        ConnectionString connectionString = getConnectionString();
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }


    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), properties.getDatabase());
    }
    private ConnectionString getConnectionString() {
        String str = String.format("mongodb://%s:%s@%s:%s/%s?authSource=admin&retryWrites=false",
                properties.getUsername(),
                properties.getPassword(),
                properties.getHost(),
                properties.getPort(),
                properties.getDatabase()
        );

        return new ConnectionString(str);
    }
}
