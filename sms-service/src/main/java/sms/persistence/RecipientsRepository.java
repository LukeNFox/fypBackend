package sms.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientsRepository extends JpaRepository<Recipient, Integer> {
    List<Recipient> findBySmsId_SmsId(Integer smsId);
}
