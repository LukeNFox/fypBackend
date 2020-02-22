package sms;

import com.twilio.Twilio;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class SMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SMSApplication.class, args);
    }
}
