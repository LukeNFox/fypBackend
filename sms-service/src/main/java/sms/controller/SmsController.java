package sms.controller;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sms.controller.dto.ScheduleRequest;
import sms.controller.dto.ScheduleResponse;
import sms.persistence.Recipient;
import sms.persistence.RecipientsRepository;
import sms.persistence.Sms;
import sms.persistence.SmsRepository;
import sms.service.SchedulerService;
import sms.service.SmsService;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    SmsRepository smsRepository;

    @Autowired
    RecipientsRepository recipientsRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() throws Exception {
        throw new Exception("Impossible just happened");
    }

    @GetMapping("/sms/all")
    public List<Sms> getAllSms(){
        return smsRepository.findAll();
    }

    @GetMapping("/recipients/all")
    public List<Recipient> getAllRecipients(){
        return recipientsRepository.findAll();
    }

    @GetMapping("/sms")
    public Sms getSms(@RequestParam(value="smsId") String id) {
        int smsId = Integer.parseInt(id);
        return smsRepository.findById(smsId).get();
    }

    @GetMapping("/recipients")
    public List<Recipient> getRecipients(@RequestParam(value="smsId")  String id) {
        int diveId = Integer.parseInt(id);
        return recipientsRepository.findBySmsId_SmsId(diveId);
    }

    @PostMapping("/sms")
    public Recipient createSms(@RequestBody Map<String, String> body) throws Exception{
        String name   = body.get("name");
        String phone  = body.get("phone");
        String deliveryTimeString = body.get("deliverytime");
        String message    = body.get("message");

        LocalTime deliveryTime = LocalTime.parse(deliveryTimeString);

        Sms sms = new Sms(message,deliveryTime);

        smsRepository.save(sms);

        Recipient recipient = new Recipient(name, phone);

        recipient.setSmsId(sms);

        recipientsRepository.save(recipient);

        smsService.sendMessage(phone, message, name);

        return recipient;

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/schedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ScheduleResponse schedule(@RequestBody ScheduleRequest details) throws Exception {

        Class<?> jobClass = Class.forName(details.getJobClass());
        schedulerService.scheduleJob((Class<? extends Job>) jobClass, details.getJobName(), details.getJobGroup(),
                details.getCronExpression());
        return new ScheduleResponse("Successfully scheduled " + details.getJobName());
    }

}
