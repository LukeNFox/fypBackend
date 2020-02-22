package sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SmsService {

    private final static Logger LOGGER = Logger.getLogger(SmsService.class.getName());
    private final static String ACCOUNT_SID =  "AC71411f14c9d3b4f5bcefa6be6bff421a";
    private final static String AUTH_ID = "1a7d1bb5a0c74e4cbbe9f0e1404c4d16";
    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }
    private String phoneNumber="+12055129871";

    public SmsService() {

    }

    public void sendMessage(String to, String message, String name) {

        String textMessage = "Hi " + name + ". This is a message from scuba Sos! " + message;

        try {
            Message.creator(new PhoneNumber(to), new PhoneNumber(phoneNumber), textMessage).create();
        }catch(Exception e){
            LOGGER.info("Error sending the sms Message"+e);
        }
    }
}
