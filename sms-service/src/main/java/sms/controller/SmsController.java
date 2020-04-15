package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sms.persistence.Recipient;
import sms.persistence.RecipientsRepository;
import sms.persistence.Sms;
import sms.persistence.SmsRepository;
//import sms.service.SchedulerService;
import sms.service.SmsService;

import java.time.*;
import java.util.List;
import java.util.Map;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;
//
//    @Autowired
//    SchedulerService schedulerService;

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
        int smsId = Integer.parseInt(id);
        return recipientsRepository.findBySmsId_SmsId(smsId);
    }

    @PostMapping("/sms")
    public Recipient createSms(@RequestBody Map<String, String> body) throws Exception{
        String name   = body.get("name");
        String phone  = body.get("phone");
        String deliveryTimeString = body.get("deliverytime");
        String message    = body.get("message");

        LocalTime deliveryTime = LocalTime.parse(deliveryTimeString);

        LocalTime utcTime = LocalDateTime.of(LocalDate.now(), deliveryTime)
                .atZone(ZoneId.of("Europe/Dublin"))
                .withZoneSameInstant(ZoneOffset.UTC)
                .toLocalTime();

        Sms sms = new Sms(message,utcTime);

        smsRepository.save(sms);

        Recipient recipient = new Recipient(name, phone);

        recipient.setSmsId(sms);

        recipientsRepository.save(recipient);

        smsService.schedule(sms.getSmsId(),phone, message, name, utcTime);

        return recipient;

    }

    @DeleteMapping("/sms")
    public void cancelSms(@RequestParam(value="smsId") String id) throws Exception{
        int smsId = Integer.parseInt(id);
        smsService.cancel(smsId);
    }

    @PostMapping("/sms/now")
    public void sendSmsImmediate(@RequestParam(value="smsId")  String id) throws Exception{
        int smsId = Integer.parseInt(id);

        List<Recipient> recipients = recipientsRepository.findBySmsId_SmsId(smsId);

        Sms sms = smsRepository.findById(smsId).get();

        for(Recipient recipient: recipients) {

            smsService.sendMessage(recipient.getPhone(), sms.getMessage(), recipient.getName());
        }
        smsService.cancel(smsId);
    }
}
