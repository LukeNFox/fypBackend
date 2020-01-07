package dive;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class DiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveApplication.class, args);
    }
}