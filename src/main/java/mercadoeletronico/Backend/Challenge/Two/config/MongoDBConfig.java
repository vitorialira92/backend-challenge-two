package mercadoeletronico.Backend.Challenge.Two.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "mercadoeletronico.Backend.Challenge.Two.repositories")
public class MongoDBConfig {
}
