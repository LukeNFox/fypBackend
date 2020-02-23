package sms.job;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import sms.service.SmsService;
import sms.util.QuartzJob;

@QuartzJob
@DisallowConcurrentExecution
public class SmsJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(SmsJob.class);

    @Autowired
    SmsService smsService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info(smsService.sayHello("Luke"));

        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String name = dataMap.getString("name");
        String phone = dataMap.getString("phone");
        String message = dataMap.getString("message");
        smsService.sendMessage(phone,message,name);

    }
}
