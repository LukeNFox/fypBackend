package sms.job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
    }
}
