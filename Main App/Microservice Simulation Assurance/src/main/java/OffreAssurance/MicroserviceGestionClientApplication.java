package OffreAssurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"OffreAssurance.*"})
@EnableJpaRepositories(basePackages = {"OffreAssurance.repository"})
@EntityScan(basePackages = {"OffreAssurance.model"})
public class MicroserviceGestionClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGestionClientApplication.class, args);
    }

}
