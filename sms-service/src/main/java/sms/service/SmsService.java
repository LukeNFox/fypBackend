package sms.service;

import com.twilio.http.TwilioRestClient;
import org.springframework.stereotype.Component;
import sms.Twilio.Credentials;
import sms.Twilio.TwilioMessageCreator;

@Component
public class SmsService {

    public String baseMessage = "Hello, this is a test?";

    private Credentials credentials;
    private TwilioMessageCreator messageCreator;

    public SmsService() {
        this.credentials = new Credentials();
        this.messageCreator = new TwilioMessageCreator(
                new TwilioRestClient.Builder(credentials.getAccountSid(), credentials.getAuthToken()).build()
        );
    }

    public SmsService(TwilioMessageCreator messageCreator, Credentials credentials) {
        this.credentials = credentials;
        this.messageCreator = messageCreator;
    }

    public void sendMessage(String to, String message) {
        messageCreator.create(to, credentials.getPhoneNumber(), message);
    }
}
