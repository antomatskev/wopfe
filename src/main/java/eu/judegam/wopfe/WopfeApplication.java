package eu.judegam.wopfe;


import eu.judegam.wopfe.models.repositories.school.tests.repository.TestsRepository;
import eu.judegam.wopfe.models.school.tests.Test;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class WopfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WopfeApplication.class, args);
    }
}