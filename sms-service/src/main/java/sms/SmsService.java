package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SmsService {

    @Autowired
    SmsRepository smsrepository;
}
