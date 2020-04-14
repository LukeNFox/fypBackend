package sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import sms.job.SmsJob;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class SmsService {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

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
        String textMessage = "Hi " + name +
                ". This is a message from scuba Sos. This text has been sent to you" +
                " because a diver is in danger and you have been selected as an emergency contact." +
                " Please attempt to contact the divers immediately! If unsuccessful contact the emergency services." +
                message;

        try {
            Message.creator(new PhoneNumber(to), new PhoneNumber(phoneNumber), textMessage).create();
        }catch(Exception e){
            LOGGER.info("Error sending the sms Message"+e);
        }
    }


    public void schedule(int tempSmsId, String phone, String message, String name, LocalTime deliveryTime) throws Exception {

        Scheduler scheduler = schedulerFactory.getScheduler();

        String smsId = String.valueOf(tempSmsId);

        JobDetail myJob = newJob(SmsJob.class)
                .withIdentity(smsId, "sms") // name "myJob", group "group1"
                .usingJobData("message", message)
                .usingJobData("name", name)
                .usingJobData("phone", phone)
                .build();

        LocalDate date = LocalDate.now();

        Instant instant = deliveryTime.atDate(LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())).
                atZone(ZoneId.systemDefault()).toInstant();
        Date newTime = Date.from(instant);


        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .startAt(newTime)
                .forJob(smsId, "sms")
                .build();

        scheduler.scheduleJob(myJob,trigger);

    }

    public void cancel(int tempSmsId) throws Exception {

        String smsId = String.valueOf(tempSmsId);

        Scheduler scheduler = schedulerFactory.getScheduler();

        JobKey key = new JobKey(smsId,"sms");

        scheduler.deleteJob(key);

    }
}
