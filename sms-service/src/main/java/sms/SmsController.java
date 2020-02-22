package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SmsController {

    @Autowired
    SmsService service;


}
