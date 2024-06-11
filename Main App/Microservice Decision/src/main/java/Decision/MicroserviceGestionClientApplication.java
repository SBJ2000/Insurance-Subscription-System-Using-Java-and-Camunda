package Decision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"Decision.*"})
@EnableJpaRepositories(basePackages = {"Decision.repository"})
@EntityScan(basePackages = {"Decision.model"})
public class MicroserviceGestionClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGestionClientApplication.class, args);
    }

}
